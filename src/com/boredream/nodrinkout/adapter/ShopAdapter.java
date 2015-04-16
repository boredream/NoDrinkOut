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

public class ShopAdapter extends BaseAdapter {

	private Context context;
	private List<CoffeeShop> datas;
	// 1- ’≤ÿ 2-æ‡¿Î
	private int type;

	public void setDatas(List<CoffeeShop> datas) {
		this.datas = datas;
	}

	public ShopAdapter(Context context, List<CoffeeShop> datas) {
		this.context = context;
		this.datas = datas;
		this.type = 1;
	}
	
	public ShopAdapter(Context context, List<CoffeeShop> datas, int type) {
		this.context = context;
		this.datas = datas;
		this.type = type;
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
			holder.tv_subhead = (TextView) convertView.findViewById(R.id.tv_subhead);
			holder.tv_body = (TextView) convertView.findViewById(R.id.tv_body);
			holder.iv_shop_right = (ImageView) convertView.findViewById(R.id.iv_shop_right);
			holder.tv_shop_right = (TextView) convertView.findViewById(R.id.tv_shop_right);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// set data
		CoffeeShop item = getItem(position);
		holder.tv_subhead.setText(item.getName());
		holder.tv_body.setText(item.getAddress());
		if(type == 1) {
			holder.iv_shop_right.setImageResource(R.drawable.ic_favorite);
			holder.tv_shop_right.setText(item.getFollowCount()+"");
		} else if(type == 2) {
			holder.iv_shop_right.setImageResource(R.drawable.ic_location_on);
			holder.tv_shop_right.setText("100m");
		}
		
		return convertView;
	}

	public static class ViewHolder{
		public TextView tv_subhead;
		public TextView tv_body;
		public ImageView iv_shop_right;
		public TextView tv_shop_right;
	}

}
