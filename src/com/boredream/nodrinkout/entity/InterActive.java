package com.boredream.nodrinkout.entity;

public class InterActive extends BmobBaseObj {
	private InfoBean info;
	private int type;
	private int count;

	public InfoBean getInfo() {
		return info;
	}

	public void setInfo(InfoBean info) {
		this.info = info;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
