package com.boredream.nodrinkout.bmob;

import android.content.Context;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

import com.boredream.nodrinkout.entity.InfoBean;
import com.boredream.nodrinkout.entity.InfoComment;
import com.boredream.nodrinkout.entity.InfoLike;
import com.boredream.nodrinkout.entity.InfoRecommend;
import com.boredream.nodrinkout.entity.UserBean;
import com.boredream.nodrinkout.utils.Logger;

public class BmobApi {

	/**
	 * ��ѯ������Ϣ
	 * 
	 * @param context
	 * @param objectId
	 * @param listener
	 */
	public static void queryUserInfo(Context context, String objectId,
			FindListener<UserBean> listener) {
		BmobQuery<UserBean> query = new BmobQuery<UserBean>();
		query.addWhereEqualTo("objectId", objectId);
		query.findObjects(context, listener);
	}

	public static void insertInfo(Context context) {
		InfoBean info = new InfoBean();
		info.setTitle("title~");
		info.setCommentsCount(0);
		info.setLikesCount(0);
		info.setUser(UserBean.getCurrentUser(context, UserBean.class));
		
		info.save(context, new SaveListener() {
			@Override
			public void onSuccess() {
				Logger.show("DDD", "onSuccess");
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				Logger.show("DDD", "onFailure ... " + arg0 + ":" + arg1);
			}
		});
	}
	
	/**
	 * �������Ͳ���Ѷ
	 * 
	 * @param context
	 * @param cateId
	 *            ��Ѷ���� 1-bigger
	 * @param listener
	 */
	public static void queryInfoByCategory(Context context, int cateId,
			FindListener<InfoBean> listener) {
		BmobQuery<InfoBean> query = new BmobQuery<InfoBean>();
		query.include("user");
		query.include("comments");
		query.include("likes");
		query.addWhereEqualTo("cateId", cateId);
		query.findObjects(context, listener);
	}

	/**
	 * �������Ͳ���Ѷ(��ҳ)
	 * 
	 * @param context
	 * @param cateId
	 *            ��Ѷ���� 1-bigger
	 * @param page
	 *            ҳ��
	 * @param limit
	 *            ÿҳ��ʾ����
	 * @param listener
	 */
	public static void queryInfoByCategory(Context context, int cateId,
			int page, int limit, FindListener<InfoBean> listener) {
		BmobQuery<InfoBean> query = new BmobQuery<InfoBean>();
		query.include("user");
		query.include("comments");
		query.include("likes");
		query.addWhereEqualTo("cateId", cateId);
		query.setLimit(limit);
		query.setSkip(page * limit);
		query.findObjects(context, listener);
	}

	/**
	 * �����Ƽ����Ͳ���Ѷ
	 * 
	 * @param context
	 * @param recType
	 *            �Ƽ����� 1-��ҳ�����Ƽ� 2-��ҳ�����Ƽ�
	 * @param listener
	 */
	public static void queryRecomendInfo(Context context, int recType,
			FindListener<InfoRecommend> listener) {
		BmobQuery<InfoRecommend> query = new BmobQuery<InfoRecommend>();
		query.include("info");
		query.addWhereEqualTo("type", recType);
		query.findObjects(context, listener);
	}
	
	/**
	 * ��ȡĳ����Ѷ������
	 * 
	 * @param context
	 * @param info
	 * @param listener
	 */
	public static void queryCommentOfInfo(Context context, InfoBean info,
			FindListener<InfoComment> listener) {
		BmobQuery<InfoComment> query = new BmobQuery<InfoComment>();
		query.include("user");
		query.include("info");
		query.addWhereEqualTo("info", info);
		query.findObjects(context, listener);
		
//		// ��ѯ���΢������������,ע�⣺����ĵ�һ��������Weibo���е�comments�ֶ�
//		query.addWhereRelatedTo("comments", new BmobPointer(info));		
//		query.include("author");
	}
	
	/**
	 * ���ĳ����Ѷ��������
	 * 
	 * @param context
	 * @param info
	 * @param listener
	 */
	public static void insertComment(final Context context, final InfoBean info, String comment,
			final UpdateSimpleListener listener) {
		final InfoComment infoComment = new InfoComment();
		infoComment.setContent(comment);
		infoComment.setUser(UserBean.getCurrentUser(context, UserBean.class));
		infoComment.setInfo(info);
		infoComment.save(context, new SaveSimpleListener(context, null){

			@Override
			public void onSuccess() {
				super.onSuccess();
				updateCommentOfInfo(context, info, infoComment, listener);
			}
			
		});
	}
	
	/**
	 * ����������������Ѷ��Ϣ
	 * @param context
	 * @param info
	 * @param infoComment
	 * @param listener
	 */
	public static void updateCommentOfInfo(Context context, InfoBean info, InfoComment infoComment,
			UpdateSimpleListener listener){
		info.increment("commentsCount");
		info.update(context, listener);
	}
	
	
	/**
	 * ���ĳ����Ѷ��������
	 * 
	 * @param context
	 * @param info
	 * @param listener
	 */
	public static void insertLike(final Context context, final InfoBean info,
			final UpdateSimpleListener listener) {
		final InfoLike infoLike = new InfoLike();
		infoLike.setUser(UserBean.getCurrentUser(context, UserBean.class));
		infoLike.setInfo(info);
		infoLike.save(context, new SaveSimpleListener(context, null){

			@Override
			public void onSuccess() {
				super.onSuccess();
				updateLikeOfInfo(context, info, infoLike, listener);
			}
			
		});
	}
	
	/**
	 * ���µ�����������Ѷ��Ϣ
	 * @param context
	 * @param info
	 * @param infoLike
	 * @param listener
	 */
	public static void updateLikeOfInfo(Context context, InfoBean info, InfoLike infoLike,
			UpdateSimpleListener listener){
		info.increment("likesCount");
		info.update(context, listener);
	}
}
