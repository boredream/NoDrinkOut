package com.boredream.baas.bmobimpl;

import android.content.Context;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import com.boredream.baas.BDBaseObj;
import com.boredream.baas.abs.BDAbsObjHelper;
import com.boredream.baas.abs.BDObjListener;

public class BmobObjHelper<T> extends BDAbsObjHelper<BDBaseObj> {

	private Context context;
	
	public BmobObjHelper(Context context) {
		this.context = context;
	}

	@Override
	public void save(BDBaseObj data) {
		data.save(context);
	}

	@Override
	public void save(final BDBaseObj data, final BDObjListener<BDBaseObj> listener) {
		data.save(context, new SaveListener() {
			@Override
			public void onSuccess() {
				listener.onSuccess(data);
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				listener.onError(String.valueOf(arg0), arg1, null);
			}
		});
	}

	@Override
	public void delete(BDBaseObj data) {
		data.delete(context);
	}

	@Override
	public void delete(final BDBaseObj data, final BDObjListener<BDBaseObj> listener) {
		data.delete(context, new DeleteListener() {
			@Override
			public void onSuccess() {
				listener.onSuccess(data);
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				listener.onError(String.valueOf(arg0), arg1, null);
			}
		});
	}

	@Override
	public void update(BDBaseObj data) {
		data.update(context);
	}

	@Override
	public void update(final BDBaseObj data, final BDObjListener<BDBaseObj> listener) {
		data.update(context, new UpdateListener() {
			@Override
			public void onSuccess() {
				listener.onSuccess(data);
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				listener.onError(String.valueOf(arg0), arg1, null);
			}
		});
	}

}
