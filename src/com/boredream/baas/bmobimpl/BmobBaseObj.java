package com.boredream.baas.bmobimpl;

import cn.bmob.v3.BmobObject;

import com.avos.avoscloud.AVObject;
import com.boredream.baas.BDObjable;

public class BmobBaseObj extends BmobObject implements BDObjable {
	@Override
	public String getTableName() {
		return getClass().getSimpleName();
	}
}
