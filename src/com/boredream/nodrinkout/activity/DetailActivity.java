package com.boredream.nodrinkout.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.boredream.nodrinkout.BaseActivity;
import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.bmob.BmobApi;
import com.boredream.nodrinkout.bmob.UpdateSimpleListener;
import com.boredream.nodrinkout.entity.InfoBean;
import com.boredream.nodrinkout.utils.ViewUtils;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

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
	
	private EditText et_comment;
	private Button btn_comment_send;
	
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
		et_comment = (EditText) findViewById(R.id.et_comment);
		btn_comment_send = (Button) findViewById(R.id.btn_comment_send);
		
		tv_like.setOnClickListener(this);
		btn_comment_send.setOnClickListener(this);
	}
	
	private void setData() {
		imageLoader.displayImage(user.getAvatarUrl(), iv_avatar);
		tv_title.setText(info.getTitle());
		tv_name.setText(user.getUsername());
		tv_time.setText(info.getCreatedAt());
		tv_like.setText(String.valueOf(info.getLikesCount()));
		imageLoader.displayImage(info.getImgCompleteUrl(), iv_content, new ImageLoadingListener() {
			@Override
			public void onLoadingStarted(String imageUri, View view) {
				
			}
			
			@Override
			public void onLoadingFailed(String imageUri, View view,
					FailReason failReason) {
				
			}
			
			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				float scale = (float) view.getWidth() / (float) loadedImage.getWidth();  
		        int defaultHeight = Math.round(loadedImage.getHeight() * scale);  
		        LayoutParams params = iv_content.getLayoutParams();  
		        params.height = defaultHeight;  
		        iv_content.setLayoutParams(params);  
			}
			
			@Override
			public void onLoadingCancelled(String imageUri, View view) {
				
			}
		});
		tv_content.setText(info.getContent());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_comment_send:
			String comment = et_comment.getText().toString().trim();
			if(TextUtils.isEmpty(comment)) {
				showToast("评论内容不能为空");
				return;
			}
			insertComment(comment);
			break;
		case R.id.tv_like:
			insertLike();
			break;

		default:
			break;
		}
	}

	private void insertComment(String comment) {
		BmobApi.insertComment(this, info, comment, 
				new UpdateSimpleListener(DetailActivity.this, progressDialog){

					@Override
					public void onSuccess() {
						super.onSuccess();
						showToast("评论成功");
					}
			
		});
	}
	
	private void insertLike() {
		BmobApi.insertLike(this, info,
				new UpdateSimpleListener(DetailActivity.this, progressDialog){
			
			@Override
			public void onSuccess() {
				super.onSuccess();
				showToast("点赞成功");
			}
			
		});
	}

}

