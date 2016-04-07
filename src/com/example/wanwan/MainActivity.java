package com.example.wanwan;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.example.wanwan.api.WanwanApi;





import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.util.LruCache;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView.ScaleType;

public class MainActivity extends Activity implements OnClickListener {

	private ImageCache imageCache;
	private LruCache<String, Bitmap> mCache;
    private LinearLayout ll_focus_indicator_container = null;
    private MyGallery gallery = null;
	private Banner banner;

    private ArrayList<Drawable> imgList = new ArrayList<Drawable>();
    private String [] str = {"http://baike.baidu.com/link?url=OA9BYe7HAGNy_OPy6KkmPdKh7EpV4I42sCcK1cinrRisFRBd0eonPLOdYBIs2neal-l1HeZnuyn3FyxiZ9c4Ah_4GeQZBqjijTIsdxgEeD7","http://www.iqiyi.com","http://www.oneniceapp.com"};
    /**
     * 存储上一个选择项的Index
     */
    private int preSelImgIndex = 0;
	private ListView mlistview;
	private RequestQueue queue;
	private Context context;
	private Game game;
	private ImageView user_menu,user_my,game1,zhuanti;
	private ScrollView scrollview_game;
	private LinearLayout xiamian_linear;
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	context = this.getApplicationContext();
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
	InitImgList();
	ll_focus_indicator_container = (LinearLayout) findViewById(R.id.ll_focus_indicator_container);
	InitFocusIndicatorContainer();
	gallery = (MyGallery) findViewById(R.id.banner_gallery);
mlistview = (ListView) findViewById(R.id.mlistview);
user_menu = (ImageView) findViewById(R.id.user_menu);
user_my = (ImageView) findViewById(R.id.user_my);
game1 = (ImageView) findViewById(R.id.game);
zhuanti = (ImageView) findViewById(R.id.zhuanti);
scrollview_game = (ScrollView) findViewById(R.id.scrollview_game);
xiamian_linear = (LinearLayout) findViewById(R.id.xiamian_linear);
scrollview_game.smoothScrollTo(0,20);
login();
banner();
user_my.setOnClickListener(this);
user_menu.setOnClickListener(this);
zhuanti.setOnClickListener(this);
game1.setOnClickListener(this);
xiamian_linear.setOnClickListener(this);
	gallery.setAdapter(new ImageAdapter(this));
	gallery.setFocusable(true);
	gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

	    public void onItemSelected(AdapterView<?> arg0, View arg1,
		    int selIndex, long arg3) {
		//修改上一次选中项的背景
	    	selIndex = selIndex % imgList.size();
	    	
		ImageView preSelImg = (ImageView) ll_focus_indicator_container
		.findViewById(preSelImgIndex);
	preSelImg.setImageDrawable(MainActivity.this
		.getResources().getDrawable(R.drawable.ic_focus));
		//修改当前选中项的背景
		ImageView curSelImg = (ImageView) ll_focus_indicator_container
			.findViewById(selIndex);
		curSelImg
			.setImageDrawable(MainActivity.this
				.getResources().getDrawable(
					R.drawable.ic_focus_select));
		preSelImgIndex = selIndex;
	    }

	    public void onNothingSelected(AdapterView<?> arg0) {
	    }
	});
	
	
	Log.i("msg", "260px:"+px2dip(this, 260));

	mlistview.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
		
			if(position<game.data.size()){

			Intent  intent = new Intent(MainActivity.this,GameActivity.class);
			intent.putExtra("strName", game.data.get(position).strName);
			intent.putExtra("iPopularity", game.data.get(position).iPopularity);
			intent.putExtra("strDesc", game.data.get(position).strDesc);
			intent.putExtra("strUrl", game.data.get(position).strUrl);
			intent.putExtra("strIcon", game.data.get(position).strIcon);
			startActivity(intent);
			}
			

		}
	});
    }
    private void login() {
		StringRequest srl = new StringRequest(Request.Method.GET,
				WanwanApi.game, new Response.Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						try {
							
					
					
						game = new Game();
						game = GsonUtil.jsonToBean(arg0, Game.class);
						BackPackAdapter   adater = new BackPackAdapter(context, game);
						mlistview.setAdapter(adater);
						mlistview.setDivider(null);
				
						} catch (Exception e) {
							// TODO: handle exception
							json();
						}
							
						
					}

				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub

					}

				}) ;
		queue.add(srl);
	}   
    private void banner() {
    	StringRequest srl1 = new StringRequest(Request.Method.GET,
    			WanwanApi.banner, new Response.Listener<String>() {
    		

			@Override
    		public void onResponse(String arg0) {
    			
    				try {
				
    				System.out.println(arg0);
    				banner = new Banner();
    				banner = GsonUtil.jsonToBean(arg0, Banner.class);
    				gallery.setOnItemClickListener(new OnItemClickListener() {
    					@Override public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3)
    					{ Intent  intent  = new Intent(MainActivity.this,MyGameActivity.class);
    					intent.putExtra("strName", "广告推荐");		
    						if(arg2<4){
    							
    						
    							intent.putExtra("strUrl", banner.data.get(arg2).strUrl);
    						}else{
    							intent.putExtra("strUrl", banner.data.get(arg2%3).strUrl);
    						}
    					startActivity(intent);
    						} 
    					}
    				);
    				
					} catch (Exception e) {
						// TODO: handle exception
						banne();

					}
    		}
    		
    	}, new Response.ErrorListener() {
    		
    		@Override
    		public void onErrorResponse(VolleyError arg0) {
    			// TODO Auto-generated method stub
    			
    		}
    		
    	}) ;
    	queue.add(srl1);
    }   
    public static float dip2px(Context context,float dpValue)
    {
    	final float scale = context.getResources().getDisplayMetrics().density;
    	return (dpValue * scale + 0.5f);
    }
    
    public static float px2dip(Context context, float pxValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        Log.i("msg", "scale:"+scale);
        return (pxValue / scale + 0.5f);  
    }  


    private void InitFocusIndicatorContainer() {
	for (int i = 0; i < imgList.size(); i++) {
	    ImageView localImageView = new ImageView(this);
	    localImageView.setId(i);
	    ImageView.ScaleType localScaleType = ImageView.ScaleType.FIT_XY;
	    localImageView.setScaleType(localScaleType);
	    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(
		    24, 24);
	    localImageView.setLayoutParams(localLayoutParams);
	    localImageView.setPadding(5, 5, 5, 5);
	    localImageView.setImageResource(R.drawable.ic_focus);
	    this.ll_focus_indicator_container.addView(localImageView);
	}
    }

    private void InitImgList() {
	// 加载图片数据（本demo仅获取本地资源，实际应用中，可异步加载网络数据）
	imgList.add(this.getResources().getDrawable(R.drawable.banner01));
	imgList.add(this.getResources().getDrawable(R.drawable.banner02));
	imgList.add(this.getResources().getDrawable(R.drawable.banner03));
	
    }

    public class ImageAdapter extends BaseAdapter {
	private Context _context;

	public ImageAdapter(Context context) {
	    _context = context;
	}

	public int getCount() {
	    return Integer.MAX_VALUE;
	}

	public Object getItem(int position) {

	    return position;
	}

	public long getItemId(int position) {
	    return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if(convertView == null)
		{
			viewHolder = new ViewHolder();
			ImageView imageView = new ImageView(_context);
		    imageView.setAdjustViewBounds(true);
		    imageView.setScaleType(ScaleType.FIT_XY);
		    imageView.setLayoutParams(new Gallery.LayoutParams(
			    LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		    convertView = imageView;
		    viewHolder.imageView = (ImageView)convertView; 
		    convertView.setTag(viewHolder);
			
		}
		else
		{
			viewHolder = (ViewHolder)convertView.getTag();
		}
	    viewHolder.imageView.setImageDrawable(imgList.get(position%imgList.size()));
	    
	    return convertView;
	}
	
    }
    
    private static class ViewHolder
	{
		ImageView imageView;
	}
    protected void onDestroy()
	{
		super.onDestroy();
		gallery.destroy();
	}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private void json() {
	try {
		
	
		//将json文件读取到buffer数组中
		InputStream is = this.getResources().openRawResource(R.raw.game);
		byte[] buffer = new byte[is.available()];
		is.read(buffer);

		//将字节数组转换为以GB2312编码的字符串
		String json = new String(buffer, "GB2312");
		System.out.println(json);
		game = new Game();
		game = GsonUtil.jsonToBean(json, Game.class);
		BackPackAdapter   adater = new BackPackAdapter(context, game);
		mlistview.setAdapter(adater);
		mlistview.setDivider(null);
	
	} catch (Exception e) {
		// TODO: handle exception
		Toast.makeText(context, "请检查服务器", 0).show();

	}
		
		}
    private void banne() {
    	try {
    		
    		
    		//将json文件读取到buffer数组中
    		InputStream is = this.getResources().openRawResource(R.raw.banner);
    		byte[] buffer = new byte[is.available()];
    		is.read(buffer);
    		
    		//将字节数组转换为以GB2312编码的字符串
    		String json = new String(buffer, "GB2312");
    		banner = new Banner();
			banner = GsonUtil.jsonToBean(json, Banner.class);
			gallery.setOnItemClickListener(new OnItemClickListener() {
				@Override public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3)
				{ Intent  intent  = new Intent(MainActivity.this,MyGameActivity.class);
				intent.putExtra("strName", "广告推荐");		
					if(arg2<4){
						
					
						intent.putExtra("strUrl", banner.data.get(arg2).strUrl);
					}else{
						intent.putExtra("strUrl", banner.data.get(arg2%3).strUrl);
					}
				startActivity(intent);
					} 
				}
			);
			
    		
    	} catch (Exception e) {
    		// TODO: handle exception
			Toast.makeText(context, "请检查服务器", 0).show();
    		
    	}
    	
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.user_menu:
		case R.id.user_my:
			Intent  intent = new Intent(MainActivity.this,PersonalCenterActivity.class);
			startActivity(intent);
			break;
		case R.id.zhuanti:
			Toast.makeText(context, "该功能暂未开放", 0).show();

			break;
		case R.id.game:
		
			break;

		default:
			break;
		}
	}
	
	
	 class BackPackAdapter extends BaseAdapter {
			private Context mContext;
			Game mDate;
			private TextView game_start;
			public BackPackAdapter(Context context, Game date) {
				mContext = context;
				mDate = date;
			}
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mDate.data.size()+1;
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return position;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}

			@Override
			public View getView(final int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				if(mDate.data.size()>position){
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.game_item, parent, false);
			TextView gamename = 	(TextView) convertView.findViewById(R.id.gamename);
			TextView text_hot = 	(TextView) convertView.findViewById(R.id.text_hot);
			TextView gameover = 	(TextView) convertView.findViewById(R.id.gameover);
			ImageView gamepic = (ImageView) convertView.findViewById(R.id.gamepic);
			game_start = (TextView) convertView.findViewById(R.id.game_start);
			gamename.setText(mDate.data.get(position).strName);
			text_hot.setText(mDate.data.get(position).iPopularity);
			gameover.setText(mDate.data.get(position).strTitle);
			ImageDownLoad.downLoadImage(WanwanApi.pic+mDate.data.get(position).strIcon,
					gamepic, queue, imageCache);
			game_start.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent  intent = new Intent(MainActivity.this,MyGameActivity.class);
					intent.putExtra("strName", mDate.data.get(position).strName);
					intent.putExtra("strUrl", mDate.data.get(position).strUrl);
					startActivity(intent);
				}

			
			});
				}else{
					convertView = LayoutInflater.from(mContext).inflate(
							R.layout.game_item1, parent, false);
				}
			
				return convertView;
			}

			
			public void removeList() {
				if (mDate != null) {
					mDate = null;
				
				}
			

			}
		}

}