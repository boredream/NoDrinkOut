package com.boredream.nodrinkout;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.boredream.baas.BDBaaS;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class BaseApplication extends Application {

	private List<Activity> activityList = new LinkedList<Activity>();

	private static BaseApplication instance;
	// ����ģʽ�л�ȡΨһ��ExitApplication ʵ��
	public static BaseApplication getInstance() {
		if (null == instance) {
			instance = new BaseApplication();
		}
		return instance;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.i("DDD", "oncreate app");
		
		BDBaaS.init(this);
		initImageLoader(this);
	}
	
	// ��ʼ��ͼƬ����
	private void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = ImageLoaderConfiguration.createDefault(this);
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}

	// ���Activity ��������
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}
	
	// ��������ɾ��Activity
	public void removeActivity(Activity activity) {
		activityList.remove(activity);
	}

	// ��������Activity ��finish
	public void exit() {
		for (Activity activity : activityList) {
			activity.finish();
		}
		System.exit(0);
	}
}
