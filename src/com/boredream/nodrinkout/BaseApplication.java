package com.boredream.nodrinkout;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.boredream.baas.BDBaaS;

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
