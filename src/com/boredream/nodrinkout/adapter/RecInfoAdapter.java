package com.boredream.nodrinkout.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import cn.bmob.v3.datatype.BmobPointer;

import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.entity.InfoBean;
import com.boredream.nodrinkout.entity.InfoRecommend;
import com.boredream.nodrinkout.entity.InfoRecommend;
import com.nostra13.universalimageloader.core.ImageLoader;

public class RecInfoAdapter extends BaseAdapter {

	private Context context;
	private List<InfoRecommend> datas;
	private ImageLoader imageLoader;

	public RecInfoAdapter(Context context, List<InfoRecommend> datas) {
		this.context = context;
		this.datas = datas;
		this.imageLoader = ImageLoader.getInstance();
	}

	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public InfoRecommend getItem(int position) {
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
		InfoRecommend rec = getItem(position);
		InfoBean bean = rec.getInfo();
		imageLoader.displayImage(bean.getImgCompleteUrl(), holder.iv_content);
		holder.tv_title.setText(bean.getTitle());
		holder.tv_content.setText(bean.getContent());
		
//		List<BmobPointer> commentsObjs = bean.getComments().getObjects();
//		holder.tv_comment.setText(commentsObjs.size()+"");
//		
//		List<BmobPointer> likesObjs = bean.getLikes().getObjects();
//		holder.tv_like.setText(likesObjs.size()+"");

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
