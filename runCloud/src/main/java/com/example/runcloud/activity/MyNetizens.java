package com.example.runcloud.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.baoyz.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.baoyz.swipemenulistview.SwipeMenuListView.OnSwipeListener;
import com.example.runcloud.BaseActivity;
import com.example.runcloud.R;
import com.example.runcloud.adapt.MyAttentionAdapt;
import com.example.runcloud.adapt.MyAttentionAdapt.MyClickListener;
import com.example.runcloud.application.MyApplication;
import com.example.runcloud.entity.ImportentNetizen;
import com.example.runcloud.entity.MyAttentionListItem;
import com.example.runcloud.entity.NetEvent;
import com.example.runcloud.entity.Netizens;
import com.example.runcloud.entity.SensitiveMessage;
import com.example.runcloud.extra.CustomDialog;
import com.example.runcloud.extra.CustomProgressDialog;
import com.example.runcloud.fragment.FragmentMainActivity;
import com.example.runcloud.receiver.NetReceiver;
import com.example.runcloud.util.FunctionUtils;
import com.example.runcloud.util.HttpDeleteWithBody;
import com.example.runcloud.util.HttpUtils;
import com.example.runcloud.util.NetUtils;

import de.greenrobot.event.EventBus;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyNetizens extends BaseActivity implements OnClickListener {
	/** 全局变量 */
	private MyApplication myApplication;
	/** 本地存储 */
	private SharedPreferences sp;
	/** 参数 */
	private String userId;
	private String orgId;
	/** url部分 */
	private String uri;
	private String rulesUrlPart = "/ares/restful/netizen/netizens?";
	private String netizenUrlPart = "/ares/restful/netizen/netizen";
	/** 带userId和orderId的规则url */
	private String finalRulesUrl;
	private String finalNetizenUrl;
	/** 重点网民集合 */
	private List<ImportentNetizen> attendStr = new ArrayList<ImportentNetizen>();

	private int currentPosition;

	// private List<MyAttentionListItem> list = new
	// ArrayList<MyAttentionListItem>();
	private MyAttentionAdapt mAdapter;
	private SwipeMenuListView mListView;

	private TextView backFirstPageView;
	private TextView add_attentionText;
	private RelativeLayout add_attention_button;
	private ImageView backView;
	private TextView titleShow;

	private NetReceiver mReceiver;
	private RelativeLayout no_network_rl;
	private boolean isConnected;
	
	private CustomProgressDialog progressDialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setTranslucentStatus();
		setContentView(R.layout.my_attention_list);

		// 监听网络
		initReceive();
		no_network_rl = (RelativeLayout) findViewById(R.id.net_view_rl);
		EventBus.getDefault().register(this);

		/** url */
		myApplication = (MyApplication) getApplication();
		userId = myApplication.getInformation().getUserId();
		orgId = myApplication.getInformation().getOrgId();
		sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
		uri = sp.getString("URI", "");

		finalRulesUrl = uri + rulesUrlPart + "userId=" + userId + "&orgId="
				+ orgId;
		finalNetizenUrl = uri + netizenUrlPart;

		backFirstPageView = (TextView) findViewById(R.id.back_firstPage);
		backView = (ImageView) findViewById(R.id.back);
		titleShow = (TextView) findViewById(R.id.title_show2);
		add_attentionText = (TextView) findViewById(R.id.add_attentionText);
		add_attentionText.setText("添加网民");
		titleShow.setText("网民设置");

		add_attention_button = (RelativeLayout) findViewById(R.id.add_attention_button);

		backFirstPageView.setOnClickListener(this);
		backView.setOnClickListener(this);
		add_attention_button.setOnClickListener(this);

		new GetNetizansTask().execute();

		// initFruits();

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent();
			intent.setClass(this, SettingActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onDestroy() {
		unregisterReceiver(mReceiver);
		EventBus.getDefault().unregister(this);
		super.onDestroy();
	}

	/** 初始化网络的广播接收器 */
	private void initReceive() {
		mReceiver = new NetReceiver();
		IntentFilter mFilter = new IntentFilter();
		mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(mReceiver, mFilter);
	}

	/** EventBus的onEventMainThread方法接收到NetEvent */
	public void onEventMainThread(NetEvent event) {

		setNetState(event.isNet());
	}

	public void setNetState(boolean netState) {

		if (no_network_rl != null) {
			no_network_rl.setVisibility(netState ? View.GONE : View.VISIBLE);
			no_network_rl.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					NetUtils.startToSettings(MyNetizens.this);
				}
			});
		}
	}

	private class GetNetizansTask extends AsyncTask<Void, Void, Netizens> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// dialog = ProgressDialog.show(Validate.this, "登录提示",
			// "正在登录，请稍等...",false);
			startProgressDialog();
		};

		@Override
		protected Netizens doInBackground(Void... arg0) {
			String url = finalRulesUrl;
			String jsonStr = HttpUtils.getJsonStr(url);
			Netizens netizens = HttpUtils.NetizensFromJson(jsonStr);
			return netizens;
		}

		@Override
		protected void onPostExecute(Netizens netizens) {
			stopProgressDialog();
			if (netizens == null) {
				FunctionUtils.showCustomDialog("服务器异常暂无数据，\n请稍后刷新...",
						MyNetizens.this);
				return;
			}
			attendStr = netizens.getNetizenList();
			mListView = (SwipeMenuListView) findViewById(R.id.myAttention_listView);
			mAdapter = new MyAttentionAdapt(getApplicationContext(), attendStr,
					mListener);

			mListView.setAdapter(mAdapter);
			mAdapter.notifyDataSetChanged();

			// step 1. create a MenuCreator
			SwipeMenuCreator creator = new SwipeMenuCreator() {

				@Override
				public void create(SwipeMenu menu) {

					// create "delete" item
					SwipeMenuItem deleteItem = new SwipeMenuItem(
							getApplicationContext());
					// set item background
					deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
							0x3F, 0x25)));
					// set item width
					deleteItem.setWidth(dp2px(90));
					// set a icon
					deleteItem.setIcon(R.drawable.delete_attention2x);
					// add to menu
					menu.addMenuItem(deleteItem);
				}
			};
			// set creator
			mListView.setMenuCreator(creator);

			// step 2. listener item click event
			mListView.setOnMenuItemClickListener(new OnMenuItemClickListener() {
				@Override
				public void onMenuItemClick(int position, SwipeMenu menu,
						int index) {
					switch (index) {
					case 0:
						// delete
						showAlertDialog(position);
						// new DeleteNetizansTask(position,
						// finalNetizenUrl).execute();
						// attendStr.remove(position);

						break;
					}
				}
			});

			// set SwipeListener
			mListView.setOnSwipeListener(new OnSwipeListener() {

				@Override
				public void onSwipeStart(int position) {
					// swipe start
				}

				@Override
				public void onSwipeEnd(int position) {
					// swipe end
				}
			});

		}
	}

	/** 提示框 */
	public void showAlertDialog(final int position) {

		CustomDialog.Builder builder = new CustomDialog.Builder(this);
		builder.setMessage("确定要删除吗？");
		builder.setTitle("提示");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

				new DeleteNetizansTask(position, finalNetizenUrl).execute();
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

	private class DeleteNetizansTask extends AsyncTask<Void, Void, Integer> {
		int position;
		String mUrl;

		public DeleteNetizansTask(int position, String mUrl) {
			this.position = position;
			this.mUrl = mUrl;

		}

		@Override
		protected Integer doInBackground(Void... arg0) {
			String id = attendStr.get(position).getSelectId();
			String uri = mUrl + "?netizenId=" + id + "&userId=" + userId
					+ "&orgId=" + orgId;
			int code = HttpUtils.doDelete(uri);

			return code;
		}

		@Override
		protected void onPostExecute(Integer code) {
			if (code == 200) {
				Log.d("+++++++++++++", "到这里了");
				attendStr.remove(position);
				mAdapter.notifyDataSetChanged();
			}
		}
	}

	private int dp2px(int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				getResources().getDisplayMetrics());
	}

	@Override
	public void onClick(View view) {
		Intent intent = null;
		switch (view.getId()) {
		case R.id.back_firstPage:
			intent = new Intent(this, FragmentMainActivity.class);
			startActivity(intent);
			break;
		case R.id.add_attention_button:
			intent = new Intent(this, AddNetizen.class);
			startActivity(intent);
			overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
			break;
		case R.id.back:
			intent = new Intent();
			intent.setClass(this, SettingActivity.class);
			startActivity(intent);
			break;

		}
		

	}

	private MyClickListener mListener = new MyClickListener() {
		@Override
		public void myOnClick(int position, View v) {
			Intent intent = new Intent();
			intent.putExtra("SelectId", attendStr.get(position).getSelectId());
			intent.putExtra("SelectName", attendStr.get(position)
					.getSelectName());
			intent.setClass(getApplicationContext(), PutNetizen.class);
			startActivity(intent);
			overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
		}

	};
	
	private void startProgressDialog() {
		if (progressDialog == null) {
			progressDialog = CustomProgressDialog.createDialog(this);
			progressDialog.setMessage("正在加载中，\n请稍等...");
		}

		progressDialog.show();
	}

	private void stopProgressDialog() {
		if (progressDialog != null) {
			progressDialog.dismiss();
			progressDialog = null;
		}
	}

}
