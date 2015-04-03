package com.boredream.nodrinkout.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.boredream.nodrinkout.activity.MainTabActivity;
import com.boredream.nodrinkout.entity.UserBean;
import com.boredream.nodrinkout.utils.DialogUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

public class BaseFragment extends Fragment {
	
	protected MainTabActivity activity;
	protected Dialog loadDialog;
	
	protected ImageLoader imageLoader;
	protected UserBean user;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		activity = (MainTabActivity) getActivity();
		loadDialog = DialogUtils.createLoadingDialog(activity);
		
		imageLoader = ImageLoader.getInstance();
		user = UserBean.getCurrentUser(activity, UserBean.class);
	}
	
	protected void intent2Activity(Class<? extends Activity> tarActivity) {
		Intent intent = new Intent(activity, tarActivity);
		startActivity(intent);
	}
}
