package com.boredream.nodrinkout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.boredream.baas.BDAbsObjHelper;
import com.boredream.baas.BDBaaS;
import com.boredream.baas.BDObjable;
import com.boredream.nodrinkout.utils.CommonConstants;

public abstract class BaseActivity extends Activity {

	protected String TAG;

	protected ProgressDialog progressDialog;
	protected BaseApplication application;
	protected Bundle bundle;
	protected SharedPreferences sp;
	protected Uri pickImageUri;
	
	protected BDAbsObjHelper<? extends BDObjable> objHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TAG = this.getClass().getSimpleName();
		showLog("onCreate()", 2);
		application = (BaseApplication) getApplication();
		if (getIntent() != null) {
			bundle = getIntent().getExtras();
		}
		sp = getSharedPreferences(CommonConstants.SP_NAME, MODE_PRIVATE);
		progressDialog = new ProgressDialog(this);
		application.addActivity(this);
		
		objHelper = BDBaaS.getObjHelper(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
		showLog("onStart()", 2);
	}

	@Override
	protected void onResume() {
		super.onResume();

		showLog("onResume()", 2);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		application.removeActivity(this);
		
		showLog("onDestroy()", 2);
	}

	@Override
	protected void onStop() {
		super.onStop();

		showLog("onStop()", 2);
	}

	@Override
	protected void onPause() {
		super.onPause();
		showLog("onPause()", 2);
	}

	protected void finishActivity() {
		this.finish();
	}

	protected void showToast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	protected void showLog(String log) {
		showLog(log, 1);
	}

	/**
	 * œ‘ ælog
	 * 
	 * @param log
	 * @param level
	 *            1-info; 2-debug; 3-verbose
	 */
	protected void showLog(String log, int level) {
		switch (level) {
		case 1:
			Log.i(TAG, log);
			break;
		case 2:
			Log.d(TAG, log);
			break;
		case 3:
			Log.v(TAG, log);
			break;
		default:
			Log.i(TAG, log);
			break;
		}
	}

}
