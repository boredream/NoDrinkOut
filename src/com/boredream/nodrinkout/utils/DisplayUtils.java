package com.boredream.nodrinkout.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

public class DisplayUtils {
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	
	public static int getScreenWidthPixels(Activity context) {
		 DisplayMetrics metric = new DisplayMetrics();
		 context.getWindowManager().getDefaultDisplay().getMetrics(metric);
		return metric.widthPixels;
	}
	
	public static int getScreenHeightPixels(Activity context) {
		DisplayMetrics metric = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(metric);
		return metric.heightPixels;
	}
}
