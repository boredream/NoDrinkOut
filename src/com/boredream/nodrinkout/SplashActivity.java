package com.boredream.nodrinkout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.danmu.DanMu;
import com.boredream.nodrinkout.danmu.DanMuLayout;

public class SplashActivity extends Activity {

	private DanMuLayout dmlLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		Random random = new Random();
		List<DanMu> dms = new ArrayList<DanMu>();
		String[] dmContents = getResources().getStringArray(R.array.danmus);
		for(String content : dmContents) {
			DanMu dm = new DanMu(random.nextInt(5000), content);
			dms.add(dm);
		}
		
		dmlLayout = (DanMuLayout) findViewById(R.id.splash_dm);
		dmlLayout.setDanMu(dms);
		dmlLayout.startShowDanMu();
		
		findViewById(R.id.splash_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dmlLayout.stopShowDanMu();
				
				Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
				startActivity(intent);
			}
		});
	}

}
