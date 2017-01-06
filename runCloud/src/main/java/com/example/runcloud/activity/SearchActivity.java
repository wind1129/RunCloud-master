package com.example.runcloud.activity;


import com.example.runcloud.BaseActivity;
import com.example.runcloud.R;
import com.example.runcloud.fragment.FragmentMainActivity;
import com.example.runcloud.fragment.HighSearchFragment;
import com.example.runcloud.fragment.QuickSearchFragment;


import com.simple.widgets.SegmentView;
import com.simple.widgets.SegmentView.OnItemCheckedListener;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

@SuppressLint("NewApi")
public class SearchActivity extends BaseActivity implements OnClickListener{
	private QuickSearchFragment mTab01;
	private HighSearchFragment mTab02;
	private FragmentManager fragmentManager;
	
	
	
	private TextView backFirstPageView;
	private ImageView backView;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setTranslucentStatus();
		setContentView(R.layout.search_title_bar);
		
		//SegmentedRadioGroup group = (SegmentedRadioGroup) findViewById(R.id.segment_text);
		//group.setOnCheckedChangeListener(this);
		fragmentManager = getFragmentManager();
		setTabSelection(0);
		backFirstPageView =(TextView) findViewById(R.id.back_firstPage);
		backView = (ImageView) findViewById(R.id.back);
		backFirstPageView.setOnClickListener(this);
		backView.setOnClickListener(this);
		
		
		SegmentView rGroup = (SegmentView) findViewById(R.id.rg);
		// 默认为横向排列，如上图所示，如需纵向排列请调用setOrientation方法
		// rGroup.setOrientation(SegmentView.VERTICAL);

		// 设置tabs
		rGroup.setTabs(new String[] {
		                "快速搜索", "高级搜索"
		        });
		((RadioButton) rGroup.getChildAt(0)).setChecked(true);
		// 设置点击事件
		rGroup.setOnItemCheckedListener(new OnItemCheckedListener() {

		      @Override
		      public void onCheck(RadioButton button, int position, String title) {
		             /*Toast.makeText(getApplicationContext(),
		                        "checked id = " + position + ", title = " + title,
		                        Toast.LENGTH_SHORT).show();*/
		    	  setTabSelection(position);
		      }
		 });
		
	}

	/*@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
        case R.id.button_one:
        	setTabSelection(0);
            break;

        case R.id.button_two:
        	setTabSelection(1);
            break;

        default:
            break;
    }
		
	}*/
	
	
	/**
	 * 根据传入的index参数来设置选中的tab页。
	 * 
	 */
	@SuppressLint("NewApi")
	private void setTabSelection(int index)
	{
		// 重置按钮
		//resetBtn();
		// 开启一个Fragment事务
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
		hideFragments(transaction);
		switch (index)
		{
		case 0:
			// 当点击了消息tab时，改变控件的图片和文字颜色
			/*((ImageButton) mTabBtnPublicfeelings.findViewById(R.id.btn_tab_bottom_publicfeelings))
					.setImageResource(R.drawable.label_publicfeelings_selected_2x);*/
			if (mTab01 == null)
			{
				// 如果MessageFragment为空，则创建一个并添加到界面上
				mTab01 = new QuickSearchFragment();
				transaction.add(R.id.id_content_of_search, mTab01);
			} else
			{
				// 如果MessageFragment不为空，则直接将它显示出来
				transaction.show(mTab01);
			}
			break;
		case 1:
			// 当点击了消息tab时，改变控件的图片和文字颜色
			/*((ImageButton) mTabBtnOversea.findViewById(R.id.btn_tab_bottom_oversea))
					.setImageResource(R.drawable.label_oversea_selected_2x);*/
			if (mTab02 == null)
			{
				// 如果MessageFragment为空，则创建一个并添加到界面上
				mTab02 = new HighSearchFragment();
				transaction.add(R.id.id_content_of_search, mTab02);
			} else
			{
				// 如果MessageFragment不为空，则直接将它显示出来
				transaction.show(mTab02);
			}
			break;

		}
		transaction.commit();
	}
	
	/**
	 * 将所有的Fragment都置为隐藏状态。
	 * 
	 * @param transaction
	 *            用于对Fragment执行操作的事务
	 */
	@SuppressLint("NewApi")
	private void hideFragments(FragmentTransaction transaction)
	{
		if (mTab01 != null)
		{
			transaction.hide(mTab01);
		}
		if (mTab02 != null)
		{
			transaction.hide(mTab02);
		}
		
	}

	@Override
	public void onClick(View arg0) {
		Intent intent = null;
		switch(arg0.getId()){
		case R.id.back_firstPage:
			intent =new Intent(this, FragmentMainActivity.class);
			break;
		case R.id.back:
			finish();
			return;
		
		}
		startActivity(intent);
		
	}
	

	
}
