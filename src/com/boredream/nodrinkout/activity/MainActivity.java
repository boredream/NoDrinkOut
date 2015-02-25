package com.boredream.nodrinkout.activity;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import cn.bmob.v3.BmobQuery;

import com.boredream.nodrinkout.BaseActivity;
import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.adapter.InfoAdapter;
import com.boredream.nodrinkout.adapter.VpAdapter;
import com.boredream.nodrinkout.entity.InfoBean;
import com.boredream.nodrinkout.http.FindSimpleListener;

public class MainActivity extends BaseActivity implements OnClickListener {
	private TextView tv_title;
	private ViewPager vp_gallery;
	private TextView tv_bigger;
	private TextView tv_zishi;
	private TextView tv_huodong;
	private TextView tv_dian;
	private ListView lv_jingxuan;

	private InfoAdapter infoAdapter;
	private VpAdapter vpAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		initView();

		progressDialog.show();
		BmobQuery<InfoBean> query = new BmobQuery<InfoBean>();
		query.addWhereEqualTo("cateId", 1);
		query.findObjects(this, new FindSimpleListener<InfoBean>(this,
				progressDialog) {

			@Override
			public void onSuccess(List<InfoBean> arg0) {
				super.onSuccess(arg0);
				
				infoAdapter = new InfoAdapter(MainActivity.this, arg0);
				lv_jingxuan.setAdapter(infoAdapter);
				
				vpAdapter = new VpAdapter(MainActivity.this, arg0);
				vp_gallery.setAdapter(vpAdapter);
			}

		});

	}

	private void initView() {
		tv_title = (TextView) findViewById(R.id.tv_title);
		vp_gallery = (ViewPager) findViewById(R.id.vp_gallery);
		tv_bigger = (TextView) findViewById(R.id.tv_bigger);
		tv_zishi = (TextView) findViewById(R.id.tv_zishi);
		tv_huodong = (TextView) findViewById(R.id.tv_huodong);
		tv_dian = (TextView) findViewById(R.id.tv_dian);
		lv_jingxuan = (ListView) findViewById(R.id.lv_jingxuan);
		
		tv_bigger.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_bigger:
			Intent intent = new Intent(this, BiggerActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

}
