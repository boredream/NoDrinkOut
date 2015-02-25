package com.boredream.nodrinkout.bmob;

import android.content.Context;
import cn.bmob.v3.Bmob;

public class BmobInit {
	
	public static final String BMOB_APP_ID = "0b6f86b80e5684fd6314657117748f14";
	
	public static void init(Context context) {
		Bmob.initialize(context, BMOB_APP_ID);
	}
}
