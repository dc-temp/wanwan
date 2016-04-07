package com.example.wanwan;


import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;


public class ImageDownLoad {
	public static void downLoadImage(String path,ImageView imageView,RequestQueue queue,ImageCache imageCache)
	{
		
		ImageLoader imageLoader=new ImageLoader(queue, imageCache);
		ImageListener imageListener=imageLoader.getImageListener(imageView, 
				R.drawable.icon0011, R.drawable.icon0011);
		imageLoader.get(path, imageListener);
		
	}
	
}
