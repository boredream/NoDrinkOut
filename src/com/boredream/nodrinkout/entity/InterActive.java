package com.boredream.nodrinkout.entity;

/**
 * 互动信息表,点赞收藏等操作
 */
public class InterActive extends BmobBaseObj {
	private String id;
	/**
	 * 互动类型 1-info针对状态信息的点赞 2-shop针对店面信息的收藏 3-comment针对评论的点赞
	 */
	private int type;
	/**
	 * 互动目标id,根据类型不同对应不同实体类的id
	 */
	private String tarId;
	private User user;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTarId() {
		return tarId;
	}

	public void setTarId(String tarId) {
		this.tarId = tarId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
