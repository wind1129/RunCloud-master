package com.example.runcloud.fragment;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;

import com.example.runcloud.R;
import com.example.runcloud.activity.PieChartsActivity;
import com.example.runcloud.activity.QuicklySearchShow;
import com.example.runcloud.application.MyApplication;
import com.example.runcloud.view.SwitchButton;
import com.example.runcloud.view.SwitchButton.OnChangeListener;
import com.example.runcloud.view.XCFlowLayout;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint("NewApi")
public class QuickSearchFragment extends Fragment implements OnClickListener {
	View newsLayout;
	/** 全局变量 */
	private MyApplication myApplication;
	/** 本地存储 */
	private SharedPreferences sp;
	private RelativeLayout click_quick_search;
	private EditText quick_search_context;
	private ImageView quick_search_go;

	private String uri;
	private String baseUrl = "/ares/restful/fulltextsearch/search";
	private String url;

	private String userId;
	private String orgId;
	private String keywords;

	/** url拼接 */
	StringBuilder builder = new StringBuilder();

	/*
	 * private String mNames[] = { "welcome","android","TextView",
	 * "apple","jamy","kobe bryant", "jordan","layout","viewgroup",
	 * "margin","padding","text", "name","type","search","logcat" };
	 */
	private XCFlowLayout mFlowLayout;
	public static final String SEARCH_HISTORY = "search_history";
	private ArrayList<String> mOriginalValues = new ArrayList<String>();// 所有的Item

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		newsLayout = inflater.inflate(R.layout.quick_search_tab, container,
				false);

		/** url */
		myApplication = (MyApplication) getActivity().getApplication();
		userId = myApplication.getInformation().getUserId();
		orgId = myApplication.getInformation().getOrgId();

		sp = getActivity().getSharedPreferences("userInfo",
				Context.MODE_PRIVATE);
		uri = sp.getString("URI", "");
		url = uri + baseUrl;

		quick_search_context = (EditText) newsLayout
				.findViewById(R.id.quick_search_context);
		quick_search_go = (ImageView) newsLayout
				.findViewById(R.id.quick_search_go);
		quick_search_go.setOnClickListener(this);
		click_quick_search = (RelativeLayout) newsLayout
				.findViewById(R.id.click_quick_search);
		click_quick_search.setOnClickListener(this);

		initChildViews();

		return newsLayout;
	}

	private void initChildViews() {
		
		initSearchHistory();
		if(mOriginalValues.size()<1){
			return;
					}
		// TODO Auto-generated method stub
		mFlowLayout = (XCFlowLayout) newsLayout.findViewById(R.id.flowlayout);
		MarginLayoutParams lp = new MarginLayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp.leftMargin = 6;
		lp.rightMargin = 6;
		lp.topMargin = 6;
		lp.bottomMargin = 6;
		
		for (int i = 0; i < mOriginalValues.size(); i++) {
			final TextView view = new TextView(getActivity());
			view.setText(mOriginalValues.get(i));
			view.setTextColor(Color.rgb(163, 163, 163));
			view.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.textview_bg));
			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					quick_search_context.setText(view.getText());

				}
			});
			mFlowLayout.addView(view, lp);
		}
	}

	/*
	 * 保存搜索记录
	 */
	private void saveSearchHistory() {
		String text = quick_search_context.getText().toString().trim();
		if (text.length() < 1) {
			return;
		}
		SharedPreferences sp = getActivity().getSharedPreferences(
				SEARCH_HISTORY, 0);
		String longhistory = sp.getString(SEARCH_HISTORY, "");
		String[] tmpHistory = longhistory.split(",");
		ArrayList<String> history = new ArrayList<String>(
				Arrays.asList(tmpHistory));
		if (history.size() > 0) {
			int i;
			for (i = 0; i < history.size(); i++) {
				if (text.equals(history.get(i))) {
					history.remove(i);
					break;
				}
				if (i > 10) {
					history.remove(i);
				}
			}
			history.add(0, text);
		}
		if (history.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < history.size(); i++) {
				sb.append(history.get(i) + ",");
			}
			sp.edit().putString(SEARCH_HISTORY, sb.toString()).commit();
		} else {
			sp.edit().putString(SEARCH_HISTORY, text + ",").commit();
		}
	}

	/**
	 * 读取历史搜索记录
	 */
	public void initSearchHistory() {
		SharedPreferences sp = getActivity().getSharedPreferences(
				QuickSearchFragment.SEARCH_HISTORY, 0);
		String longhistory = sp.getString(QuickSearchFragment.SEARCH_HISTORY,
				"");
		if(longhistory.trim().equals("")){
			return;
		}
		String[] hisArrays = longhistory.split(",");
		//mOriginalValues = new ArrayList<String>();
		
		  //if (hisArrays.length == 0) { return; }
		 
		for (int i = 0; i < hisArrays.length; i++) {
			mOriginalValues.add(hisArrays[i]);
		}
	}

	@Override
	public void onClick(View arg0) {
		Intent intent = new Intent();
		switch (arg0.getId()) {
		case R.id.quick_search_go:
			saveSearchHistory();
			builder.append(url + "?userId=" + userId + "&orgId=" + orgId);
			keywords = quick_search_context.getText().toString();
			if (keywords != null && keywords.trim().length() != 0) {
				builder.append("&keywords=" + keywords);
			}
			intent.putExtra("keywords",keywords);
			intent.putExtra("URL", builder.toString());
			builder.delete(0, builder.length());
			intent.setClass(getActivity(), QuicklySearchShow.class);
			break;

		case R.id.click_quick_search:
			saveSearchHistory();
			builder.append(url + "?userId=" + userId + "&orgId=" + orgId);
			keywords = quick_search_context.getText().toString();
			if (keywords != null && keywords.trim().length() != 0) {
				builder.append("&keywords=" + keywords);
			}
			intent.putExtra("keywords",keywords);
			intent.putExtra("URL", builder.toString());
			builder.delete(0, builder.length());
			intent.setClass(getActivity(), QuicklySearchShow.class);
			break;

		}
		startActivity(intent);

	}

}
