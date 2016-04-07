package com.example.wanwan;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.example.wanwan.api.WanwanApi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity implements OnClickListener {
	private RequestQueue queue;

	private String strName,strIcon;
	private String iPopularity;
	private String strDesc,strUrl;
	private LinearLayout game_ok,gamelinear,xiamian;
	private TextView miaoshu,gamename,text_hot,gameover;
	private GameAdapter   adapter;
	private Context  context;
	private LinearLayout image_layout;
	private ImageView back,user_my,zhanti,mygame,gamepic;
	private Display display;
	private ImageCache imageCache;
	private LruCache<String, Bitmap> mCache;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		context = getApplicationContext();
	strName = getIntent().getStringExtra("strName");
	iPopularity = getIntent().getStringExtra("iPopularity");
	strDesc = getIntent().getStringExtra("strDesc");
	strUrl = getIntent().getStringExtra("strUrl");
	strIcon = getIntent().getStringExtra("strIcon");
	WindowManager windowManager = getWindowManager();
	queue = Volley.newRequestQueue(context);
	int maxSize = 10 * 1024 * 1024;
	mCache = new LruCache<String, Bitmap>(maxSize) {
		@Override
		protected int sizeOf(String key, Bitmap bitmap) {
			return bitmap.getRowBytes() * bitmap.getHeight();
		}
	};
	imageCache = new ImageCache() {

		@Override
		public void putBitmap(String arg0, Bitmap arg1) {
			mCache.put(arg0, arg1);
		}

		@Override
		public Bitmap getBitmap(String arg0) {

			return mCache.get(arg0);
		}
	};
	display = windowManager.getDefaultDisplay();

	game_ok = (LinearLayout) findViewById(R.id.game_ok);
	xiamian = (LinearLayout) findViewById(R.id.xiamian);
	gamename = (TextView) findViewById(R.id.gamename);
	text_hot = (TextView) findViewById(R.id.text_hot);
	gameover = (TextView) findViewById(R.id.gameover);
	gamelinear = (LinearLayout) findViewById(R.id.gamelinear);
back = (ImageView) findViewById(R.id.back);
mygame = (ImageView) findViewById(R.id.mygame);
zhanti = (ImageView) findViewById(R.id.zhanti);
user_my = (ImageView) findViewById(R.id.user_my);
gamepic = (ImageView) findViewById(R.id.gamepic);
	image_layout = (LinearLayout) findViewById(R.id.image_layout);
	game_ok.setOnClickListener(this);
	back.setOnClickListener(this);
	mygame.setOnClickListener(this);
	gamelinear.setOnClickListener(this);
	zhanti.setOnClickListener(this);
	user_my.setOnClickListener(this);
miaoshu = (TextView) findViewById(R.id.miaoshu);
ImageDownLoad.downLoadImage(WanwanApi.pic+strIcon,
		gamepic, queue, imageCache);
adapter = new GameAdapter(context);
gamename.setText(strName);
text_hot.setText(iPopularity);
gameover.setText(strDesc);
miaoshu.setText(strDesc);
xiamian.setOnClickListener(this);
LinearLayout.LayoutParams layoutParams =new LinearLayout.LayoutParams((display.getWidth()/2), ((display.getHeight() / 2) + 10));
layoutParams.leftMargin=10;
ImageView  imgv1 = new ImageView(context);
imgv1.setBackgroundResource(R.drawable.im1);

imgv1.setLayoutParams(new LinearLayout.LayoutParams((int) (display
		.getWidth() / 2), (display.getHeight() / 2) + 10));
imgv1.setPadding(12, 4, 0, 4);
//imageLayout.addView(iv, imageLayout.getChildCount() - 1);
ImageView  imgv2 = new ImageView(context);
imgv2.setBackgroundResource(R.drawable.im2);
imgv2.setLayoutParams(new LinearLayout.LayoutParams((int) (display
		.getWidth() / 2), (display.getHeight() / 2) + 10));
imgv2.setPadding(12, 4, 0, 4);
ImageView  imgv3 = new ImageView(context);
imgv3.setBackgroundResource(R.drawable.im3);
imgv3.setLayoutParams(new LinearLayout.LayoutParams((int) (display
		.getWidth() / 2), (display.getHeight() / 2) + 10));
imgv3.setPadding(12, 4, 0, 4);

ImageView  imgv4 = new ImageView(context);
imgv4.setBackgroundResource(R.drawable.im4);
imgv4.setLayoutParams(new LinearLayout.LayoutParams((int) (display
		.getWidth() / 2), (display.getHeight() / 2) + 10));
imgv4.setPadding(12, 4, 0, 4);


ImageView  imgv5 = new ImageView(context);
imgv5.setBackgroundResource(R.drawable.im5);

imgv5.setLayoutParams(new LinearLayout.LayoutParams((int) (display
		.getWidth() / 2), (display.getHeight() / 2) + 10));
imgv5.setPadding(12, 4, 0, 4);
image_layout.addView(imgv1);
image_layout.addView(imgv2,layoutParams);
image_layout.addView(imgv3,layoutParams);
image_layout.addView(imgv4,layoutParams);
image_layout.addView(imgv5,layoutParams);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back:
		case R.id.mygame:
			finish();
			break;
		case R.id.zhanti:
Toast.makeText(context, "该功能暂未开放", 0).show();
break;
		case R.id.user_my:
			Intent  intent1 = new Intent(GameActivity.this,PersonalCenterActivity.class);
			startActivity(intent1);
			break;
		case R.id.gamelinear:
		case R.id.game_ok:

			Intent  intent = new Intent(GameActivity.this,MyGameActivity.class);
			intent.putExtra("strName", strName);		
			intent.putExtra("strUrl", strUrl);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
}
