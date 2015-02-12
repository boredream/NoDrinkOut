package com.boredream.nodrinkout.detail;

import cn.bmob.v3.BmobUser;

import com.boredream.baas.bmobimpl.BmobBaseObj;
import com.boredream.nodrinkout.info.InfoBean;

public class InfoComment extends BmobBaseObj {

	private BmobUser user;
	private InfoBean info;
	private String content;

	public BmobUser getUser() {
		return user;
	}

	public void setUser(BmobUser user) {
		this.user = user;
	}

	public InfoBean getInfo() {
		return info;
	}

	public void setInfo(InfoBean info) {
		this.info = info;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
