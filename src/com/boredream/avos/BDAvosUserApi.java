package com.boredream.avos;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.RequestMobileCodeCallback;

public class BDAvosUserApi {

	public void registerSendSmsCode(String phone, RequestMobileCodeCallback callback) {
		AVOSCloud.requestSMSCodeInBackgroud(phone, callback);
	}

	public void registerOrLoginUserBySms(String phone, String smsCode, LogInCallback<AVUser> callback) {
		AVUser.signUpOrLoginByMobilePhoneInBackground(phone, smsCode, callback);
	}

}
