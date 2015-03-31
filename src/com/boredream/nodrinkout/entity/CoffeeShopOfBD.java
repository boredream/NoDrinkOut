package com.boredream.nodrinkout.entity;

public class CoffeeShopOfBD extends BmobBaseObj {
	private static final long serialVersionUID = 1541611980033948795L;

	// baidu info
	public String address;
	public String city;
	public String uid;
	private String type;
	private String location; // latitude-longitude
	public String name;
	public String phoneNum;
	public boolean isPano;
	public boolean hasCaterDetails;
	// customer info
	private String imgUrl;
	private int infoCount;
	private int followCount;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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
