package com.boredream.avos;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.DeleteCallback;
import com.avos.avoscloud.SaveCallback;
import com.boredream.bdbaasapi.BDBaasObjHelper;
import com.boredream.bdbaasapi.BDBaasObjListener;

/**
 * AVOS的数据操作类实现
 */
public class AVOSObjHelper extends BDBaasObjHelper<AVObject>{

	@Override
	public AVObject save(AVObject data) {
		try {
			data.save();
		} catch (AVException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void save(final AVObject data, final BDBaasObjListener<AVObject> listener) {
		data.saveInBackground(new SaveCallback() {
			@Override
			public void done(AVException arg0) {
				if(arg0 == null) {
					listener.onSuccess(data);
				} else {
					listener.onError(String.valueOf(arg0.getCode()), arg0.getMessage(), null);
				}
			}
		});
	}

	@Override
	public void delete(AVObject data) {
		try {
			data.delete();
		} catch (AVException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(final AVObject data, final BDBaasObjListener<AVObject> listener) {
		data.deleteInBackground(new DeleteCallback() {
			@Override
			public void done(AVException arg0) {
				if(arg0 == null) {
					listener.onSuccess(data);
				} else {
					listener.onError(String.valueOf(arg0.getCode()), arg0.getMessage(), null);
				}
			}
		});
	}

	@Override
	public AVObject update(AVObject data) {
		try {
			data.save();
		} catch (AVException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(final AVObject data, final BDBaasObjListener<AVObject> listener) {
		data.saveInBackground(new SaveCallback() {
			@Override
			public void done(AVException arg0) {
				if(arg0 == null) {
					listener.onSuccess(data);
				} else {
					listener.onError(String.valueOf(arg0.getCode()), arg0.getMessage(), null);
				}
			}
		});
	}


}
