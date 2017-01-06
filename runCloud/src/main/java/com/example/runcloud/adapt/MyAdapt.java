package com.example.runcloud.adapt;

import java.util.List;





import com.example.runcloud.R;
import com.example.runcloud.entity.NewsListEntity;




import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapt extends BaseAdapter{
	private List<NewsListEntity> entitys;
	private Context context; 
	

	public MyAdapt(Context context,List<NewsListEntity> entitys) {
		this.context = context;
		this.entitys = entitys;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return entitys.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return entitys.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	 class ViewHolder{
		ImageView newslistImage;
		TextView newslistTitle;
		TextView newslistTime;
		ImageView newslistFromImage;
		TextView newslistFrom;
		ImageView newslistAutherImage;
		TextView newslistAuther;
		TextView newslistContext;
		ImageView newslistHitImage;
		TextView newslistHit;
		ImageView newslistReplyImage;
		TextView newslistReply;
		ImageView newslistPositiveImage;
		TextView newslistPositive;
 
	    }  

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		NewsListEntity newsListEntity = (NewsListEntity)getItem(position);
		ViewHolder viewHolder = null;
		if(convertView==null){
			convertView = LayoutInflater.from(context).inflate(  
                    R.layout.news_list, null);  
			viewHolder = new ViewHolder();
			
			viewHolder.newslistImage = (ImageView)convertView.findViewById(  
                    R.id.newslist_image);
			viewHolder.newslistTitle = (TextView)convertView.findViewById(  
                    R.id.newslist_title);
			viewHolder.newslistTime = (TextView)convertView.findViewById(  
                    R.id.newslist_time);
			viewHolder.newslistFromImage = (ImageView)convertView.findViewById(  
                    R.id.newslist_fromImage);
			viewHolder.newslistFrom = (TextView)convertView.findViewById(  
                    R.id.newslist_from);
			viewHolder.newslistAutherImage = (ImageView)convertView.findViewById(  
                    R.id.newslist_autherImage);
			viewHolder.newslistAuther = (TextView)convertView.findViewById(  
                    R.id.newslist_auther);
			viewHolder.newslistContext = (TextView)convertView.findViewById(  
                    R.id.newslist_context);
			viewHolder.newslistHitImage = (ImageView)convertView.findViewById(  
                    R.id.newslist_hitImage);
			viewHolder.newslistHit = (TextView)convertView.findViewById(  
                    R.id.newslist_hit);
			viewHolder.newslistReplyImage = (ImageView)convertView.findViewById(  
                    R.id.newslist_replyImage);
			viewHolder.newslistReply = (TextView)convertView.findViewById(  
                    R.id.newslist_reply);
			viewHolder.newslistPositiveImage = (ImageView)convertView.findViewById(  
                    R.id.newslist_positiveImage);
			viewHolder.newslistPositive = (TextView)convertView.findViewById(  
                    R.id.newslist_positive);
			convertView.setTag(viewHolder);
		}else{  
			viewHolder = (ViewHolder)convertView.getTag();
		}
		viewHolder.newslistImage.setImageResource(newsListEntity.getNewslistImage());
		viewHolder.newslistTitle.setText(Html.fromHtml(newsListEntity.getNewslistTitle()));
		viewHolder.newslistTime.setText(newsListEntity.getNewslistTime());
		viewHolder.newslistFromImage.setImageResource(newsListEntity.getNewslistFromImage());
		viewHolder.newslistFrom.setText(newsListEntity.getNewslistFrom());
		viewHolder.newslistAutherImage.setImageResource(newsListEntity.getNewslistAutherImage());
		viewHolder.newslistAuther.setText(newsListEntity.getNewslistAuther());
		viewHolder.newslistContext.setText(Html.fromHtml(newsListEntity.getNewslistContext()));
		viewHolder.newslistHitImage.setImageResource(newsListEntity.getNewslistHitImage());
		viewHolder.newslistHit.setText(newsListEntity.getNewslistHit());
		viewHolder.newslistReplyImage.setImageResource(newsListEntity.getNewslistReplyImage());
		viewHolder.newslistReply.setText(newsListEntity.getNewslistReply());
		viewHolder.newslistPositiveImage.setImageResource(newsListEntity.getNewslistPositiveImage());		
		viewHolder.newslistPositive.setText(newsListEntity.getNewslistPositive());
		if(newsListEntity.getNewslistPositive().equals("负面")){
		    viewHolder.newslistPositive.setTextColor(Color.rgb(238, 59, 59));
		}else if(newsListEntity.getNewslistPositive().equals("正面")){
			viewHolder.newslistPositive.setTextColor(Color.rgb(34, 139, 34));
		}else if(newsListEntity.getNewslistPositive().equals("中性")){
			viewHolder.newslistPositive.setTextColor(Color.rgb(238, 154, 0));
		}
		
		return convertView;
	}

}
