package com.boredream.nodrinkout.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.entity.CoffeeShop;
import com.boredream.nodrinkout.utils.DisplayUtils;
import com.boredream.nodrinkout.utils.ImageOptHelper;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ShopAdapter extends BaseAdapter {

	private Context context;
	private List<CoffeeShop> datas;
	private ImageLoader imageLoader;

	public ShopAdapter(Context context, List<CoffeeShop> datas) {
		this.context = context;
		this.datas = datas;
		imageLoader = ImageLoader.getInstance();
	}

	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public CoffeeShop getItem(int position) {
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
			convertView = View.inflate(context, R.layout.item_shop, null);
			holder.iv_image = (ImageView) convertView.findViewById(R.id.iv_image);
			holder.tv_subhead = (TextView) convertView.findViewById(R.id.tv_subhead);
			holder.tv_body = (TextView) convertView.findViewById(R.id.tv_body);
			holder.tv_collect = (TextView) convertView.findViewById(R.id.tv_collect);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// set data
		CoffeeShop item = getItem(position);
		imageLoader.displayImage(item.getImgUrl(), holder.iv_image, 
				ImageOptHelper.getCornerOptions(DisplayUtils.dip2px(context, 2)));
		holder.tv_subhead.setText(item.getName());
		holder.tv_body.setText(item.getAddress());
		holder.tv_collect.setText(item.getFollowCount()+"");
		
		return convertView;
	}


	public static class ViewHolder{
		public ImageView iv_image;
		public TextView tv_subhead;
		public TextView tv_body;
		public TextView tv_collect;
	}

}
