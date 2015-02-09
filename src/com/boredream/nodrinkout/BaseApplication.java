package com.boredream.nodrinkout;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

public class BaseApplication extends Application {

	//public UserInfo userInfo;
	//public BDLocation myLocation;

	private List<Activity> activityList = new LinkedList<Activity>();

	private static BaseApplication instance;
	// 单例模式中获取唯一的ExitApplication 实例
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
