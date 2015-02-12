package com.boredream.baas;

import android.content.Context;
import cn.bmob.v3.Bmob;

import com.boredream.baas.bmobimpl.BmobObjHelper;

public class BDBaaS {
	
	public static final String BMOB_APP_ID = "0b6f86b80e5684fd6314657117748f14";
	public static final String AVOS_APP_ID = "0z4jhm8zyhlujpqfj7n7yuvh8ckdp5s78h1puqmtwj3badx9";
	public static final String AVOS_APP_KEY = "ri39c28wwfzkp5e3cgupwqagcntfez7djy98kc7nge5bm789";
	
	private static BDAbsObjHelper<? extends BDObjable> helper;
	
	public static BDAbsObjHelper<? extends BDObjable> getObjHelper(Context context) {
		if(helper == null) {
//			helper = new AVOSObjHelper();
			helper = new BmobObjHelper(context);
		}
		return helper;
	}
	
	public static void init(Context context) {
		Bmob.initialize(context, BMOB_APP_ID);
		
//		AVOSCloud.initialize(context, AVOS_APP_ID, AVOS_APP_KEY);
//		AVObject.registerSubclass(BDBaseObj.class);
	}
}
