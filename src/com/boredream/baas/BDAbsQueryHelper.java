package com.boredream.baas;

import java.util.List;

public abstract class BDAbsQueryHelper<T> {

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
