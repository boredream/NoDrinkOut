package com.boredream.nodrinkout.bmob;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobGeoPoint;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

import com.bmob.BmobProFile;
import com.bmob.btp.callback.UploadBatchListener;
import com.boredream.nodrinkout.constants.CommonConstants;
import com.boredream.nodrinkout.entity.CoffeeInfo;
import com.boredream.nodrinkout.entity.CoffeeShop;
import com.boredream.nodrinkout.entity.StatusComment;
import com.boredream.nodrinkout.entity.InterActive;
import com.boredream.nodrinkout.entity.User;
import com.boredream.nodrinkout.listener.SaveSimpleListener;
import com.boredream.nodrinkout.utils.Logger;

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
			FindListener<User> listener) {
		BmobQuery<User> query = new BmobQuery<User>();
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
	public static void queryShopsNameLike(Context context, String nameLike, 
			FindListener<CoffeeShop> listener) {
		BmobQuery<CoffeeShop> query = new BmobQuery<CoffeeShop>();
		query.include("user");
		query.addWhereContains("name", nameLike);
		query.findObjects(context, listener);
	}
	
	/**
	 * 查询咖啡店(根据全部条件过滤,不需要改条件时传null, -1)
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
		if(page != -1) {
			query.setLimit(CommonConstants.COUNT_PER_PAGE);
			query.setSkip((page - 1) * CommonConstants.COUNT_PER_PAGE);
		}
		query.findObjects(context, listener);
	}
	
	/**
	 * 查询附近咖啡店(根据全部条件过滤,不需要改条件时传null, -1)
	 * @param context
	 * @param geo 中心点位置
	 * @param page 
	 * @param listener
	 */
	public static void queryShopsWhereNear(Context context,
			BmobGeoPoint geo, int page, FindListener<CoffeeShop> listener) {
		BmobQuery<CoffeeShop> query = new BmobQuery<CoffeeShop>();
		query.addWhereNear("location", geo);
		query.include("user,shop");
		if(page != -1) {
			query.setLimit(CommonConstants.COUNT_PER_PAGE);
			query.setSkip((page - 1) * CommonConstants.COUNT_PER_PAGE);
		}
		query.findObjects(context, listener);
	}
	
	public static void updateShopsBatch(Context context, List<BmobObject> shops,
			UpdateListener updateListener) {
		CoffeeShop shopUpdateBatch = new CoffeeShop();
		shopUpdateBatch.updateBatch(context, shops, updateListener);
	}
	
	////////////////////////////// 图文状态 //////////////////////////////

	/**
	 * 发表某个咖啡店所属全部图文状态,带图
	 * @param context
	 */
	public static void insertInfo(final Context context, final CoffeeInfo info,
			String[] filePaths, final UpdateListener updateListener) {
		
		if(filePaths != null && filePaths.length > 0) {
			uploadImage(context, filePaths, new UploadBatchListener() {
				@Override
				public void onError(int arg0, String arg1) {
					updateListener.onFailure(arg0, arg1);
				}
				
				@Override
				public void onSuccess(boolean arg0, String[] arg1, String[] arg2) {
					insertInfo(context, info, updateListener);
				}
				
				@Override
				public void onProgress(int arg0, int arg1, int arg2, int arg3) {
					Logger.show("DDD", "upload img onProgress " + arg0 + " : "
							+ arg1 + " : " + arg2 + " : " + arg3);
				}
			});
		} else {
			insertInfo(context, info, updateListener);
		}
	}
	
	/**
	 * 发表某个咖啡店所属全部图文状态
	 * @param context
	 */
	public static void insertInfo(final Context context, final CoffeeInfo info,
			final UpdateListener updateListener) {
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
	 * 
	 * <br>
	 * 注意: shop和user最多只有一者非空
	 * @param context
	 * @param shop
	 * @param user
	 * @param order
	 * @param page 
	 * @param listener
	 */
	public static void queryInfosWhere(Context context, CoffeeShop shop, User user,
			String order, int page, FindListener<CoffeeInfo> listener) {
		
		BmobQuery<CoffeeInfo> query = new BmobQuery<CoffeeInfo>();
		if(order != null) {
			query.order(order);
		}
		if(shop != null) {
			query.addWhereEqualTo("shop", shop);
		}
		if(shop != null) {
			query.addWhereEqualTo("user", user);
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
			final UpdateListener listener) {
		final StatusComment infoComment = new StatusComment();
		infoComment.setContent(comment);
		infoComment.setInfo(info);
		infoComment.setUser(User.getCurrentUser(context, User.class));
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
	
	public static void queryComments(Context context, CoffeeInfo info,
			FindListener<StatusComment> listener) {
		BmobQuery<StatusComment> query = new BmobQuery<StatusComment>();
		query.include("user,info");
		query.addWhereEqualTo("info", info);
		query.findObjects(context, listener);
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
			final UpdateListener listener) {
		final InterActive interActive = new InterActive();
		interActive.setUser(User.getCurrentUser(context, User.class));
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
	
	public static void isLikeInfo(Context context, CoffeeInfo info,
			FindListener<InterActive> listener) {
		BmobQuery<InterActive> query = new BmobQuery<InterActive>();
		
		BmobQuery<InterActive> query1 = new BmobQuery<InterActive>();
		query1.addWhereEqualTo("user", User.getCurrentUser(context, User.class));
		
		BmobQuery<InterActive> query2 = new BmobQuery<InterActive>();
		query2.addWhereEqualTo("type", 1);
		
		BmobQuery<InterActive> query3 = new BmobQuery<InterActive>();
		query3.addWhereEqualTo("tarId", info.getObjectId());
		
		List<BmobQuery<InterActive>> queries = new ArrayList<BmobQuery<InterActive>>();
		queries.add(query1);
		queries.add(query2);
		queries.add(query3);
		query.and(queries);
		
		query.findObjects(context, listener);
	}
	
	/**
	 * 关注店面
	 * 
	 * @param context
	 * @param shop
	 * @param listener
	 */
	public static void followShop(final Context context, final CoffeeShop shop,
			final UpdateListener listener) {
		final InterActive interActive = new InterActive();
		interActive.setUser(User.getCurrentUser(context, User.class));
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
		
	public static void likeComment(final Context context, final StatusComment comment,
			final UpdateListener listener) {
		final InterActive interActive = new InterActive();
		interActive.setUser(User.getCurrentUser(context, User.class));
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
	
	//////////////////////////////其他 //////////////////////////////
	public static void uploadImage(Context context, String[] filePaths, UploadBatchListener uploadBatchListener) {
		BmobProFile.getInstance(context).uploadBatch(filePaths, uploadBatchListener);
	}
}
