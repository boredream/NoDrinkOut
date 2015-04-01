package com.boredream.nodrinkout.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.navisdk.util.logic.MapUtil;
import com.boredream.nodrinkout.BaseActivity;
import com.boredream.nodrinkout.BaseApplication;
import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.entity.CoffeeInfo;
import com.boredream.nodrinkout.entity.CoffeeShop;
import com.boredream.nodrinkout.listener.SaveSimpleListener;
import com.boredream.nodrinkout.utils.CommonConstants;
import com.boredream.nodrinkout.utils.MapUtils;
import com.boredream.nodrinkout.utils.TitleBuilder;
import com.boredream.nodrinkout.view.WrapHeightGridView;

public class AddInfoActivity extends BaseActivity implements OnClickListener {
	private LinearLayout ll_checkin;
	private TextView tv_checkin;
	private CheckBox cb_checkin;
	private EditText et_info;
	private WrapHeightGridView gv_info_images;
	
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
		gv_info_images = (WrapHeightGridView) findViewById(R.id.gv_info_images);
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
		double shopLatitude = Double.parseDouble(shop.getLocation().split("-")[0]);
		double shopLongitude = Double.parseDouble(shop.getLocation().split("-")[1]);
		
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
		
		progressDialog.show();
		info.save(this, new SaveSimpleListener(this, progressDialog){

			@Override
			public void onSuccess() {
				super.onSuccess();
				
				
			}
			
		});
	}
	
	private void uploadImage() {
		
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

}
