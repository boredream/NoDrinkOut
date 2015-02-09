package com.boredream.nodrinkout.info;

import cn.bmob.v3.BmobUser;

import com.boredream.baas.BDBaseObj;

public class InfoComment extends BDBaseObj {

	private BmobUser user;
	private InfoBean info;
	private String content;

	public BmobUser getUser() {
		return user;
	}

	public void setUser(BmobUser user) {
		this.user = user;
	}

	public InfoBean getInfo() {
		return info;
	}

	public void setInfo(InfoBean info) {
		this.info = info;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
