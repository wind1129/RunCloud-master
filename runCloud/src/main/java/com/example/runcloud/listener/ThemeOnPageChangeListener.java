package com.example.runcloud.listener;



import com.example.runcloud.R;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.ImageView;

public class ThemeOnPageChangeListener implements OnPageChangeListener{
	protected ImageView[] mImageViews;
	protected ImageView[] tips;
	
	
	
	public ThemeOnPageChangeListener(ImageView[] mImageViews,ImageView[] tips) {
		this.mImageViews = mImageViews;
		this.tips=tips;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		setImageBackground(arg0 % mImageViews.length);
	}
	
	/**
	 * 设置选中的tip的背景
	 * @param selectItems
	 */
	private void setImageBackground(int selectItems){
		for(int i=0; i<tips.length; i++){
			if(i == selectItems){
				tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
			}else{
				tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
			}
		}
	}

}
