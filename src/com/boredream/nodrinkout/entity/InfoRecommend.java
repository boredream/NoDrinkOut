package com.boredream.nodrinkout.entity;


public class InfoRecommend extends BmobBaseObj {
	/**
	 * 推荐类型 1-首页海报推荐 2-首页热门推荐
	 */
	private int type;
	private InfoBean info;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public InfoBean getInfo() {
		return info;
	}

	public void setInfo(InfoBean info) {
		this.info = info;
	}

}
