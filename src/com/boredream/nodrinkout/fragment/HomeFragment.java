package com.boredream.nodrinkout.fragment;

import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.adapter.InfoAdapter;
import com.boredream.nodrinkout.bmob.BmobApi;
import com.boredream.nodrinkout.entity.CoffeeInfo;
import com.boredream.nodrinkout.listener.FindSimpleListener;
import com.boredream.nodrinkout.listener.SimpleOnItemClickListener;
import com.boredream.nodrinkout.utils.TitleBuilder;
import com.boredream.nodrinkout.view.Pull2RefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

public class HomeFragment extends BaseFragment {

	private View view;
	
	private Pull2RefreshListView plv_home;
	private InfoAdapter infoAdapter;
	
	private int curPage = 1;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(getActivity(), R.layout.frag_home, null);
		
		initView();
		
		loadData();
		
		return view;
	}

	private void initView() {
		new TitleBuilder(view).setTitleText("Ê×Ò³").build();
		
		plv_home = (Pull2RefreshListView) view.findViewById(R.id.plv_home);
		plv_home.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				loadData();
			}
		});
		plv_home.setOnItemClickListener(new SimpleOnItemClickListener(activity));
	}
	
	private void loadData() {
		BmobApi.queryInfosWhere(activity, null, null, null, curPage, 
				new FindSimpleListener<CoffeeInfo>(activity, loadDialog){

					@Override
					public void onSuccess(List<CoffeeInfo> arg0) {
						super.onSuccess(arg0);
						
						infoAdapter = new InfoAdapter(activity, arg0);
						plv_home.setAdapter(infoAdapter);
						
						plv_home.onRefreshComplete();
					}
		});
	}
}
