package com.boredream.nodrinkout.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.activity.SearchActivity;

public class SearchFragment extends BaseFragment {
	private EditText et_search;
	private LinearLayout lay_discount, lay_newshop, lay_meng, lay_daka,
			lay_zhoubian;
	private ImageView img_tuijian;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = View.inflate(getActivity(), R.layout.activity_find, null);
		et_search = (EditText) view.findViewById(R.id.ed_search);
		lay_discount = (LinearLayout) view.findViewById(R.id.lay_discount);
		lay_newshop = (LinearLayout) view.findViewById(R.id.lay_newshop);
		lay_meng = (LinearLayout) view.findViewById(R.id.lay_xiaomenghelp);
		lay_daka = (LinearLayout) view.findViewById(R.id.lay_daka);
		lay_zhoubian = (LinearLayout) view.findViewById(R.id.lay_zhoubian);
		setListener();
		LoadData();
		return view;
	}

	private void LoadData() {

	}

	private void setListener() {
		et_search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), SearchActivity.class);
				startActivity(intent);
			}
		});
	}
}
