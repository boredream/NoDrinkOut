package com.boredream.nodrinkout.adapter;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.activity.ImageBrowserActivity;
import com.boredream.nodrinkout.activity.InfoDetailActivity;
import com.boredream.nodrinkout.bmob.BmobApi;
import com.boredream.nodrinkout.constants.CommonConstants;
import com.boredream.nodrinkout.entity.CoffeeInfo;
import com.boredream.nodrinkout.entity.User;
import com.boredream.nodrinkout.listener.OnAdapterMultiClickListener;
import com.boredream.nodrinkout.listener.UpdateSimpleListener;
import com.boredream.nodrinkout.utils.DialogUtils;
import com.boredream.nodrinkout.utils.ImageOptHelper;
import com.boredream.nodrinkout.view.WrapHeightGridView;
import com.nostra13.universalimageloader.core.ImageLoader;

public class InfoAdapter extends BaseAdapter {

	private Context context;
	private List<CoffeeInfo> datas;
	private ImageLoader imageLoader;
	private User user;
	
	private OnAdapterMultiClickListener onAdapterMultiClickListener;
	private ProgressDialog progressDialog;

	public InfoAdapter(Context context, List<CoffeeInfo> datas) {
		this.context = context;
		this.datas = datas;
		imageLoader = ImageLoader.getInstance();
		user = User.getCurrentUser(context, User.class);
		onAdapterMultiClickListener = new OnAdapterMultiClickListener(context);
		progressDialog = DialogUtils.createLoadingDialog(context);
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
			convertView = View.inflate(context, R.layout.item_info, null);
			holder.ll_top_content = (LinearLayout) convertView.findViewById(R.id.ll_top_content);
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
			holder.ll_share_bottom = (LinearLayout) convertView
					.findViewById(R.id.ll_share_bottom);
			holder.iv_share_bottom = (ImageView) convertView
					.findViewById(R.id.iv_share_bottom);
			holder.tv_share_bottom = (TextView) convertView
					.findViewById(R.id.tv_share_bottom);
			holder.ll_comment_bottom = (LinearLayout) convertView
					.findViewById(R.id.ll_comment_bottom);
			holder.iv_comment_bottom = (ImageView) convertView
					.findViewById(R.id.iv_comment_bottom);
			holder.tv_comment_bottom = (TextView) convertView
					.findViewById(R.id.tv_comment_bottom);
			holder.ll_like_bottom = (LinearLayout) convertView
					.findViewById(R.id.ll_like_bottom);
			holder.iv_like_bottom = (ImageView) convertView
					.findViewById(R.id.iv_like_bottom);
			holder.tv_like_bottom = (TextView) convertView
					.findViewById(R.id.tv_like_bottom);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		// set data
		final CoffeeInfo item = getItem(position);
		imageLoader.displayImage(item.getImgUrls(), holder.iv_avatar,
				ImageOptHelper.getAvatarOptions());
		holder.tv_subhead.setText(user.getUsername());
		holder.tv_body.setText(user.isMale()?"��":"Ů");
		
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

		holder.gv_images.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(context, ImageBrowserActivity.class);
				intent.putExtra("info", item);
				intent.putExtra("position", position);
				context.startActivity(intent);
			}
		});
		
		holder.ll_share_bottom.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
		
		holder.ll_comment_bottom.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, InfoDetailActivity.class);
				intent.putExtra("info", item);
				context.startActivity(intent);
			}
		});
		
		holder.ll_like_bottom.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				sendLike(item);
			}
		});
		
		holder.ll_top_content.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onAdapterMultiClickListener.onItemClick(
						OnAdapterMultiClickListener.TYPE_INFO_DETAIL, item);
			}
		});
		
		return convertView;
	}

	public static class ViewHolder{
		public LinearLayout ll_top_content;
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

		public LinearLayout ll_share_bottom;
		public ImageView iv_share_bottom;
		public TextView tv_share_bottom;
		public LinearLayout ll_comment_bottom;
		public ImageView iv_comment_bottom;
		public TextView tv_comment_bottom;
		public LinearLayout ll_like_bottom;
		public ImageView iv_like_bottom;
		public TextView tv_like_bottom;
	}

	private void sendLike(CoffeeInfo info) {
		progressDialog.show();
		BmobApi.likeInfo(context, info, 
				new UpdateSimpleListener(context, progressDialog){
			@Override
			public void onSuccess() {
				super.onSuccess();
//				setLikeState();
			}
		});
	}
}
