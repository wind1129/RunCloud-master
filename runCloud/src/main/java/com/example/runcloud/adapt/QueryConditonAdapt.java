package com.example.runcloud.adapt;

import java.util.ArrayList;

import com.example.runcloud.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class QueryConditonAdapt<T> extends BaseAdapter{
	private ArrayList<T> conditions;
	private Context context; 
	
	 public QueryConditonAdapt(Context context, ArrayList<T> conditions) {
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		 if (convertView == null) {
	            convertView = LayoutInflater.from(context).inflate(R.layout.query_items, null);
	        }
	        TextView tv = (TextView)convertView.findViewById(R.id.query_item_text);
	        tv.setText((CharSequence) conditions.get(position));
	         
	        return convertView;
	    }
	

}
