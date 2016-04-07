package com.example.wanwan;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalCenterActivity extends Activity implements OnClickListener {
	private FrameLayout back;
	private RelativeLayout center_my,layout_luobobi,layout_renwuliebiao,layout_renwu1_parent,layout_renwu2_parent;
	private TextView name;
	private SharedPreferences sp;
	private LinearLayout layout_xiaoxi,layout_libao;
	private ImageView     zhuanti,mygame;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personal_center);
	back = (FrameLayout) findViewById(R.id.back);
	name = (TextView) findViewById(R.id.text_dengru);
	
	layout_xiaoxi = (LinearLayout) findViewById(R.id.layout_xiaoxi);
	layout_libao = (LinearLayout) findViewById(R.id.layout_libao);
	layout_luobobi = (RelativeLayout) findViewById(R.id.layout_luobobi);
	layout_renwuliebiao = (RelativeLayout) findViewById(R.id.layout_renwuliebiao);
	layout_renwu1_parent = (RelativeLayout) findViewById(R.id.layout_renwu1_parent);
	layout_renwu2_parent = (RelativeLayout) findViewById(R.id.layout_renwu2_parent);
    zhuanti = (ImageView) findViewById(R.id.zhuanti);
    mygame = (ImageView) findViewById(R.id.mygame);
    
    
    
	back.setOnClickListener(this);
	layout_xiaoxi.setOnClickListener(this);
	layout_libao.setOnClickListener(this);
	layout_luobobi.setOnClickListener(this);
	layout_renwuliebiao.setOnClickListener(this);
	layout_renwu1_parent.setOnClickListener(this);
	layout_renwu2_parent.setOnClickListener(this);
	mygame.setOnClickListener(this);
	zhuanti.setOnClickListener(this);

	center_my = (RelativeLayout) findViewById(R.id.center_my);
	center_my.setOnClickListener(this);

	sp=getSharedPreferences("login", 0);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.mygame:
			intent = new Intent(PersonalCenterActivity.this,MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);//设置不要刷新将要跳到的界面  
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//它可以关
			startActivity(intent);

			finish();
			break;
		case R.id.center_my:
			intent = new Intent(PersonalCenterActivity.this,UserLoginActivity.class);
			startActivity(intent);
			
			break;
		case R.id.layout_xiaoxi:
		case R.id.layout_libao:
		case R.id.layout_luobobi:
		case R.id.layout_renwuliebiao:
		case R.id.layout_renwu1_parent:
		case R.id.layout_renwu2_parent:
		case R.id.zhuanti:
			
			Toast.makeText(PersonalCenterActivity.this, "该功能暂未开放", 0).show();
			break;
			
		default:
			break;
		}
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		if("5".equals(sp.getString("ha", ""))){
		String  gamename = sp.getString("gamename", "");
		if(gamename!=null&&gamename!=""){
			name.setText(gamename);
		}
		}
		super.onResume();
	}
}
