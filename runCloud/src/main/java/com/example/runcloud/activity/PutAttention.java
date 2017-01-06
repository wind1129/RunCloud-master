package com.example.runcloud.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.example.runcloud.BaseActivity;
import com.example.runcloud.R;
import com.example.runcloud.adapt.ViewPageAdapt;
import com.example.runcloud.application.MyApplication;
import com.example.runcloud.entity.HttpMessage;
import com.example.runcloud.entity.NetizenShow;
import com.example.runcloud.entity.NetizenShowBasic;
import com.example.runcloud.entity.SensRuleShow;
import com.example.runcloud.entity.SubSensRulesShow;
import com.example.runcloud.entity.SubSensRulesShowJson;
import com.example.runcloud.entity.SubSensRulesShowPart;
import com.example.runcloud.entity.SubSensRulesShowText;
import com.example.runcloud.extra.EditChangedListener;
import com.example.runcloud.extra.SystemStatusManager;
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
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PutAttention extends BaseActivity implements OnClickListener {
	public static PutAttention putAttention;
	/** 全局变量 */
	private MyApplication myApplication;
	/** 本地存储 */
	private SharedPreferences sp;

	/** url部分 */
	private String uri;
	private String rulesUrlPart = "/ares/restful/sensitives/sensrule?";
	private String baseRulesUrlPart = "/ares/restful/sensitives/sensrule";

	/** 带userId和orderId的规则url */
	private String finalRulesUrl;
	private String finalBaseRulesUrl;

	private LinearLayout little_warning;
	private LinearLayout medium_warning;
	private LinearLayout serious_warning;
	private LinearLayout heavy_warning;
	private LinearLayout other_warning;
	private EditText rule_name;
	private EditText effect_date;
	private TextView add_rule_sure;
	private TextView add_rule_cancel;

	private TextView titleShow;
	private TextView backFirstPageView;
	private ImageView backView;

	private TextView tip;

	/** 参数 */
	private String userId;
	private String orgId;
	private List<String> defaultSensLevelId;
	private String otherSensLevelId;
	private String sensLevelId;
	private String selectId;
	private String selectName;

	/** 重点网民集合 */
	List<SubSensRulesShow> attendStr = new ArrayList<SubSensRulesShow>();
	private List<SubSensRulesShowText> subSensRulesTexts = new ArrayList<SubSensRulesShowText>();
	private List<SubSensRulesShowPart> subSensRules = new ArrayList<SubSensRulesShowPart>();
	// 定义ViewPager对象
	private ViewPager viewPager;
	// 定义ViewPager适配器
	private ViewPageAdapt viewPageAdapt;
	// 定义一个ArrayList来存放View
	private ArrayList<View> views = new ArrayList<View>();

	private int mark = 0;
	private String jsonString;
	private HttpResponse httpResponse;

	private String urlRegx = "(^((?![_\\s-]+$)([\\u4e00-\\u9fa5-_\\s]|[a-zA-Z0-9])+)$)";
	private String tagShow = "PutAttention";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setTranslucentStatus();
		setContentView(R.layout.attention_setting);
		putAttention = this;

		/** url */
		myApplication = (MyApplication) getApplication();
		userId = myApplication.getInformation().getUserId();
		orgId = myApplication.getInformation().getOrgId();
		defaultSensLevelId = myApplication.getInformation()
				.getDefaultSensLevelId();

		sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
		uri = sp.getString("URI", "");
		finalBaseRulesUrl = uri + baseRulesUrlPart;
		finalRulesUrl = uri + rulesUrlPart + "userId=" + userId + "&orgId="
				+ orgId;

		rule_name = (EditText) findViewById(R.id.rule_name);
		effect_date = (EditText) findViewById(R.id.effect_date);
		final Calendar c = Calendar.getInstance();
		effect_date.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DatePickerDialog dialog = new DatePickerDialog(
						PutAttention.this,
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

		titleShow = (TextView) findViewById(R.id.title_show2);
		titleShow.setText("编辑规则");

		tip = (TextView) findViewById(R.id.tip);

		backFirstPageView = (TextView) findViewById(R.id.back_firstPage);
		backView = (ImageView) findViewById(R.id.back);
		backFirstPageView.setOnClickListener(this);
		backView.setOnClickListener(this);
		add_rule_sure = (TextView) findViewById(R.id.add_rule_sure);
		add_rule_sure.setOnClickListener(this);
		add_rule_cancel = (TextView) findViewById(R.id.add_rule_cancel);
		add_rule_cancel.setOnClickListener(this);

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

			// subShowText.setPersonWords((EditText)
			// linearLayout.findViewById(R.id.people_or_organization));
			// subShowText.setLocalWords((EditText)
			// linearLayout.findViewById(R.id.place));
			// subShowText.setEventWords((EditText)
			// linearLayout.findViewById(R.id.events));
			// subShowText.setExcludeWords((EditText)
			// linearLayout.findViewById(R.id.words_of_remove));
			subSensRulesTexts.add(subShowText);
			views.add(linearLayout);
		}
		// 实例化ViewPager适配器
		viewPageAdapt = new ViewPageAdapt(views);
		// 设置Adapter
		viewPager.setAdapter(viewPageAdapt);

		new GetSensRuleShowTask().execute();

	}

	private class GetSensRuleShowTask extends
			AsyncTask<Void, Void, SensRuleShow> {

		@Override
		protected SensRuleShow doInBackground(Void... arg0) {
			Intent intent = getIntent();
			selectId = intent.getStringExtra("SelectId");

			String url = finalRulesUrl + "&sensruleId=" + selectId;
			String jsonStr = HttpUtils.getJsonStr(url);
			// Log.d("jsonStr++++++++++++++++++", jsonStr);
			SensRuleShow sensRuleShow = HttpUtils.SensRuleShowFromJson(jsonStr);
			return sensRuleShow;
		}

		@Override
		protected void onPostExecute(SensRuleShow sensRuleShow) {
			if (sensRuleShow == null) {
				FunctionUtils.showCustomDialog("服务器异常暂无数据，\n请稍后刷新...",
						PutAttention.this);
				return;
			}
			attendStr = sensRuleShow.getSubSensRules();
			Intent intent = getIntent();
			selectName = intent.getStringExtra("SelectName");
			rule_name.setText(selectName);
			effect_date.setText(FunctionUtils.timesOne(sensRuleShow
					.getSensrule().getExpireDate()));
			sensLevelId = sensRuleShow.getSensrule().getSensruleLevelId();
			Log.d("++++++sensLevelId", sensLevelId);
			if (sensLevelId.equals(defaultSensLevelId.get(0))) {
				little_warning
						.setBackgroundResource(R.drawable.layout_little_warning_selected_style);
			} else if (sensLevelId.equals(defaultSensLevelId.get(1))) {
				medium_warning
						.setBackgroundResource(R.drawable.layout_medium_warning_selected_style);
			} else if (sensLevelId.equals(defaultSensLevelId.get(2))) {
				serious_warning
						.setBackgroundResource(R.drawable.layout_serious_warning_selected_style);
			} else if (sensLevelId.equals(defaultSensLevelId.get(3))) {
				heavy_warning
						.setBackgroundResource(R.drawable.layout_heavy_warning_selected_style);
			} else {
				other_warning.setVisibility(View.VISIBLE);
				other_warning
						.setBackgroundResource(R.drawable.layout_other_warning_selected_style);
				otherSensLevelId = sensLevelId;
			}

			for (int i = 0; i < attendStr.size(); i++) {
				subSensRulesTexts.get(i).getPersonWords()
						.setText(attendStr.get(i).getPersonWords());
				subSensRulesTexts.get(i).getLocalWords()
						.setText(attendStr.get(i).getLocalWords());
				subSensRulesTexts.get(i).getEventWords()
						.setText(attendStr.get(i).getEventWords());
				subSensRulesTexts.get(i).getExcludeWords()
						.setText(attendStr.get(i).getExcludeWords());
			}
		}
	}

	/** 判断规则是否为空的Task */
	private class AttentionJudgementTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... arg0) {
			// 只是个标记
			return rulesUrlPart;
		}

		@Override
		protected void onPostExecute(String urlPart) {
			if (TextUtils.isEmpty(rule_name.getText())) {
				FunctionUtils.showCustomDialog("规则名称不能为空", PutAttention.this);
				return;
			}
			if (sensLevelId.equals("empty")) {
				FunctionUtils.showCustomDialog("预警级别不能为空，\n请选择预警级别",
						PutAttention.this);
				return;
			}
			if (TextUtils.isEmpty(effect_date.getText())) {
				FunctionUtils.showCustomDialog("有效日期不能为空", PutAttention.this);
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
						PutAttention.this);
				return;
			}
			SubSensRulesShowJson json = new SubSensRulesShowJson();
			json.setSensruleId(selectId);
			json.setOrgId(orgId);
			json.setUserId(userId);
			json.setExpireDate(effect_date.getText().toString());
			json.setRuleName(rule_name.getText().toString());
			json.setSensLevelId(sensLevelId);
			json.setSubSensRules(subSensRules);
			Gson gson = new Gson();
			jsonString = gson.toJson(json);
			new AttentionPutTask().execute();

		}
	}

	/** 判断规则是否为空的Task */
	private class AttentionPutTask extends AsyncTask<Void, Void, Integer> {

		@Override
		protected Integer doInBackground(Void... arg0) {
			int code;
			HttpPut httpPut = new HttpPut(finalBaseRulesUrl);
			StringEntity se;
			try {
				se = new StringEntity(jsonString, "utf-8");
				httpPut.setEntity(se);
				// 发送请求
				Log.d("++++++++++++++", "bababababbaba");
				httpResponse = HttpClientTool.getHttpClient().execute(httpPut);
				code = httpResponse.getStatusLine().getStatusCode();
				Log.d("++++++++++++++", code+"");
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
					HttpMessage httpMessage = HttpUtils
							.HttpMessageFromJson(rev);
					if (httpMessage.getResultCode().equals("000000")) {
						/*
						 * FunctionUtils.showCustomDialog("添加成功",
						 * PutAttention.this);
						 */
						Intent intent = new Intent(PutAttention.this,
								MyAttention.class);
						startActivity(intent);
					} else {
						FunctionUtils.showCustomDialog(
								httpMessage.getMessage(), PutAttention.this);
						return;
					}
					
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				FunctionUtils.showCustomDialog("与服务器失去连接，\n请稍后再试",
						PutAttention.this);
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
			sensLevelId = defaultSensLevelId.get(0);
			tip.setText("国内网民对该舆情关注度低，传播速度慢，舆情影响局限在较小范围内，没有转化为行为舆论的可能。");
			medium_warning
					.setBackgroundResource(R.drawable.layout_medium_warning_style);
			serious_warning
					.setBackgroundResource(R.drawable.layout_serious_warning_style);
			heavy_warning
					.setBackgroundResource(R.drawable.layout_heavy_warning_style);
			other_warning
					.setBackgroundResource(R.drawable.layout_other_warning_style);
			return;
		case R.id.medium_warning:
			medium_warning
					.setBackgroundResource(R.drawable.layout_medium_warning_selected_style);
			sensLevelId = defaultSensLevelId.get(1);
			tip.setText("国内网民对该舆情关注度较高，传播速度中等，舆情影响局限在一定范围内，没有转化为行为舆论的可能。");
			little_warning
					.setBackgroundResource(R.drawable.layout_little_warning_style);
			serious_warning
					.setBackgroundResource(R.drawable.layout_serious_warning_style);
			heavy_warning
					.setBackgroundResource(R.drawable.layout_heavy_warning_style);
			other_warning
					.setBackgroundResource(R.drawable.layout_other_warning_style);
			return;
		case R.id.serious_warning:
			serious_warning
					.setBackgroundResource(R.drawable.layout_serious_warning_selected_style);
			sensLevelId = defaultSensLevelId.get(2);
			tip.setText("国内网民对该舆情关注度高，境外媒体开始关注，传播速度快，影响扩散到了很大范围，舆情有转化为行为舆论的可能。");
			little_warning
					.setBackgroundResource(R.drawable.layout_little_warning_style);
			medium_warning
					.setBackgroundResource(R.drawable.layout_medium_warning_style);
			heavy_warning
					.setBackgroundResource(R.drawable.layout_heavy_warning_style);
			other_warning
					.setBackgroundResource(R.drawable.layout_other_warning_style);
			return;
		case R.id.heavy_warning:
			heavy_warning
					.setBackgroundResource(R.drawable.layout_heavy_warning_selected_style);
			sensLevelId = defaultSensLevelId.get(3);
			tip.setText("国内网民对该舆情关注度极高,境外媒体高度关注,传播速度非常快,影响扩大到了整个社会,舆情即将化为行为舆论。");
			little_warning
					.setBackgroundResource(R.drawable.layout_little_warning_style);
			medium_warning
					.setBackgroundResource(R.drawable.layout_medium_warning_style);
			serious_warning
					.setBackgroundResource(R.drawable.layout_serious_warning_style);
			other_warning
					.setBackgroundResource(R.drawable.layout_other_warning_style);

			return;
		case R.id.other_warning:
			other_warning
					.setBackgroundResource(R.drawable.layout_other_warning_selected_style);
			sensLevelId = otherSensLevelId;
			tip.setText("其他类型的预警。");
			little_warning
					.setBackgroundResource(R.drawable.layout_little_warning_style);
			medium_warning
					.setBackgroundResource(R.drawable.layout_medium_warning_style);
			serious_warning
					.setBackgroundResource(R.drawable.layout_serious_warning_style);
			heavy_warning
					.setBackgroundResource(R.drawable.layout_heavy_warning_style);
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
