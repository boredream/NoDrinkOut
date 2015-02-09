package com.boredream.baas.abs;

import java.util.List;

public abstract class BDAbsBaasQueryHelper<T> {

	/**
	 * 设置查询条件
	 */
	public abstract void initQueryParams();

	/**
	 * 同步查询
	 * 
	 * @return 查询结果
	 */
	public abstract List<T> query();

	/**
	 * 异步查询
	 * 
	 * @param listener 查询结果回调
	 */
	public abstract void query(BDQueryListener<T> listener);

}
