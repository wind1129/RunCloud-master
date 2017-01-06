package com.example.runcloud.activity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.example.runcloud.BaseActivity;
import com.example.runcloud.R;
import com.example.runcloud.adapt.ViewPageAdapt;
import com.example.runcloud.application.MyApplication;
import com.example.runcloud.entity.HttpMessage;
import com.example.runcloud.entity.SensitiveMessage;
import com.example.runcloud.entity.SubSensRulesShow;
import com.example.runcloud.entity.SubSensRulesShowJson;
import com.example.runcloud.entity.SubSensRulesShowPart;
import com.example.runcloud.entity.SubSensRulesShowText;
import com.example.runcloud.extra.EditChangedListener;
import com.example.runcloud.fragment.FragmentMainActivity;
import com.example.runcloud.util.FunctionUtils;
import com.example.runcloud.util.HttpClientTool;
import com.example.runcloud.util.HttpUtils;
import com.google.gson.Gson;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AddAttention extends BaseActivity implements OnClickListener {
	public static AddAttention addAttention;
	/** 全局变量 */
	private MyApplication myApplication;
	/** 本地存储 */
	private SharedPreferences sp;

	private LinearLayout little_warning;
	private LinearLayout medium_warning;
	private LinearLayout serious_warning;
	private LinearLayout heavy_warning;
	private LinearLayout other_warning;
	private EditText rule_name;
	private EditText effect_date;
	private TextView add_rule_sure;
	private TextView add_rule_cancel;

	private TextView tip;

	private List<SubSensRulesShowText> subSensRulesTexts = new ArrayList<SubSensRulesShowText>();
	/** 参数 */
	private String userId;
	private String orgId;
	private String defaultTopicId;
	private String sensLevelId = "empty";
	private List<String> defaultSensLevelId;

	private String uri;
	private String urlPart = "/ares/restful/sensitives/sensrule";
	private String finalURL;

	private TextView titleShow;
	private TextView backFirstPageView;
	private ImageView backView;
	private List<SubSensRulesShowPart> subSensRules = new ArrayList<SubSensRulesShowPart>();

	private int mark = 0;
	private String jsonStr;
	// 定义ViewPager对象
	private ViewPager viewPager;
	// 定义ViewPager适配器
	private ViewPageAdapt viewPageAdapt;
	// 定义一个ArrayList来存放View
	private ArrayList<View> views = new ArrayList<View>();

	private HttpResponse httpResponse;

	private String urlRegx = "(^((?![_\\s-]+$)([\\u4e00-\\u9fa5-_\\s]|[a-zA-Z0-9])+)$)";
	private String tagShow = "AddAttention";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setTranslucentStatus();
		setContentView(R.layout.attention_setting);
		addAttention = this;

		/** url */
		myApplication = (MyApplication) getApplication();
		userId = myApplication.getInformation().getUserId();
		orgId = myApplication.getInformation().getOrgId();
		defaultTopicId = myApplication.getInformation().getDefaultTopicId();
		defaultSensLevelId = myApplication.getInformation()
				.getDefaultSensLevelId();

		sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
		uri = sp.getString("URI", "");

		finalURL = uri + urlPart;

		rule_name = (EditText) findViewById(R.id.rule_name);
		effect_date = (EditText) findViewById(R.id.effect_date);
		// effect_date.clearFocus();
		// final EditText et1=(EditText)findViewById(R.id.editText1);
		final Calendar c = Calendar.getInstance();
		/** 设置日期的监听 */
		effect_date.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DatePickerDialog dialog = new DatePickerDialog(
						AddAttention.this,
						new DatePickerDialog.OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								c.set(year, monthOfYear, dayOfMonth);
								effect_date.setText(DateFormat.format(
										"yyy-MM-dd", c));
							}
						}, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
								.get(Calendar.DAY_OF_MONTH));
				dialog.show();
			}
		});

		backFirstPageView = (TextView) findViewById(R.id.back_firstPage);
		backView = (ImageView) findViewById(R.id.back);
		backFirstPageView.setOnClickListener(this);
		backView.setOnClickListener(this);
		add_rule_sure = (TextView) findViewById(R.id.add_rule_sure);
		add_rule_sure.setOnClickListener(this);
		add_rule_cancel = (TextView) findViewById(R.id.add_rule_cancel);
		add_rule_cancel.setOnClickListener(this);

		titleShow = (TextView) findViewById(R.id.title_show2);
		titleShow.setText("添加规则");

		tip = (TextView) findViewById(R.id.tip);

		little_warning = (LinearLayout) findViewById(R.id.little_warning);
		medium_warning = (LinearLayout) findViewById(R.id.medium_warning);
		serious_warning = (LinearLayout) findViewById(R.id.serious_warning);
		heavy_warning = (LinearLayout) findViewById(R.id.heavy_warning);
		other_warning = (LinearLayout) findViewById(R.id.other_warning);

		little_warning.setOnClickListener(this);
		medium_warning.setOnClickListener(this);
		serious_warning.setOnClickListener(this);
		heavy_warning.setOnClickListener(this);
		other_warning.setOnClickListener(this);

		viewPager = (ViewPager) findViewById(R.id.attention_viewPager);
		// 获取布局inflater用来加载RelativeLayout
		for (int i = 0; i < 3; i++) {
			LayoutInflater inflater = LayoutInflater.from(this);
			LinearLayout linearLayout = (LinearLayout) inflater.inflate(
					R.layout.attention_setting_context, null);
			SubSensRulesShowText subShowText = new SubSensRulesShowText();

			EditText people_or_organization = (EditText) linearLayout
					.findViewById(R.id.people_or_organization);
			people_or_organization
					.addTextChangedListener(new EditChangedListener(
							people_or_organization, urlRegx, tagShow));
			subShowText.setPersonWords(people_or_organization);

			EditText place = (EditText) linearLayout.findViewById(R.id.place);
			place.addTextChangedListener(new EditChangedListener(place,
					urlRegx, tagShow));
			subShowText.setLocalWords(place);

			EditText events = (EditText) linearLayout.findViewById(R.id.events);
			events.addTextChangedListener(new EditChangedListener(events,
					urlRegx, tagShow));
			subShowText.setEventWords(events);

			EditText words_of_remove = (EditText) linearLayout
					.findViewById(R.id.words_of_remove);
			words_of_remove.addTextChangedListener(new EditChangedListener(
					words_of_remove, urlRegx, tagShow));
			subShowText.setExcludeWords(words_of_remove);
			subSensRulesTexts.add(subShowText);
			views.add(linearLayout);
		}
		// 实例化ViewPager适配器
		viewPageAdapt = new ViewPageAdapt(views);
		// 设置Adapter
		viewPager.setAdapter(viewPageAdapt);

	}

	/** 判断规则是否为空的Task */
	private class AttentionJudgementTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... arg0) {
			return urlPart;
		}

		@Override
		protected void onPostExecute(String urlPart) {
			if (TextUtils.isEmpty(rule_name.getText())) {
				FunctionUtils.showCustomDialog("规则名称不能为空", AddAttention.this);
				return;
			}
			if (sensLevelId.equals("empty")) {
				FunctionUtils.showCustomDialog("预警级别不能为空，\n请选择预警级别",
						AddAttention.this);
				return;
			}
			if (TextUtils.isEmpty(effect_date.getText())) {
				FunctionUtils.showCustomDialog("有效日期不能为空", AddAttention.this);
				return;
			}

			for (SubSensRulesShowText text : subSensRulesTexts) {
				SubSensRulesShowPart part = new SubSensRulesShowPart();
				if (!TextUtils.isEmpty(text.getPersonWords().getText())) {
					mark = 1;
				}
				if (!TextUtils.isEmpty(text.getLocalWords().getText())) {
					mark = 1;
				}
				if (!TextUtils.isEmpty(text.getEventWords().getText())) {
					mark = 1;
				}
				part.setPersonWords(text.getPersonWords().getText().toString());
				part.setLocalWords(text.getLocalWords().getText().toString());
				part.setEventWords(text.getEventWords().getText().toString());
				part.setExcludeWords(text.getExcludeWords().getText()
						.toString());
				subSensRules.add(part);
				// Log.d("++++++++++++看看",
				// text.getPersonWords().getText().toString());
			}
			if (mark == 0) {
				FunctionUtils.showCustomDialog("人物或组织，地点，事件，\n请至少填写一项",
						AddAttention.this);
				return;
			}
			SubSensRulesShowJson json = new SubSensRulesShowJson();
			json.setOrgId(orgId);
			json.setUserId(userId);
			json.setExpireDate(effect_date.getText().toString());
			json.setRuleName(rule_name.getText().toString());
			json.setSensLevelId(sensLevelId);
			json.setSubSensRules(subSensRules);
			json.setDefaultTopicId(defaultTopicId);
			Gson gson = new Gson();
			jsonStr = gson.toJson(json);
			new AttentionPostTask().execute();

		}
	}

	/** 判断规则是否为空的Task */
	private class AttentionPostTask extends AsyncTask<Void, Void, Integer> {

		@Override
		protected Integer doInBackground(Void... arg0) {
			int code;
			HttpPost httpPost = new HttpPost(finalURL);
			StringEntity se;
			try {
				se = new StringEntity(jsonStr, "utf-8");
				httpPost.setEntity(se);
				// 发送请求
				httpResponse = HttpClientTool.getHttpClient().execute(httpPost);
				code = httpResponse.getStatusLine().getStatusCode();
				Log.d("dddddddd",code+"");
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
					// Log.d("++++++++++++++++++++++++rev", rev);
					HttpMessage httpMessage = HttpUtils
							.HttpMessageFromJson(rev);
					 Log.d("++++++httpMessage", httpMessage.getResultCode());
					if (httpMessage.getResultCode().equals("000000")) {
						/*
						 * FunctionUtils.showCustomDialog("添加成功",
						 * AddAttention.this);
						 */
						// Toast.makeText(AddAttention.this, "添加成功",
						// Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(AddAttention.this,
								MyAttention.class);
						startActivity(intent);
					} else {
						FunctionUtils.showCustomDialog(
								httpMessage.getMessage(), AddAttention.this);
						return;
					}

				} catch (ParseException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				FunctionUtils.showCustomDialog("与服务器失去连接，\n请稍后再试",
						AddAttention.this);
			}

		}
	}

	@Override
	public void onClick(View view) {
		Intent intent = null;
		switch (view.getId()) {
		case R.id.little_warning:
			little_warning
					.setBackgroundResource(R.drawable.layout_little_warning_selected_style);
			tip.setText("国内网民对该舆情关注度低，传播速度慢，舆情影响局限在较小范围内，没有转化为行为舆论的可能。");
			sensLevelId = defaultSensLevelId.get(0);
			medium_warning
					.setBackgroundResource(R.drawable.layout_medium_warning_style);
			serious_warning
					.setBackgroundResource(R.drawable.layout_serious_warning_style);
			heavy_warning
					.setBackgroundResource(R.drawable.layout_heavy_warning_style);

			return;
		case R.id.medium_warning:
			medium_warning
					.setBackgroundResource(R.drawable.layout_medium_warning_selected_style);
			tip.setText("国内网民对该舆情关注度较高，传播速度中等，舆情影响局限在一定范围内，没有转化为行为舆论的可能。");
			sensLevelId = defaultSensLevelId.get(1);
			little_warning
					.setBackgroundResource(R.drawable.layout_little_warning_style);
			serious_warning
					.setBackgroundResource(R.drawable.layout_serious_warning_style);
			heavy_warning
					.setBackgroundResource(R.drawable.layout_heavy_warning_style);

			return;
		case R.id.serious_warning:
			serious_warning
					.setBackgroundResource(R.drawable.layout_serious_warning_selected_style);
			tip.setText("国内网民对该舆情关注度高，境外媒体开始关注，传播速度快，影响扩散到了很大范围，舆情有转化为行为舆论的可能。");
			sensLevelId = defaultSensLevelId.get(2);
			little_warning
					.setBackgroundResource(R.drawable.layout_little_warning_style);
			medium_warning
					.setBackgroundResource(R.drawable.layout_medium_warning_style);
			heavy_warning
					.setBackgroundResource(R.drawable.layout_heavy_warning_style);

			return;
		case R.id.heavy_warning:
			heavy_warning
					.setBackgroundResource(R.drawable.layout_heavy_warning_selected_style);
			tip.setText("国内网民对该舆情关注度极高,境外媒体高度关注,传播速度非常快,影响扩大到了整个社会,舆情即将化为行为舆论。");
			sensLevelId = defaultSensLevelId.get(3);
			little_warning
					.setBackgroundResource(R.drawable.layout_little_warning_style);
			medium_warning
					.setBackgroundResource(R.drawable.layout_medium_warning_style);
			serious_warning
					.setBackgroundResource(R.drawable.layout_serious_warning_style);

			return;

		case R.id.back_firstPage:
			intent = new Intent(this, FragmentMainActivity.class);
			break;
		case R.id.back:
			finish();
			overridePendingTransition(0, R.anim.base_slide_right_out);
			return;
		case R.id.add_rule_cancel:
			finish();
			overridePendingTransition(0, R.anim.base_slide_right_out);
			return;
		case R.id.add_rule_sure:
			new AttentionJudgementTask().execute();
			return;
		}
		startActivity(intent);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(0, R.anim.base_slide_right_out);
	}
}
