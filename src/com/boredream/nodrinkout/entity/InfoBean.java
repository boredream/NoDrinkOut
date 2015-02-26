package com.boredream.nodrinkout.entity;

import cn.bmob.v3.datatype.BmobRelation;

public class InfoBean extends BmobBaseObj {
	private String title;
	private String content;
	private String imgCompleteUrl;
	/**
	 * 资讯类型 1-bigger 2-knowledge
	 */
	private int cateId;

	private UserBean user;
	private int commentsCount;
	private int likesCount;

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

	public String getImgCompleteUrl() {
		return imgCompleteUrl;
	}

	public void setImgCompleteUrl(String imgCompleteUrl) {
		this.imgCompleteUrl = imgCompleteUrl;
	}

	public int getCateId() {
		return cateId;
	}

	public void setCateId(int cateId) {
		this.cateId = cateId;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public int getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}

	public int getLikesCount() {
		return likesCount;
	}

	public void setLikesCount(int likesCount) {
		this.likesCount = likesCount;
	}

}
