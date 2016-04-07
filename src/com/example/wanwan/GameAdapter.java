package com.example.wanwan;
import java.util.ArrayList;

import android.content.Context;
import android.content.Loader.ForceLoadContentObserver;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/*
 * 
 * 
 * 鑳屽寘閫傞厤鍣?
 */
public class GameAdapter extends PagerAdapter {
	private Context mContext;
	  private ArrayList<ImageView>views ;
	public GameAdapter(Context context) {
		mContext = context;
		views= new ArrayList<ImageView>();
	
		ImageView view = new ImageView(context);
			view.setBackgroundResource(R.drawable.image0011);
			views.add(view);
			ImageView view1 = new ImageView(context);
			view.setBackgroundResource(R.drawable.image0012);
			views.add(view1);
			ImageView view2 = new ImageView(context);
			view.setBackgroundResource(R.drawable.image0013);
			views.add(view2);
	    
	}
	   public int getCount() {
           return views.size();
       }
     //滑动切换的时候销毁当前的组件
       @Override
       public void destroyItem(ViewGroup container, int position,
               Object object) {
           ((ViewPager) container).removeView(views.get(position));
       }
     //每次滑动的时候生成的组件
       @Override
       public Object instantiateItem(ViewGroup container, int position) {
           ((ViewPager) container).addView(views.get(position));
           return views.get(position);
       }

       @Override
       public boolean isViewFromObject(View arg0, Object arg1) {
           return arg0 == arg1;
       }

      

    
	
	
}
