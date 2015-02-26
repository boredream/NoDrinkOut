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
	 * 查询个人信息
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
	 * 根据类型查资讯
	 * 
	 * @param context
	 * @param cateId
	 *            资讯类型 1-bigger
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
	 * 根据类型查资讯(多页)
	 * 
	 * @param context
	 * @param cateId
	 *            资讯类型 1-bigger
	 * @param page
	 *            页数
	 * @param limit
	 *            每页显示数量
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
	 * 根据推荐类型查资讯
	 * 
	 * @param context
	 * @param recType
	 *            推荐类型 1-首页海报推荐 2-首页热门推荐
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
	 * 获取某个资讯的评论
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
		
//		// 查询这个微博的所有评论,注意：这里的第一个参数是Weibo表中的comments字段
//		query.addWhereRelatedTo("comments", new BmobPointer(info));		
//		query.include("author");
	}
	
	/**
	 * 针对某个资讯发表评论
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
	 * 更新评论所属的资讯信息
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
	 * 针对某个资讯发表评论
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
	 * 更新点赞所属的资讯信息
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
