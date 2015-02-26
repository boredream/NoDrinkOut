package com.boredream.nodrinkout.entity;

@SuppressWarnings("serial")
public class InfoLike extends BmobBaseObj {
	private UserBean user;
	private InfoBean info;

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

}
