package com.boredream.nodrinkout.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;


public class ImageUtils {
	public static final int GET_IMAGE_BY_CAMERA = 5001;
	public static final int GET_IMAGE_FROM_PHONE = 5002;
	public static final int CROP_IMAGE = 5003;
	public static Uri imageUriFromCamera;
	public static Uri cropImageUri;

	public static void openCameraImage(final Activity activity) {
		ImageUtils.imageUriFromCamera = ImageUtils.createImagePathUri(activity);
		
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// MediaStore.EXTRA_OUTPUT����������ʱ,ϵͳ���Զ�����һ��uri,����ֻ�᷵��һ������ͼ
		// ����ͼƬ��onActivityResult��ͨ�����´����ȡ
		// Bitmap bitmap = (Bitmap) data.getExtras().get("data"); 
		intent.putExtra(MediaStore.EXTRA_OUTPUT, ImageUtils.imageUriFromCamera);
		activity.startActivityForResult(intent, ImageUtils.GET_IMAGE_BY_CAMERA);
	}
	
	public static void openLocalImage(final Activity activity) {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		activity.startActivityForResult(intent, ImageUtils.GET_IMAGE_FROM_PHONE);
	}
	
	public static void cropImage(Activity activity, Uri srcUri) {
		ImageUtils.cropImageUri = ImageUtils.createImagePathUri(activity);
		
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(srcUri, "image/*");
		intent.putExtra("crop", "true");
		
		////////////////////////////////////////////////////////////////
		// 1.��ߺͱ�����������ʱ,�ü���������е���(�����ʹ�С�������������)
		////////////////////////////////////////////////////////////////
		// 2.ֻ���òü����߱�(aspect)��,�ü�������̶����ɵ���,ֻ�ܵ�����С
		////////////////////////////////////////////////////////////////
		// 3.�ü�������ͼƬ���(output)�����úͲü����޹�,ֻ������������ͼƬ��С
		////////////////////////////////////////////////////////////////
		// 4.�ü����߱���(aspect)���ԺͲü�������ͼƬ����(output)��ͬ,��ʱ,
		//	���Բü���Ŀ�Ϊ׼,���ղü���߱�������һ��ͼƬ,��ͼ�Ϳ�ѡ���ֿ��ܲ�ͬ,
		//  ��ͬ����������ǽ�ȡ��ѡ��һ����,Ҳ���ܳ�����ѡ����,�������첹��
		////////////////////////////////////////////////////////////////
		
		// aspectX aspectY �ǲü����ߵı���
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY �ǲü�������ͼƬ�Ŀ��
//		intent.putExtra("outputX", 300);
//		intent.putExtra("outputY", 100);
		
		// return-dataΪtrueʱ,��ֱ�ӷ���bitmap����,���Ǵ�ͼ�ü�ʱ���������,�Ƽ�����Ϊfalseʱ�ķ�ʽ
		// return-dataΪfalseʱ,���᷵��bitmap,����Ҫָ��һ��MediaStore.EXTRA_OUTPUT����ͼƬuri
		intent.putExtra(MediaStore.EXTRA_OUTPUT, ImageUtils.cropImageUri);
		intent.putExtra("return-data", false);
		
		activity.startActivityForResult(intent, CROP_IMAGE);
	}
	
	/**
	 * ����һ��ͼƬ��ַuri,���ڱ������պ����Ƭ
	 * 
	 * @param context
	 * @return ͼƬ��uri
	 */
	private static Uri createImagePathUri(Context context) {
		Uri imageFilePath = null;
		String status = Environment.getExternalStorageState();
		SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA);
		long time = System.currentTimeMillis();
		String imageName = timeFormatter.format(new Date(time));
		// ContentValues������ϣ��������¼������ʱ������������Ϣ
		ContentValues values = new ContentValues(3);
		values.put(MediaStore.Images.Media.DISPLAY_NAME, imageName);
		values.put(MediaStore.Images.Media.DATE_TAKEN, time);
		values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
		if (status.equals(Environment.MEDIA_MOUNTED)) {// �ж��Ƿ���SD��,����ʹ��SD���洢,��û��SD��ʱʹ���ֻ��洢
			imageFilePath = context.getContentResolver().insert(
					MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
		} else {
			imageFilePath = context.getContentResolver().insert(
					MediaStore.Images.Media.INTERNAL_CONTENT_URI, values);
		}
		Log.i("", "���ɵ���Ƭ���·����" + imageFilePath.toString());
		return imageFilePath;
	}
	
	/**
	 * ����Uri��ȡͼƬ����·�������Android4.4���ϰ汾Uriת��
	 * @param context
	 * @param imageUri
	 */
	@TargetApi(Build.VERSION_CODES.KITKAT)
	public static String getImageAbsolutePath(Activity context, Uri imageUri) {
		if (context == null || imageUri == null)
			return null;
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, imageUri)) {
			if (isExternalStorageDocument(imageUri)) {
				String docId = DocumentsContract.getDocumentId(imageUri);
				String[] split = docId.split(":");
				String type = split[0];
				if ("primary".equalsIgnoreCase(type)) {
					return Environment.getExternalStorageDirectory() + "/" + split[1];
				}
			} else if (isDownloadsDocument(imageUri)) {
				String id = DocumentsContract.getDocumentId(imageUri);
				Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
				return getDataColumn(context, contentUri, null, null);
			} else if (isMediaDocument(imageUri)) {
				String docId = DocumentsContract.getDocumentId(imageUri);
				String[] split = docId.split(":");
				String type = split[0];
				Uri contentUri = null;
				if ("image".equals(type)) {
					contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				} else if ("video".equals(type)) {
					contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
				} else if ("audio".equals(type)) {
					contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
				}
				String selection = MediaStore.Images.Media._ID + "=?";
				String[] selectionArgs = new String[] { split[1] };
				return getDataColumn(context, contentUri, selection, selectionArgs);
			}
		} // MediaStore (and general)
		else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
			// Return the remote address
			if (isGooglePhotosUri(imageUri))
				return imageUri.getLastPathSegment();
			return getDataColumn(context, imageUri, null, null);
		}
		// File
		else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
			return imageUri.getPath();
		}
		return null;
	}

	private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
		Cursor cursor = null;
		String column = MediaStore.Images.Media.DATA;
		String[] projection = { column };
		try {
			cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
			if (cursor != null && cursor.moveToFirst()) {
				int index = cursor.getColumnIndexOrThrow(column);
				return cursor.getString(index);
			}
		} finally {
			if (cursor != null)
				cursor.close();
		}
		return null;
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is ExternalStorageProvider.
	 */
	private static boolean isExternalStorageDocument(Uri uri) {
		return "com.android.externalstorage.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is DownloadsProvider.
	 */
	private static boolean isDownloadsDocument(Uri uri) {
		return "com.android.providers.downloads.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is MediaProvider.
	 */
	private static boolean isMediaDocument(Uri uri) {
		return "com.android.providers.media.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is Google Photos.
	 */
	private static boolean isGooglePhotosUri(Uri uri) {
		return "com.google.android.apps.photos.content".equals(uri.getAuthority());
	}
}
