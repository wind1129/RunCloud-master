package com.example.runcloud.view;



import com.example.runcloud.R;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;

public class SegmentedRadioGroup extends RadioGroup{

	public SegmentedRadioGroup(Context context) {
		super(context);
	}
	
	public SegmentedRadioGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		changeButtonsImages();
	}
	
	private void changeButtonsImages(){
		int count = super.getChildCount();
     
		super.getChildAt(0).setBackgroundResource(R.drawable.segment_radio_left);
		super.getChildAt(count-1).setBackgroundResource(R.drawable.segment_radio_right);
		
	}

}
