package com.boredream.nodrinkout.entity;

@SuppressWarnings("serial")
public class StatusComment extends BmobBaseObj {
	private User user;
	private User replyUser;
	private CoffeeInfo info;
	private String content;
	private int likeCount;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getReplyUser() {
		return replyUser;
	}

	public void setReplyUser(User replyUser) {
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
