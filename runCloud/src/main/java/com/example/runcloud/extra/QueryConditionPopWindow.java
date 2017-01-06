package com.example.runcloud.extra;

import com.example.runcloud.R;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

public class QueryConditionPopWindow extends PopupWindow {
	private View mMenuView;

	public QueryConditionPopWindow(Activity context,OnClickListener itemsOnClick) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.query_popwindow,null);
		
		//设置SelectPicPopupWindow的View
				this.setContentView(mMenuView);
				//设置SelectPicPopupWindow弹出窗体的宽
				//this.setWidth(300);
				//设置SelectPicPopupWindow弹出窗体的高
				//this.setHeight(LayoutParams.WRAP_CONTENT);
				this.setHeight(700);
				//设置SelectPicPopupWindow弹出窗体可点击
				this.setFocusable(true);
				//设置SelectPicPopupWindow弹出窗体动画效果
				//this.setAnimationStyle(R.style.AnimBottom);
				//实例化一个ColorDrawable颜色为半透明
				ColorDrawable dw = new ColorDrawable(0xb0000000);
				//设置SelectPicPopupWindow弹出窗体的背景
				this.setBackgroundDrawable(dw);
				//mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
				
	}

}
