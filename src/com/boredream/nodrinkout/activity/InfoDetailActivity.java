package com.boredream.nodrinkout.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.boredream.nodrinkout.BaseActivity;
import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.adapter.InfoCommentAdapter;
import com.boredream.nodrinkout.adapter.InfoImagesAdapter;
import com.boredream.nodrinkout.bmob.BmobApi;
import com.boredream.nodrinkout.entity.CoffeeInfo;
import com.boredream.nodrinkout.entity.InfoComment;
import com.boredream.nodrinkout.utils.CommonConstants;
import com.boredream.nodrinkout.utils.ImageOptionsHelper;
import com.boredream.nodrinkout.view.DrawableTextView;
import com.boredream.nodrinkout.view.Pull2RefreshListView;
import com.boredream.nodrinkout.view.WrapHeightGridView;

public class InfoDetailActivity extends BaseActivity implements OnClickListener {
	
	private TextView include_tv_subhead;
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
	private DrawableTextView tv_comment;
	private DrawableTextView tv_like;
	
	private Pull2RefreshListView plv_comment;
	
	private TextView bottom_tv_share;
	private TextView bottom_tv_comment;
	private TextView bottom_tv_like;
	
	private List<InfoComment> comments;
	private InfoCommentAdapter adapter;
	private CoffeeInfo info;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_infodetail);
		
		initView();
		
		info = (CoffeeInfo) intent.getSerializableExtra("info");
		
		setData();
	}


	private void initView() {
		include_tv_subhead = (TextView) View.inflate(this, R.layout.tv_subhead, null);
		include_card_content = View.inflate(this, R.layout.include_card_content, null);
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
		tv_comment = (DrawableTextView) include_card_content.findViewById(R.id.tv_comment);
		tv_like = (DrawableTextView) include_card_content.findViewById(R.id.tv_like);
		
		plv_comment = (Pull2RefreshListView) findViewById(R.id.plv_comment);
		
		bottom_tv_share = (TextView) findViewById(R.id.tv_share);
		bottom_tv_comment = (TextView) findViewById(R.id.tv_comment);
		bottom_tv_like = (TextView) findViewById(R.id.tv_like);
		bottom_tv_share.setOnClickListener(this);
		bottom_tv_comment.setOnClickListener(this);
		bottom_tv_like.setOnClickListener(this);
	}
	
	private void setData() {
		imageLoader.displayImage(info.getImgUrls(), iv_avatar,
				ImageOptionsHelper.getAvatarOptions());
		tv_subhead.setText(user.getUsername());
		tv_body.setText(user.isMale()?"ÄÐ":"Å®");
		
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
		include_tv_subhead.setText("ÆÀÂÛ");
		plv_comment.getRefreshableView().addHeaderView(include_card_content);
		plv_comment.getRefreshableView().addHeaderView(include_tv_subhead);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_share:
			break;
		case R.id.tv_comment:
			
			break;
		case R.id.tv_like:
			
			break;

		default:
			break;
		}
	}
}
