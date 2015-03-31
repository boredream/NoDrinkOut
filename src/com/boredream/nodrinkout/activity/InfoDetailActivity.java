package com.boredream.nodrinkout.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.boredream.nodrinkout.BaseActivity;
import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.adapter.InfoCommentAdapter;
import com.boredream.nodrinkout.adapter.InfoImagesAdapter;
import com.boredream.nodrinkout.bmob.BmobApi;
import com.boredream.nodrinkout.entity.CoffeeInfo;
import com.boredream.nodrinkout.entity.InfoComment;
import com.boredream.nodrinkout.entity.InterActive;
import com.boredream.nodrinkout.listener.FindSimpleListener;
import com.boredream.nodrinkout.listener.UpdateSimpleListener;
import com.boredream.nodrinkout.utils.CommonConstants;
import com.boredream.nodrinkout.utils.DialogUtils;
import com.boredream.nodrinkout.utils.ImageOptHelper;
import com.boredream.nodrinkout.utils.ViewUtils;
import com.boredream.nodrinkout.view.Pull2RefreshListView;
import com.boredream.nodrinkout.view.WrapHeightGridView;

public class InfoDetailActivity extends BaseActivity implements OnClickListener, OnCheckedChangeListener {
	
	private TextView include_tv_subhead;
	private RadioGroup include_tab_infodetail;
	private View include_card_content;
	private ImageView iv_avatar;
	private RelativeLayout rl_content;
	private TextView tv_subhead;
	private TextView tv_body;
	private FrameLayout fl_imageview;
	private WrapHeightGridView gv_images;
	private ImageView iv_image;
	private TextView tv_content;
	private ImageView iv_location;
	private TextView tv_location;
	private TextView tv_comment;
	private TextView tv_like;
	
	private Pull2RefreshListView plv_comment;
	
	private LinearLayout ll_share_bottom;
	private ImageView iv_share_bottom;
	private TextView tv_share_bottom;
	private LinearLayout ll_comment_bottom;
	private ImageView iv_comment_bottom;
	private TextView tv_comment_bottom;
	private LinearLayout ll_like_bottom;
	private ImageView iv_like_bottom;
	private TextView tv_like_bottom;
	
	private Dialog addCommentDialog;
	private EditText et_comment;
	private Button btn_send;
	
	private List<InfoComment> comments;
	private InfoCommentAdapter adapter;
	private CoffeeInfo info;
	
	private boolean hasLiked;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_infodetail);
		
		initView();
		
		info = (CoffeeInfo) intent.getSerializableExtra("info");
		
		setData();
		
		loadComments();
		loadLikeState();
	}


	private void initView() {
		ViewUtils.initTitleBack(this, "咖讯详情", simpleOnClickListener);
		
		include_tv_subhead = (TextView) View.inflate(this, R.layout.tv_subhead, null);
		include_tab_infodetail = (RadioGroup) View.inflate(this, R.layout.include_tab_infodetail, null);
		include_card_content = View.inflate(this, R.layout.include_item_info_content, null);
		include_card_content.setBackgroundResource(R.color.transparent);
		iv_avatar = (ImageView) include_card_content.findViewById(R.id.iv_avatar);
		rl_content = (RelativeLayout) include_card_content.findViewById(R.id.rl_content);
		tv_subhead = (TextView) include_card_content.findViewById(R.id.tv_subhead);
		tv_body = (TextView) include_card_content.findViewById(R.id.tv_body);
		fl_imageview = (FrameLayout) include_card_content.findViewById(R.id.fl_imageview);
		gv_images = (WrapHeightGridView) include_card_content.findViewById(R.id.gv_images);
		iv_image = (ImageView) include_card_content.findViewById(R.id.iv_image);
		tv_content = (TextView) include_card_content.findViewById(R.id.tv_content);
		iv_location = (ImageView) include_card_content.findViewById(R.id.iv_location);
		tv_location = (TextView) include_card_content.findViewById(R.id.tv_location);
		tv_comment = (TextView) include_card_content.findViewById(R.id.tv_comment);
		tv_like = (TextView) include_card_content.findViewById(R.id.tv_like);
		include_tab_infodetail.setOnCheckedChangeListener(this);
		
		plv_comment = (Pull2RefreshListView) findViewById(R.id.plv_comment);
		
		ll_share_bottom = (LinearLayout) findViewById(R.id.ll_share_bottom);
		iv_share_bottom = (ImageView) findViewById(R.id.iv_share_bottom);
		tv_share_bottom = (TextView) findViewById(R.id.tv_share_bottom);
		ll_comment_bottom = (LinearLayout) findViewById(R.id.ll_comment_bottom);
		iv_comment_bottom = (ImageView) findViewById(R.id.iv_comment_bottom);
		tv_comment_bottom = (TextView) findViewById(R.id.tv_comment_bottom);
		ll_like_bottom = (LinearLayout) findViewById(R.id.ll_like_bottom);
		iv_like_bottom = (ImageView) findViewById(R.id.iv_like_bottom);
		tv_like_bottom = (TextView) findViewById(R.id.tv_like_bottom);
		ll_share_bottom.setOnClickListener(this);
		ll_comment_bottom.setOnClickListener(this);
		ll_like_bottom.setOnClickListener(this);
		
		addCommentDialog = DialogUtils.createCommentDialog(this);
		et_comment = (EditText) addCommentDialog.findViewById(R.id.et_comment);
		btn_send = (Button) addCommentDialog.findViewById(R.id.btn_send);
		btn_send.setOnClickListener(this);
	}
	
	private void setData() {
		imageLoader.displayImage(info.getImgUrls(), iv_avatar,
				ImageOptHelper.getAvatarOptions());
		tv_subhead.setText(user.getUsername());
		tv_body.setText(user.isMale()?"男":"女");
		
		String imgUrls = info.getImgUrls()+"";
		String[] urls = imgUrls.split(CommonConstants.DIVIDER_IMAGE_URLS);
		if(urls.length == 1) {
			fl_imageview.setVisibility(View.VISIBLE);
			gv_images.setVisibility(View.GONE);
			iv_image.setVisibility(View.VISIBLE);
			
			imageLoader.displayImage(imgUrls, iv_image);
		} else if(urls.length > 1) {
			fl_imageview.setVisibility(View.VISIBLE);
			gv_images.setVisibility(View.VISIBLE);
			iv_image.setVisibility(View.GONE);
			
			InfoImagesAdapter imagesAdapter = new InfoImagesAdapter(this, urls);
			gv_images.setAdapter(imagesAdapter);
		} else {
			fl_imageview.setVisibility(View.GONE);
		}
		
		tv_content.setVisibility(TextUtils.isEmpty(info.getContent()) ? 
				View.GONE : View.VISIBLE);
		tv_content.setText(info.getContent());
		tv_location.setText(info.getShop().getName());
		tv_comment.setText(info.getCommentCount()+"");
		tv_like.setText(info.getLikeCount()+"");
		
		comments = new ArrayList<InfoComment>();
		adapter = new InfoCommentAdapter(this, comments);
		plv_comment.setAdapter(adapter);
		include_tv_subhead.setText("评论");
		plv_comment.getRefreshableView().addHeaderView(include_card_content);
		plv_comment.getRefreshableView().addHeaderView(include_tv_subhead);
	}
	
	private void sendComment() {
		String comment = et_comment.getText().toString().trim();
		if(TextUtils.isEmpty(comment)) {
			showToast("评论不能为空");
			return;
		}
		
		progressDialog.show();
		BmobApi.insertComment(this, info, comment, 
				new UpdateSimpleListener(this, progressDialog){

					@Override
					public void onSuccess() {
						super.onSuccess();
						
						et_comment.setText("");
						addCommentDialog.dismiss();
						
						loadComments();
					}
		});
	}
	
	private void setLikeState() {
		if(hasLiked) {
			iv_like_bottom.setColorFilter(getResources().getColor(R.color.red));
			tv_like_bottom.setText("已赞");
		} else {
			iv_like_bottom.clearColorFilter();
			tv_like_bottom.setText("点赞");
		}
	}

	private void sendLike() {
		if(hasLiked) {
			showToast("已经赞过了");
			return;
		}
		
		progressDialog.show();
		BmobApi.likeInfo(this, info, 
				new UpdateSimpleListener(this, progressDialog){
			@Override
			public void onSuccess() {
				super.onSuccess();
				
				hasLiked = true;
				setLikeState();
			}
		});
	}
	
	private void loadComments() {
		progressDialog.show();
		BmobApi.queryComments(this, info, 
				new FindSimpleListener<InfoComment>(this, progressDialog){

					@Override
					public void onSuccess(List<InfoComment> arg0) {
						super.onSuccess(arg0);
						
						comments.clear();
						comments.addAll(arg0);
						adapter.notifyDataSetChanged();
					}
		});
	}
	
	private void loadLikeState() {
		BmobApi.isLikeInfo(this, info, 
				new FindSimpleListener<InterActive>(this, progressDialog){

					@Override
					public void onSuccess(List<InterActive> arg0) {
						super.onSuccess(arg0);
						
						hasLiked = arg0.size() > 0;
						setLikeState();
					}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_share_bottom:
			break;
		case R.id.ll_comment_bottom:
			addCommentDialog.show();
			break;
		case R.id.ll_like_bottom:
			sendLike();
			break;
		case R.id.btn_send:
			sendComment();
			break;

		default:
			break;
		}
	}


	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.rb_userinfo:
			
			break;
		case R.id.rb_infos:
			
			break;
		case R.id.rb_track:
			
			break;
		}
	}
}
