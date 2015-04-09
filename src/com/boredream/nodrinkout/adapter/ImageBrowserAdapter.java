package com.boredream.nodrinkout.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.boredream.nodrinkout.view.photoview.PhotoView;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ImageBrowserAdapter extends PagerAdapter {

	private String[] mPhotos;
	private ImageLoader mImageLoader;

	public ImageBrowserAdapter(String[] photos) {
		mPhotos = photos;
		mImageLoader = ImageLoader.getInstance();
	}

	@Override
	public int getCount() {
		if (mPhotos.length > 1) {
			return Integer.MAX_VALUE;
		}
		return mPhotos.length;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	@Override
	public View instantiateItem(ViewGroup container, int position) {
		PhotoView photoView = new PhotoView(container.getContext());
		mImageLoader.displayImage(mPhotos[position % mPhotos.length], photoView);
		container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		return photoView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}
}
