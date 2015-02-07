package com.boredream.nodrinkout.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.boredream.nodrinkout.BaseFragment;
import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.home.personalcentre.UserCenter;

public class UserFragment extends BaseFragment{
	private LinearLayout mLayout;
	private LinearLayout mLayout2;
	private LinearLayout mLayout3;
	private ImageView touxiang;
	
	private void initView(View view) {
		touxiang =(ImageView) view.findViewById(R.id.touxiang);
		mLayout=(LinearLayout) view.findViewById(R.id.l1);
		mLayout2=(LinearLayout) view.findViewById(R.id.l2);
		mLayout3=(LinearLayout) view.findViewById(R.id.l3);
		mLayout.setOnClickListener(new OnClickListener(
				) {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),UserCenter.class);
				startActivity(intent);
			}
		});
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.main_user, container, false);
		initView(view);
		return view;
		
	}

}
