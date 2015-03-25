package com.boredream.nodrinkout.utils;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.boredream.nodrinkout.R;

public class ViewUtils {

	public static View initTitle(final Activity activity, String title) {
		activity.findViewById(R.id.titlebar_iv_left).setOnClickListener(
				new OnClickListener() {
			@Override
			public void onClick(View v) {
				activity.finish();
			}
		});
		TextView title_tv = (TextView) activity.findViewById(R.id.titlebar_tv);
		title_tv.setText(title);
		return activity.findViewById(R.id.ll_titlebar);
	}
	
}
