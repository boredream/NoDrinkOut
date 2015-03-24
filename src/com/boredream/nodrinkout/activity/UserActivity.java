package com.boredream.nodrinkout.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.boredream.nodrinkout.BaseActivity;
import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.adapter.InfoAdapter;
import com.boredream.nodrinkout.bmob.BmobApi;
import com.boredream.nodrinkout.bmob.FindSimpleListener;
import com.boredream.nodrinkout.entity.CoffeeInfo;
import com.boredream.nodrinkout.entity.UserBean;
import com.boredream.nodrinkout.utils.ImageOptionsHelper;
import com.boredream.nodrinkout.utils.ViewUtils;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class UserActivity extends BaseActivity {
	
	private View headView;
	private ImageView iv_userinfo_head;
	private ImageView iv_avatar;
	private TextView tv_name;
	private TextView tv_intro;
	
	private PullToRefreshListView plv_info;
	
	private List<CoffeeInfo> infos;
	private InfoAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_user);
		
		initView();
		setData();
		loadData();
	}

	private void initView() {
		View titlebar = ViewUtils.initTitle(this, "个人信息");
		titlebar.setBackgroundColor(getResources().getColor(R.color.transparent));
		
		headView = View.inflate(this, R.layout.include_user_head, null);
		iv_userinfo_head = (ImageView) headView.findViewById(R.id.iv_userinfo_head);
		iv_avatar = (ImageView) headView.findViewById(R.id.iv_avatar);
		tv_name = (TextView) headView.findViewById(R.id.tv_name);
		tv_intro = (TextView) headView.findViewById(R.id.tv_intro);
		
		plv_info = (PullToRefreshListView) findViewById(R.id.plv_info);
		
	}
	
	private void setData() {
		user = getCurrentUser();
		imageLoader.displayImage(user.getAvatarUrl(), iv_avatar, 
				ImageOptionsHelper.getAvatarOptions());
//		imageLoader.displayImage(user.getAvatarUrl(), iv_userinfo_head);
		tv_name.setText(user.getUsername());
		tv_intro.setText(user.getDetail());
		
		infos = new ArrayList<CoffeeInfo>();
		adapter = new InfoAdapter(this, infos);
		plv_info.setAdapter(adapter);
		plv_info.getRefreshableView().addHeaderView(headView);
	}
	
	private void loadData() {
		progressDialog.show();
		BmobApi.queryInfosWhere(this, null, UserBean.getCurrentUser(this, UserBean.class), null, 1, 
				new FindSimpleListener<CoffeeInfo>(this, progressDialog) {

					@Override
					public void onSuccess(List<CoffeeInfo> arg0) {
						super.onSuccess(arg0);
						
						infos.addAll(arg0);
						adapter.notifyDataSetChanged();
					}
		});
	}
	
}
