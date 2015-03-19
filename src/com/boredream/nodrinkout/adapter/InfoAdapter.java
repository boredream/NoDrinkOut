package com.boredream.nodrinkout.adapter;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.entity.CoffeeInfo;
import com.boredream.nodrinkout.entity.UserBean;
import com.boredream.nodrinkout.utils.CommonConstants;
import com.boredream.nodrinkout.utils.ImageOptionsHelper;
import com.boredream.nodrinkout.view.DrawableTextView;
import com.boredream.nodrinkout.view.WrapHeightGridView;
import com.nostra13.universalimageloader.core.ImageLoader;

public class InfoAdapter extends BaseAdapter {

	private Context context;
	private List<CoffeeInfo> datas;
	private ImageLoader imageLoader;
	private UserBean user;

	public InfoAdapter(Context context, List<CoffeeInfo> datas) {
		this.context = context;
		this.datas = datas;
		imageLoader = ImageLoader.getInstance();
		user = UserBean.getCurrentUser(context, UserBean.class);
	}

	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public CoffeeInfo getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.item_card, null);
			holder.iv_avatar = (ImageView) convertView.findViewById(R.id.iv_avatar);
			holder.rl_content = (RelativeLayout) convertView.findViewById(R.id.rl_content);
			holder.tv_subhead = (TextView) convertView.findViewById(R.id.tv_subhead);
			holder.tv_body = (TextView) convertView.findViewById(R.id.tv_body);
			holder.fl_imageview = (FrameLayout) convertView.findViewById(R.id.fl_imageview);
			holder.gv_images = (WrapHeightGridView) convertView.findViewById(R.id.gv_images);
			holder.iv_image = (ImageView) convertView.findViewById(R.id.iv_image);
			holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
			holder.iv_location = (ImageView) convertView.findViewById(R.id.iv_location);
			holder.tv_location = (TextView) convertView.findViewById(R.id.tv_location);
			holder.tv_comment = (DrawableTextView) convertView.findViewById(R.id.tv_comment);
			holder.tv_like = (DrawableTextView) convertView.findViewById(R.id.tv_like);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		// set data
		CoffeeInfo item = getItem(position);
		imageLoader.displayImage(item.getImgUrls(), holder.iv_avatar,
				ImageOptionsHelper.getAvatarOptions());
		holder.tv_subhead.setText(user.getUsername());
		holder.tv_body.setText(user.isMale()?"ÄÐ":"Å®");
		
		String imgUrls = item.getImgUrls()+"";
		String[] urls = imgUrls.split(CommonConstants.DIVIDER_IMAGE_URLS);
		if(urls.length == 1) {
			holder.fl_imageview.setVisibility(View.VISIBLE);
			holder.gv_images.setVisibility(View.GONE);
			holder.iv_image.setVisibility(View.VISIBLE);
			
			imageLoader.displayImage(imgUrls, holder.iv_image);
		} else if(urls.length > 1) {
			holder.fl_imageview.setVisibility(View.VISIBLE);
			holder.gv_images.setVisibility(View.VISIBLE);
			holder.iv_image.setVisibility(View.GONE);
			
			InfoImagesAdapter imagesAdapter = new InfoImagesAdapter(context, urls);
			holder.gv_images.setAdapter(imagesAdapter);
		} else {
			holder.fl_imageview.setVisibility(View.GONE);
		}
		
		holder.tv_content.setVisibility(TextUtils.isEmpty(item.getContent()) ? 
				View.GONE : View.VISIBLE);
		holder.tv_content.setText(item.getContent());
		holder.tv_location.setText(item.getShop().getName());
		holder.tv_comment.setText(item.getCommentCount()+"");
		holder.tv_like.setText(item.getLikeCount()+"");
		
		return convertView;
	}


	public static class ViewHolder{
		public ImageView iv_avatar;
		public RelativeLayout rl_content;
		public TextView tv_subhead;
		public TextView tv_body;
		public FrameLayout fl_imageview;
		public WrapHeightGridView gv_images;
		public ImageView iv_image;
		public TextView tv_content;
		public ImageView iv_location;
		public TextView tv_location;
		public DrawableTextView tv_comment;
		public DrawableTextView tv_like;
	}

}
