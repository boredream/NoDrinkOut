package com.boredream.nodrinkout.entity;

import cn.bmob.v3.datatype.BmobRelation;

public class InfoBean extends BmobBaseObj {
	private String title;
	private String content;
	private String imgCompleteUrl;
	/**
	 * 资讯类型
	 */
	private int cateId;
	/**
	 * 推荐类型 1-首页海报推荐 2-首页热点推荐 3-新品推荐
	 */
	private int recommendType;

	private BmobRelation comments;
	private BmobRelation likes;

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

	public int getRecommendType() {
		return recommendType;
	}

	public void setRecommendType(int recommendType) {
		this.recommendType = recommendType;
	}

	public BmobRelation getComments() {
		return comments;
	}

	public void setComments(BmobRelation comments) {
		this.comments = comments;
	}

	public BmobRelation getLikes() {
		return likes;
	}

	public void setLikes(BmobRelation likes) {
		this.likes = likes;
	}

}
