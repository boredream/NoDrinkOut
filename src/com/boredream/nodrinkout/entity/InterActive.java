package com.boredream.nodrinkout.entity;

/**
 * ������Ϣ��,�����ղصȲ���
 */
public class InterActive extends BmobBaseObj {
	private String id;
	/**
	 * �������� 1-info���״̬��Ϣ�ĵ��� 2-shop��Ե�����Ϣ���ղ� 3-comment������۵ĵ���
	 */
	private int type;
	/**
	 * ����Ŀ��id,�������Ͳ�ͬ��Ӧ��ͬʵ�����id
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
