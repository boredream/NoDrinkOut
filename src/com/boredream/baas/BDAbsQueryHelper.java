package com.boredream.baas;

import java.util.List;

public abstract class BDAbsQueryHelper<T> {

	/**
	 * ͬ����ѯ
	 * 
	 * @return ��ѯ���
	 */
	public abstract List<T> query();

	/**
	 * �첽��ѯ
	 * 
	 * @param listener ��ѯ����ص�
	 */
	public abstract void query(BDQueryListener<T> listener);

}
