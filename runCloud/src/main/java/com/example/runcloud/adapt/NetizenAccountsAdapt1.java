package com.example.runcloud.adapt;

import java.util.ArrayList;
import java.util.List;

import com.example.runcloud.R;
import com.example.runcloud.adapt.MyAdapt.ViewHolder;
import com.example.runcloud.entity.NetizenShowBasic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class NetizenAccountsAdapt1 extends BaseAdapter{
	private List<NetizenShowBasic> conditions;
	private Context context; 
	
	 public NetizenAccountsAdapt1(Context context, List<NetizenShowBasic> conditions) {
	        super();
	        this.context = context;
	        this.conditions = conditions;
	    }

	@Override
	public int getCount() {
		return conditions.size();
	}

	@Override
	public Object getItem(int position) {
		return conditions.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	 class ViewHolder{
		 TextView account;
	 }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		NetizenShowBasic basic = (NetizenShowBasic) getItem(position);
		ViewHolder viewHolder = null; 
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.query_items, null);
	       //convertView = LayoutInflater.from(context).inflate(R.layout.query_items1, null);
	       viewHolder = new ViewHolder();
	       viewHolder.account =(TextView)convertView.findViewById(R.id.query_item_text);
	       convertView.setTag(viewHolder);
		}else{
		   viewHolder = (ViewHolder)convertView.getTag();
		 }
		   viewHolder.account.setText(basic.getAccount()); 
	       return convertView;
	    }
	

}
