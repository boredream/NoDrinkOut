package com.boredream.baas.bmobimpl;

import android.content.Context;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import com.boredream.baas.BDAbsObjHelper;
import com.boredream.baas.BDObjListener;

public class BmobObjHelper extends BDAbsObjHelper<BmobBaseObj> {

	private Context context;
	
	public BmobObjHelper(Context context) {
		this.context = context;
	}

	@Override
	public void save(BmobBaseObj data) {
		data.save(context);
	}

	@Override
	public void save(final BmobBaseObj data, final BDObjListener<BmobBaseObj> listener) {
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
	public void delete(BmobBaseObj data) {
		data.delete(context);
	}

	@Override
	public void delete(final BmobBaseObj data, final BDObjListener<BmobBaseObj> listener) {
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
	public void update(BmobBaseObj data) {
		data.update(context);
	}

	@Override
	public void update(final BmobBaseObj data, final BDObjListener<BmobBaseObj> listener) {
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
