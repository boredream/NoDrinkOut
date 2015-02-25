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
	// ģ�� ����
	// �û� ע��
	// ��½
	// ��ѯ������Ϣ
	// ��Ѷ �������Ͳ���Ѷ
	// �����Ƽ����Ͳ���Ѷ
	// ��ҳ������Ѷ
	// ���� ��ȡĳ����Ѷ������
	// ��������
	// ���� ����

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

	/**
	 * �������Ͳ���Ѷ
	 * 
	 * @param context
	 * @param category
	 *            ��Ѷ���� 1-bigger
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
	 * �������Ͳ���Ѷ(��ҳ)
	 * 
	 * @param context
	 * @param category
	 *            ��Ѷ���� 1-bigger
	 * @param page
	 *            ҳ��
	 * @param limit
	 *            ÿҳ��ʾ����
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
	}
	
	/**
	 * ���ĳ����Ѷ��������
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
