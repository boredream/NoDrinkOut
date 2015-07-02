package com.boredream.nodrinkout.entity;

public class CoffeeInfo extends BmobBaseObj {

	private String content;
	private String imgUrls;
	private User user;
	private CoffeeShop shop;
	private int commentCount;
	private int likeCount;
	private boolean isChecked;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImgUrls() {
		return imgUrls;
	}

	public void setImgUrls(String imgUrls) {
		this.imgUrls = imgUrls;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CoffeeShop getShop() {
		return shop;
	}

	public void setShop(CoffeeShop shop) {
		this.shop = shop;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

}
