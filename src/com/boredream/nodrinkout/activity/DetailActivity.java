package com.boredream.nodrinkout.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.boredream.nodrinkout.BaseActivity;
import com.boredream.nodrinkout.R;

public class DetailActivity extends BaseActivity {
	private ImageView detail_iv;
	private LinearLayout ll;
	private TextView detail_tv_view;
	private TextView detail_tv_love;
	private TextView detail_tv_comment;

	private void initView() {
		detail_iv = (ImageView) findViewById(R.id.detail_iv);
		ll = (LinearLayout) findViewById(R.id.ll);
		detail_tv_view = (TextView) findViewById(R.id.detail_tv_view);
		detail_tv_love = (TextView) findViewById(R.id.detail_tv_love);
		detail_tv_comment = (TextView) findViewById(R.id.detail_tv_comment);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		initView();
	}

}
