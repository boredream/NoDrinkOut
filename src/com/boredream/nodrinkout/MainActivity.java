package com.boredream.nodrinkout;

import android.os.Bundle;

import com.boredream.baas.BDBaaS;
import com.boredream.baas.BDBaseObj;
import com.boredream.baas.abs.BDAbsObjHelper;
import com.boredream.baas.abs.BDObjListener;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		BDAbsObjHelper<BDBaseObj> helper = BDBaaS.getObjHelper(this);
		
		BDBaseObj data = new BDBaseObj();
		helper.save(data, new BDObjListener<BDBaseObj>() {
			@Override
			public void onSuccess(BDBaseObj data) {
				showToast("³É¹¦");
			}
			
			@Override
			public void onError(String code, String msg, Object errorEntity) {
				showToast(msg);
			}
		});
	}
	
}
