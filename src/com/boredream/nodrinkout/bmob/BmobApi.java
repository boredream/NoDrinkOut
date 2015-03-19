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
	
	////////////////////////////// ���� //////////////////////////////
	
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
	
	////////////////////////////// ���ȵ� //////////////////////////////
	
	/**
	 * ��ѯ���ȵ�(���ݿ��ȵ���ģ����ѯ)
	 * @param context
	 * @param nameLike ģ����ѯ�Ŀ��ȵ���
	 * @param listener
	 */
	public static void queryShopsNameLike(Context context, String nameLike, FindListener<CoffeeShop> listener) {
		BmobQuery<CoffeeShop> query = new BmobQuery<CoffeeShop>();
		query.include("user");
		query.addWhereContains("name", nameLike);
		query.findObjects(context, listener);
	}
	
	
	/**
	 * ��ѯ���ȵ�(����ȫ����������,����Ҫ������ʱ��null)
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
	
	////////////////////////////// ͼ��״̬ //////////////////////////////

//	/**
//	 * ��ѯȫ��ͼ��״̬
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
//	 * ��ѯȫ��ͼ��״̬(����ָ����������)
//	 * @param context
//	 * @param order ��������,ǰ׺��-ʱΪ����
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
//	 * ��ѯĳ�����ȵ�����ȫ��ͼ��״̬
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
//	 * ��ѯĳ�����ȵ�����ȫ��ͼ��״̬(����ָ����������)
//	 * @param context
//	 * @param order ��������,ǰ׺��-ʱΪ����
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
	 * ����ĳ�����ȵ�����ȫ��ͼ��״̬
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
				
				// ͼ��״̬����ɹ����������shop��ͼ������
				CoffeeShop shop = info.getShop();
				shop.increment("infoCount");
				shop.update(context, updateListener);
			}
			
		});
	}
	
	/**
	 * ��ѯͼ��״̬(����ȫ����������,����Ҫ������ʱ��null)
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

	////////////////////////////// ���� //////////////////////////////
	
	/**
	 * ���ĳ��״̬��������
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
				
				// ���۷���ɹ����������info��������
				info.increment("commentCount");
				info.update(context, listener);
			}

			@Override
			public void onFailure(int arg0, String arg1) {
				listener.onFailure(arg0, arg1);
			}
			
		});
	}
	
	//////////////////////////////���� //////////////////////////////
	
	/**
	 * ����ͼ��״̬
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
				
				// ��ע�ɹ����������info�Ĺ�ע��
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
	 * ��ע����
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
				
				// ��ע�ɹ����������info�Ĺ�ע��
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
	 * ��������
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
				
				// ��ע�ɹ����������info�Ĺ�ע��
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
