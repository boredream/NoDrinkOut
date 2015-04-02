package com.boredream.nodrinkout.adapter;

import java.util.List;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.boredream.nodrinkout.R;

public class AddImagesAdapter extends BaseAdapter {

	private Context context;
	private List<Uri> imgUris;
	
	private OnAddImgItemClickListener onAddImgItemClickListener;

	public void setOnAddImgItemClickListener(
			OnAddImgItemClickListener onAddImgItemClickListener) {
		this.onAddImgItemClickListener = onAddImgItemClickListener;
	}

	public AddImagesAdapter(Context context, List<Uri> datas) {
		this.context = context;
		this.imgUris = datas;
	}

	@Override
	public int getCount() {
		return imgUris.size() + 1;
	}

	@Override
	public Uri getItem(int position) {
		return imgUris.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.item_grid_image, null);
			holder.iv_image = (ImageView) convertView
					.findViewById(R.id.iv_image);
			holder.iv_remove_img = (ImageView) convertView
					.findViewById(R.id.iv_remove_img);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (position == imgUris.size()) {
			holder.iv_image.setImageResource(R.drawable.ic_launcher);
			holder.iv_remove_img.setVisibility(View.GONE);
		} else {
			// set data
			Uri uri = getItem(position);
			holder.iv_image.setImageURI(uri);
			holder.iv_remove_img.setVisibility(View.VISIBLE);
		}
		
		if(onAddImgItemClickListener != null) {
			holder.iv_remove_img.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					onAddImgItemClickListener.onRemoveBtnClick(v, position);
				}
			});
			
			convertView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					onAddImgItemClickListener.onBodyClick(v, position);
				}
			});
		}

		return convertView;
	}

	public static class ViewHolder {
		public ImageView iv_image;
		public ImageView iv_remove_img;
	}
	
	public static interface OnAddImgItemClickListener {
		public void onBodyClick(View v, int position);
		public void onRemoveBtnClick(View v, int position);
	}

}
