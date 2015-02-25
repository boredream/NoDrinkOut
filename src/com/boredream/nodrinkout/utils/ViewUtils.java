package com.boredream.nodrinkout.utils;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.boredream.nodrinkout.R;

public class ViewUtils {

	public static void initTitle(final Activity activity, String title) {
		activity.findViewById(R.id.title_iv_left).setOnClickListener(
				new OnClickListener() {
			@Override
			public void onClick(View v) {
				activity.finish();
			}
		});
		TextView title_tv = (TextView) activity.findViewById(R.id.title_tv);
		title_tv.setText(title);
	}
	
}
