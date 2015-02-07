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
				// 实例化SelectPicPopupWindow
				menuWindow = new SelectPicPopupWindow(UserCenter.this,
						itemsOnClick);
				// 显示窗口
				menuWindow.showAtLocation(
						UserCenter.this.findViewById(R.id.ziliao_line1),
						Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
				// 设置layout在PopupWindow中显示的位置
			}
		});

	}

	// 为弹出窗口实现监听类
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
	 * 裁剪图片方法实现
	 * 
	 * @param uri
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_CANCELED) {
			return;
		}

		switch (requestCode) {
		// 拍照获取图片
		case ImageUtils.GET_IMAGE_BY_CAMERA:
			// uri传入与否影响图片获取方式,以下二选一
			// 方式一,自定义Uri(ImageUtils.imageUriFromCamera),用于保存拍照后图片地址
			if (ImageUtils.imageUriFromCamera != null) {
				// 可以直接显示图片,或者进行其他处理(如压缩或裁剪等)
				// iv.setImageURI(ImageUtils.imageUriFromCamera);

				// 对图片进行裁剪
				ImageUtils.cropImage(this, ImageUtils.imageUriFromCamera);
				break;
			}

			break;
		// 手机相册获取图片
		case ImageUtils.GET_IMAGE_FROM_PHONE:
			if (data != null && data.getData() != null) {
				// 可以直接显示图片,或者进行其他处理(如压缩或裁剪等)
				// iv.setImageURI(data.getData());

				// 对图片进行裁剪
				ImageUtils.cropImage(this, data.getData());
			}
			break;
		// 裁剪图片后结果
		case ImageUtils.CROP_IMAGE:
			if (ImageUtils.cropImageUri != null) {
				// 可以直接显示图片,或者进行其他处理(如压缩等)
				touxiang.setImageURI(ImageUtils.cropImageUri);
			}
			break;
		default:
			break;
		}
	}
}
