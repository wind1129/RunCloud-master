package com.example.runcloud.adapt;

import java.util.List;

import com.example.runcloud.R;
import com.example.runcloud.activity.LineChartsActivity;
import com.example.runcloud.entity.ImportentNetizen;
import com.example.runcloud.entity.MyAttentionListItem;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener; 

public class CopyOfMyAttentionAdapt extends BaseAdapter{
	private Context mContext;
	private List<ImportentNetizen> items;
	private static final String TAG = "ListViewAdapter"; 
	

	public CopyOfMyAttentionAdapt(Context mContext, List<ImportentNetizen> items) {
		super();
		this.mContext = mContext;
		this.items = items;
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
		OnClick listener = null;
		if (convertView == null) { 
			convertView = View.inflate(mContext, R.layout.my_attention_list_item, null);
			viewHolder = new ViewHolder(); 
			viewHolder.myAttentionItemText =(TextView) convertView.findViewById(R.id.myattention_list_text);
			viewHolder.myAttentionItemEditImage=(ImageView) convertView.findViewById(R.id.myattention_list_editImage);
			listener = new OnClick();//在这里新建监听对象
			viewHolder.myAttentionItemEditImage.setOnClickListener(listener); 
			
			convertView.setTag(viewHolder);
			convertView.setTag(viewHolder.myAttentionItemEditImage.getId(), listener);//对监听对象保存 
		}else{
			viewHolder = (ViewHolder) convertView.getTag();  
            listener = (OnClick) convertView.getTag(viewHolder.myAttentionItemEditImage.getId());//重新获得监听对象 
		}
		
		ImportentNetizen myAttentionListItem = items.get(position);//设置监听对象的值
		viewHolder.myAttentionItemText.setText(myAttentionListItem.getSelectName());  
		listener.setPosition(position); 
		Log.d(TAG,  
                "position is " + position + " listener is "  
                        + listener.toString()); 
		return convertView;
	}
	
	class OnClick implements OnClickListener {  
        int position;  
  
        public void setPosition(int position) {  
            this.position = position;  
        }  
  
        @Override  
        public void onClick(View v) {  
            Log.d(TAG, items.get(position).getSelectId());
        }  
    } 

}
