package com.boredream.nodrinkout.entity;

public class InfoBean extends BmobBaseObj {
	private String title;
	private String content;
	private String imgUrl;
	/**
	 * 资讯类型 1-bigger
	 */
	private int category;

	private UserBean user;
	private InterActive interAct;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public int getCategory() {
		return category;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public InterActive getInterAct() {
		return interAct;
	}

	public void setInterAct(InterActive interAct) {
		this.interAct = interAct;
	}

}
