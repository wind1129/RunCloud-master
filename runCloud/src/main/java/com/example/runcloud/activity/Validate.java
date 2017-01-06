package com.example.runcloud.activity;

import java.io.IOException;
import java.security.KeyStore;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import com.example.runcloud.mvp.utils.SPUtil;

import com.example.runcloud.BaseActivity;
import com.example.runcloud.R;
import com.example.runcloud.application.MyApplication;
import com.example.runcloud.entity.UserInformation;
import com.example.runcloud.extra.CustomDialog;
import com.example.runcloud.extra.CustomDialogEdit;
import com.example.runcloud.extra.CustomProgressDialog;
import com.example.runcloud.fragment.FragmentMainActivity;
import com.example.runcloud.util.*;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Validate extends BaseActivity implements OnClickListener {
	private static final String TAG = "JPush";
	private MyApplication myApplication;
	private CheckView mMyView = null;
	private String[] res = new String[4];// 获取每次更新的验证码，可用于判断用户输入是否正确
	private String markOfUser = "";

	private TextView set_ip;
	private CheckBox remember_password;
	private TextView usernameTextView;
	private TextView passwordTextView;
	private EditText validate_validate;

	private RelativeLayout validate_footer;
	private RelativeLayout validate_root;

	private TextView forget_password;

	private Button button;

	/** 本地存储 */
	private SharedPreferences sp;
	private Editor editor;

	private String userName;
	private String password;
	private String oraginPassword;
	private String uriPart = "/ares/restful/login/login";

	private HttpResponse response;

	private long exitTime = 0;

	private static final int MSG_SET_ALIAS = 1001;

	private String alias;

	private ProgressDialog dialog = null;
	private CustomProgressDialog progressDialog = null;

	/** ip信息 */
	// private String text ="start";
	private String text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setTranslucentStatus();
		setContentView(R.layout.validate);
		myApplication = (MyApplication) getApplication();
		sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
		if (sp != null) {
			text = sp.getString("URI", "");
		}
		if(text.equals("")|text == null){
			text ="https://rundata.cc";
			editor = sp.edit();
			editor.putString("URI", text);
			editor.commit();
			SPUtil.save("BasicURI",text);
		}




		mMyView = (CheckView) findViewById(R.id.checkView);
		mMyView.setOnClickListener(this);
		// 初始化验证码
		res = mMyView.getValidataAndSetImage();
		for (String s : res) {
			markOfUser = markOfUser + s;
		}

		set_ip = (TextView) findViewById(R.id.set_ip);
		set_ip.setOnClickListener(this);
		remember_password = (CheckBox) findViewById(R.id.remember_password);
		usernameTextView = (TextView) findViewById(R.id.validate_username);
		passwordTextView = (TextView) findViewById(R.id.validate_password);
		validate_validate = (EditText) findViewById(R.id.validate_validate);
		button = (Button) findViewById(R.id.validate_hit);

		forget_password = (TextView) findViewById(R.id.forget_password);
		forget_password.setOnClickListener(this);

		// 判断记住密码多选框的状态
		if (sp.getBoolean("ISCHECK", false)) {
			// 设置默认是记录密码状态
			remember_password.setChecked(true);
			usernameTextView.setText(sp.getString("USERNAME", ""));
			passwordTextView.setText(sp.getString("PASSWORD", ""));
		}

		validate_footer = (RelativeLayout) findViewById(R.id.validate_footer);
		validate_root = (RelativeLayout) findViewById(R.id.validate_root);

		//onKeyboardLayout(validate_root, validate_root);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				userName = usernameTextView.getText().toString();
				oraginPassword = passwordTextView.getText().toString();
				password = EncryptUtils.encodeMD5String(oraginPassword);
				if (validate_validate.getText() == null
						| validate_validate.getText().toString().equals("")) {
					/*
					 * Toast.makeText(getApplicationContext(), "验证码为空",
					 * Toast.LENGTH_SHORT).show();
					 */
					FunctionUtils.showCustomDialog("验证码不能为空", Validate.this);
				} else if (validate_validate.getText().toString()
						.equals(markOfUser)) {
					if (remember_password.isChecked()) {
						editor = sp.edit();
						editor.putString("USERNAME", userName);
						editor.putString("PASSWORD", oraginPassword);
						editor.commit();
					}
					new HttpGetTask().execute();
				} else {
					FunctionUtils.showCustomDialog("验证码输入不正确", Validate.this);
				}

			}
		});

		// 监听记住密码多选框按钮事件
		remember_password
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (remember_password.isChecked()) {

							System.out.println("记住密码已选中");
							sp.edit().putBoolean("ISCHECK", true).commit();

						} else {

							System.out.println("记住密码没有选中");
							sp.edit().putBoolean("ISCHECK", false).commit();

						}

					}
				});

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "再按一次退出程序",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {

				myApplication.exit();
				// finish();
				// System.exit(0);
				// android.os.Process.killProcess(android.os.Process.myPid());
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private class HttpGetTask extends AsyncTask<Void, Void, Integer> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			//dialog = ProgressDialog.show(Validate.this, "登录提示", "正在登录，请稍等...",false);
			startProgressDialog();
		};

		@Override
		protected Integer doInBackground(Void... arg0) {
			int code;
			/** https双向验证 */
			Context ctx = Validate.this.getBaseContext();
			// 把客户端证书和私钥用openssl导出为P12格式
			String p12File = "clientkey.p12";
			// 你需要知道P12的密码
			String pwd1 = "client4runc";
			//String pwd1 = "client";
			// 生成KeyStore，密码为P12的密码
			KeyStore keyStore1 = KsManager.getKeyStoreByP12(ctx, p12File, pwd1);

			// 服务器证书
			String serverCrt = "tomcat.cer";
			// 生成TrustStore
			KeyStore trustStore2 = KsManager.getTrustStoreByCrt(ctx, serverCrt);

			HttpClientTool.init(keyStore1, pwd1, trustStore2);

			JSONObject param = new JSONObject();
			try {
				param.put("userName", userName);
				param.put("password", password);
				response = HttpUtils.doPost(text + uriPart, param);

				if (response != null) {
					code = response.getStatusLine().getStatusCode();

				} else {
					return 0;
				}
			} catch (JSONException e) {
				e.printStackTrace();
				return 0;
			}
			return code;
		}

		@Override
		protected void onPostExecute(Integer code) {
			//Log.i("+++++",code+"");
			if (code == 200 && code != null) {
				String rev;
				try {
					rev = EntityUtils.toString(response.getEntity());
					//Log.i("+++",rev);

					UserInformation information = HttpUtils
							.UserInformationFromJson(rev);
					if (information.getResultCode().equals("000000")) {
						myApplication.setInformation(information);
						SPUtil.save("OrgId",information.getOrgId());
						SPUtil.save("UserId",information.getUserId());
						alias = information.getAlias();
						setAlias(alias);
						Intent intent = new Intent(getApplicationContext(),
								FragmentMainActivity.class);
						startActivity(intent);
					} else if (information.getResultCode().equals("U00001")) {
						stopProgressDialog();
						FunctionUtils.showCustomDialog("账号不存在", Validate.this);
						return;

					} else if (information.getResultCode().equals("U00004")) {
						stopProgressDialog();
						FunctionUtils.showCustomDialog("密码不正确", Validate.this);
						return;
					}else if (information.getResultCode().equals("U00002")) {
						stopProgressDialog();
						FunctionUtils.showCustomDialog("非手机客户端用户", Validate.this);
						return;
					}else if (information.getResultCode().equals("U00003")) {
						stopProgressDialog();
						FunctionUtils.showCustomDialog("使用期限已到，请咨询系统管理员", Validate.this);
						return;
					}else if (information.getResultCode().equals("U00005")) {
						stopProgressDialog();
						FunctionUtils.showCustomDialog("用户帐户被停用，请咨询系统管理员", Validate.this);
						return;
					}else if (information.getResultCode().equals("U00006")) {
						stopProgressDialog();
						FunctionUtils.showCustomDialog("用户机构被停用，请咨询系统管理员", Validate.this);
						return;
					}
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				stopProgressDialog();
				FunctionUtils.showCustomDialog("与服务器失去连接，\n请稍后重试",
						Validate.this);

			}

		}

	}

	private void setAlias(String alias) {

		// 调用JPush API设置Alias
		mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
	}

	private final TagAliasCallback mAliasCallback = new TagAliasCallback() {

		@Override
		public void gotResult(int code, String alias, Set<String> tags) {
			String logs;
			switch (code) {
			case 0:
				logs = "Set tag and alias success";
				Log.i(TAG, logs);
				break;

			case 6002:
				logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
				Log.i(TAG, logs);
				if (ExampleUtil.isConnected(getApplicationContext())) {
					mHandler.sendMessageDelayed(
							mHandler.obtainMessage(MSG_SET_ALIAS, alias),
							1000 * 60);
				} else {
					Log.i(TAG, "No network");
				}
				break;

			default:
				logs = "Failed with errorCode = " + code;
				Log.e(TAG, logs);
			}

			// ExampleUtil.showToast(logs, getApplicationContext());
		}

	};

	private final Handler mHandler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case MSG_SET_ALIAS:
				Log.d(TAG, "Set alias in handler.");
				JPushInterface.setAliasAndTags(getApplicationContext(),
						(String) msg.obj, null, mAliasCallback);
				break;

			default:
				Log.i(TAG, "Unhandled msg - " + msg.what);
			}
		}
	};

	/** 提示框 */
	public void showAlertDialog() {

		final CustomDialogEdit.Builder builder = new CustomDialogEdit.Builder(
				this);
		builder.setMessage(text);
		builder.setTitle("IP设置");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

				EditText editText = (EditText) builder.getLayout()
						.findViewById(R.id.edit_message);
				//if(TextUtils.isEmpty(editText.getText())).
				text = editText.getText().toString();
				builder.setMessage(text);
				editor = sp.edit();
				editor.putString("URI", text);
				editor.commit();
				SPUtil.save("BasicURI",text);

				// new DeleteAccountTask(position, accountUrlPart).execute();
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

	/** 提示框 */
	public void showAlertDialog(Context context) {

		CustomDialog.Builder builder = new CustomDialog.Builder(this);
		builder.setMessage("400-810-0868");
		builder.setTitle("客服电话");
		builder.setPositiveButton("拨打电话", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
						+ "4008100868"));
				startActivity(intent);
				// new DeleteAccountTask(position, finalAccountUrl).execute();
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
	
	
	private void startProgressDialog(){
		if (progressDialog == null){
			progressDialog = CustomProgressDialog.createDialog(this);
	    	progressDialog.setMessage("正在登录，\n请稍等...");
		}
		
    	progressDialog.show();
	}
	
	private void stopProgressDialog(){
		if (progressDialog != null){
			progressDialog.dismiss();
			progressDialog = null;
		}
	}
	
	
	/**
	 * 调整键盘视图
	 * @param root 
	 * @param bottomView 
	 */
	private void onKeyboardLayout(final View root, final View bottomView) {
		root.getViewTreeObserver().addOnGlobalLayoutListener( new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				Rect rect = new Rect();
				root.getWindowVisibleDisplayFrame(rect);
				int rootInvisibleHeight = root.getRootView().getHeight() - rect.bottom;
				if (rootInvisibleHeight > 100) {
					int[] location = new int[2];
					bottomView.getLocationInWindow(location);
					int srollHeight = (location[1] + bottomView.getHeight()) - rect.bottom;
					root.scrollTo(0, srollHeight);
				} else {
					root.scrollTo(0, 0);
				}
			}
		});
	}

	@Override
	public void onClick(View argView) {
		switch (argView.getId()) {
		case R.id.set_ip:
			showAlertDialog();
			break;
		case R.id.forget_password:
			showAlertDialog(Validate.this);
			break;
		case R.id.checkView:
			markOfUser = "";
			// 重新初始化验证码
			res = mMyView.getValidataAndSetImage();
			for (String s : res) {
				markOfUser = markOfUser + s;
			}

		default:
			break;
		}

	}

}
