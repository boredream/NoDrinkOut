package com.boredream.nodrinkout.bmob;

import android.content.Context;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.boredream.nodrinkout.entity.CoffeeInfo;
import com.boredream.nodrinkout.entity.CoffeeShop;
import com.boredream.nodrinkout.entity.InfoComment;
import com.boredream.nodrinkout.entity.InterActive;
import com.boredream.nodrinkout.entity.UserBean;
import com.boredream.nodrinkout.utils.CommonConstants;

public class BmobApi {
	
	////////////////////////////// 个人 //////////////////////////////
	
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
	
	////////////////////////////// 咖啡店 //////////////////////////////
	
	/**
	 * 查询咖啡店(根据咖啡店名模糊查询)
	 * @param context
	 * @param nameLike 模糊查询的咖啡店名
	 * @param listener
	 */
	public static void queryShopsNameLike(Context context, String nameLike, FindListener<CoffeeShop> listener) {
		BmobQuery<CoffeeShop> query = new BmobQuery<CoffeeShop>();
		query.include("user");
		query.addWhereContains("name", nameLike);
		query.findObjects(context, listener);
	}
	
	
	/**
	 * 查询咖啡店(根据全部条件过滤,不需要改条件时传null)
	 * @param context
	 * @param order
	 * @param page 
	 * @param listener
	 */
	public static void queryShopsWhere(Context context,
			String order, int page, FindListener<CoffeeShop> listener) {
		
		BmobQuery<CoffeeShop> query = new BmobQuery<CoffeeShop>();
		if(order != null) {
			query.order(order);
		}
		query.include("user,shop");
		query.setLimit(CommonConstants.COUNT_PER_PAGE);
		query.setSkip((page - 1) * CommonConstants.COUNT_PER_PAGE);
		query.findObjects(context, listener);
	}
	
	////////////////////////////// 图文状态 //////////////////////////////

//	/**
//	 * 查询全部图文状态
//	 * @param context
//	 * @param listener
//	 */
//	public static void queryAllInfos(Context context, FindListener<CoffeeInfo> listener) {
//		BmobQuery<CoffeeInfo> query = new BmobQuery<CoffeeInfo>();
//		query.include("user,shop");
//		query.findObjects(context, listener);
//	}
//	
//	/**
//	 * 查询全部图文状态(根据指定条件排序)
//	 * @param context
//	 * @param order 排序条件,前缀加-时为倒序
//	 * @param listener
//	 */
//	public static void queryInfosEqualAndOrderBy(Context context, String order, 
//			String equalKey, String equalValue, FindListener<CoffeeInfo> listener) {
//		BmobQuery<CoffeeInfo> query = new BmobQuery<CoffeeInfo>();
//		query.include("user,shop");
//		query.order(order);
//		query.addWhereEqualTo(equalKey, equalValue);
//		query.findObjects(context, listener);
//	}
//	
//	/**
//	 * 查询某个咖啡店所属全部图文状态
//	 * @param context
//	 * @param listener
//	 */
//	public static void queryShopInfos(Context context, CoffeeShop shop, 
//			FindListener<CoffeeInfo> listener) {
//		BmobQuery<CoffeeInfo> query = new BmobQuery<CoffeeInfo>();
//		query.include("user,shop");
//		query.addWhereEqualTo("shop", shop);
//		query.findObjects(context, listener);
//	}
//	
//	/**
//	 * 查询某个咖啡店所属全部图文状态(根据指定条件排序)
//	 * @param context
//	 * @param order 排序条件,前缀加-时为倒序
//	 * @param listener
//	 */
//	public static void queryShopInfosEqualAndOrderBy(Context context, String order, 
//			FindListener<CoffeeInfo> listener) {
//		BmobQuery<CoffeeInfo> query = new BmobQuery<CoffeeInfo>();
//		query.include("user,shop");
//		query.order(order);
//		query.findObjects(context, listener);
//	}
	
	/**
	 * 发表某个咖啡店所属全部图文状态
	 * @param context
	 */
	public static void insertInfo(final Context context, final CoffeeInfo info,
			final UpdateSimpleListener updateListener) {
		info.save(context, new SaveSimpleListener(context, null){

			@Override
			public void onFailure(int arg0, String arg1) {
				updateListener.onFailure(arg0, arg1);
			}

			@Override
			public void onSuccess() {
				super.onSuccess();
				
				// 图文状态发表成功后更新所属shop的图文数量
				CoffeeShop shop = info.getShop();
				shop.increment("infoCount");
				shop.update(context, updateListener);
			}
			
		});
	}
	
	/**
	 * 查询图文状态(根据全部条件过滤,不需要改条件时传null)
	 * @param context
	 * @param shop
	 * @param order
	 * @param page 
	 * @param listener
	 */
	public static void queryInfosWhere(Context context, CoffeeShop shop,
			String order, int page, FindListener<CoffeeInfo> listener) {
		
		BmobQuery<CoffeeInfo> query = new BmobQuery<CoffeeInfo>();
		if(order != null) {
			query.order(order);
		}
		if(shop != null) {
			query.addWhereEqualTo("shop", shop);
		}
		query.include("user,shop");
		query.setLimit(CommonConstants.COUNT_PER_PAGE);
		query.setSkip((page - 1) * CommonConstants.COUNT_PER_PAGE);
		query.findObjects(context, listener);
	}

	////////////////////////////// 评论 //////////////////////////////
	
	/**
	 * 针对某个状态发表评论
	 * 
	 * @param context
	 * @param info
	 * @param listener
	 */
	public static void insertComment(final Context context, final CoffeeInfo info, String comment,
			final UpdateSimpleListener listener) {
		final InfoComment infoComment = new InfoComment();
		infoComment.setContent(comment);
		infoComment.setInfo(info);
		infoComment.setUser(UserBean.getCurrentUser(context, UserBean.class));
		infoComment.save(context, new SaveSimpleListener(context, null){

			@Override
			public void onSuccess() {
				super.onSuccess();
				
				// 评论发表成功后更新所属info的评论数
				info.increment("commentCount");
				info.update(context, listener);
			}

			@Override
			public void onFailure(int arg0, String arg1) {
				listener.onFailure(arg0, arg1);
			}
			
		});
	}
	
	//////////////////////////////互动 //////////////////////////////
	
	/**
	 * 点赞图文状态
	 * 
	 * @param context
	 * @param info
	 * @param listener
	 */
	public static void likeInfo(final Context context, final CoffeeInfo info,
			final UpdateSimpleListener listener) {
		final InterActive interActive = new InterActive();
		interActive.setUser(UserBean.getCurrentUser(context, UserBean.class));
		interActive.setType(1);
		interActive.setTarId(info.getObjectId());
		interActive.save(context, new SaveSimpleListener(context, null){
			
			@Override
			public void onSuccess() {
				super.onSuccess();
				
				// 关注成功后更新所属info的关注数
				info.increment("likeCount");
				info.update(context, listener);
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				listener.onFailure(arg0, arg1);
			}
			
		});
	}
	
	/**
	 * 关注店面
	 * 
	 * @param context
	 * @param shop
	 * @param listener
	 */
	public static void followShop(final Context context, final CoffeeShop shop,
			final UpdateSimpleListener listener) {
		final InterActive interActive = new InterActive();
		interActive.setUser(UserBean.getCurrentUser(context, UserBean.class));
		interActive.setType(2);
		interActive.setTarId(shop.getObjectId());
		interActive.save(context, new SaveSimpleListener(context, null){

			@Override
			public void onSuccess() {
				super.onSuccess();
				
				// 关注成功后更新所属info的关注数
				shop.increment("followCount");
				shop.update(context, listener);
			}

			@Override
			public void onFailure(int arg0, String arg1) {
				listener.onFailure(arg0, arg1);
			}
			
		});
	}
	
	/**
	 * 点赞评论
	 * 
	 * @param context
	 * @param info
	 * @param listener
	 */
	public static void likeComment(final Context context, final InfoComment comment,
			final UpdateSimpleListener listener) {
		final InterActive interActive = new InterActive();
		interActive.setUser(UserBean.getCurrentUser(context, UserBean.class));
		interActive.setType(3);
		interActive.setTarId(comment.getObjectId());
		interActive.save(context, new SaveSimpleListener(context, null){
			
			@Override
			public void onSuccess() {
				super.onSuccess();
				
				// 关注成功后更新所属info的关注数
				comment.increment("likeCount");
				comment.update(context, listener);
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				listener.onFailure(arg0, arg1);
			}
			
		});
	}
	
}
