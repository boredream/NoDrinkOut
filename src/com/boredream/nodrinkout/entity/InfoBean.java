package com.boredream.nodrinkout.entity;

import cn.bmob.v3.datatype.BmobRelation;

public class InfoBean extends BmobBaseObj {
	private String title;
	private String content;
	private String imgCompleteUrl;
	/**
	 * ��Ѷ����
	 */
	private int cateId;
	/**
	 * �Ƽ����� 1-��ҳ�����Ƽ� 2-��ҳ�ȵ��Ƽ� 3-��Ʒ�Ƽ�
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
