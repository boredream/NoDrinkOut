package com.boredream.nodrinkout.home;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.boredream.nodrinkout.BaseFragment;
import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.detail.DetailActivity;

public class HomeFragment extends BaseFragment implements OnItemClickListener {
	private TextView home_tv_common;
	private TextView home_tv_turntable;
	private TextView home_tv_test;
	private GridView home_gv_new;
	private GridAdapter adapter;

	private void initView(View view) {
		home_tv_common = (TextView) view.findViewById(R.id.home_tv_common);
		home_tv_turntable = (TextView) view.findViewById(R.id.home_tv_turntable);
		home_tv_test = (TextView) view.findViewById(R.id.home_tv_test);
		home_gv_new = (GridView) view.findViewById(R.id.home_gv_new);
		
		adapter = new GridAdapter(getActivity(), new ArrayList<GridItem>());
		home_gv_new.setAdapter(adapter);
		home_gv_new.setOnItemClickListener(this);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_home, container, false);
		initView(view);
		return view;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		GridItem item = adapter.getItem(position);
		Intent intent = new Intent(getActivity(), DetailActivity.class);
		startActivity(intent);
	}

}
