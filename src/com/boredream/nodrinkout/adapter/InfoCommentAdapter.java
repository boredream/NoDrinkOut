package com.boredream.nodrinkout.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.entity.InfoComment;
import com.boredream.nodrinkout.entity.UserBean;
import com.nostra13.universalimageloader.core.ImageLoader;

public class InfoCommentAdapter extends BaseAdapter {

	private Context context;
	private List<InfoComment> datas;
	private ImageLoader imageLoader;

	public InfoCommentAdapter(Context context, List<InfoComment> datas) {
		this.context = context;
		this.datas = datas;
		this.imageLoader = ImageLoader.getInstance();
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
			convertView = View.inflate(context, R.layout.item_comment, null);
			holder.iv_avatar = (ImageView) convertView
					.findViewById(R.id.iv_avatar);
			holder.tv_title = (TextView) convertView
					.findViewById(R.id.tv_title);
			holder.tv_content = (TextView) convertView
					.findViewById(R.id.tv_content);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// set data
		InfoComment bean = getItem(position);
		UserBean user = bean.getUser();
		
		imageLoader.displayImage(user.getAvatarUrl(), holder.iv_avatar);
		holder.tv_title.setText(user.getUsername());
		holder.tv_content.setText(bean.getContent());
		return convertView;
	}

	public static class ViewHolder {
		public ImageView iv_avatar;
		public TextView tv_title;
		public TextView tv_content;
	}

}
