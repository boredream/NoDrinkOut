package com.boredream.nodrinkout.bmob;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import android.content.Context;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindCallback;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

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
	public static void queryShopsNameLike(Context context, String nameLike, 
			FindListener<CoffeeShop> listener) {
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

	/**
	 * ����ĳ�����ȵ�����ȫ��ͼ��״̬
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
				
				// ͼ��״̬����ɹ����������shop��ͼ������
				CoffeeShop shop = info.getShop();
				shop.increment("infoCount");
				shop.update(context, updateListener);
			}
			
		});
	}
	
	/**
	 * ��ѯͼ��״̬(����ȫ����������,����Ҫ������ʱ��null)
	 * 
	 * <br>
	 * ע��: shop��user���ֻ��һ�߷ǿ�
	 * @param context
	 * @param shop
	 * @param user
	 * @param order
	 * @param page 
	 * @param listener
	 */
	public static void queryInfosWhere(Context context, CoffeeShop shop, UserBean user,
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

	////////////////////////////// ���� //////////////////////////////
	
	/**
	 * ���ĳ��״̬��������
	 * 
	 * @param context
	 * @param info
	 * @param listener
	 */
	public static void insertComment(final Context context, final CoffeeInfo info, String comment,
			final UpdateListener listener) {
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
	
	public static void queryComments(Context context, CoffeeInfo info,
			FindListener<InfoComment> listener) {
		BmobQuery<InfoComment> query = new BmobQuery<InfoComment>();
		query.include("user,info");
		query.addWhereEqualTo("info", info);
		query.findObjects(context, listener);
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
			final UpdateListener listener) {
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
	
	public static void isLikeInfo(Context context, CoffeeInfo info,
			FindListener<InterActive> listener) {
		BmobQuery<InterActive> query = new BmobQuery<InterActive>();
		
		BmobQuery<InterActive> query1 = new BmobQuery<InterActive>();
		query1.addWhereEqualTo("user", UserBean.getCurrentUser(context, UserBean.class));
		
		BmobQuery<InterActive> query2 = new BmobQuery<InterActive>();
		query2.addWhereEqualTo("type", "1");
		
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
	 * ��ע����
	 * 
	 * @param context
	 * @param shop
	 * @param listener
	 */
	public static void followShop(final Context context, final CoffeeShop shop,
			final UpdateListener listener) {
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
		
	public static void likeComment(final Context context, final InfoComment comment,
			final UpdateListener listener) {
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
