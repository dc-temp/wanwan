package com.example.wanwan;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class MyGameActivity extends Activity implements OnClickListener {
	private TextView cartoon_page_title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
	WebView mwebview = 	(WebView) findViewById(R.id.mywebview);
	ImageView  back = 	(ImageView) findViewById(R.id.back);
	cartoon_page_title = (TextView) findViewById(R.id.cartoon_page_title);
	mwebview.getSettings().setJavaScriptEnabled(true);
	mwebview.getSettings().setAllowFileAccess(true);
	back.setOnClickListener(this);
	String  url = getIntent().getStringExtra("strUrl");
	String  gamename = getIntent().getStringExtra("strName");
	cartoon_page_title.setText(gamename);
	mwebview.loadUrl(url);
	mwebview.setWebViewClient(new WebViewClient() {
		
		    public boolean shouldOverrideUrlLoading(WebView view, String url)
		
		      { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
		
		            view.loadUrl(url);
		
		            return true;
		
		      }
		
		});

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;

		default:
			break;
		}
	}

}
