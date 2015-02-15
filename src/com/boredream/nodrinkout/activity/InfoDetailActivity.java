package com.boredream.nodrinkout.activity;

import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.bmob.BmobProFile;
import com.bmob.btp.callback.DownloadListener;
import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.adapter.InfoCommentAdapter;
import com.boredream.nodrinkout.entity.InfoBean;
import com.boredream.nodrinkout.entity.InfoComment;

public class InfoDetailActivity extends Activity {
	private TextView infodetail_tv_title;
	private ImageView infodetail_iv;
	private TextView infodetail_tv_content;
	private ListView infodetail_lv;

	private InfoBean info;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_infodetail);
		
		info = (InfoBean) getIntent().getSerializableExtra("info");
		
		initView();
		
		BmobQuery<InfoComment> query = new BmobQuery<InfoComment>();
		query.addWhereEqualTo("info", info);
		query.include("info,user");
		query.findObjects(this, new FindListener<InfoComment>() {
			@Override
			public void onSuccess(List<InfoComment> arg0) {
				infodetail_lv.setAdapter(new InfoCommentAdapter(InfoDetailActivity.this, arg0));
			}
			
			@Override
			public void onError(int arg0, String arg1) {
				
			}
		});
	}
	

	private void initView() {
		infodetail_tv_title = (TextView) findViewById(R.id.infodetail_tv_title);
		infodetail_iv = (ImageView) findViewById(R.id.infodetail_iv);
		infodetail_tv_content = (TextView) findViewById(R.id.infodetail_tv_content);
		infodetail_lv = (ListView) findViewById(R.id.infodetail_lv);
		
		infodetail_tv_title.setText(info.getTitle());
		infodetail_tv_content.setText(info.getContent());
		BmobProFile.getInstance(this).download(info.getImgName(), new DownloadListener() {
			@Override
			public void onError(int arg0, String arg1) {
				
			}
			
			@Override
			public void onSuccess(String arg0) {
				Bitmap bm = BitmapFactory.decodeFile(arg0);
				infodetail_iv.setImageBitmap(bm);
			}
			
			@Override
			public void onProgress(String arg0, int arg1) {
				
			}
		});
	}
}
