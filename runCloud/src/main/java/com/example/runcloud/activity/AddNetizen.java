package com.example.runcloud.activity;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.runcloud.BaseActivity;
import com.example.runcloud.R;
import com.example.runcloud.application.MyApplication;
import com.example.runcloud.entity.HttpMessage;
import com.example.runcloud.entity.SubSensRulesShowJson;
import com.example.runcloud.entity.SubSensRulesShowPart;
import com.example.runcloud.entity.SubSensRulesShowText;
import com.example.runcloud.entity.UserInformation;
import com.example.runcloud.fragment.FragmentMainActivity;
import com.example.runcloud.util.FunctionUtils;
import com.example.runcloud.util.HttpClientTool;
import com.example.runcloud.util.HttpUtils;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AddNetizen extends BaseActivity implements OnClickListener{
	/**全局变量*/
	private MyApplication myApplication;
	/**本地存储*/
	private SharedPreferences sp;
	
	private EditText siteName;
	private EditText account_number;
	private TextView tip;
	private LinearLayout net_people_sina;
	private LinearLayout net_people_facebook;
	private LinearLayout net_people_twitter;
	
	private TextView titleShow;
	private TextView backFirstPageView;
	private ImageView backView;
	
	private String uri;
	private String urlPart ="/ares/restful/netizen/netizen";
	private String finalURL;
	
	private TextView sure;
	private TextView cancel;
	/**参数*/
	private String userId;
	private String orgId;
	private String defaultNetizenGroupId;
	private int accountType = 0;
	
	private JSONObject param = new JSONObject();
	HttpResponse httpResponse;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTranslucentStatus();
	    //setContentView(R.layout.net_people_setting);
	    /**url*/
		myApplication =(MyApplication)getApplication();
		userId = myApplication.getInformation().getUserId();
		orgId =myApplication.getInformation().getOrgId();
		defaultNetizenGroupId = myApplication.getInformation().getDefaultNetizenGroupId();
		
		sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
		uri = sp.getString("URI", "");
		
		finalURL = uri + urlPart;
		
		backFirstPageView =(TextView) findViewById(R.id.back_firstPage);
		backView = (ImageView) findViewById(R.id.back);
		backFirstPageView.setOnClickListener(this);
		backView.setOnClickListener(this);
	    
	    siteName = (EditText) findViewById(R.id.siteName);
	    account_number = (EditText) findViewById(R.id.account_number);
	    account_number.setFocusable(true);
	    tip = (TextView) findViewById(R.id.tip);
	    sure =(TextView) findViewById(R.id.add_people_sure);
	    cancel =(TextView) findViewById(R.id.add_people_cancel);
	    net_people_sina = (LinearLayout) findViewById(R.id.net_people_sina);
	    net_people_facebook = (LinearLayout) findViewById(R.id.net_people_facebook);
	    net_people_twitter = (LinearLayout) findViewById(R.id.net_people_twitter);
	    net_people_sina.setOnClickListener(this);
	    net_people_facebook.setOnClickListener(this);
	    net_people_twitter.setOnClickListener(this);
	    sure.setOnClickListener(this);
	    cancel.setOnClickListener(this);
	    
	    titleShow = (TextView) findViewById(R.id.title_show2);
		titleShow.setText("添加网民");
	}

	@Override
	public void onClick(View view) {
		//Intent intent = new Intent();
		switch (view.getId()) {
		case R.id.net_people_sina:
			net_people_sina.setBackgroundResource(R.drawable.layout_sina_bottom_selected_style);
			accountType = 2;
			tip.setText("新浪微博：请在帐号名称一栏输入昵称，例如：姚晨");
			net_people_facebook.setBackgroundResource(R.drawable.layout_facebook_bottom_style);
			net_people_twitter.setBackgroundResource(R.drawable.layout_twitter_bottom_style);
			
			return;
		case R.id.net_people_facebook:
			net_people_facebook.setBackgroundResource(R.drawable.layout_facebook_bottom_selected_style);
			accountType = 7;
			tip.setText("Facebook：请在账号名称一栏输入昵称，例如：James");
			net_people_sina.setBackgroundResource(R.drawable.layout_sina_bottom_style);
			net_people_twitter.setBackgroundResource(R.drawable.layout_twitter_bottom_style);
			
			return;
		case R.id.net_people_twitter:
			net_people_twitter.setBackgroundResource(R.drawable.layout_twitter_bottom_selected_style);
			accountType = 1;
			tip.setText("Twitter：请在账号名称一栏输入昵称，例如：WangDan1985");
			net_people_sina.setBackgroundResource(R.drawable.layout_sina_bottom_style);
			net_people_facebook.setBackgroundResource(R.drawable.layout_facebook_bottom_style);
			
			return;
		case R.id.add_people_sure:
			new NetizenJudgementTask().execute();
			//dopost();
			//intent.setClass(this, MyNetizens.class);
			break;
		case R.id.back_firstPage:
			Intent intent =new Intent(this, FragmentMainActivity.class);
			startActivity(intent);
			break;
		case R.id.back:
			finish();
			overridePendingTransition(0, R.anim.base_slide_right_out);
			return;
		case R.id.add_people_cancel:
			finish();
			overridePendingTransition(0, R.anim.base_slide_right_out);
			return;
		}
		//startActivity(intent);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(0, R.anim.base_slide_right_out);
	}
	
	/** 判断网民是否为空的Task */
	private class NetizenJudgementTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... arg0) {
			return urlPart;
		}

		@Override
		protected void onPostExecute(String urlPart) {
			if (TextUtils.isEmpty(siteName.getText())) {
				FunctionUtils.showCustomDialog("网民名称不能为空", AddNetizen.this);
				return;
			}
			if (accountType == 0) {
				FunctionUtils.showCustomDialog("网站类型不能为空，\n请选择网站类型",
						AddNetizen.this);
				return;
			}
			if (TextUtils.isEmpty(account_number.getText())) {
				FunctionUtils.showCustomDialog("账号名称不能为空", AddNetizen.this);
				return;
			}
			
			try {
				param.put("netizen", siteName.getText().toString());
			    param.put("account", account_number.getText().toString());
			    param.put("accountType", String.valueOf(accountType));
			    param.put("userId", userId);
			    param.put("orgId", orgId);
			    param.put("defaultNetizenGroupId", defaultNetizenGroupId);
			    new NetizenPostTask().execute();
			} catch (JSONException e) {
				FunctionUtils.showCustomDialog("缺失参数，\n请稍后再试",
						AddNetizen.this);
			}

		}
	}

	/** 判断规则是否为空的Task */
	private class NetizenPostTask extends AsyncTask<Void, Void, Integer> {

		@Override
		protected Integer doInBackground(Void... arg0) {
			int code;
			HttpPost httpPost = new HttpPost(finalURL);
			StringEntity se;
			try {
				se = new StringEntity(param.toString(), "utf-8");
				httpPost.setEntity(se);
				// 发送请求
				httpResponse = HttpClientTool.getHttpClient()
						.execute(httpPost);
				code = httpResponse.getStatusLine().getStatusCode();
				return code;
			} catch (Exception e) {

				return 0;
			}

		}

		@Override
		protected void onPostExecute(Integer code) {
			if (code == 200) {
				String rev;
				try {
					rev = EntityUtils.toString(httpResponse.getEntity());
					  //Log.d("++++++++++++++++++++++++rev", rev);
					HttpMessage httpMessage = HttpUtils.HttpMessageFromJson(rev);
					Log.d("++++++httpMessage", httpMessage.getResultCode());
					if(httpMessage.getResultCode().equals("000000")){
						/*
						 * FunctionUtils.showCustomDialog("添加成功",
						 * AddNetizen.this);
						 */
						Intent intent = new Intent(AddNetizen.this, MyNetizens.class);
						startActivity(intent);
					}else{
						FunctionUtils.showCustomDialog(httpMessage.getMessage(),
								AddNetizen.this);
						return;
					}	
				
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				FunctionUtils.showCustomDialog("与服务器失去连接，\n请稍后再试",
						AddNetizen.this);
			}

		}
	}
}
