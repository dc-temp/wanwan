package com.example.wanwan;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterActivity extends  Activity implements OnClickListener {
private ImageView back;
private EditText user_name;
private EditText user_nickname;
private EditText password;
private String name;
private String email;
private String passwords;
private SharedPreferences sp;

@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.user_signup);
	 sp=getSharedPreferences("login", 0);

	back = (ImageView) findViewById(R.id.back);
	user_name = (EditText) findViewById(R.id.user_name);
	user_nickname = (EditText) findViewById(R.id.user_nickname);
	password = (EditText) findViewById(R.id.password);
	Button btn_signup =  (Button) findViewById(R.id.btn_signup);

	btn_signup.setOnClickListener(this);
	back.setOnClickListener(this);
}

@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	switch (v.getId()) {
	case R.id.btn_signup:
		name = user_name.getText().toString().trim();
		email = user_nickname.getText().toString().trim();
		passwords = password.getText().toString().trim();
		if(!MyChinaUtil.isEmail(name)){
			Toast.makeText(getApplication(), "邮箱格式错误", 1).show();
		}else{
		if(!MyChinaUtil.isMobileNO(email)){
			Toast.makeText(getApplication(), "请输入正确的手机号", 1).show();

		}else{
			if(passwords!=null){
				sp.edit().putString("gamename", name).commit();
				sp.edit().putString("gamepw", passwords).commit();
				finish();
			}else{
				Toast.makeText(getApplication(), "密码为空", 1).show();

			}
		}
		}
		
		break;
	case R.id.back:
		
			finish();
		
		break;

	default:
		break;
	}
}
}
