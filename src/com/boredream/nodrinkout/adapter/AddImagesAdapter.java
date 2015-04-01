package com.boredream.nodrinkout.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.boredream.nodrinkout.R;
import com.nostra13.universalimageloader.core.ImageLoader;

public class AddImagesAdapter extends BaseAdapter{

	private Context context;
	private String[] datas;
	private ImageLoader imageLoader;

	public AddImagesAdapter(Context context, String[] datas) {
		this.context = context;
		this.datas = datas;
		imageLoader = ImageLoader.getInstance();
	}

	@Override
	public int getCount() {
		return datas.length;
	}

	@Override
	public String getItem(int position) {
		return datas[position];
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
			convertView = View.inflate(context, R.layout.item_grid_image, null);
			holder.iv_image = (ImageView) convertView.findViewById(R.id.iv_image);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// set data
		String item = getItem(position);
		imageLoader.displayImage(item, holder.iv_image);
		
		return convertView;
	}


	public static class ViewHolder{
		public ImageView iv_image;
	}



}
