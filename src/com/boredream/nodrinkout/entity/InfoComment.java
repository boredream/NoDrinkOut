package com.boredream.nodrinkout.entity;

@SuppressWarnings("serial")
public class InfoComment extends BmobBaseObj {
	private UserBean user;
	private UserBean replyUser;
	private CoffeeInfo info;
	private String content;
	private int likeCount;

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public UserBean getReplyUser() {
		return replyUser;
	}

	public void setReplyUser(UserBean replyUser) {
		this.replyUser = replyUser;
	}

	public CoffeeInfo getInfo() {
		return info;
	}

	public void setInfo(CoffeeInfo info) {
		this.info = info;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

}
