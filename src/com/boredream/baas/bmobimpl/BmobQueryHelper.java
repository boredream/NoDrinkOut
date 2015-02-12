package com.boredream.baas.bmobimpl;

import java.util.List;

import android.content.Context;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.boredream.baas.BDAbsQueryHelper;
import com.boredream.baas.BDQueryListener;

public class BmobQueryHelper<T> extends BDAbsQueryHelper<T> {

	private Context context;
	private BmobQuery<T> query;

	public BmobQueryHelper(Context context) {
		this.context = context;
		this.query = new BmobQuery<T>();
	}

	@Override
	public List<T> query() {
		throw new RuntimeException("Bmob 不提供同步查询方法");
	}

	@Override
	public void query(final BDQueryListener<T> listener) {
		query.findObjects(context, new FindListener<T>() {

			@Override
			public void onError(int arg0, String arg1) {
				listener.onError(String.valueOf(arg0), arg1, null);
			}

			@Override
			public void onSuccess(List<T> arg0) {
				listener.onSuccess(arg0);
			}
		});
	}

}
