package com.boredream.baas.abs;

import java.util.List;

public interface BDQueryListener<T> {

	public void onSuccess(List<T> datas);

	public void onError(String code, String msg, Object errorEntity);

}
