package com.boredream.nodrinkout.home;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.boredream.nodrinkout.R;

public class GridAdapter extends BaseAdapter {

	private Context context;
	private List<GridItem> datas;

	public GridAdapter(Context context, List<GridItem> datas) {
		this.context = context;
		this.datas = datas;
	}

	@Override
	public int getCount() {
		return 3;
	}

	@Override
	public GridItem getItem(int position) {
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
			convertView = View.inflate(context, R.layout.item_grid, null);
			holder.griditem_iv = (ImageView) convertView.findViewById(R.id.griditem_iv);
			holder.griditem_tv = (TextView) convertView.findViewById(R.id.griditem_tv);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// set data

		return convertView;
	}

	public static class ViewHolder{
		public ImageView griditem_iv;
		public TextView griditem_tv;
	}


}
