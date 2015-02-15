package com.boredream.nodrinkout;

import java.util.List;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ListView;
import android.widget.TextView;
import cn.bmob.v3.BmobQuery;

import com.boredream.nodrinkout.http.FindSimpleListener;
import com.boredream.nodrinkout.info.InfoAdapter;
import com.boredream.nodrinkout.info.InfoBean;

public class MainActivity extends BaseActivity {
	private TextView tv_title;
	private ViewPager vp_gallery;
	private TextView tv_bigger;
	private TextView tv_zishi;
	private TextView tv_huodong;
	private TextView tv_dian;
	private ListView lv_jingxuan;

	private InfoAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		initView();

		progressDialog.show();
		BmobQuery<InfoBean> query = new BmobQuery<InfoBean>();
		query.addWhereEqualTo("cateId", "1");
		query.findObjects(this, new FindSimpleListener<InfoBean>(this,
				progressDialog) {

			@Override
			public void onSuccess(List<InfoBean> arg0) {
				super.onSuccess(arg0);
				
				adapter = new InfoAdapter(MainActivity.this, arg0);
				lv_jingxuan.setAdapter(adapter);
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
		
		tv_bigger.settext
	}

}
