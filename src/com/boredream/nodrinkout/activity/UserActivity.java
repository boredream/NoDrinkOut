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
import com.boredream.nodrinkout.entity.CoffeeInfo;
import com.boredream.nodrinkout.entity.User;
import com.boredream.nodrinkout.listener.FindSimpleListener;
import com.boredream.nodrinkout.utils.ImageOptHelper;
import com.boredream.nodrinkout.utils.TitleBuilder;
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
	
	private User tarUser;
	private boolean iCurrentUser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_user);
		
		initView();
		setData();
		loadData();
	}

	private void initView() {
		new TitleBuilder(this).setTitleText("个人信息")
				.setLeftImage(R.drawable.ic_chevron_left)
				.setLeftOnClickListener(simpleOnClickListener).build();
		
		headView = View.inflate(this, R.layout.include_user_head, null);
		iv_userinfo_head = (ImageView) headView.findViewById(R.id.iv_userinfo_head);
		iv_avatar = (ImageView) headView.findViewById(R.id.iv_avatar);
		tv_name = (TextView) headView.findViewById(R.id.tv_name);
		tv_intro = (TextView) headView.findViewById(R.id.tv_intro);
		
		plv_info = (PullToRefreshListView) findViewById(R.id.plv_info);
		
	}
	
	private void setData() {
		tarUser = (User) intent.getSerializableExtra("user");
		iCurrentUser = tarUser.getObjectId().equals(user.getObjectId());
		
		imageLoader.displayImage(tarUser.getAvatarUrl(), iv_avatar, 
				ImageOptHelper.getAvatarOptions());
//		imageLoader.displayImage(user.getAvatarUrl(), iv_userinfo_head);
		tv_name.setText(tarUser.getUsername());
		tv_intro.setText(tarUser.getDetail());
		
		infos = new ArrayList<CoffeeInfo>();
		adapter = new InfoAdapter(this, infos);
		plv_info.setAdapter(adapter);
		plv_info.getRefreshableView().addHeaderView(headView);
	}
	
	private void loadData() {
		progressDialog.show();
		BmobApi.queryInfosWhere(this, null, User.getCurrentUser(this, User.class), null, 1, 
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
