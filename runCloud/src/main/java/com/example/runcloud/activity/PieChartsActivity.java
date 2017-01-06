package com.example.runcloud.activity;

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
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

public class PieChartsActivity extends BaseActivity{
	/**本地存储*/
	private SharedPreferences sp;
	/**全局变量*/
	private MyApplication myApplication;
	private String urlpart = "/ares/restful/sensitives/pieCharts?";
	private String url;
	private String uri;
	
	private CustomProgressDialog progressDialog = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setTranslucentStatus();
		setContentView(R.layout.pie_charts); 
		/**url*/
		myApplication =(MyApplication)getApplication();
		String userId = myApplication.getInformation().getUserId();
		String orgId =myApplication.getInformation().getOrgId();
		
		sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
		uri = sp.getString("URI", "");
		
		//初始化url
		url = uri+urlpart+"userId="+userId+"&orgId="+orgId;
    	new PieChartsTask(url).execute();
	}
	
	private class PieChartsTask extends AsyncTask<Void, Void, StatisticalCharts>{
    	String mUrl;
    	

		public PieChartsTask(String mUrl) {
			this.mUrl = mUrl;

		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// dialog = ProgressDialog.show(Validate.this, "登录提示",
			// "正在登录，请稍等...",false);
			startProgressDialog();
		};


		@Override
		protected StatisticalCharts doInBackground(Void... arg0) {
			Intent intent = getIntent();
			String queryDay =intent.getStringExtra("queryDay");
			String jsonStr = HttpUtils.getJsonStr(mUrl+"&queryDay="+queryDay);
			//Log.d("++++++++", jsonStr);
			StatisticalCharts charts = HttpUtils.StatisticalChartsFromJson(jsonStr);
			return charts;
		}
		
		@Override  
        protected void onPostExecute(StatisticalCharts charts){
			stopProgressDialog();
			if(charts==null){
				FunctionUtils.showCustomDialog("服务器异常暂无数据，\n请稍后刷新...", PieChartsActivity.this);
				return;
				//finish();
			}
			List<Chart> chartList =charts.getCharts();
			if(chartList.size()==2){
			PieChart mChart = (PieChart) findViewById(R.id.pie_chart);
			mChart.setVisibility(View.VISIBLE);
			mChart.setCenterText(chartList.get(0).getTitle());
			PieChart mChart1 = (PieChart) findViewById(R.id.pie_chart1);
			mChart1.setVisibility(View.VISIBLE);
			mChart1.setCenterText(chartList.get(1).getTitle());
	        PieData mPieData = getPieData(chartList.get(0), 100);  
	        PieData mPieData1 = getPieData(chartList.get(1), 100); 
	        showChart(mChart, mPieData);  
	        showChart(mChart1, mPieData1); 
			}else if(chartList.size()==1){
				PieChart mChart = (PieChart) findViewById(R.id.pie_chart);
				mChart.setVisibility(View.VISIBLE);
				mChart.setCenterText(chartList.get(0).getTitle());
				PieData mPieData = getPieData(chartList.get(0), 100); 
				showChart(mChart, mPieData); 
			}
		}
	}
	
	 private void showChart(PieChart pieChart, PieData pieData) {    
	        pieChart.setHoleColorTransparent(true);
	    
	        pieChart.setHoleRadius(70f);  //半径    
	        pieChart.setTransparentCircleRadius(74f); // 半透明圈    
	        //pieChart.setHoleRadius(0)  //实心圆    
	    
	        pieChart.setDescription(null); 
	       /* pieChart.setNoDataText("正在刷新...");
	        pieChart.setNoDataTextDescription("正在刷新...");
	        pieChart.invalidate();*/
	    
	        // mChart.setDrawYValues(true);    
	        pieChart.setDrawCenterText(true);  //饼状图中间可以添加文字    
	    
	        pieChart.setDrawHoleEnabled(true);    
	    
	        pieChart.setRotationAngle(90); // 初始旋转角度    
	    
	        // draws the corresponding description value into the slice    
	        // mChart.setDrawXValues(true);    
	    
	        // enable rotation of the chart by touch    
	        pieChart.setRotationEnabled(true); // 可以手动旋转    
	    
	        // display percentage values    
	        pieChart.setUsePercentValues(true);
	        pieChart.setDrawSliceText(false);
	        pieChart.highlightValue(null);
	        //显示成百分比    
	        // mChart.setUnit(" €");    
	        // mChart.setDrawUnitsInChart(true);    
	    
	        // add a selection listener    
//	      mChart.setOnChartValueSelectedListener(this);    
	        // mChart.setTouchEnabled(false);    
	    
//	      mChart.setOnAnimationListener(this);    
	    
	        //pieChart.setCenterText("统计");  //饼状图中间的文字    
	    
	        //设置数据    
	        pieChart.setData(pieData);     
	            
	        // undo all highlights    
//	      pieChart.highlightValues(null);    
//	      pieChart.invalidate();    
	    
	        Legend mLegend = pieChart.getLegend();  //设置比例图    
	        mLegend.setPosition(LegendPosition.RIGHT_OF_CHART);  //最右边显示    
//	      mLegend.setForm(LegendForm.LINE);  //设置比例图的形状，默认是方形    
	        mLegend.setXEntrySpace(7f);    
	        mLegend.setYEntrySpace(5f);  
	        //mLegend.setFormToTextSpace(20f);
	            
	        pieChart.animateXY(1000, 1000);  //设置动画    
	        // mChart.spin(2000, 0, 360);    
	    }    
	    
	    /**  
	     *   
	     * count 分成几部分
	     * @param range  
	     */    
	    private PieData getPieData(Chart chart2, float range) { 
	    	List<String> yList =new ArrayList<String>();
			List<String> nameList =new ArrayList<String>();
				for(ChartData data:chart2.getData()){
					yList.add(data.getY());
					nameList.add(data.getName());
				}

	            
	        ArrayList<String> xValues = new ArrayList<String>();  //xVals用来表示每个饼块上的内容    
	    
	        for (int i = 0; i < nameList.size(); i++) {    
	            //xValues.add("Quarterly" + (i + 1));  //饼块上显示成Quarterly1, Quarterly2, Quarterly3, Quarterly4    
	        	xValues.add(nameList.get(i));
	        }    
	    
	        ArrayList<Entry> yValues = new ArrayList<Entry>();  //yVals用来表示封装每个饼块的实际数据
	    
	        // 饼图数据    
	        /**  
	         * 将一个饼形图分成四部分， 四部分的数值比例为14:14:34:38  
	         * 所以 14代表的百分比就是14%   
	         */    
	        /*float quarterly1 = 50;    
	        float quarterly2 = 14;    
	        float quarterly3 = 34;    
	        float quarterly4 = 38;    
	    
	        yValues.add(new Entry(quarterly1, 0));    
	        yValues.add(new Entry(quarterly2, 1));    
	        yValues.add(new Entry(quarterly3, 2));    
	        yValues.add(new Entry(quarterly4, 3)); */  
	        for (int i = 0; i < nameList.size(); i++) {  
	            float val = Float.valueOf(yList.get(i)); 
	            yValues.add(new BarEntry(val, i));
	        }  
	    
	        //y轴的集合    
	        PieDataSet pieDataSet = new PieDataSet(yValues, "饼状图统计"/*显示在比例图上*/);
	        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离    
	        
	        // 设置折线上显示数据的格式。如果不设置，将默认显示float数据格式。  
	        pieDataSet.setValueFormatter(new ValueFormatter() {
				@Override
				public String getFormattedValue(float value, Entry entry,
						int dataSetIndex, ViewPortHandler viewPortHandler) {
					// TODO Auto-generated method stub
					int n = (int) value;
					if(n>0){
	 	            String s =n+"%";
	 	            return s; }else{
						return "";
					}
				}  
	        });
	    
	        ArrayList<Integer> colors = new ArrayList<Integer>();    
	    
	        // 饼图颜色    
	        colors.add(Color.rgb(205, 205, 205));    
	        colors.add(Color.rgb(114, 188, 223));    
	        colors.add(Color.rgb(255, 123, 124));    
	        colors.add(Color.rgb(57, 135, 200)); 
	        colors.add(Color.RED);
	        colors.add(Color.rgb(154,205,50));
	        colors.add(Color.rgb(178,58,238));
	        colors.add(Color.rgb(238,238,0));
	        colors.add(Color.rgb(255,187,255));
	        colors.add(Color.rgb(78,238,148));
	    
	        pieDataSet.setColors(colors);    
	    
	        DisplayMetrics metrics = getResources().getDisplayMetrics();    
	        float px = 5 * (metrics.densityDpi / 160f);    
	        pieDataSet.setSelectionShift(px); // 选中态多出的长度    
	        //pieDataSet.setDrawValues(false);
	    
	        PieData pieData = new PieData(xValues, pieDataSet);
	        pieData.setValueTextSize(12f);
	        
	            
	        return pieData;    
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
