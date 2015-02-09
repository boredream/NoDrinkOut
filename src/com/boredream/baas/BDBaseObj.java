package com.boredream.baas;

import cn.bmob.v3.BmobObject;

import com.avos.avoscloud.AVObject;
import com.boredream.baas.abs.BDBaasObj;

public class BDBaseObj extends BmobObject implements BDBaasObj {
	@Override
	public String getTableName() {
		return getClass().getSimpleName();
	}
}
