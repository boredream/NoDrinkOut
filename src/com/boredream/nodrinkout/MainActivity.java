package com.boredream.nodrinkout;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends BaseActivity {
	private TextView tv_title;
	private ViewPager vp_gallery;
	private TextView tv_bigger;
	private TextView tv_zishi;
	private TextView tv_huodong;
	private TextView tv_dian;
	private ListView lv_jingxuan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		initView();
		
	}

	private void initView() {
		tv_title = (TextView) findViewById(R.id.tv_title);
		vp_gallery = (ViewPager) findViewById(R.id.vp_gallery);
		tv_bigger = (TextView) findViewById(R.id.tv_bigger);
		tv_zishi = (TextView) findViewById(R.id.tv_zishi);
		tv_huodong = (TextView) findViewById(R.id.tv_huodong);
		tv_dian = (TextView) findViewById(R.id.tv_dian);
		lv_jingxuan = (ListView) findViewById(R.id.lv_jingxuan);
	}

}
