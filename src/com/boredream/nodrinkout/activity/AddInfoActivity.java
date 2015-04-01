package com.boredream.nodrinkout.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.boredream.nodrinkout.BaseActivity;
import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.utils.TitleBuilder;
import com.boredream.nodrinkout.view.WrapHeightGridView;

public class AddInfoActivity extends BaseActivity implements OnClickListener {
	private LinearLayout ll_checkin;
	private TextView tv_checkin;
	private CheckBox cb_checkin;
	private EditText et_info;
	private WrapHeightGridView gv_info_images;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_add_info);
		
		initView();
	}
	
	private void initView() {
		new TitleBuilder(this).setTitleText("发咖讯")
				.setLeftText("取消")
				.setLeftOnClickListener(this)
				.setRightText("发布")
				.setRightOnClickListener(this)
				.build();

		ll_checkin = (LinearLayout) findViewById(R.id.ll_checkin);
		tv_checkin = (TextView) findViewById(R.id.tv_checkin);
		cb_checkin = (CheckBox) findViewById(R.id.cb_checkin);
		et_info = (EditText) findViewById(R.id.et_info);
		gv_info_images = (WrapHeightGridView) findViewById(R.id.gv_info_images);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.titlebar_tv_left:
			
			break;
		case R.id.titlebar_tv_right:
			
			break;
		}
	}

}
