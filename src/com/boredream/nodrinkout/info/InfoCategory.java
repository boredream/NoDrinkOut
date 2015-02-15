package com.boredream.nodrinkout.info;

import cn.bmob.v3.datatype.BmobRelation;

import com.boredream.nodrinkout.http.BmobBaseObj;

public class InfoCategory extends BmobBaseObj {

	private String name;
	private int cateId;
	private BmobRelation info;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCateId() {
		return cateId;
	}

	public void setCateId(int cateId) {
		this.cateId = cateId;
	}

	public BmobRelation getInfo() {
		return info;
	}

	public void setInfo(BmobRelation info) {
		this.info = info;
	}

}
