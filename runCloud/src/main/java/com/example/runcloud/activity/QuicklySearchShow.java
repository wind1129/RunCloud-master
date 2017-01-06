package com.example.runcloud.activity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.example.runcloud.BaseActivity;
import com.example.runcloud.R;
import com.example.runcloud.adapt.MyAdapt;
import com.example.runcloud.adapt.QueryConditonAdapt;
import com.example.runcloud.application.MyApplication;
import com.example.runcloud.entity.GetParameters;
import com.example.runcloud.entity.NetEvent;
import com.example.runcloud.entity.NewsListEntity;
import com.example.runcloud.entity.UserDataSource;
import com.example.runcloud.extra.QueryConditionPopWindow;
import com.example.runcloud.fragment.FragmentMainActivity;
import com.example.runcloud.net.API;
import com.example.runcloud.receiver.NetReceiver;
import com.example.runcloud.util.FunctionUtils;
import com.example.runcloud.util.NetUtils;


import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import com.example.runcloud.mvp.adapt.SearchNewsListAdapter;
import com.example.runcloud.mvp.interf.NewsPresenter;
import com.example.runcloud.mvp.interf.NewsView;
import com.example.runcloud.mvp.interf.OnListFragmentInteract;
import com.example.runcloud.mvp.other.Data;
import com.example.runcloud.mvp.other.RecycleViewDivider;
import com.example.runcloud.mvp.presenter.NewsDataPresenter;
import com.example.runcloud.mvp.utils.Constants;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.PopupWindow.OnDismissListener;

public class QuicklySearchShow extends BaseActivity implements OnClickListener,OnListFragmentInteract,NewsView,SwipeRefreshLayout.OnRefreshListener {
	/** 标题 */
	private TextView title_show;

	/** 查询条件按键 */
	private ArrayList<String> dateList;
	private ArrayList<String> emotionList;
	private ArrayList<String> sourceFromList = new ArrayList<String>();
	private View queryDate;
	private View queryEmotion;
	private View querySourceFrom;
	private QueryConditionPopWindow queryPopWindow;
	// 当前选中的列表项位置
	int clickPsition = -1;
	/** 全局变量 */
	private MyApplication myApplication;

	/** get参数对象 */
	private GetParameters para = new GetParameters();
	/** url部分 */
	private String urlpart = "/ares/restful/fulltextsearch/search?";
	private String url;
	/** 带userId和orderId的url */
	private String finalURL;
	private String queryDay = "30";

	/** 新闻列表集合 */
	private LinkedList<NewsListEntity> entitys = new LinkedList<NewsListEntity>();
	private LinkedList<NewsListEntity> mEntitys = new LinkedList<NewsListEntity>();

	/** 主列表页的适配器 */
	private MyAdapt myAdapt;
	/** 每次查询内容数量以及加载的数量 */
	private static final int mLoadDataCount = 4;
	/** 每次加载内容List的起始位置 */
	private int mCurIndex = 0;
	/** 页数 */
	private int page = 2;

	private TextView backFirstPageView;
	private ImageView backView;

	private NetReceiver mReceiver;
	private RelativeLayout no_network_rl;
	private boolean isConnected;



	//升级

	@Bind(R.id.list)
	RecyclerView recyclerView;
	@Bind(R.id.swipe_refresh)
	SwipeRefreshLayout swipeRefresh;

	private LinearLayoutManager layoutManager;
	private BaseActivity mActivity;

	private SearchNewsListAdapter adapter;

	private NewsPresenter presenter;

	String keywords;

	int lastPosition;       //last visible position
	int firstPosition;      //first visible position
	private static final int PRELOAD_COUNT = 1;

	private int newType = API.TYPE_SEARCH;

	private void onListScrolled() {
		firstPosition = layoutManager.findFirstVisibleItemPosition();
		lastPosition = layoutManager.findLastVisibleItemPosition();
		if (lastPosition + PRELOAD_COUNT == adapter.getItemCount()) {
			presenter.loadBefore(newType,url);
		}

	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quick_search_list);
		ButterKnife.bind(this);


		mActivity = (BaseActivity) this;
		layoutManager = new LinearLayoutManager(mActivity);
		//recyclerView.setHasFixedSize(true);
		swipeRefresh.setColorSchemeResources(R.color.colorPrimary,
				R.color.colorPrimaryDark, R.color.colorAccent);
		swipeRefresh.setOnRefreshListener(this);
		recyclerView.addItemDecoration(new RecycleViewDivider(mActivity, LinearLayoutManager.VERTICAL));
		recyclerView.setLayoutManager(layoutManager);
		adapter = new SearchNewsListAdapter(this, mActivity,newType);
		recyclerView.setAdapter(adapter);
		swipeRefresh.setProgressViewOffset(false, 0, (int) TypedValue
				.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
						.getDisplayMetrics()));


		recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
				super.onScrollStateChanged(recyclerView, newState);
				if (newState == RecyclerView.SCROLL_STATE_IDLE) {
					onListScrolled();
				}
			}
		});

		// 监听网络
		initReceive();
		no_network_rl = (RelativeLayout) findViewById(R.id.net_view_rl);
		EventBus.getDefault().register(this);
		/** url */
		myApplication = (MyApplication) getApplication();
		String userId = myApplication.getInformation().getUserId();
		String orgId = myApplication.getInformation().getOrgId();

		Intent intent = getIntent();
		keywords = intent.getStringExtra("keywords");
		finalURL = intent.getStringExtra("URL");
		url = finalURL;
		Log.d("+++++keywords", finalURL);

		/** 查询按键点击 */
		dateList = getDateList();
		emotionList = getEmotionList();

		List<UserDataSource> datasource = myApplication.getInformation()
				.getDataSourceList();
		for (UserDataSource userDataSource : datasource) {
			sourceFromList.add(userDataSource.getSelectName());
		}
		sourceFromList.add(0, "全部");

		queryDate = findViewById(R.id.query_time1);
		queryEmotion = findViewById(R.id.query_emotion1);
		querySourceFrom = findViewById(R.id.source_from1);
		queryDate.setOnClickListener(this);
		queryEmotion.setOnClickListener(this);
		querySourceFrom.setOnClickListener(this);

		backFirstPageView = (TextView) findViewById(R.id.back_firstPage);
		backView = (ImageView) findViewById(R.id.back);

		backFirstPageView.setOnClickListener(this);
		backView.setOnClickListener(this);

		/** 标题点击事件 */
		// initDatas();
		// attendStr.addAll(Arrays.asList(mString));
		title_show = (TextView) findViewById(R.id.title_show2);
		title_show.setText("查询结果");

		presenter = new NewsDataPresenter(this, this);
		onRefresh();



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
					NetUtils.startToSettings(QuicklySearchShow.this);
				}
			});
		}
	}



	@Override
	public void showProgress() {
		showProgress(true);
	}

	@Override
	public void addNews(Data news) {
		adapter.notifyDataSetChanged();
		if(adapter.getList().size()==0){
			hideProgress();
			FunctionUtils.showCustomDialog("没有查询到数据",
					this);
		}
	}

	@Override
	public void hideProgress() {
		showProgress(false);
	}

	@Override
	public void loadFailed(String msg) {

			FunctionUtils.showCustomDialog("没有查询到数据",
					this);
			return;
		}


	@Override
	public void onListFragmentInteraction(RecyclerView.ViewHolder viewHolder) {
		if (viewHolder instanceof SearchNewsListAdapter.ItemViewHolder){
			SearchNewsListAdapter.ItemViewHolder holder =(SearchNewsListAdapter.ItemViewHolder)viewHolder;
			Intent intent = new Intent(this, StoryActivity.class);
			intent.putExtra(Constants.ID,holder.newsContent.getDocId());
			intent.putExtra(Constants.KEY_WORDS,keywords);
			ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
					holder.mItem, getString(R.string.shared_context));
			ActivityCompat.startActivity(this, intent, optionsCompat.toBundle());
			/*holder.newslistContext.setTextColor(WechatNewsListAdapter.textGrey);
			holder.newslistTitle.setTextColor(WechatNewsListAdapter.textGrey);*/

		}
	}

	@Override
	public void onRefresh() {
		presenter.loadNews(newType,url);
	}

	public void showProgress(final boolean refreshState) {
		if (null != swipeRefresh) {
			swipeRefresh.setRefreshing(refreshState);
		}
	}




	/**
	 * 得到查询时间的list集合的方法
	 * 
	 * @return
	 */
	public ArrayList<String> getDateList() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("全部");
		list.add("今天");
		list.add("3天内");
		list.add("一周内");
		list.add("一个月内");

		return list;

	}

	public ArrayList<String> getEmotionList() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("全部");
		list.add("正面");
		list.add("中性");
		list.add("负面");
		return list;

	}

	/***/
	public ListView queryConditonPopWindow(View view, int id, ArrayList list) {
		queryPopWindow = new QueryConditionPopWindow(QuicklySearchShow.this,
				itemsOnClick);
		int w = view.getMeasuredWidth();
		queryPopWindow.setWidth(w);
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.alpha = 0.4f;
		getWindow().setAttributes(lp);
		queryPopWindow.setOutsideTouchable(true);
		// queryPopWindow.setFocusable(true);
		queryPopWindow.setOnDismissListener(new OnDismissListener() {

			// 在dismiss中恢复透明度
			public void onDismiss() {
				WindowManager.LayoutParams lp = getWindow().getAttributes();
				lp.alpha = 1f;
				getWindow().setAttributes(lp);
			}
		});
		queryPopWindow.showAsDropDown(view);
		View queryPopView = queryPopWindow.getContentView();
		ListView lv = (ListView) queryPopView.findViewById(id);
		lv.setAdapter(new QueryConditonAdapt(QuicklySearchShow.this, list));
		return lv;

	}

	/** 为弹出窗口(PopUpWindow)实现监听类 */
	private OnClickListener itemsOnClick = new OnClickListener() {

		public void onClick(View v) {
			switch (v.getId()) {

			default:
				break;
			}

		}

	};

	@Override
	public void onClick(View viewArg) {
		// Intent intent = new Intent();
		switch (viewArg.getId()) {
		case R.id.back_firstPage:
			Intent intent = new Intent(this, FragmentMainActivity.class);
			startActivity(intent);
			break;
		case R.id.back:
			finish();
			return;
		case R.id.query_time1:
			isConnected = NetUtils.isNetworkConnected(QuicklySearchShow.this);
			if (isConnected == false) {
				FunctionUtils.showCustomDialog("网络已断开，\n请检查网络...",
						QuicklySearchShow.this);
				return;
			}
			ListView lvDate = queryConditonPopWindow(queryDate, R.id.query_pop,
					dateList);
			lvDate.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					String date = dateList.get(position);
					switch (position) {
					case 0:
						queryDay = "30";
						para.setQueryDay("");
						break;
					case 1:
						queryDay = "1";
						para.setQueryDay("&queryDay=1");
						break;
					case 2:
						queryDay = "3";
						para.setQueryDay("&queryDay=3");
						break;
					case 3:
						queryDay = "7";
						para.setQueryDay("&queryDay=7");
						break;
					case 4:
						queryDay = "30";
						para.setQueryDay("&queryDay=30");
						break;
					}
					url = finalURL + para.getQueryDay() + para.getMediaType()
							+ para.getNegativeStatus();
					onRefresh();
					if (clickPsition != position) {
						clickPsition = position;
					}
					queryPopWindow.dismiss();

				}

			});
			return;
		case R.id.query_emotion1:
			isConnected = NetUtils.isNetworkConnected(QuicklySearchShow.this);
			if (isConnected == false) {
				FunctionUtils.showCustomDialog("网络已断开，\n请检查网络...",
						QuicklySearchShow.this);
				return;
			}
			ListView lvEmotion = queryConditonPopWindow(queryEmotion,
					R.id.query_pop, emotionList);
			lvEmotion
					.setOnItemClickListener(new AdapterView.OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							String emotion = emotionList.get(position);
							switch (position) {
							case 0:
								para.setNegativeStatus("");
								break;
							case 1:
								para.setNegativeStatus("&negativeStatus=1");
								break;
							case 2:
								para.setNegativeStatus("&negativeStatus=0");
								break;
							case 3:
								para.setNegativeStatus("&negativeStatus=-1");
								break;
							}
							url = finalURL + para.getQueryDay()
									+ para.getMediaType()
									+ para.getNegativeStatus();
							onRefresh();
							if (clickPsition != position) {
								clickPsition = position;
							}
							queryPopWindow.dismiss();

						}

					});
			return;
		case R.id.source_from1:
			isConnected = NetUtils.isNetworkConnected(QuicklySearchShow.this);
			if (isConnected == false) {
				FunctionUtils.showCustomDialog("网络已断开，\n请检查网络...",
						QuicklySearchShow.this);
				return;
			}
			ListView lvSourceFrom = queryConditonPopWindow(querySourceFrom,
					R.id.query_pop, sourceFromList);
			lvSourceFrom
					.setOnItemClickListener(new AdapterView.OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							String sourceFrom = sourceFromList.get(position);
							if (sourceFrom.equals("全部")) {
								para.setMediaType("");
							} else if (sourceFrom.equals("新闻")) {
								para.setMediaType("&mediaType=1");
							} else if (sourceFrom.equals("论坛")) {
								para.setMediaType("&mediaType=2");
							} else if (sourceFrom.equals("博客")) {
								para.setMediaType("&mediaType=3");
							} else if (sourceFrom.equals("微博")) {
								para.setMediaType("&mediaType=5");
							} else if (sourceFrom.equals("音视频")) {
								para.setMediaType("&mediaType=4");
							} else if (sourceFrom.equals("微信")) {
								para.setMediaType("&mediaType=6");
							} else if (sourceFrom.equals("移动")) {
								para.setMediaType("&mediaType=7");
							}else if (sourceFrom.equals("电子报刊")) {
								para.setMediaType("&mediaType=9");
							}
							url = finalURL + para.getQueryDay()
									+ para.getMediaType()
									+ para.getNegativeStatus();
							onRefresh();
							if (clickPsition != position) {
								clickPsition = position;
							}
							queryPopWindow.dismiss();

						}

					});
			return;
		}
		// startActivity(intent);

	}

}
