package com.boredream.baas.abs;

public abstract class BDAbsObjHelper<T> {

	public abstract void save(T data);
	public abstract void save(T data, BDObjListener<T> listener);
	
	public abstract void delete(T data);
	public abstract void delete(T data, BDObjListener<T> listener);
	
	public abstract void update(T data);
	public abstract void update(T data, BDObjListener<T> listener);
}