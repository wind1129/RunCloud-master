package com.example.runcloud.adapt;

import java.util.List;

import com.example.runcloud.R;
import com.example.runcloud.activity.LineChartsActivity;
import com.example.runcloud.entity.ImportentNetizen;
import com.example.runcloud.entity.MyAttentionListItem;
import com.example.runcloud.entity.Sensrule;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener; 

public class MyAttentionAdapt1 extends BaseAdapter{
	private Context mContext;
	private List<Sensrule> items;
	private static final String TAG = "ListViewAdapter"; 
	private MyClickListener mListener;
	

	public MyAttentionAdapt1(Context mContext, List<Sensrule> items,MyClickListener listener) {
		super();
		this.mContext = mContext;
		this.items = items;
		mListener = listener;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	class ViewHolder{
		TextView myAttentionItemText;
		TextView myAttentionItemSelectId;
		ImageView myAttentionItemEditImage;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder =null;
		//OnClick listener = null;
		if (convertView == null) { 
			convertView = View.inflate(mContext, R.layout.my_attention_list_item, null);
			viewHolder = new ViewHolder(); 
			viewHolder.myAttentionItemText =(TextView) convertView.findViewById(R.id.myattention_list_text);
			viewHolder.myAttentionItemEditImage=(ImageView) convertView.findViewById(R.id.myattention_list_editImage);
			//listener = new OnClick();//在这里新建监听对象
			//viewHolder.myAttentionItemEditImage.setOnClickListener(listener); 
			
			convertView.setTag(viewHolder);
			//convertView.setTag(viewHolder.myAttentionItemEditImage.getId(), listener);//对监听对象保存 
		}else{
			viewHolder = (ViewHolder) convertView.getTag();  
            //listener = (OnClick) convertView.getTag(viewHolder.myAttentionItemEditImage.getId());//重新获得监听对象 
		}
		
		Sensrule myAttentionListItem = items.get(position);//设置监听对象的值
		viewHolder.myAttentionItemText.setText(myAttentionListItem.getSelectName()); 
		viewHolder.myAttentionItemEditImage.setOnClickListener(mListener);
		viewHolder.myAttentionItemEditImage.setTag(position);
		//listener.setPosition(position); 
		return convertView;
	}
	
	 public static abstract class MyClickListener implements OnClickListener {
	        /**
	         * 基类的onClick方法
	         */
	        @Override
	        public void onClick(View v) {
	            myOnClick((Integer) v.getTag(), v);
	        }
	        public abstract void myOnClick(int position, View v);
	    } 

}
