package com.boredream.nodrinkout.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.activity.UserActivity;
import com.boredream.nodrinkout.entity.UserBean;
import com.boredream.nodrinkout.utils.ImageOptionsHelper;

public class UserFragment extends BaseFragment implements OnClickListener {

	private View view;

	private LinearLayout ll_userinfo;
	private ImageView iv_avatar;
	private TextView tv_name;
	private TextView tv_intro;
	private LinearLayout ll_about_us;
	private LinearLayout ll_check_update;

	private void initView() {
		ll_userinfo = (LinearLayout) view.findViewById(R.id.ll_userinfo);
		iv_avatar = (ImageView) view.findViewById(R.id.iv_avatar);
		tv_name = (TextView) view.findViewById(R.id.tv_name);
		tv_intro = (TextView) view.findViewById(R.id.tv_intro);
		ll_about_us = (LinearLayout) view.findViewById(R.id.ll_about_us);
		ll_check_update = (LinearLayout) view.findViewById(R.id.ll_check_update);

		ll_userinfo.setOnClickListener(this);
		ll_about_us.setOnClickListener(this);
		ll_check_update.setOnClickListener(this);
	}
	
	private UserBean user;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(activity, R.layout.frag_user, null);

		initView();
		setData();

		return view;
	}

	
	private void setData() {
		user = getCurrentUser();
		imageLoader.displayImage(user.getAvatarUrl(), iv_avatar, 
				ImageOptionsHelper.getAvatarOptions());
		tv_name.setText(user.getUsername());
		tv_intro.setText(user.getDetail());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_userinfo:
			Intent intent = new Intent(activity, UserActivity.class);
			startActivity(intent);
			break;
		case R.id.ll_about_us:

			break;
		case R.id.ll_check_update:

			break;
		}
	}
}
