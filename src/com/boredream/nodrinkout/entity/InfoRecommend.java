package com.boredream.nodrinkout.entity;


public class InfoRecommend extends BmobBaseObj {
	/**
	 * �Ƽ����� 1-��ҳ�����Ƽ� 2-��ҳ�����Ƽ�
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
