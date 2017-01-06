package com.example.runcloud.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.runcloud.BaseActivity;
import com.example.runcloud.R;
import com.example.runcloud.adapt.NetizenAccountsAdapt;
import com.example.runcloud.adapt.QueryConditonAdapt;
import com.example.runcloud.application.MyApplication;
import com.example.runcloud.entity.HttpMessage;
import com.example.runcloud.entity.ImportentNetizen;
import com.example.runcloud.entity.NetizenShow;
import com.example.runcloud.entity.NetizenShowBasic;
import com.example.runcloud.entity.Netizens;
import com.example.runcloud.entity.UserInformation;
import com.example.runcloud.extra.CustomDialog;
import com.example.runcloud.extra.QueryConditionPopWindow;
import com.example.runcloud.fragment.FragmentMainActivity;
import com.example.runcloud.util.FunctionUtils;
import com.example.runcloud.util.HttpClientTool;
import com.example.runcloud.util.HttpUtils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class PutNetizen extends BaseActivity implements OnClickListener {
	/** 全局变量 */
	private MyApplication myApplication;
	/** 本地存储 */
	private SharedPreferences sp;

	private EditText siteName;
	private EditText account_number;
	private TextView account_number_delete;
	private TextView tip;
	private LinearLayout net_people_sina;
	private LinearLayout net_people_facebook;
	private LinearLayout net_people_twitter;

	private TextView sure;
	private TextView cancel;
	private TextView titleShow;
	private TextView backFirstPageView;
	private ImageView backView;

	/** url部分 */
	private String uri;
	private String rulesUrlPart = "/ares/restful/netizen/netizen?";
	private String baseRulesUrlPart = "/ares/restful/netizen/netizen";
	private String accountUrlPart = "/ares/restful/netizen/account?";
	/** 带userId和orderId的规则url */
	private String finalRulesUrl;
	private String finalBaseRulesUrl;
	private String finalAccountUrl;

	/** 重点网民集合 */
	private List<NetizenShowBasic> attendStr = new ArrayList<NetizenShowBasic>();
	// NetizenShowBasic netizenShowBasic = new NetizenShowBasic();
	private NetizenAccountsAdapt mAdapt;
	private ListView lv;

	/** 参数 */
	private String userId;
	private String orgId;
	private int accountType = 0;
	private String selectId;
	private String selectName;

	/** 弹出窗方面 */
	private QueryConditionPopWindow queryPopWindow;

	private JSONObject param = new JSONObject();
	private HttpResponse httpResponse;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setTranslucentStatus();
		setContentView(R.layout.net_people_setting);
		/** url */
		myApplication = (MyApplication) getApplication();
		userId = myApplication.getInformation().getUserId();
		orgId = myApplication.getInformation().getOrgId();

		sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
		uri = sp.getString("URI", "");

		finalAccountUrl = uri + accountUrlPart;
		finalBaseRulesUrl = uri + baseRulesUrlPart;
		finalRulesUrl = uri + rulesUrlPart + "userId=" + userId + "&orgId="
				+ orgId;

		siteName = (EditText) findViewById(R.id.siteName);
		account_number = (EditText) findViewById(R.id.account_number);
		account_number.setVisibility(View.GONE);

		account_number_delete = (TextView) findViewById(R.id.account_number_delete);
		account_number_delete.setVisibility(View.VISIBLE);
		account_number_delete.setOnClickListener(this);

		backFirstPageView = (TextView) findViewById(R.id.back_firstPage);
		backView = (ImageView) findViewById(R.id.back);
		backFirstPageView.setOnClickListener(this);
		backView.setOnClickListener(this);

		tip = (TextView) findViewById(R.id.tip);
		sure = (TextView) findViewById(R.id.add_people_sure);
		cancel = (TextView) findViewById(R.id.add_people_cancel);
		sure.setOnClickListener(this);
		cancel.setOnClickListener(this);

		Intent intent = getIntent();
		selectName = intent.getStringExtra("SelectName");
		selectId = intent.getStringExtra("SelectId");
		siteName.setText(selectName);

		net_people_sina = (LinearLayout) findViewById(R.id.net_people_sina);
		net_people_facebook = (LinearLayout) findViewById(R.id.net_people_facebook);
		net_people_twitter = (LinearLayout) findViewById(R.id.net_people_twitter);
		net_people_sina.setOnClickListener(this);
		net_people_facebook.setOnClickListener(this);
		net_people_twitter.setOnClickListener(this);

		titleShow = (TextView) findViewById(R.id.title_show2);
		titleShow.setText("编辑网民");

	}

	private class GetNetizanShowTask extends AsyncTask<Void, Void, NetizenShow> {

		@Override
		protected NetizenShow doInBackground(Void... arg0) {
			Intent intent = getIntent();
			// selectId =intent.getStringExtra("SelectId");

			String url = finalRulesUrl + "&netizenId=" + selectId;
			String jsonStr = HttpUtils.getJsonStr(url);
			NetizenShow netizenShow = HttpUtils.NetizenShowFromJson(jsonStr);
			return netizenShow;
		}

		@Override
		protected void onPostExecute(NetizenShow netizenShow) {
			if (netizenShow == null) {
				FunctionUtils.showCustomDialog("服务器异常暂无数据，\n请稍后刷新...",
						PutNetizen.this);
				return;
			}
			if (accountType != 0) {
				attendStr.clear();
				List<NetizenShowBasic> attendStrs = new ArrayList<NetizenShowBasic>();
				for (NetizenShowBasic basic : netizenShow.getAccountList()) {
					if (basic.getCategory().equals(String.valueOf(accountType))) {
						attendStr.add(basic);
					}
				}

			} else {
				attendStr.clear();
				attendStr = netizenShow.getAccountList();
			}

			if (attendStr.size() != 0) {
				queryPopWindow = new QueryConditionPopWindow(PutNetizen.this,
						itemsOnClick);
				queryPopWindow.setHeight(LayoutParams.WRAP_CONTENT);
				int w = account_number_delete.getMeasuredWidth();
				queryPopWindow.setWidth(w);
				queryPopWindow.showAsDropDown(account_number_delete);
				View queryPopView = queryPopWindow.getContentView();
				lv = (ListView) queryPopView.findViewById(R.id.query_pop);
				mAdapt = new NetizenAccountsAdapt(PutNetizen.this, attendStr);
				lv.setAdapter(mAdapt);
				// mAdapt.notifyDataSetChanged();
				lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {

						showAlertDialog(view, position);
					}
				});
			} else {
				if (accountType == 0) {
					FunctionUtils.showCustomDialog("当前网民\n没有关注账号",
							PutNetizen.this);
				} else if (accountType == 2) {
					FunctionUtils.showCustomDialog("当前网民\n没有新浪微博关注账号",
							PutNetizen.this);
				} else if (accountType == 7) {
					FunctionUtils.showCustomDialog("当前网民\n没有Facebook关注账号",
							PutNetizen.this);
				} else if (accountType == 1) {
					FunctionUtils.showCustomDialog("当前网民\n没有Twitter关注账号",
							PutNetizen.this);
				}
			}
		}

	}

	private class DeleteAccountTask extends AsyncTask<Void, Void, Integer> {
		int position;
		String mUrl;

		public DeleteAccountTask(int position, String mUrl) {
			this.position = position;
			this.mUrl = mUrl;

		}

		@Override
		protected Integer doInBackground(Void... arg0) {
			String accountId = attendStr.get(position).getAccountId();
			String netizenId = selectId;
			String uri = mUrl + "netizenId=" + netizenId + "&accountId="
					+ accountId + "&userId=" + userId + "&orgId=" + orgId;
			Log.d("+++++uri", uri);
			int code = HttpUtils.doDelete(uri);
			Log.d("+++++code", code + "");
			return code;
		}

		@Override
		protected void onPostExecute(Integer code) {
			if (code == 200) {
				Log.d("+++++++++++++", "到这里了");
				attendStr.remove(position);
				mAdapt.notifyDataSetChanged();
			}
		}

	}

	/** 提示框 */
	public void showAlertDialog(View view, final int position) {

		CustomDialog.Builder builder = new CustomDialog.Builder(this);
		builder.setMessage("确定要删除吗？");
		builder.setTitle("提示");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

				new DeleteAccountTask(position, finalAccountUrl).execute();
				dialog.dismiss();
				// 设置你的操作事项
			}
		});

		builder.setNegativeButton("取消",
				new android.content.DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

		builder.create().show();

	}

	/** 判断网民是否为空的Task */
	private class NetizenJudgementTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... arg0) {
			return rulesUrlPart;
		}

		@Override
		protected void onPostExecute(String urlPart) {
			if (TextUtils.isEmpty(siteName.getText())) {
				FunctionUtils.showCustomDialog("网民名称不能为空", PutNetizen.this);
				return;
			}
			try {
				param.put("netizenName", siteName.getText().toString());
				param.put("netizenId", selectId);
				param.put("userId", userId);
				param.put("orgId", orgId);
				new NetizenPutTask().execute();
			} catch (JSONException e) {
				Log.d("JSONException", e.getMessage());
				e.printStackTrace();
			}

		}
	}

	/** 判断规则是否为空的Task */
	private class NetizenPutTask extends AsyncTask<Void, Void, Integer> {

		@Override
		protected Integer doInBackground(Void... arg0) {
			int code;
			HttpPut httpPut = new HttpPut(finalBaseRulesUrl);
			StringEntity se;
			try {
				se = new StringEntity(param.toString(), "utf-8");
				httpPut.setEntity(se);
				// 发送请求
				httpResponse = HttpClientTool.getHttpClient().execute(httpPut);
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
					HttpMessage httpMessage = HttpUtils
							.HttpMessageFromJson(rev);
					if (httpMessage.getResultCode().equals("000000")) {
						/*
						 * FunctionUtils.showCustomDialog("添加成功",
						 * PutNetizen.this);
						 */
						Intent intent = new Intent(PutNetizen.this,
								MyNetizens.class);
						startActivity(intent);
					} else {
						FunctionUtils.showCustomDialog(
								httpMessage.getMessage(), PutNetizen.this);
						return;
					}

				} catch (ParseException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				FunctionUtils.showCustomDialog("与服务器失去连接，\n请稍后再试",
						PutNetizen.this);
			}

		}
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.add_people_sure:
			// dopost();
			new NetizenJudgementTask().execute();
			break;
		case R.id.account_number_delete:
			// account_number_delete.setBackgroundColor(Color.RED);
			new GetNetizanShowTask().execute();
			// Log.d("+++++++++++", "真的点了我");
			break;
		case R.id.net_people_sina:
			net_people_sina
					.setBackgroundResource(R.drawable.layout_sina_bottom_selected_style);
			accountType = 2;
			tip.setText("当前网民所关注的新浪微博账号");
			net_people_facebook
					.setBackgroundResource(R.drawable.layout_facebook_bottom_style);
			net_people_twitter
					.setBackgroundResource(R.drawable.layout_twitter_bottom_style);

			return;
		case R.id.net_people_facebook:
			net_people_facebook
					.setBackgroundResource(R.drawable.layout_facebook_bottom_selected_style);
			accountType = 7;
			tip.setText("当前网民所关注的Facebook账号");
			net_people_sina
					.setBackgroundResource(R.drawable.layout_sina_bottom_style);
			net_people_twitter
					.setBackgroundResource(R.drawable.layout_twitter_bottom_style);

			return;
		case R.id.net_people_twitter:
			net_people_twitter
					.setBackgroundResource(R.drawable.layout_twitter_bottom_selected_style);
			accountType = 1;
			tip.setText("当前网民所关注的Twitter账号");
			net_people_sina
					.setBackgroundResource(R.drawable.layout_sina_bottom_style);
			net_people_facebook
					.setBackgroundResource(R.drawable.layout_facebook_bottom_style);

			return;
		case R.id.back_firstPage:
			Intent intent = new Intent(this, FragmentMainActivity.class);
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
	}

	/** 为弹出窗口(PopUpWindow)实现监听类 */
	private OnClickListener itemsOnClick = new OnClickListener() {

		public void onClick(View v) {

		}

	};
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(0, R.anim.base_slide_right_out);
	}
}
