package com.boredream.nodrinkout.home.personalcentre;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.boredream.nodrinkout.R;

public class UserCenter extends Activity {
	private ImageView back;
	private LinearLayout line1;
	private LinearLayout line2;
	private LinearLayout line3;
	private LinearLayout line4;
	private LinearLayout line5;
	private SelectPicPopupWindow menuWindow;
	private ImageView touxiang;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personal_data);
		initView();
	}

	private void initView() {
		back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		line1 = (LinearLayout) findViewById(R.id.ziliao_line1);
		line1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ʵ����SelectPicPopupWindow
				menuWindow = new SelectPicPopupWindow(UserCenter.this,
						itemsOnClick);
				// ��ʾ����
				menuWindow.showAtLocation(
						UserCenter.this.findViewById(R.id.ziliao_line1),
						Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
				// ����layout��PopupWindow����ʾ��λ��
			}
		});

	}

	// Ϊ��������ʵ�ּ�����
	private OnClickListener itemsOnClick = new OnClickListener() {

		public void onClick(View v) {
			menuWindow.dismiss();
			switch (v.getId()) {
			case R.id.btn_take_photo:
				Intent intent = new Intent();
				intent.setAction("android.media.action.STILL_IMAGE_CAMERA");
				intent.addCategory("android.intent.category.DEFAULT");
				startActivityForResult(intent, ImageUtils.GET_IMAGE_BY_CAMERA);
				break;
			case R.id.btn_pick_photo:
				Intent intent2 = new Intent(Intent.ACTION_PICK, null);
				intent2.setDataAndType(
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
				startActivityForResult(intent2, ImageUtils.GET_IMAGE_FROM_PHONE);
				break;
			default:
				break;
			}

		}

	};

	/**
	 * �ü�ͼƬ����ʵ��
	 * 
	 * @param uri
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_CANCELED) {
			return;
		}

		switch (requestCode) {
		// ���ջ�ȡͼƬ
		case ImageUtils.GET_IMAGE_BY_CAMERA:
			// uri�������Ӱ��ͼƬ��ȡ��ʽ,���¶�ѡһ
			// ��ʽһ,�Զ���Uri(ImageUtils.imageUriFromCamera),���ڱ������պ�ͼƬ��ַ
			if (ImageUtils.imageUriFromCamera != null) {
				// ����ֱ����ʾͼƬ,���߽�����������(��ѹ����ü���)
				// iv.setImageURI(ImageUtils.imageUriFromCamera);

				// ��ͼƬ���вü�
				ImageUtils.cropImage(this, ImageUtils.imageUriFromCamera);
				break;
			}

			break;
		// �ֻ�����ȡͼƬ
		case ImageUtils.GET_IMAGE_FROM_PHONE:
			if (data != null && data.getData() != null) {
				// ����ֱ����ʾͼƬ,���߽�����������(��ѹ����ü���)
				// iv.setImageURI(data.getData());

				// ��ͼƬ���вü�
				ImageUtils.cropImage(this, data.getData());
			}
			break;
		// �ü�ͼƬ����
		case ImageUtils.CROP_IMAGE:
			if (ImageUtils.cropImageUri != null) {
				// ����ֱ����ʾͼƬ,���߽�����������(��ѹ����)
				touxiang.setImageURI(ImageUtils.cropImageUri);
			}
			break;
		default:
			break;
		}
	}
}
