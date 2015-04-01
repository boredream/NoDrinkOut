package com.boredream.nodrinkout.utils;

import android.graphics.Bitmap;

import com.boredream.nodrinkout.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class ImageOptHelper {
	
	private static DisplayImageOptions imgOptions;
	private static DisplayImageOptions avatarOptions;

	public static DisplayImageOptions getImgOptions() {
		if (imgOptions == null) {
			imgOptions = new DisplayImageOptions.Builder().cacheOnDisk(true)
					.cacheInMemory(true).bitmapConfig(Bitmap.Config.RGB_565)
					.showImageForEmptyUri(R.drawable.coffee1)
					.showImageOnFail(R.drawable.coffee1)
					.showImageOnLoading(R.drawable.coffee1)
					.displayer(new RoundedBitmapDisplayer(4)).build();
		}
		return imgOptions;
	}
	
	
	public static DisplayImageOptions getAvatarOptions() {
		if(avatarOptions == null) {
			avatarOptions = new DisplayImageOptions.Builder()
			.cacheOnDisk(true)
			.cacheInMemory(true)
			.bitmapConfig(Bitmap.Config.RGB_565)
			.showImageForEmptyUri(R.drawable.ic_launcher)
			.showImageOnFail(R.drawable.ic_launcher)
			.showImageOnLoading(R.drawable.ic_launcher)
			.displayer(new RoundedBitmapDisplayer(999))
			.build();
		}
		return avatarOptions;
	}
	
	
	public static DisplayImageOptions getCornerOptions(int cornerRadiusPixels) {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.cacheOnDisk(true).cacheInMemory(true)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.showImageForEmptyUri(R.drawable.ic_launcher)
				.showImageOnFail(R.drawable.ic_launcher)
				.showImageOnLoading(R.drawable.ic_launcher)
				.displayer(new RoundedBitmapDisplayer(cornerRadiusPixels)).build();
		return options;
	}
	
	public static DisplayImageOptions getShopOptions(int cornerRadiusPixels) {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
		.cacheOnDisk(true).cacheInMemory(true)
		.bitmapConfig(Bitmap.Config.RGB_565)
		.showImageForEmptyUri(R.drawable.coffee_shop_def)
		.showImageOnFail(R.drawable.coffee_shop_def)
		.showImageOnLoading(R.drawable.coffee_shop_loading)
		.displayer(new RoundedBitmapDisplayer(cornerRadiusPixels)).build();
		return options;
	}

}
