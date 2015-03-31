package com.boredream.nodrinkout.activity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.baidu.mapapi.SDKInitializer;
import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.fragment.FragmentController;
import com.boredream.nodrinkout.receiver.BMapSDKReceiver;

public class MainTabActivity extends FragmentActivity implements
		OnCheckedChangeListener {
	
	private RadioGroup rg_tab;
	private RadioButton rb_home;
	private RadioButton rb_shop;
	private RadioButton rb_search;
	private RadioButton rb_user;
	
	private FragmentController fc;
	
	private BMapSDKReceiver mReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main_tab);
		
		initView();
		
		initData();
		
		// ×¢²á SDK ¹ã²¥¼àÌýÕß
		IntentFilter iFilter = new IntentFilter();
		iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
		iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
		mReceiver = new BMapSDKReceiver();
		registerReceiver(mReceiver, iFilter);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mReceiver);
	}
	
	private void initView() {
		rg_tab = (RadioGroup) findViewById(R.id.rg_tab);
		rb_home = (RadioButton) findViewById(R.id.rb_home);
		rb_shop = (RadioButton) findViewById(R.id.rb_shop);
		rb_search = (RadioButton) findViewById(R.id.rb_search);
		rb_user = (RadioButton) findViewById(R.id.rb_user);
		
		rg_tab.setOnCheckedChangeListener(this);
	}

	private void initData() {
		fc = new FragmentController(this, R.id.fl_content);
		rb_home.setChecked(true);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.rb_home:
			fc.showHomeFragment();
			break;
		case R.id.rb_shop:
			fc.showShopFragment();
			break;
		case R.id.rb_search:
			fc.showSearchFragment();
			break;
		case R.id.rb_user:
			fc.showUserFragment();
			break;

		default:
			break;
		}
	}

}
