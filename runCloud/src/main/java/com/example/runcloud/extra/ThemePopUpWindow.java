package com.example.runcloud.extra;



import com.example.runcloud.R;


import com.example.runcloud.fragment.MainTab01;
import com.example.runcloud.util.Blur;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

public class ThemePopUpWindow extends PopupWindow {
	private View mMenuView;

	public ThemePopUpWindow(Activity context,OnClickListener itemsOnClick) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.theme_popupwindow,null);
		
		/*mMenuView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
		mMenuView.setDrawingCacheEnabled(true);
		mMenuView.buildDrawingCache();
		Bitmap drawingCache = mMenuView.getDrawingCache();
		long startTime = SystemClock.currentThreadTimeMillis();
		Bitmap apply = Blur.apply(MainTab01.mainContext,drawingCache);
		
		 BitmapDrawable bd = new BitmapDrawable(MainTab01.mainContext.getResources(),apply);
            bd.setAlpha(90);
            bd.setColorFilter(Color.parseColor("#ddeeddee"), PorterDuff.Mode.DST_ATOP);
            mMenuView.setBackgroundDrawable(bd);*/
		
		//设置SelectPicPopupWindow的View
				this.setContentView(mMenuView);
				//设置SelectPicPopupWindow弹出窗体的宽
				this.setWidth(LayoutParams.FILL_PARENT);
				//设置SelectPicPopupWindow弹出窗体的高
				this.setHeight(LayoutParams.WRAP_CONTENT);
				//设置SelectPicPopupWindow弹出窗体可点击
				this.setFocusable(false);
				//设置SelectPicPopupWindow弹出窗体动画效果
				//this.setAnimationStyle(R.style.AnimBottom);
				//实例化一个ColorDrawable颜色为半透明
				ColorDrawable dw = new ColorDrawable(0xb0000000);
				//ColorDrawable dw = new ColorDrawable(Color.RED);
				//设置SelectPicPopupWindow弹出窗体的背景
				this.setBackgroundDrawable(dw);
				//mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
				//this.setOutsideTouchable(true);
				
				
	}
	
	

}
