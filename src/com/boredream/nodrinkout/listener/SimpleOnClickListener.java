package com.boredream.nodrinkout.listener;

import com.boredream.nodrinkout.R;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

public class SimpleOnClickListener implements OnClickListener {

	private Activity context;
	
	public SimpleOnClickListener(Activity context) {
		this.context = context;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.titlebar_iv_left:
			context.finish();
			break;

		default:
			break;
		}
	}

}
