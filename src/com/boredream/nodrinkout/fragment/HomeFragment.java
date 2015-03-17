package com.boredream.nodrinkout.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.adapter.InfoAdapter;
import com.boredream.nodrinkout.bmob.BmobApi;
import com.boredream.nodrinkout.bmob.FindSimpleListener;
import com.boredream.nodrinkout.entity.CoffeeInfo;
import com.boredream.nodrinkout.entity.InfoBean;
import com.boredream.nodrinkout.view.Pull2RefreshListView;

public class HomeFragment extends BaseFragment {

	private Pull2RefreshListView plv_home;
	
	private int curPage = 1;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = View.inflate(getActivity(), R.layout.frag_home, null);
		
		plv_home = (Pull2RefreshListView) view.findViewById(R.id.plv_home);
		List<InfoBean> beans = new ArrayList<InfoBean>();
		plv_home.setAdapter(new InfoAdapter(activity, beans));
		
		BmobApi.queryInfosWhere(activity, null, null, null, curPage, 
				new FindSimpleListener<CoffeeInfo>(activity, loadDialog){
			
		});
		
		return view;
	}
	
}
