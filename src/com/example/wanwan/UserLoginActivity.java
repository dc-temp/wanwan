package com.example.wanwan;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class UserLoginActivity extends Activity implements OnClickListener {
	private SharedPreferences sp;
	private EditText user_name;
	private EditText password;
	private String aname;
	private String apassword;
	private String abname;
	private String abpassword;
	private Button btn_login;

@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.user_login);
	Button signup =   (Button) findViewById(R.id.signup);
	btn_login = (Button) findViewById(R.id.btn_login);
user_name = (EditText) findViewById(R.id.user_name);
password = (EditText) findViewById(R.id.password);
ImageView back = (ImageView) findViewById(R.id.back);
 sp=getSharedPreferences("login", 0);

back.setOnClickListener(this);
signup.setOnClickListener(this);
btn_login.setOnClickListener(this);
}

@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	switch (v.getId()) {
	case R.id.back:
		finish();
		break;
	case R.id.btn_login:
		aname = user_name.getText().toString().trim();
		apassword = password.getText().toString().trim();
		abname = sp.getString("gamename", "");
		abpassword = sp.getString("gamepw", "");
		if(aname.equals(abname)){
			if(apassword.equals(abpassword)){
				Toast.makeText(getApplication(), "登陆成功", 0).show();
				sp.edit().putString("ha", "5").commit();
				finish();

			}else{
				Toast.makeText(getApplication(), "请输入正确的密码", 1).show();

			}
			
		}else{
			Toast.makeText(getApplication(), "请输入有效的用户名", 1).show();
		}
		break;
	case R.id.signup:
		
		Intent  intent  = new Intent(UserLoginActivity.this,RegisterActivity.class);
		startActivity(intent);

		break;

	default:
		break;
	}
}
}
