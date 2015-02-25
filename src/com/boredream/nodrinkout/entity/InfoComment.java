package com.boredream.nodrinkout.entity;

public class InfoComment extends BmobBaseObj {

	private UserBean user;
	private InfoBean info;
	private InterActive interAct;
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

	public InterActive getInterAct() {
		return interAct;
	}

	public void setInterAct(InterActive interAct) {
		this.interAct = interAct;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
