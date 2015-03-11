package com.boredream.nodrinkout.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.activity.DetailActivity;
import com.boredream.nodrinkout.activity.MainActivity;
import com.boredream.nodrinkout.entity.InfoBean;
import com.boredream.nodrinkout.entity.InfoRecommend;
import com.nostra13.universalimageloader.core.ImageLoader;

public class RecVpAdapter extends PagerAdapter {

	private Context context;
	private List<InfoRecommend> infos;
	private List<View> views;
	private ImageLoader imageLoader;

	public RecVpAdapter(Context context, List<InfoRecommend> infos) {
		this.context = context;
		this.infos = infos;
		this.imageLoader = ImageLoader.getInstance();
		initViews();
	}

	private void initViews() {
		views = new ArrayList<View>();
		for(int i=0; i<infos.size(); i++) {
			View view = View.inflate(context, R.layout.item_vp, null);
			views.add(view);
		}
	}
	
	public InfoRecommend getItem(int position) {
		return infos.get(position);
	}

	@Override
	public int getCount() {
		return infos.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0==arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		View view = views.get(position);
		container.removeView(view);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view = views.get(position);
		ImageView iv = (ImageView) view.findViewById(R.id.vpitem_iv);
		TextView tv = (TextView) view.findViewById(R.id.vpitem_tv);
		
		InfoRecommend rec = infos.get(position);
		final InfoBean info = rec.getInfo();
		imageLoader.displayImage(info.getImgCompleteUrl(), iv);
		tv.setText(info.getTitle());
		
		view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, DetailActivity.class);
				intent.putExtra("info", info);
				context.startActivity(intent);
			}
		});
		
		container.addView(view);
		return view;
	}

}
