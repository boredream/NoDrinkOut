package com.boredream.nodrinkout.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.boredream.nodrinkout.BaseActivity;
import com.boredream.nodrinkout.BaseApplication;
import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.adapter.AddImagesAdapter;
import com.boredream.nodrinkout.adapter.AddImagesAdapter.OnAddImgItemClickListener;
import com.boredream.nodrinkout.bmob.BmobApi;
import com.boredream.nodrinkout.constants.CommonConstants;
import com.boredream.nodrinkout.entity.CoffeeInfo;
import com.boredream.nodrinkout.entity.CoffeeShop;
import com.boredream.nodrinkout.listener.UpdateSimpleListener;
import com.boredream.nodrinkout.utils.DialogUtils;
import com.boredream.nodrinkout.utils.ImageUtils;
import com.boredream.nodrinkout.utils.MapUtils;
import com.boredream.nodrinkout.utils.TitleBuilder;
import com.boredream.nodrinkout.view.WrapHeightGridView;

public class WriteInfoActivity extends BaseActivity 
	implements OnClickListener, OnAddImgItemClickListener {
	private LinearLayout ll_checkin;
	private TextView tv_checkin;
	private CheckBox cb_checkin;
	private EditText et_info;
	private WrapHeightGridView gv_add_images;
	
	private AddImagesAdapter adapter;
	private List<Uri> imgUris;
	
	private CoffeeShop shop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_add_info);

		shop = (CoffeeShop) intent.getSerializableExtra("shop");
		
		initView();
		
		setData();
	}

	private void initView() {
		new TitleBuilder(this).setTitleText("发咖讯")
				.setLeftText("取消")
				.setLeftOnClickListener(this)
				.setRightText("发布")
				.setRightOnClickListener(this)
				.build();

		ll_checkin = (LinearLayout) findViewById(R.id.ll_checkin);
		tv_checkin = (TextView) findViewById(R.id.tv_checkin);
		cb_checkin = (CheckBox) findViewById(R.id.cb_checkin);
		et_info = (EditText) findViewById(R.id.et_info);
		gv_add_images = (WrapHeightGridView) findViewById(R.id.gv_add_images);
		
		imgUris = new ArrayList<Uri>();
		adapter = new AddImagesAdapter(this, imgUris);
		gv_add_images.setAdapter(adapter);
		adapter.setOnAddImgItemClickListener(this);
	}
	
	private void setData() {
		tv_checkin.setText(shop.getName());
		cb_checkin.setEnabled(isAtShop());
	}
	
	/**
	 * 如果距离小于1000米,则视为在店范围内
	 * @return
	 */
	private boolean isAtShop() {
		double shopLatitude = shop.getLocation().getLatitude();
		double shopLongitude = shop.getLocation().getLongitude();
		
		double distance = MapUtils.getDistance(shopLatitude, shopLongitude, 
				BaseApplication.curLocation.latitude, BaseApplication.curLocation.longitude);
		
		return Math.abs(distance) <= 1000;
	}
	
	private void addInfo() {
		String content = et_info.getText().toString().trim();
		
		CoffeeInfo info = new CoffeeInfo();
		info.setUser(user);
		info.setShop(shop);
		info.setChecked(cb_checkin.isChecked());
		info.setContent(content);
		info.setCommentCount(0);
		info.setLikeCount(0);
		info.setImgUrls(CommonConstants.DIVIDER_IMAGE_URLS);
		
		List<String> filePaths = new ArrayList<String>();
		for(Uri uri : imgUris) {
			filePaths.add(ImageUtils.getImageAbsolutePath(this, uri));
		}
		
		progressDialog.show();
		BmobApi.insertInfo(this, info, filePaths.toArray(new String[filePaths.size()]), 
				new UpdateSimpleListener(this, progressDialog){
					@Override
					public void onSuccess() {
						super.onSuccess();
						
						showToast("发表成功");
					}
		});
	}

	@Override
	public void onBodyClick(View v, int position) {
		if(position == adapter.getCount() - 1) {
			DialogUtils.showImagePickDialog(this);
		} else {
			
		}
	}

	@Override
	public void onRemoveBtnClick(View v, int position) {
		imgUris.remove(position);
		adapter.notifyDataSetChanged();
	}
	
	private void showIfNeedEditDialog(final Uri imageUri) {
		DialogUtils.showListDialog(this, "是否需要编辑图片?", new String[]{"编辑图片", "使用原图"}, 
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if(which == 0) {
							intent2editImage(imageUri);
						} else {
							imgUris.add(imageUri);
							adapter.notifyDataSetChanged();
						}
					}
				});
	}
	
	private void intent2editImage(Uri uri) {
		Intent intent = new Intent(this, ImageFilterActivity.class);
		intent.putExtra("path", ImageUtils.getImageAbsolutePath(this, uri));
		startActivity(intent);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.titlebar_tv_left:
			finish();
			break;
		case R.id.titlebar_tv_right:
			addInfo();
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_CANCELED) {
			return;
		}

		switch (requestCode) {
		// 拍照获取图片
		case ImageUtils.GET_IMAGE_BY_CAMERA:
			if(ImageUtils.imageUriFromCamera != null) {
				showIfNeedEditDialog(ImageUtils.imageUriFromCamera);
			}
			break;
		// 手机相册获取图片
		case ImageUtils.GET_IMAGE_FROM_PHONE:
			if(data != null && data.getData() != null) {
				showIfNeedEditDialog(data.getData());
			}
			break;
		// 裁剪图片后结果
//		case ImageUtils.CROP_IMAGE:
//			if(ImageUtils.cropImageUri != null) {
//				// 可以直接显示图片,或者进行其他处理(如压缩等)
//			}
//			break;
		}
	}
}
