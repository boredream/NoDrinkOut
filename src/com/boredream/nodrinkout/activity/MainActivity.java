//package com.boredream.nodrinkout.activity;
//
//import java.util.List;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v4.view.ViewPager;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import com.boredream.nodrinkout.BaseActivity;
//import com.boredream.nodrinkout.R;
//import com.boredream.nodrinkout.adapter.RecInfoAdapter;
//import com.boredream.nodrinkout.adapter.RecVpAdapter;
//import com.boredream.nodrinkout.bmob.BmobApi;
//import com.boredream.nodrinkout.bmob.FindSimpleListener;
//import com.boredream.nodrinkout.entity.InfoRecommend;
//
//public class MainActivity extends BaseActivity implements OnClickListener {
//	private TextView tv_title;
//	private ViewPager vp_gallery;
//	private TextView tv_bigger;
//	private TextView tv_zishi;
//	private TextView tv_huodong;
//	private TextView tv_dian;
//	private ListView lv_jingxuan;
//
//	private RecInfoAdapter recJxAdapter;
//	private RecVpAdapter recVpAdapter;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//
//		setContentView(R.layout.activity_main);
//		initView();
//		
//		progressDialog.show();
//
//	}
//
//	private void initView() {
//		tv_title = (TextView) findViewById(R.id.tv_title);
//		vp_gallery = (ViewPager) findViewById(R.id.vp_gallery);
//		tv_bigger = (TextView) findViewById(R.id.tv_bigger);
//		tv_zishi = (TextView) findViewById(R.id.tv_zishi);
//		tv_huodong = (TextView) findViewById(R.id.tv_huodong);
//		tv_dian = (TextView) findViewById(R.id.tv_dian);
//		lv_jingxuan = (ListView) findViewById(R.id.lv_jingxuan);
//		
//		tv_bigger.setOnClickListener(this);
//		tv_zishi.setOnClickListener(this);
//		tv_huodong.setOnClickListener(this);
//		tv_dian.setOnClickListener(this);
//		lv_jingxuan.setOnItemClickListener(new OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				InfoRecommend recommend = recJxAdapter.getItem(position);
//				intent2detail(recommend);
//			}
//		});
//	}
//	
//	private void intent2detail(InfoRecommend recommend) {
//		Intent intent = new Intent(MainActivity.this, DetailActivity.class);
//		intent.putExtra("info", recommend.getInfo());
//		startActivity(intent);
//	}
//	
//	private void intent2list(int cateId) {
//		Intent intent = new Intent(MainActivity.this, InfoListActivity.class);
//		intent.putExtra("cateId", cateId);
//		startActivity(intent);
//	}
//
//	@Override
//	public void onClick(View v) {
//		switch (v.getId()) {
//		case R.id.tv_bigger:
//			intent2list(1);
//			break;
//		case R.id.tv_zishi:
//			intent2list(2);
//			break;
//		case R.id.tv_huodong:
//			intent2list(3);
//			break;
//		case R.id.tv_dian:
//			intent2list(4);
//			break;
//		default:
//			break;
//		}
//	}
//
//}
