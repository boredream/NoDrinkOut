package com.boredream.nodrinkout.entity;

import android.provider.ContactsContract.CommonDataKinds.Relation;

public class InfoComment extends BmobBaseObj {

	private UserBean user;
	private InfoBean info;
	private String content;

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
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
