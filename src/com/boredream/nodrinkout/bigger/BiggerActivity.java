package com.boredream.nodrinkout.bigger;

import java.util.List;

import android.os.Bundle;
import android.widget.ListView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.boredream.nodrinkout.BaseActivity;
import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.info.InfoAdapter;
import com.boredream.nodrinkout.info.InfoBean;

public class BiggerActivity extends BaseActivity {
	
	private ListView lv_bigger;
	private InfoAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bigger);
		
		lv_bigger = (ListView) findViewById(R.id.lv_bigger);		
		progressDialog.show();
		
		BmobQuery<InfoBean> query = new BmobQuery<InfoBean>();
		query.findObjects(this, new FindListener<InfoBean>() {
			@Override
			public void onSuccess(List<InfoBean> arg0) {
				progressDialog.dismiss();
				adapter = new InfoAdapter(BiggerActivity.this, arg0);
				lv_bigger.setAdapter(adapter);
			}
			
			@Override
			public void onError(int arg0, String arg1) {
				progressDialog.dismiss();
				showToast(arg1);
			}
		});
	}
}
