package com.boredream.nodrinkout.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.TextView;

import com.boredream.nodrinkout.BaseActivity;
import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.adapter.ImageBrowserAdapter;
import com.boredream.nodrinkout.constants.CommonConstants;
import com.boredream.nodrinkout.entity.CoffeeInfo;

public class ImageBrowserActivity extends BaseActivity implements OnPageChangeListener{

	private ViewPager vp_imagebrowser;
	private TextView tv_imagebrowser;
	
	private ImageBrowserAdapter mAdapter;
	
	private CoffeeInfo info;
	private String[] imageUrls;
	private int currentPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_imagebrowser);
		
		initView();
		setData();
	}

	private void initView() {
		vp_imagebrowser = (ViewPager) findViewById(R.id.vp_imagebrowser);
		tv_imagebrowser = (TextView) findViewById(R.id.tv_imagebrowser);
		
		vp_imagebrowser.setOnPageChangeListener(this);
	}

	private void setData() {
		info = (CoffeeInfo) intent.getSerializableExtra("info");
		currentPosition = intent.getIntExtra("position", 0);
		
		imageUrls = info.getImgUrls().split(CommonConstants.DIVIDER_IMAGE_URLS);
		
		mAdapter = new ImageBrowserAdapter(imageUrls);
		vp_imagebrowser.setAdapter(mAdapter);
		
		tv_imagebrowser.setText((currentPosition + 1) + "/" + imageUrls.length);
		vp_imagebrowser.setCurrentItem(currentPosition);
	}

	@Override
	public void onBackPressed() {
		finish();
		overridePendingTransition(0, R.anim.zoom_exit);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}

	@Override
	public void onPageSelected(int arg0) {
		currentPosition = arg0;
		tv_imagebrowser.setText((arg0 % imageUrls.length + 1) + "/" + imageUrls.length);
	}

}
