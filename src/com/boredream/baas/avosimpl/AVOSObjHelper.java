//package com.boredream.baas.avosimpl;
//
//import com.avos.avoscloud.AVException;
//import com.avos.avoscloud.DeleteCallback;
//import com.avos.avoscloud.SaveCallback;
//import com.boredream.baas.BDBaseObj;
//import com.boredream.baas.abs.BDAbsObjHelper;
//import com.boredream.baas.abs.BDObjListener;
//
///**
// * AVOS的数据操作类实现
// */
//public class AVOSObjHelper<T> extends BDAbsObjHelper<BDBaseObj>{
//
//	@Override
//	public void save(BDBaseObj data) {
//		try {
//			data.save();
//		} catch (AVException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void save(final BDBaseObj data, final BDObjListener<BDBaseObj> listener) {
//		data.saveInBackground(new SaveCallback() {
//			@Override
//			public void done(AVException arg0) {
//				if(arg0 == null) {
//					listener.onSuccess(data);
//				} else {
//					listener.onError(String.valueOf(arg0.getCode()), arg0.getMessage(), null);
//				}
//			}
//		});
//	}
//
//	@Override
//	public void delete(BDBaseObj data) {
//		try {
//			data.delete();
//		} catch (AVException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void delete(final BDBaseObj data, final BDObjListener<BDBaseObj> listener) {
//		data.deleteInBackground(new DeleteCallback() {
//			@Override
//			public void done(AVException arg0) {
//				if(arg0 == null) {
//					listener.onSuccess(data);
//				} else {
//					listener.onError(String.valueOf(arg0.getCode()), arg0.getMessage(), null);
//				}
//			}
//		});
//	}
//
//	@Override
//	public void update(BDBaseObj data) {
//		try {
//			data.save();
//		} catch (AVException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void update(final BDBaseObj data, final BDObjListener<BDBaseObj> listener) {
//		data.saveInBackground(new SaveCallback() {
//			@Override
//			public void done(AVException arg0) {
//				if(arg0 == null) {
//					listener.onSuccess(data);
//				} else {
//					listener.onError(String.valueOf(arg0.getCode()), arg0.getMessage(), arg0);
//				}
//			}
//		});
//	}
//
//
//}
