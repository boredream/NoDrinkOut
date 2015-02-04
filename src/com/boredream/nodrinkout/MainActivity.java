package com.boredream.nodrinkout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.boredream.nodrinkout.fragment.BuyFragment;
import com.boredream.nodrinkout.fragment.UserFragment;
import com.boredream.nodrinkout.home.HomeFragment;

public class MainActivity extends FragmentActivity implements
		OnCheckedChangeListener {

	/**
	 * 当前选项卡类型 1-home 2-buy 3-user
	 */
	private int current_type = 1;
	
	private final String TABTAG_HOME = "home";
	private final String TABTAG_BUY = "buy";
	private final String TABTAG_USER = "user";

	private FragmentManager fm;
	
	private HomeFragment homeFragment;
	private BuyFragment buyFragment;
	private UserFragment userFragment;
	public RadioGroup rg;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		
		initView();
		initFrag();
	}
	
	private void initFrag() {
		fm = getSupportFragmentManager();
		
		homeFragment = new HomeFragment();
		buyFragment = new BuyFragment();
		userFragment = new UserFragment();
		
		FragmentTransaction ft = fm.beginTransaction();
		ft.add(R.id.main_fl_content, homeFragment, TABTAG_HOME);
		ft.commit();
	}

	public void initView() {
		rg = (RadioGroup) findViewById(R.id.main_rg);
		rg.setOnCheckedChangeListener(this);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			current_type = bundle.getInt("type");
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		FragmentTransaction ft = fm.beginTransaction();
		fragReplace(checkedId, ft);
		ft.commit();
	}

	/**
	 * 利用FragmentTransaction的replace方法切换fragment
	 */
	private void fragReplace(int checkedId, FragmentTransaction ft) {
		switch (checkedId) {
		case R.id.main_rb_home:
			if(current_type != 1) {
				ft.replace(R.id.main_fl_content, homeFragment, TABTAG_HOME);
			}
			current_type = 1;
			break;
		case R.id.main_rb_catagory:
			if(current_type != 2) {
				ft.replace(R.id.main_fl_content, buyFragment, TABTAG_BUY);
			}
			current_type = 2;
			break;
		case R.id.main_rb_user:
			if(current_type != 3) {
				ft.replace(R.id.main_fl_content, userFragment, TABTAG_USER);
			}
			current_type = 3;
			break;
		default:
			break;
		}
	}

}
