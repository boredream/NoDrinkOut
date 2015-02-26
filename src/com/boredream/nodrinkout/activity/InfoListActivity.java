package com.boredream.nodrinkout.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.boredream.nodrinkout.BaseActivity;
import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.adapter.InfoAdapter;
import com.boredream.nodrinkout.bmob.BmobApi;
import com.boredream.nodrinkout.bmob.FindSimpleListener;
import com.boredream.nodrinkout.entity.InfoBean;
import com.boredream.nodrinkout.utils.ViewUtils;
import com.boredream.nodrinkout.view.Pull2RefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;

public class InfoListActivity extends BaseActivity {
	
	private Pull2RefreshListView lv_info;
	private InfoAdapter adapter;
	private List<InfoBean> infos; 
	
	private int currentPage = 1;
	
	/**
	 * 资讯类型 1-bigger 2-knowledge
	 */
	private int cateId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_infolist);

		initView();

		cateId = intent.getIntExtra("cateId", 1);
		
		loadData();
	}
	
	private void initView() {
		ViewUtils.initTitle(this, "Bigger");
		lv_info = (Pull2RefreshListView) findViewById(R.id.lv_info);		
		lv_info.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(InfoListActivity.this, DetailActivity.class);
				intent.putExtra("info", adapter.getItem(position-1));
				startActivity(intent);
			}
		});
		lv_info.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				currentPage = 1;
				loadData();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				currentPage ++;
				loadData();
			}
		});
		
		infos = new ArrayList<InfoBean>();
	}

	private void loadData() {
		BmobApi.queryInfoByCategory(this, cateId, currentPage, 10, new FindSimpleListener<InfoBean>(this, null) {
			@Override
			public void onSuccess(List<InfoBean> arg0) {
				if(currentPage == 1) {
					infos = arg0;
				} else {
					infos.addAll(arg0);
				}
				adapter = new InfoAdapter(InfoListActivity.this, infos);
				lv_info.setAdapter(adapter);
				
				lv_info.onRefreshComplete();
			}
			
			@Override
			public void onError(int arg0, String arg1) {
				super.onError(arg0, arg1);
				
				lv_info.onRefreshComplete();
			}
		});
	}
}
