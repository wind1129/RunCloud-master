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
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

public class LineChartsActivity extends BaseActivity{
	/**本地存储*/
	private SharedPreferences sp;
	/**全局变量*/
	private MyApplication myApplication;
	private int dayCount = 30;
	private String urlpart = "/ares/restful/sensitives/lineCharts?";
	//String url = "http://118.126.142.130/ares/restful/sensitives/lineCharts?userId=16&orgId=10&queryDay=30";
	private String url;
	private String uri;

	private CustomProgressDialog progressDialog = null;
	
	
	/*Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg){
			switch(msg.what){
			case 1:
				mCharts = (StatisticalCharts) msg.obj;
				break;
			default:
				break;
			}
			
		}
	};*/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setTranslucentStatus();
		setContentView(R.layout.line_charts);
		/**url*/
		myApplication =(MyApplication)getApplication();
		String userId = myApplication.getInformation().getUserId();
		String orgId =myApplication.getInformation().getOrgId();

		sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
		uri = sp.getString("URI", "");

		//初始化url
		url = uri+urlpart+"userId="+userId+"&orgId="+orgId;

         new LineChartsTask(url).execute();
	}


	/**设置折线图上的数据*/
	private LineData makeLineData(int count,StatisticalCharts mCharts) {

		List<String> yList =new ArrayList<String>();
		List<String> nameList =new ArrayList<String>();
		List<Chart> chartList =mCharts.getCharts();
		for(Chart chart:chartList){
			for(ChartData data:chart.getData()){
				yList.add(data.getY());
				nameList.add(data.getName().substring(5));
			}
		}
		 ArrayList<String> x = new ArrayList<String>();
	        for (int i = 0; i < nameList.size(); i++) {
	            // x轴显示的数据
	            //x.add("x:" + i);
	        	x.add(nameList.get(i));
	        }

       // y轴的数据
       ArrayList<Entry> y = new ArrayList<Entry>();
       for (int i = 0; i <  nameList.size(); i++) {
           float val = Float.valueOf(yList.get(i));
           Entry entry = new Entry(val, i);  //纵坐标和横坐标
           y.add(entry);
       }

       // y轴数据集
       LineDataSet mLineDataSet = new LineDataSet(y, "趋势统计折线图");

       /**用y轴的集合来设置参数  **/

       // 线宽
       mLineDataSet.setLineWidth(3.0f);
       // 显示的圆形大小
       mLineDataSet.setCircleSize(5.0f);
       // 折线的颜色
       mLineDataSet.setColor(Color.rgb(72,118,255));
       // 圆球的颜色
       mLineDataSet.setCircleColor(Color.rgb(72,118,255));

       // 设置mLineDataSet.setDrawHighlightIndicators(false)后，
       // Highlight的十字交叉的纵横线将不会显示，
       // 同时，mLineDataSet.setHighLightColor(Color.CYAN)失效。
       //mLineDataSet.setDrawHighlightIndicators(true);
       // 按击后，十字交叉线的颜色
       //mLineDataSet.setHighLightColor(Color.CYAN);
       // 设置这项上显示的数据点的字体大小。
       mLineDataSet.setValueTextSize(10.0f);
       // 填充折线上数据点、圆球里面包裹的中心空白处的颜色。
       mLineDataSet.setCircleColorHole(Color.WHITE);

       // 改变折线样式，用曲线。
       mLineDataSet.setDrawCubic(true);
       // 默认是直线
       // 曲线的平滑度，值越大越平滑。
       mLineDataSet.setCubicIntensity(0.3f);

       // 填充曲线下方的区域，红色，半透明。
       mLineDataSet.setDrawFilled(true);
       mLineDataSet.setFillAlpha(128);
       mLineDataSet.setFillColor(Color.rgb(135,206,250));

       // 设置折线上显示数据的格式。如果不设置，将默认显示float数据格式。
       mLineDataSet.setValueFormatter(new ValueFormatter() {

			@Override
			public String getFormattedValue(float value, Entry entry,
					int dataSetIndex, ViewPortHandler viewPortHandler) {
				DecimalFormat decimalFormat = new DecimalFormat("");
				String s = decimalFormat.format(value);
	            return s;
			}
       });

       ArrayList<LineDataSet> mLineDataSets = new ArrayList<LineDataSet>();
       mLineDataSets.add(mLineDataSet);

       LineData mLineData = new LineData(x, mLineDataSets);
       return mLineData;
	}

	// 设置chart显示的样式
		private void setChartStyle(LineChart chart, LineData lineData, int color) {
			// 是否在折线图上添加边框
	        chart.setDrawBorders(false);
	        chart.setDescription(null);// 数据描述
	        // 如果没有数据的时候，会显示这个，类似listview的emtpyview
	        chart.setNoDataTextDescription("找不到数据了");

	     // 是否绘制背景颜色。
	        // 如果mLineChart.setDrawGridBackground(false)，
	        // 那么mLineChart.setGridBackgroundColor(Color.CYAN)将失效;
	        chart.setDrawGridBackground(false);
	        chart.setGridBackgroundColor(Color.CYAN);

	        // 触摸
	        chart.setTouchEnabled(true);
	        // 缩放
	        chart.setScaleEnabled(true);
	        //如果设置为true，挤压缩放被打开。如果设置为false，x和y轴可以被单独挤压缩放。
	        chart.setPinchZoom(false);
	        // 设置背景
	        chart.setBackgroundColor(color);

			chart.setHighlightPerDragEnabled(false);
			chart.setHighlightPerTapEnabled(false);
			chart.setScaleYEnabled(false);

			XAxis xAxis = chart.getXAxis();
			xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);


			// 设置x,y轴的数据
	        chart.setData(lineData);

	        // 设置比例图标示，就是那个一组y的value的
	        Legend mLegend = chart.getLegend();

	        mLegend.setPosition(LegendPosition.BELOW_CHART_CENTER);
	        mLegend.setForm(LegendForm.LINE);// 样式
	        mLegend.setFormSize(15.0f);// 字体
	        mLegend.setTextColor(Color.rgb(72,118,255));// 颜色
			mLegend.setXEntrySpace(10.0f);

	        // 沿x轴动画，时间2000毫秒。
	        chart.animateX(2000);
		}


	private class LineChartsTask extends AsyncTask<Void, Void, StatisticalCharts>{
		String mUrl;


		public LineChartsTask(String mUrl) {
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
			StatisticalCharts charts = HttpUtils.StatisticalChartsFromJson(jsonStr);
			//Log.d("+++++++++++++++", jsonStr);

			return charts;
		}

		@Override
        protected void onPostExecute(StatisticalCharts charts){
			stopProgressDialog();
			if(charts==null){
				FunctionUtils.showCustomDialog("服务器异常暂无数据，\n请稍后刷新...", LineChartsActivity.this);
				return;
				//finish();
			}
			LineChart chart = (LineChart) findViewById(R.id.line_chart);
			chart.setVisibility(View.VISIBLE);
			// 制作n个数据点（沿x坐标轴）  
	        LineData mLineData = makeLineData(dayCount,charts);
	        setChartStyle(chart, mLineData, Color.WHITE);

		}

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
