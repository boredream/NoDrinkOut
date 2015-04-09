package com.boredream.nodrinkout;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.baidu.frontia.Frontia;
import com.baidu.frontia.FrontiaApplication;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MyLocationData;
import com.boredream.nodrinkout.bmob.BmobInit;
import com.boredream.nodrinkout.constants.SocialConstants;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class BaseApplication extends FrontiaApplication {

	private List<Activity> activityList = new LinkedList<Activity>();

	private static BaseApplication instance;
	// 单例模式中获取唯一的ExitApplication 实例
	public static BaseApplication getInstance() {
		if (null == instance) {
			instance = new BaseApplication();
		}
		return instance;
	}
	
	public static MyLocationData curLocation;
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.i("DDD", "oncreate app");
		
		BmobInit.init(this);
		initImageLoader(this);
//		SDKInitializer.initialize(this);
		Frontia.init(this, SocialConstants.APIKEY);
	}
	
	// 初始化图片处理
	private void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).threadPriority(Thread.NORM_PRIORITY - 2)
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO).build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}

	// 添加Activity 到容器中
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}
	
	// 从容器中删除Activity
	public void removeActivity(Activity activity) {
		activityList.remove(activity);
	}

	// 遍历所有Activity 并finish
	public void exit() {
		for (Activity activity : activityList) {
			activity.finish();
		}
		System.exit(0);
	}
}
