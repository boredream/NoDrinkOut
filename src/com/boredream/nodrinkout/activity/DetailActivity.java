package com.boredream.nodrinkout.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import cn.bmob.v3.requestmanager.ApiResult;

import com.boredream.nodrinkout.BaseActivity;
import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.bmob.BmobApi;
import com.boredream.nodrinkout.bmob.SaveSimpleListener;
import com.boredream.nodrinkout.entity.InfoBean;
import com.boredream.nodrinkout.utils.ViewUtils;

public class DetailActivity extends BaseActivity implements OnClickListener {
	private ImageView title_iv_left;
	private TextView title_tv;
	
	private ImageView iv_avatar;
	private TextView tv_title;
	private TextView tv_name;
	private TextView tv_time;
	private TextView tv_like;
	
	private ImageView iv_content;
	private TextView tv_content;
	
	private TextView tv_comment;
	
	private InfoBean info;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		initView();
		
		info = (InfoBean) getIntent().getSerializableExtra("info");
		
		setData();
	}
	
	private void initView() {
		ViewUtils.initTitle(this, "Bigger");
		
		iv_avatar = (ImageView) findViewById(R.id.iv_avatar);
		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_name = (TextView) findViewById(R.id.tv_name);
		tv_time = (TextView) findViewById(R.id.tv_time);
		tv_like = (TextView) findViewById(R.id.tv_like);
		iv_content = (ImageView) findViewById(R.id.iv_content);
		tv_content = (TextView) findViewById(R.id.tv_content);
		tv_comment = (TextView) findViewById(R.id.tv_comment);
		tv_comment.setOnClickListener(this);
	}
	
	private void setData() {
		tv_title.setText(info.getTitle());
		tv_content.setText(info.getContent());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_comment:
			insertComment("comment~");
			break;

		default:
			break;
		}
	}

	private void insertComment(String comment) {
		BmobApi.insertCommentOfInfo(this, info, comment, 
				new SaveSimpleListener(DetailActivity.this, progressDialog){

					@Override
					public void onSuccess() {
						super.onSuccess();
						
						showToast("ÆÀÂÛ³É¹¦");
					}
			
		});
	}

}

