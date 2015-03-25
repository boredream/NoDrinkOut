package com.boredream.nodrinkout.fragment;

import android.app.Dialog;
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
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		activity = (MainTabActivity) getActivity();
		loadDialog = DialogUtils.createLoadingDialog(activity);
		
		imageLoader = ImageLoader.getInstance();
	}
	
	protected UserBean getCurrentUser() {
		return UserBean.getCurrentUser(activity, UserBean.class);
	}

}
