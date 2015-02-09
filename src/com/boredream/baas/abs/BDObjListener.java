package com.boredream.baas.abs;

public interface BDObjListener<T> {

	public void onSuccess(T data);

	public void onError(String code, String msg, Object errorEntity);

}
