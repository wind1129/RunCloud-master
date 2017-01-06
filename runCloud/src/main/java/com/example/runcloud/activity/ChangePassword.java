package com.example.runcloud.activity;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.runcloud.BaseActivity;
import com.example.runcloud.R;
import com.example.runcloud.application.MyApplication;
import com.example.runcloud.entity.HttpMessage;
import com.example.runcloud.fragment.FragmentMainActivity;
import com.example.runcloud.util.EncryptUtils;
import com.example.runcloud.util.FunctionUtils;
import com.example.runcloud.util.HttpClientTool;
import com.example.runcloud.util.HttpUtils;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ChangePassword extends BaseActivity implements OnClickListener{
	/**全局变量*/
	private MyApplication myApplication;
	/**本地存储*/
	private SharedPreferences sp;
	private String userId;
	private String orgId;
	
	/**url部分*/
	private String uri;
	private String baseUrl ="/ares/restful/user/user";
	private String finalBaseUrl;
	
	private ImageView backView;
	private TextView titleView;
	private EditText orgial_password;
	private EditText new_password;
	private EditText new_password_again;
	private RelativeLayout sure_change_password;
	private HttpResponse httpResponse;
	
	JSONObject param = new JSONObject(); 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setTranslucentStatus();
		setContentView(R.layout.change_password);
		/**url*/
		myApplication =(MyApplication)getApplication();
		userId = myApplication.getInformation().getUserId();
		orgId =myApplication.getInformation().getOrgId();
		
		sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
		uri = sp.getString("URI", "");
		
		finalBaseUrl =uri+baseUrl;
		
		titleView =(TextView) findViewById(R.id.content_title_text);
		titleView.setText("修改密码");
		
		orgial_password = (EditText) findViewById(R.id.orgial_password);
		new_password = (EditText) findViewById(R.id.new_password);
		new_password_again = (EditText) findViewById(R.id.new_password_again);
		sure_change_password = (RelativeLayout) findViewById(R.id.sure_change_password);
		
		sure_change_password.setOnClickListener(this);
		
		backView = (ImageView)findViewById(R.id.content_back);
		backView.setOnClickListener(this);
		
		
	}
	
	/** 判断网民是否为空的Task */
	private class ChangePwdJudgementTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... arg0) {
			return baseUrl;
		}

		@Override
		protected void onPostExecute(String baseUrl) {
			if (TextUtils.isEmpty(orgial_password.getText())) {
				FunctionUtils.showCustomDialog("原密码不能为空", ChangePassword.this);
				return;
			}
			if (TextUtils.isEmpty(new_password.getText())) {
				FunctionUtils.showCustomDialog("新密码不能为空", ChangePassword.this);
				return;
			}
			if (TextUtils.isEmpty(new_password_again.getText())) {
				FunctionUtils.showCustomDialog("新密码确认不能为空", ChangePassword.this);
				return;
			}
			
			if(!new_password_again.getText().toString().equals(new_password.getText().toString())){
				FunctionUtils.showCustomDialog("两次密码不一致", ChangePassword.this);
				return;
			}
			try {
				param.put("exPassword", EncryptUtils.encodeMD5String(orgial_password.getText().toString()));
				param.put("password", EncryptUtils.encodeMD5String(new_password_again.getText().toString()));
				param.put("userId", userId);
				param.put("orgId", orgId);
				new ChangePwdPutTask().execute();
			} catch (JSONException e) {
				Log.d("JSONException", e.getMessage());
				e.printStackTrace();
			}

		}
	}
	
	
	/** 判断规则是否为空的Task */
	private class ChangePwdPutTask extends AsyncTask<Void, Void, Integer> {

		@Override
		protected Integer doInBackground(Void... arg0) {
			int code;
			HttpPut httpPut = new HttpPut(finalBaseUrl);
			StringEntity se;
			try {
				se = new StringEntity(param.toString(), "utf-8");
				httpPut.setEntity(se);
				// 发送请求
				httpResponse = HttpClientTool.getHttpClient()
						.execute(httpPut);
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
				Log.d("++++++++++++++++++++++++rev", rev);
				HttpMessage httpMessage = HttpUtils.HttpMessageFromJson(rev);
				if(httpMessage.getResultCode().equals("000000")){
					FunctionUtils.showCustomDialog("密码修改成功",
							ChangePassword.this);
				}else{
					FunctionUtils.showCustomDialog(httpMessage.getMessage(),
							ChangePassword.this);
					return;
				}
		
				Intent intent = new Intent();
				 intent.putExtra("FramgmentId", "Tab5");
				intent.setClass(ChangePassword.this, FragmentMainActivity.class);
				startActivity(intent);
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				FunctionUtils.showCustomDialog("与服务器失去连接，\n请稍后再试",
						ChangePassword.this);
			}

		}
	}
	
	
	private void doput(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {		
		
	try {
		JSONObject param = new JSONObject(); 
		param.put("exPassword", EncryptUtils.encodeMD5String(orgial_password.getText().toString()));
		param.put("password", EncryptUtils.encodeMD5String(new_password_again.getText().toString()));
		param.put("userId", userId);
		param.put("orgId", orgId);
		

		HttpPut httpPut = new HttpPut(finalBaseUrl);
		
		StringEntity se = new StringEntity(param.toString(), "utf-8"); 
		httpPut.setEntity(se);
		// 发送请求   
		HttpResponse httpResponse = HttpClientTool.getHttpClient().execute(httpPut); 
			
		int code=httpResponse.getStatusLine().getStatusCode();
		Log.d("++++++++++++++++++++", String.valueOf(code));
		if(code==200){
			String rev = EntityUtils.toString(httpResponse.getEntity());
			Log.d("++++++++++++++++++++++++rev", rev);
			Intent intent = new Intent(ChangePassword.this, FragmentMainActivity.class);
			startActivity(intent);
		  }
		}catch (Exception e) {
	   }
	 }		
	}).start();
   }

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		 case R.id.content_back:
			  finish();
			  overridePendingTransition(0, R.anim.base_slide_right_out);
				return;
		case R.id.sure_change_password:
			new ChangePwdJudgementTask().execute();
			break;

		default:
			break;
		}

		
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(0, R.anim.base_slide_right_out);
	}

}
