package com.boredream.nodrinkout.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;

/**
 * 构造广播监听类，监听 SDK key 验证以及网络异常广播
 */
public class BMapSDKReceiver extends BroadcastReceiver {
	public void onReceive(Context context, Intent intent) {
		String s = intent.getAction();
		Log.d("DDD", "action: " + s);
		if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
			Log.d("DDD", "key 验证出错! 请在 AndroidManifest.xml 文件中检查 key 设置");
		} else if (s.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
			Log.d("DDD", "网络出错");
		}
	}
}