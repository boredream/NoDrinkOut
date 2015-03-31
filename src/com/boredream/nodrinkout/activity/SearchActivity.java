package com.boredream.nodrinkout.activity;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.boredream.nodrinkout.BaseActivity;
import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.adapter.InfoAdapter;
import com.boredream.nodrinkout.adapter.ShopAdapter;
import com.boredream.nodrinkout.bmob.BmobApi;
import com.boredream.nodrinkout.entity.CoffeeInfo;
import com.boredream.nodrinkout.entity.CoffeeShop;
import com.boredream.nodrinkout.listener.FindSimpleListener;
import com.boredream.nodrinkout.listener.SimpleOnItemListener;
import com.boredream.nodrinkout.view.Pull2RefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

public class SearchActivity extends BaseActivity{
	private EditText et_search;
	private TextView tx_search;
	private Pull2RefreshListView plv_home;
	private ShopAdapter shopAdapter;
	private int curPage = 1;
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_search);
	et_search = (EditText) findViewById(R.id.ed_search);
	tx_search = (TextView) findViewById(R.id.tx_search);
	plv_home = (Pull2RefreshListView)findViewById(R.id.plv_home);
	setListener();
	
}



private void setListener() {
	tx_search.setOnClickListener(new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			String key = et_search.getText().toString();
			loadData(key);
		}
	});	
	plv_home.setOnItemClickListener(new SimpleOnItemListener(this));
	
}
private void loadData(String key) {
	BmobApi.queryShopsNameLike(this, key, new FindSimpleListener<CoffeeShop>(this, loadDialog){
		public void onSuccess(List<CoffeeShop> arg0) {
			super.onSuccess(arg0);
			shopAdapter = new ShopAdapter(SearchActivity.this, arg0);
			plv_home.setAdapter(shopAdapter);
			plv_home.onRefreshComplete();
		}
	});
}
}
