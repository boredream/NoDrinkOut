package com.boredream.nodrinkout;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.boredream.nodrinkout.constants.CommonConstants;
import com.boredream.nodrinkout.entity.User;
import com.boredream.nodrinkout.listener.SimpleOnClickListener;
import com.boredream.nodrinkout.listener.SimpleOnItemClickListener;
import com.boredream.nodrinkout.utils.DialogUtils;
import com.boredream.nodrinkout.utils.Logger;
import com.nostra13.universalimageloader.core.ImageLoader;

public abstract class BaseActivity extends Activity {

	protected String TAG;

	protected BaseApplication application;
	protected SharedPreferences sp;
	protected Intent intent;
	protected ProgressDialog progressDialog;
	// custom data
	protected User user;
	protected ImageLoader imageLoader;
	protected SimpleOnClickListener simpleOnClickListener;
	protected SimpleOnItemClickListener simpleOnItemClickListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TAG = this.getClass().getSimpleName();
		showLog("onCreate()");
		
		application = (BaseApplication) getApplication();
		sp = getSharedPreferences(CommonConstants.SP_NAME, MODE_PRIVATE);
		intent = getIntent();
		progressDialog = DialogUtils.createLoadingDialog(this);
		application.addActivity(this);
		
		// custom data
		user = User.getCurrentUser(this, User.class);
		imageLoader = ImageLoader.getInstance();
		simpleOnClickListener = new SimpleOnClickListener(this);
		simpleOnItemClickListener = new SimpleOnItemClickListener(this);
	}
	
	// custom
	protected User getCurrentUser() {
		return User.getCurrentUser(this, User.class);
	}
	
	protected void intent2Activity(Class<? extends Activity> tarActivity) {
		Intent intent = new Intent(this, tarActivity);
		startActivity(intent);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		showLog("onStart()");
	}

	@Override
	protected void onResume() {
		super.onResume();
		showLog("onResume()");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		showLog("onDestroy()");
		
		application.removeActivity(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		showLog("onStop()");
	}

	@Override
	protected void onPause() {
		super.onPause();
		showLog("onPause()");
	}

	protected void finishActivity() {
		this.finish();
	}

	protected void showToast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	protected void showLog(String msg) {
		Logger.show(TAG, msg);
	}

}
