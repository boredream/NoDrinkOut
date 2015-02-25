package com.boredream.nodrinkout.bmob;

import android.content.Context;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.boredream.nodrinkout.entity.InfoBean;
import com.boredream.nodrinkout.entity.InfoComment;
import com.boredream.nodrinkout.entity.InfoRecommend;
import com.boredream.nodrinkout.entity.InterActive;
import com.boredream.nodrinkout.entity.UserBean;

public class BmobApi {
	// 模块 功能
	// 用户 注册
	// 登陆
	// 查询个人信息
	// 资讯 根据类型查资讯
	// 根据推荐类型查资讯
	// 多页加载资讯
	// 评论 获取某个资讯的评论
	// 发表评论
	// 点赞 点赞

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

	/**
	 * 根据类型查资讯
	 * 
	 * @param context
	 * @param category
	 *            资讯类型 1-bigger
	 * @param listener
	 */
	public static void queryInfoByCategory(Context context, int category,
			FindListener<InfoBean> listener) {
		BmobQuery<InfoBean> query = new BmobQuery<InfoBean>();
		query.include("user");
		query.include("interAct");
		query.addWhereEqualTo("category", category);
		query.findObjects(context, listener);
	}

	/**
	 * 根据类型查资讯(多页)
	 * 
	 * @param context
	 * @param category
	 *            资讯类型 1-bigger
	 * @param page
	 *            页数
	 * @param limit
	 *            每页显示数量
	 * @param listener
	 */
	public static void queryInfoByCategory(Context context, int category,
			int page, int limit, FindListener<InfoBean> listener) {
		BmobQuery<InfoBean> query = new BmobQuery<InfoBean>();
		query.include("user");
		query.include("interAct");
		query.addWhereEqualTo("category", category);
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
	}
	
	/**
	 * 针对某个资讯发表评论
	 * 
	 * @param context
	 * @param info
	 * @param listener
	 */
	public static void insertCommentOfInfo(Context context, InfoBean info, String comment,
			SaveSimpleListener listener) {
		InfoComment infoComment = new InfoComment();
		infoComment.getInterAct().increment("count");
		infoComment.setContent(comment);
		infoComment.setUser((UserBean) UserBean.getCurrentUser(context));
		infoComment.setInfo(info);
		infoComment.save(context, listener);
	}
}
