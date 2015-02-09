package com.boredream.nodrinkout.detail;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.boredream.nodrinkout.R;

public class InfoCommentAdapter extends BaseAdapter {

	private Context context;
	private List<InfoComment> datas;

	public InfoCommentAdapter(Context context, List<InfoComment> datas) {
		this.context = context;
		this.datas = datas;
	}

	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public InfoComment getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	ViewHolder holder;
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.item_info, null);
			holder.infoitem_iv = (ImageView) convertView
					.findViewById(R.id.infoitem_iv);
			holder.infoitem_tv_title = (TextView) convertView
					.findViewById(R.id.infoitem_tv_title);
			holder.infoitem_tv_content = (TextView) convertView
					.findViewById(R.id.infoitem_tv_content);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// set data
		InfoComment bean = getItem(position);
		holder.infoitem_tv_title.setText(bean.getUser().getUsername());
		holder.infoitem_tv_content.setText(bean.getContent());
		return convertView;
	}

	public static class ViewHolder {
		public ImageView infoitem_iv;
		public TextView infoitem_tv_title;
		public TextView infoitem_tv_content;
	}

}
