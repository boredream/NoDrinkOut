package com.boredream.nodrinkout.fragment;

import com.boredream.nodrinkout.BaseFragment;
import com.boredream.nodrinkout.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BuyFragment extends BaseFragment{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_buy, container, false);
		return view;
	}

}
