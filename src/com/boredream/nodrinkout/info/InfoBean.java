package com.boredream.nodrinkout.info;

import cn.bmob.v3.datatype.BmobRelation;

import com.boredream.baas.BDBaseObj;

public class InfoBean extends BDBaseObj {
	private String title;
	private String content;
	private String imgName;
	private String imgUrl;
	private String imgCompleteUrl;
	private int cateId;
	private BmobRelation comments;

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

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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

	public BmobRelation getComments() {
		return comments;
	}

	public void setComments(BmobRelation comments) {
		this.comments = comments;
	}

}
