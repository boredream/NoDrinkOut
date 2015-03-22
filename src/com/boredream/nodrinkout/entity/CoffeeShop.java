package com.boredream.nodrinkout.entity;

public class CoffeeShop extends BmobBaseObj {
	private String name;
	private String address;
	private String geo;
	private String imgUrl;
	private int infoCount;
	private int followCount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGeo() {
		return geo;
	}

	public void setGeo(String geo) {
		this.geo = geo;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public int getInfoCount() {
		return infoCount;
	}

	public void setInfoCount(int infoCount) {
		this.infoCount = infoCount;
	}

	public int getFollowCount() {
		return followCount;
	}

	public void setFollowCount(int followCount) {
		this.followCount = followCount;
	}

}
