package com.boredream.nodrinkout.utils;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.boredream.nodrinkout.R;

public class ViewUtils {

	public static View initTitle(final Activity activity, String title) {
		TextView title_tv = (TextView) activity.findViewById(R.id.titlebar_tv);
		title_tv.setText(title);
		return activity.findViewById(R.id.rl_titlebar);
	}
	
	public static View initTitleBack(final Activity activity, String title, OnClickListener onClickListener) {
		View leftView = activity.findViewById(R.id.titlebar_iv_left);
		leftView.setVisibility(View.VISIBLE);
		leftView.setOnClickListener(onClickListener);
		TextView title_tv = (TextView) activity.findViewById(R.id.titlebar_tv);
		title_tv.setText(title);
		return activity.findViewById(R.id.rl_titlebar);
	}
	
}
