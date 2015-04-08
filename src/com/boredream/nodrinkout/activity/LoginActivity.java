package com.boredream.nodrinkout.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import cn.bmob.v3.listener.SaveListener;

import com.boredream.nodrinkout.BaseActivity;
import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.entity.UserBean;

public class LoginActivity extends BaseActivity implements OnClickListener {
	private EditText et_username;
	private EditText et_psw;
	private TextView tx_register;
	private TextView tx_login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);
		
		initView();
	}

	private void initView() {
		et_username = (EditText) findViewById(R.id.et_zhanghao);
		et_psw = (EditText) findViewById(R.id.et_psw);
		tx_register = (TextView) findViewById(R.id.tx_register);
		tx_register.setOnClickListener(this);
		tx_login = (TextView) findViewById(R.id.sign_in);
		tx_login.setOnClickListener(this);
		
		et_username.setText("loveghs");
		et_psw.setText("58421314");
		
		et_username.addTextChangedListener(new TextWatcher() {
			
			CharSequence beforeS;
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				System.out.println(beforeS);
				System.out.println(s);
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				beforeS = s;
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tx_register:
			UserBean rUser = new UserBean();
			rUser.setUsername(et_username.getText().toString());
			rUser.setPassword(et_psw.getText().toString());
			rUser.setAge(24);
			rUser.signUp(this, new SaveListener() {
				@Override
				public void onSuccess() {
					showToast("×¢²á³É¹¦");
				}
				
				@Override
				public void onFailure(int arg0, String arg1) {
					showToast(arg1);
				}
			});
			break;
		case R.id.sign_in:
			UserBean user = new UserBean();
			user.setUsername(et_username.getText().toString());
			user.setPassword(et_psw.getText().toString());
			user.login(this, new SaveListener() {
				@Override
				public void onSuccess() {
					Intent intent = new Intent(LoginActivity.this, MainTabActivity.class);
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
