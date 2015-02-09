package com.boredream.baas.abs;

import java.util.List;

public abstract class BDAbsBaasQueryHelper<T> {

	/**
	 * ���ò�ѯ����
	 */
	public abstract void initQueryParams();

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
