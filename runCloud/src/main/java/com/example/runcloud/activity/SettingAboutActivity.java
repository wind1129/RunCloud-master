package com.example.runcloud.activity;

import com.example.runcloud.BaseActivity;
import com.example.runcloud.R;


import com.example.runcloud.view.SildingFinishLayout;
import com.example.runcloud.view.SildingFinishLayout.OnSildingFinishListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingAboutActivity extends BaseActivity implements OnClickListener{
	TextView textView;
	private ImageView about_setting_back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//setTranslucentStatus();
		setContentView(R.layout.setting_about);
		
		about_setting_back = (ImageView) findViewById(R.id.about_setting_back);
		about_setting_back.setOnClickListener(this);
		
		textView = (TextView) findViewById(R.id.about_text);
		
		
		SildingFinishLayout mSildingFinishLayout = (SildingFinishLayout) findViewById(R.id.sildingFinishLayout);
		mSildingFinishLayout
				.setOnSildingFinishListener(new OnSildingFinishListener() {

					@Override
					public void onSildingFinish() {
						SettingAboutActivity.this.finish();
					}
				});

		mSildingFinishLayout.setTouchView(mSildingFinishLayout);
		
	}
	
	// Press the back button in mobile phone
		@Override
		public void onBackPressed() {
			super.onBackPressed();
			overridePendingTransition(0, R.anim.base_slide_right_out);
		}
	
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
			case R.id.about_setting_back:
				finish();
				return;
			}
	}
}


