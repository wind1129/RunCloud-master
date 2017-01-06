package com.example.runcloud.fragment;

import java.util.Calendar;

import com.example.runcloud.R;
import com.example.runcloud.activity.AddAttention;
import com.example.runcloud.activity.QuicklySearchShow;
import com.example.runcloud.application.MyApplication;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

@SuppressLint("NewApi")
public class HighSearchFragment extends Fragment implements OnCheckedChangeListener,OnClickListener{
	/**全局变量*/
	private MyApplication myApplication;
	/**本地存储*/
	private SharedPreferences sp;
	private RelativeLayout click_high_search;
	
	private EditText highSearch_keywords;
	private EditText highSearch_siteName;
	private EditText highSearch_channelName;
	private EditText highSearch_author;
	private EditText highSearch_publishTime;
	private EditText highSearch_endTime;
	
	private RadioGroup local_RadioGroup;
	private RadioButton local_RadioButton_sure;
	private RadioButton local_RadioButton_false;
	private RadioGroup mainText_RadioGroup;
	private RadioButton mainText_RadioButton_sure;
	private RadioButton mainText_RadioButton_false;
	private RadioGroup filterGarbage_RadioGroup;
	private RadioButton filterGarbage_RadioButton_sure;
	private RadioButton filterGarbage_RadioButton_false;
	
	private String uri;
	private String baseUrl ="/ares/restful/fulltextsearch/search";
	private String url;
	
	/**参数*/
	private String userId;
	private String orgId;
	private String keywords;
	private String siteName;
	private String channelName;
	private String author;
	private String startDate;
	private String endDate;
	private String localMark;
	private String contentType;
	private String isGarbage;
	
	
	/**url拼接*/
	StringBuilder builder = new StringBuilder();
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View newsLayout = inflater.inflate(R.layout.high_search_tab, container, false);
		
		/**url*/
		myApplication =(MyApplication) getActivity().getApplication();
		userId = myApplication.getInformation().getUserId();
		orgId =myApplication.getInformation().getOrgId();
		
		sp = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
		uri = sp.getString("URI", "");
		url = uri + baseUrl;
		
		click_high_search = (RelativeLayout) newsLayout.findViewById(R.id.click_high_search);
		click_high_search.setOnClickListener(this);
		
		highSearch_keywords = (EditText) newsLayout.findViewById(R.id.highSearch_keywords);
		highSearch_siteName = (EditText) newsLayout.findViewById(R.id.highSearch_siteName);
		highSearch_channelName = (EditText) newsLayout.findViewById(R.id.highSearch_channelName);
		highSearch_author = (EditText) newsLayout.findViewById(R.id.highSearch_author);
		highSearch_publishTime = (EditText) newsLayout.findViewById(R.id.highSearch_publishTime);
		highSearch_endTime = (EditText) newsLayout.findViewById(R.id.highSearch_endTime);
		
		final Calendar c = Calendar.getInstance();
		/**设置日期的监听*/
		highSearch_publishTime.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DatePickerDialog dialog = new DatePickerDialog(
						getActivity(),
						new DatePickerDialog.OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								c.set(year, monthOfYear, dayOfMonth);
								highSearch_publishTime.setText(DateFormat.format(
										"yyy-MM-dd", c));
							}
						}, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
								.get(Calendar.DAY_OF_MONTH));
				dialog.show();
			}
		});
		
		highSearch_endTime.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DatePickerDialog dialog = new DatePickerDialog(
						getActivity(),
						new DatePickerDialog.OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								c.set(year, monthOfYear, dayOfMonth);
								highSearch_endTime.setText(DateFormat.format(
										"yyy-MM-dd", c));
							}
						}, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
								.get(Calendar.DAY_OF_MONTH));
				dialog.show();
			}
		});

		
		
		local_RadioGroup = (RadioGroup) newsLayout.findViewById(R.id.local_RadioGroup);
		local_RadioButton_sure = (RadioButton) newsLayout.findViewById(R.id.local_RadioButton_sure);
		local_RadioButton_false = (RadioButton) newsLayout.findViewById(R.id.local_RadioButton_false);
		mainText_RadioGroup = (RadioGroup) newsLayout.findViewById(R.id.mainText_RadioGroup);
		mainText_RadioButton_sure = (RadioButton) newsLayout.findViewById(R.id.mainText_RadioButton_sure);
		mainText_RadioButton_false = (RadioButton) newsLayout.findViewById(R.id.mainText_RadioButton_false);
		filterGarbage_RadioGroup = (RadioGroup) newsLayout.findViewById(R.id.filterGarbage_RadioGroup);
		filterGarbage_RadioButton_sure = (RadioButton) newsLayout.findViewById(R.id.filterGarbage_RadioButton_sure);
		filterGarbage_RadioButton_false = (RadioButton) newsLayout.findViewById(R.id.filterGarbage_RadioButton_false);
		
		
		local_RadioGroup.setOnCheckedChangeListener(this);
		mainText_RadioGroup.setOnCheckedChangeListener(this);
		filterGarbage_RadioGroup.setOnCheckedChangeListener(this);
		
		
		
		return newsLayout;
	}
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch(group.getId()){
		   case R.id.local_RadioGroup:
			   if(checkedId == local_RadioButton_sure.getId()){
				   localMark = "1";
			   }else if(checkedId == local_RadioButton_false.getId()){
				   localMark = "0";
			   }
			   break;
		   case R.id.mainText_RadioGroup:
			   if(checkedId == mainText_RadioButton_sure.getId()){
				   contentType = "1";
			   }else if(checkedId == mainText_RadioButton_false.getId()){
				   contentType = "0";
			   }
			   break;
		   case R.id.filterGarbage_RadioGroup:
			   if(checkedId == filterGarbage_RadioButton_sure.getId()){
				   isGarbage = "1";
			   }else if(checkedId == filterGarbage_RadioButton_false.getId()){
				   isGarbage = "0";
			   }
			   break;
			   
		
		
		
		
		
		}
		
	}
	@Override
	public void onClick(View argView) {
		Intent intent = new Intent();
		switch (argView.getId()) {
		case R.id.click_high_search:
			builder.append(url +"?userId="+userId+"&orgId="+orgId);
			keywords = highSearch_keywords.getText().toString();
			siteName = highSearch_siteName.getText().toString();
			channelName = highSearch_channelName.getText().toString();
			author = highSearch_author.getText().toString();
			startDate = highSearch_publishTime.getText().toString();
			endDate = highSearch_endTime.getText().toString();
			
			Log.d("siteName+++++", siteName);
			
			if(keywords!=null&&keywords.trim().length()!=0){
				builder.append("&keywords="+keywords);
			}
			if(siteName!=null&&!siteName.equals("")){
				builder.append("&siteName="+siteName);
			}
			if(channelName!=null&&!channelName.equals("")){
				builder.append("&channelName="+channelName);
			}
			if(author!=null&&!author.equals("")){
				builder.append("&author="+author);
			}
			if(startDate!=null&&!startDate.equals("")){
				builder.append("&startDate="+startDate);
			}
			if(endDate!=null&&!endDate.equals("")){
				builder.append("&endDate="+endDate);
			}
			if(localMark!=null&&!localMark.equals("")){
				builder.append("&localMark="+localMark);
			}
			if(contentType!=null&&!contentType.equals("")){
				builder.append("&contentType="+contentType);
			}
			if(isGarbage!=null&&!isGarbage.equals("")){
				builder.append("&isGarbage="+isGarbage);
			}
			
			Log.d("++++++url", builder.toString());
			intent.putExtra("keywords",keywords);
			intent.putExtra("URL", builder.toString());
			
			builder.delete(0, builder.length());
			//url = baseUrl +"?userId"+userId+"&orgId"+orgId+"&keywords"+keywords;
			intent.setClass(getActivity(), QuicklySearchShow.class);
			
			break;

		default:
			break;
		}
		startActivity(intent);
		
	}

}
