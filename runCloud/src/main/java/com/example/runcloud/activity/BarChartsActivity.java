package com.example.runcloud.activity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.example.runcloud.BaseActivity;
import com.example.runcloud.R;
import com.example.runcloud.application.MyApplication;
import com.example.runcloud.entity.Chart;
import com.example.runcloud.entity.ChartData;
import com.example.runcloud.entity.StatisticalCharts;
import com.example.runcloud.extra.CustomProgressDialog;
import com.example.runcloud.util.FunctionUtils;
import com.example.runcloud.util.HttpUtils;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.YAxisLabelPosition;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class BarChartsActivity extends BaseActivity{
	/**本地存储*/
	private SharedPreferences sp;
	/**全局变量*/
	private MyApplication myApplication;
	private String urlpart = "/ares/restful/sensitives/barCharts?";
	private String url;
	private String uri;
	
	private CustomProgressDialog progressDialog = null;
	//String url = "http://118.126.142.130/ares/restful/sensitives/barCharts?userId=16&orgId=10&queryDay=30";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	//setTranslucentStatus();
    	setContentView(R.layout.bar_charts); 
    	/**url*/
		myApplication =(MyApplication)getApplication();
		String userId = myApplication.getInformation().getUserId();
		String orgId =myApplication.getInformation().getOrgId();
		
		sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
		uri = sp.getString("URI", "");
		
		//初始化url
		url = uri+urlpart+"userId="+userId+"&orgId="+orgId;
    	
    	new BarChartsTask(url).execute();
    }
    
    @Override
    protected void onDestroy() {
    	try {
			
		} catch (Exception e) {
			System.out.println("");
		}
    	super.onDestroy();
    }
    
    private class BarChartsTask extends AsyncTask<Void, Void, StatisticalCharts>{
    	String mUrl;
    	

		public BarChartsTask(String mUrl) {
			this.mUrl = mUrl;

		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			startProgressDialog();
		};

		@Override
		protected StatisticalCharts doInBackground(Void... arg0) {
			Intent intent = getIntent();
			String queryDay =intent.getStringExtra("queryDay");
			String jsonStr = HttpUtils.getJsonStr(mUrl+"&queryDay="+queryDay);
			//Log.d("+++++++", jsonStr);
			if(jsonStr!=null){
			StatisticalCharts charts = HttpUtils.StatisticalChartsFromJson(jsonStr);
			    return charts;
			}else{
				return null;
			}
		}
		
		
		@Override  
        protected void onPostExecute(StatisticalCharts charts){
			stopProgressDialog();
			if(charts==null){
				FunctionUtils.showCustomDialog("服务器异常暂无数据，\n请稍后刷新...", BarChartsActivity.this);
				//finish();
			}else{
			List<Chart> chartList =charts.getCharts();
			BarChart mBarChart = (BarChart) findViewById(R.id.bar_chart);  
			BarChart mBarChart2 = (BarChart) findViewById(R.id.bar_chart2);
			BarChart mBarChart3 = (BarChart) findViewById(R.id.bar_chart3);
			// 制作10个数据点。 
			if(chartList!=null&&chartList.size()==3){
				mBarChart.setVisibility(View.VISIBLE);
				mBarChart2.setVisibility(View.VISIBLE);
				mBarChart3.setVisibility(View.VISIBLE);
	        setData(mBarChart, chartList.get(0),Color.rgb(255, 64, 64)); 
	        setBarChartStyle(mBarChart); 
	        setData(mBarChart2, chartList.get(1),Color.rgb(30, 144, 255)); 
	        setBarChartStyle(mBarChart2);
	        setData(mBarChart3, chartList.get(2),Color.rgb(60, 179, 113)); 
	        setBarChartStyle(mBarChart3);
			}else if(chartList!=null&&chartList.size()==2){
				mBarChart.setVisibility(View.VISIBLE);
				mBarChart2.setVisibility(View.VISIBLE);
				setData(mBarChart, chartList.get(0),Color.rgb(255, 64, 64)); 
		        setBarChartStyle(mBarChart); 
		        setData(mBarChart2, chartList.get(1),Color.rgb(30, 144, 255)); 
		        setBarChartStyle(mBarChart2);
			}else if(chartList!=null&&chartList.size()==1){
				mBarChart.setVisibility(View.VISIBLE);
				setData(mBarChart, chartList.get(0),Color.rgb(255, 64, 64)); 
		        setBarChartStyle(mBarChart); 
			}else{
				FunctionUtils.showCustomDialog("暂无数据，\n请稍后刷新...", BarChartsActivity.this);
			}
	         
		  }
		}
    	
    }
    
    private void setBarChartStyle(BarChart mBarChart) {  
    	  
        mBarChart.setDrawBarShadow(false);  
        mBarChart.setDrawValueAboveBar(true);  
        mBarChart.setDescription(null);  
        mBarChart.setMaxVisibleValueCount(60);  
        mBarChart.setPinchZoom(false);  
        mBarChart.setDrawGridBackground(false);
		mBarChart.setDrawBorders(false);
		mBarChart.setHighlightPerDragEnabled(false);
		mBarChart.setHighlightPerTapEnabled(false);
		mBarChart.setScaleYEnabled(false);


		//隐藏Y轴坐标
		mBarChart.getAxisRight().setEnabled(false);
  
        XAxis xAxis = mBarChart.getXAxis();  
        xAxis.setPosition(XAxisPosition.BOTTOM);  
        xAxis.setDrawGridLines(false);  
        xAxis.setSpaceBetweenLabels(2);
  
        YAxis leftAxis = mBarChart.getAxisLeft();  
        leftAxis.setLabelCount(5, false);
        leftAxis.setPosition(YAxisLabelPosition.OUTSIDE_CHART);  
        leftAxis.setSpaceTop(15f);  
        leftAxis.setTextColor(Color.BLUE);  
  
        YAxis rightAxis = mBarChart.getAxisRight();  
        rightAxis.setDrawGridLines(false);  
        rightAxis.setLabelCount(5, false);  
        rightAxis.setSpaceTop(15f);  
        rightAxis.setTextColor(Color.BLUE);  
  
        Legend mLegend = mBarChart.getLegend();  
        mLegend.setPosition(LegendPosition.BELOW_CHART_CENTER);  
        mLegend.setForm(LegendForm.SQUARE);  
        mLegend.setFormSize(15f);  
        mLegend.setTextSize(12f);  
        mLegend.setXEntrySpace(5f);  
        
        // 沿x轴动画，时间2000毫秒。  
        mBarChart.animateX(2000); 
    }  
  
    private void setData(BarChart mBarChart, Chart chart, int mainColor) { 
    	List<String> yList =new ArrayList<String>();
		List<String> nameList =new ArrayList<String>();
		//List<Chart> chartList =mCharts.getCharts();
		
	
			for(ChartData data:chart.getData()){
				if(data.getY()!=null){
				yList.add(data.getY());
				}else{
				yList.add("0");
				}
				nameList.add(data.getName());
			}
		
  
        ArrayList<String> xVals = new ArrayList<String>();  
        for (int i = 0; i < nameList.size(); i++) {
            //xVals.add(i, i + "");  
        	xVals.add(nameList.get(i));
        }  
  
        ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();  
  
        for (int i = 0; i < nameList.size(); i++) {  
            int val = Math.round(Integer.valueOf(yList.get(i)));
			Log.i("val",val+"");
            yVals.add(new BarEntry(val, i));  
        }  
  
        BarDataSet mBarDataSet = new BarDataSet(yVals, chart.getTitle());  
  
        // 如果是0f，那么柱状图之间将紧密无空隙的拼接在一起形成一片。  
        mBarDataSet.setBarSpacePercent(30f);
  
        // 柱状图柱的颜色  
        mBarDataSet.setColor(mainColor);  
  
        // 当柱状图某一柱被选中时候的颜色  
        //mBarDataSet.setHighLightColor(Color.WHITE);
  
       mBarDataSet.setValueFormatter(new ValueFormatter() {

			@Override
			public String getFormattedValue(float value, Entry entry,
					int dataSetIndex, ViewPortHandler viewPortHandler) {
				DecimalFormat decimalFormat = new DecimalFormat("");
                String s = decimalFormat.format(value);
  
                return s;
			}  
        });
  
        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(mBarDataSet);
  
        BarData mBarData = new BarData(xVals, dataSets);
        mBarData.setValueTextSize(12f);  
  
        mBarChart.setData(mBarData);  
    } 
    
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
