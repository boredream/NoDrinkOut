package com.boredream.nodrinkout.activity;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import cn.bmob.v3.listener.FindListener;

import com.boredream.nodrinkout.BaseActivity;
import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.adapter.InfoAdapter;
import com.boredream.nodrinkout.bmob.BmobApi;
import com.boredream.nodrinkout.entity.InfoBean;
import com.boredream.nodrinkout.utils.ViewUtils;

public class InfoListActivity extends BaseActivity {
	
	private ListView lv_bigger;
	private InfoAdapter adapter;
	
	/**
	 * 资讯类型 1-bigger 2-knowledge
	 */
	private int cateId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bigger);

		ViewUtils.initTitle(this, "Bigger");
		
		lv_bigger = (ListView) findViewById(R.id.lv_bigger);		
		lv_bigger.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(InfoListActivity.this, DetailActivity.class);
				intent.putExtra("info", adapter.getItem(position));
				startActivity(intent);
			}
		});
		

		cateId = intent.getIntExtra("cateId", 1);
		
		progressDialog.show();
		BmobApi.queryInfoByCategory(this, cateId, new FindListener<InfoBean>() {
			@Override
			public void onSuccess(List<InfoBean> arg0) {
				progressDialog.dismiss();
				adapter = new InfoAdapter(InfoListActivity.this, arg0);
				lv_bigger.setAdapter(adapter);
			}
			
			@Override
			public void onError(int arg0, String arg1) {
				progressDialog.dismiss();
				showToast(arg1);
			}
		});
	}
}
