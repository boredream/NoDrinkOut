package com.boredream.nodrinkout.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.entity.InfoBean;
import com.nostra13.universalimageloader.core.ImageLoader;

public class InfoAdapter extends BaseAdapter {

	private Context context;
	private List<InfoBean> datas;
	private ImageLoader imageLoader;

	public InfoAdapter(Context context, List<InfoBean> datas) {
		this.context = context;
		this.datas = datas;
		this.imageLoader = ImageLoader.getInstance();
	}

	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public InfoBean getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.item_info, null);
			holder.iv_content = (ImageView) convertView
					.findViewById(R.id.iv_content);
			holder.tv_title = (TextView) convertView
					.findViewById(R.id.tv_title);
			holder.tv_content = (TextView) convertView
					.findViewById(R.id.tv_content);
			holder.tv_comment = (TextView) convertView
					.findViewById(R.id.tv_comment);
			holder.tv_like = (TextView) convertView
					.findViewById(R.id.tv_like);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// set data
		InfoBean bean = getItem(position);
		imageLoader.displayImage(bean.getImgCompleteUrl(), holder.iv_content);
		holder.tv_title.setText(bean.getTitle());
		holder.tv_content.setText(bean.getContent());
		holder.tv_comment.setText(String.valueOf(bean.getCommentsCount()));
		holder.tv_like.setText(String.valueOf(bean.getLikesCount()));

		return convertView;
	}

	public static class ViewHolder {
		public ImageView iv_content;
		public TextView tv_title;
		public TextView tv_content;
		public TextView tv_comment;
		public TextView tv_like;
	}

}
