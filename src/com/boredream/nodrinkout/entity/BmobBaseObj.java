package com.boredream.nodrinkout.entity;

import cn.bmob.v3.BmobObject;

public class BmobBaseObj extends BmobObject {
	private static final long serialVersionUID = 7731382476635066404L;

	@Override
	public String getTableName() {
		return getClass().getSimpleName();
	}
}
