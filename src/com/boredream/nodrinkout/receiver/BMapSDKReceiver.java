package com.boredream.nodrinkout.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;

/**
 * ����㲥�����࣬���� SDK key ��֤�Լ������쳣�㲥
 */
public class BMapSDKReceiver extends BroadcastReceiver {
	public void onReceive(Context context, Intent intent) {
		String s = intent.getAction();
		Log.d("DDD", "action: " + s);
		if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
			Log.d("DDD", "key ��֤����! ���� AndroidManifest.xml �ļ��м�� key ����");
		} else if (s.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
			Log.d("DDD", "�������");
		}
	}
}