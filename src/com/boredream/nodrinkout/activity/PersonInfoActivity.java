package com.boredream.nodrinkout.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.boredream.nodrinkout.BaseActivity;
import com.boredream.nodrinkout.R;

public class PersonInfoActivity extends BaseActivity {

	private ImageView iv_avatar;
	private TextView tv_name;
	private RadioButton rb_male;
	private RadioButton rb_female;
	private TextView tv_personal_detail;
	private TextView tv_age;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personinfo);
		initView();
		setData();
	}

	private void initView(){
		iv_avatar = (ImageView)findViewById(R.id.iv_avatar);
		tv_name = (TextView)findViewById(R.id.tv_name);
		rb_male = (RadioButton)findViewById(R.id.rb_male);
		rb_female = (RadioButton)findViewById(R.id.rb_female);
		tv_personal_detail = (TextView)findViewById(R.id.tv_personal_detail);
		tv_age = (TextView)findViewById(R.id.tv_age);
	}


	private void setData() {
		imageLoader.displayImage(user.getAvatarUrl(), iv_avatar);
		tv_name.setText(user.getUsername());
		tv_personal_detail.setText(user.getDetail());
		tv_age.setText(user.getAge()+"");
	}

}
