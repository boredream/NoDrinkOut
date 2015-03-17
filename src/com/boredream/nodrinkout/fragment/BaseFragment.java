package com.boredream.nodrinkout.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.boredream.nodrinkout.activity.MainTabActivity;
import com.boredream.nodrinkout.utils.DialogUtils;

public class BaseFragment extends Fragment {
	
	protected MainTabActivity activity;
	protected Dialog loadDialog;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		activity = (MainTabActivity) getActivity();
		loadDialog = DialogUtils.createLoadingDialog(activity);
	}

}
