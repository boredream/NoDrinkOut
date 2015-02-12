package com.boredream.nodrinkout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends BaseActivity implements OnClickListener {
	private EditText et_username;
	private EditText et_psw;
	private Button btn_register;
	private Button btn_login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);
		
		initView();
	}

	private void initView() {
		et_username = (EditText) findViewById(R.id.login_et_username);
		et_psw = (EditText) findViewById(R.id.login_et_psw);
		btn_register = (Button) findViewById(R.id.login_btn_register);
		btn_register.setOnClickListener(this);
		btn_login = (Button) findViewById(R.id.login_btn_login);
		btn_login.setOnClickListener(this);
		
		et_username.setText("boredream");
		et_psw.setText("58421314");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_btn_register:
			showToast("≤‚ ‘£¨÷±Ω””√ƒ¨»œ’À∫≈√‹¬Îµ«¬Ωº¥ø…");
//			BmobUser rUser = new BmobUser();
//			rUser.setUsername(et_username.getText().toString());
//			rUser.setPassword(et_psw.getText().toString());
//			rUser.signUp(this, new SaveListener() {
//				@Override
//				public void onSuccess() {
//					showToast("◊¢≤·≥…π¶");
//				}
//				
//				@Override
//				public void onFailure(int arg0, String arg1) {
//					showToast(arg1);
//				}
//			});
			break;
		case R.id.login_btn_login:
			BmobUser user = new BmobUser();
			user.setUsername(et_username.getText().toString());
			user.setPassword(et_psw.getText().toString());
			user.login(this, new SaveListener() {
				@Override
				public void onSuccess() {
					Intent intent = new Intent(LoginActivity.this, MainActivity.class);
					startActivity(intent);
				}
				
				@Override
				public void onFailure(int arg0, String arg1) {
					showToast(arg1);
				}
			});
			break;

		default:
			break;
		}
	}

}
