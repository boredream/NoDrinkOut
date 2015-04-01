package com.boredream.nodrinkout.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.boredream.nodrinkout.BaseActivity;
import com.boredream.nodrinkout.BaseApplication;
import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.adapter.InfoAdapter;
import com.boredream.nodrinkout.bmob.BmobApi;
import com.boredream.nodrinkout.entity.CoffeeInfo;
import com.boredream.nodrinkout.entity.CoffeeShop;
import com.boredream.nodrinkout.listener.FindSimpleListener;
import com.boredream.nodrinkout.utils.ViewUtils;
import com.boredream.nodrinkout.view.Pull2RefreshListView;

public class ShopDetailActivity extends BaseActivity implements OnClickListener {

	private TextView include_tv_subhead;
	private View include_item_shop_content;
	private ImageView iv_image;
	private TextView tv_subhead;
	private TextView tv_body;
	private ImageView iv_collect;
	private TextView tv_collect;

	private Pull2RefreshListView plv_shop;

	private LinearLayout ll_checkin_bottom;
	private ImageView iv_checkin_bottom;
	private TextView tv_checkin_bottom;
	private LinearLayout ll_sendinfo_bottom;
	private ImageView iv_sendinfo_bottom;
	private TextView tv_sendinfo_bottom;
	private LinearLayout ll_collect_bottom;
	private ImageView iv_collect_bottom;
	private TextView tv_collect_bottom;

	private List<CoffeeInfo> infos;
	private InfoAdapter adapter;
	private CoffeeShop shop;

	private boolean hasLiked;
	private int curPage = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_shopdetail);

		initView();

		shop = (CoffeeShop) intent.getSerializableExtra("shop");

		setData();

		loadInfos();
		loadLikeState();
	}

	private void initView() {
		ViewUtils.initTitleBack(this, "咖店详情", simpleOnClickListener);

		include_tv_subhead = (TextView) View.inflate(this, R.layout.tv_subhead,
				null);
		include_item_shop_content = View.inflate(this,
				R.layout.include_item_shop_content, null);
		include_item_shop_content.setBackgroundResource(R.color.white);

		iv_image = (ImageView) include_item_shop_content
				.findViewById(R.id.iv_image);
		tv_subhead = (TextView) include_item_shop_content
				.findViewById(R.id.tv_subhead);
		tv_body = (TextView) include_item_shop_content
				.findViewById(R.id.tv_body);
		iv_collect = (ImageView) include_item_shop_content
				.findViewById(R.id.iv_collect);
		tv_collect = (TextView) include_item_shop_content
				.findViewById(R.id.tv_collect);

		plv_shop = (Pull2RefreshListView) findViewById(R.id.plv_shop);

		ll_checkin_bottom = (LinearLayout) findViewById(R.id.ll_checkin_bottom);
		iv_checkin_bottom = (ImageView) findViewById(R.id.iv_checkin_bottom);
		tv_checkin_bottom = (TextView) findViewById(R.id.tv_checkin_bottom);
		ll_sendinfo_bottom = (LinearLayout) findViewById(R.id.ll_sendinfo_bottom);
		iv_sendinfo_bottom = (ImageView) findViewById(R.id.iv_sendinfo_bottom);
		tv_sendinfo_bottom = (TextView) findViewById(R.id.tv_sendinfo_bottom);
		ll_collect_bottom = (LinearLayout) findViewById(R.id.ll_collect_bottom);
		iv_collect_bottom = (ImageView) findViewById(R.id.iv_collect_bottom);
		tv_collect_bottom = (TextView) findViewById(R.id.tv_collect_bottom);
		ll_checkin_bottom.setOnClickListener(this);
		ll_sendinfo_bottom.setOnClickListener(this);
		ll_collect_bottom.setOnClickListener(this);

	}

	private void setData() {
		imageLoader.displayImage(shop.getImgUrl(), iv_image);
		tv_subhead.setText(shop.getName());
		tv_body.setText(shop.getAddress());

		infos = new ArrayList<CoffeeInfo>();
		adapter = new InfoAdapter(this, infos);
		plv_shop.setAdapter(adapter);
		include_tv_subhead.setText("咖讯");
		plv_shop.getRefreshableView().addHeaderView(include_item_shop_content);
		plv_shop.getRefreshableView().addHeaderView(include_tv_subhead);
	}

	private void setLikeState() {
		if (hasLiked) {
			iv_collect_bottom.setColorFilter(getResources().getColor(
					R.color.red));
			tv_collect_bottom.setText("已赞");
		} else {
			iv_collect_bottom.clearColorFilter();
			tv_collect_bottom.setText("点赞");
		}
	}

	private void sendLike() {
		if (hasLiked) {
			showToast("已经赞过了");
			return;
		}

		// progressDialog.show();
		// BmobApi.likeInfo(this, info,
		// new UpdateSimpleListener(this, progressDialog){
		// @Override
		// public void onSuccess() {
		// super.onSuccess();
		//
		// hasLiked = true;
		// setLikeState();
		// }
		// });
	}

	private void loadInfos() {
		progressDialog.show();
		BmobApi.queryInfosWhere(this, shop, null, null, curPage,
				new FindSimpleListener<CoffeeInfo>(this, progressDialog) {

					@Override
					public void onSuccess(List<CoffeeInfo> arg0) {
						super.onSuccess(arg0);

						infos.clear();
						infos.addAll(arg0);
						adapter.notifyDataSetChanged();
					}

				});
	}

	private void loadLikeState() {
		// BmobApi.isLikeInfo(this, info,
		// new FindSimpleListener<InterActive>(this, progressDialog){
		//
		// @Override
		// public void onSuccess(List<InterActive> arg0) {
		// super.onSuccess(arg0);
		//
		// hasLiked = arg0.size() > 0;
		// setLikeState();
		// }
		// });
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_checkin_bottom:
			System.out.println(BaseApplication.curLocation + " : " + shop.getLocation());
			break;
		case R.id.ll_sendinfo_bottom:

			break;
		case R.id.ll_collect_bottom:
			sendLike();
			break;
		default:
			break;
		}
	}
}
