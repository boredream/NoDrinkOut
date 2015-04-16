package com.boredream.nodrinkout.entity;

import cn.bmob.v3.datatype.BmobGeoPoint;

public class CoffeeShop extends BmobBaseObj {
	private static final long serialVersionUID = 1541611980033948795L;

	// baidu info
	private String address;
	private String city;
	private String uid;
	private String type;
	private BmobGeoPoint location; // lon,lat
	private String name;
	private String phoneNum;
	private boolean isPano;
	private boolean hasCaterDetails;
	// customer info
	private String imgUrl;
	private int infoCount;
	private int followCount;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BmobGeoPoint getLocation() {
		return location;
	}

	public void setLocation(BmobGeoPoint location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public boolean isPano() {
		return isPano;
	}

	public void setPano(boolean isPano) {
		this.isPano = isPano;
	}

	public boolean isHasCaterDetails() {
		return hasCaterDetails;
	}

	public void setHasCaterDetails(boolean hasCaterDetails) {
		this.hasCaterDetails = hasCaterDetails;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CoffeeShop [name=" + name + "address=" + address + "]";
	}

}
