package com.boredream.nodrinkout.fragment;

import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.adapter.ShopAdapter;
import com.boredream.nodrinkout.bmob.BmobApi;
import com.boredream.nodrinkout.entity.CoffeeShop;
import com.boredream.nodrinkout.listener.FindSimpleListener;
import com.boredream.nodrinkout.listener.SimpleOnItemListener;
import com.boredream.nodrinkout.view.Pull2RefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

public class ShopFragment extends BaseFragment {

	private Pull2RefreshListView plv_home;
	private ShopAdapter shopAdapter;
	
	private int curPage = 1;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = View.inflate(getActivity(), R.layout.frag_home, null);
		
		plv_home = (Pull2RefreshListView) view.findViewById(R.id.plv_home);
		plv_home.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				loadData();
			}
		});
		plv_home.setOnItemClickListener(new SimpleOnItemListener(activity));
		loadData();
		
		return view;
	}
	
	private void loadData() {
		BmobApi.queryShopsWhere(activity, null, curPage,
				new FindSimpleListener<CoffeeShop>(activity, loadDialog){

					@Override
					public void onSuccess(List<CoffeeShop> arg0) {
						super.onSuccess(arg0);
						
						shopAdapter = new ShopAdapter(activity, arg0);
						plv_home.setAdapter(shopAdapter);
						
						plv_home.onRefreshComplete();
					}
		});
	}
}
