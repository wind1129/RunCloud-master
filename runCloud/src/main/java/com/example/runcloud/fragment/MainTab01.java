package com.example.runcloud.fragment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.example.runcloud.BaseActivity;
import com.example.runcloud.R;
import com.example.runcloud.activity.AddAttention;
import com.example.runcloud.activity.BarChartsActivity;
import com.example.runcloud.activity.ContentActivity;
import com.example.runcloud.activity.LineChartsActivity;
import com.example.runcloud.activity.PieChartsActivity;
import com.example.runcloud.activity.SearchActivity;
import com.example.runcloud.activity.StoryActivity;
import com.example.runcloud.adapt.MyAdapt;
import com.example.runcloud.adapt.QueryConditonAdapt;
import com.example.runcloud.adapt.ViewPageAdapt;
import com.example.runcloud.application.MyApplication;
import com.example.runcloud.base.BaseFragment;
import com.example.runcloud.entity.GetParameters;
import com.example.runcloud.entity.NetEvent;
import com.example.runcloud.entity.NewsListEntity;
import com.example.runcloud.entity.Sensrule;
import com.example.runcloud.entity.Sensrules;
import com.example.runcloud.entity.UserDataSource;
import com.example.runcloud.extra.QueryConditionPopWindow;
import com.example.runcloud.extra.ThemePopUpWindow;
import com.example.runcloud.mvp.utils.UIUtils;
import com.example.runcloud.net.API;
import com.example.runcloud.receiver.NetReceiver;
import com.example.runcloud.util.FunctionUtils;
import com.example.runcloud.util.HttpUtils;
import com.example.runcloud.util.NetUtils;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.Bind;
import de.greenrobot.event.EventBus;
import com.example.runcloud.mvp.adapt.SensitiveNewsListAdapter;
import com.example.runcloud.mvp.interf.NewsPresenter;
import com.example.runcloud.mvp.interf.NewsView;
import com.example.runcloud.mvp.interf.OnListFragmentInteract;
import com.example.runcloud.mvp.other.Data;
import com.example.runcloud.mvp.other.RecycleViewDivider;
import com.example.runcloud.mvp.presenter.NewsDataPresenter;
import com.example.runcloud.mvp.utils.Constants;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.PopupWindow.OnDismissListener;

@SuppressLint("NewApi")
public class MainTab01 extends BaseFragment implements OnClickListener,OnListFragmentInteract,NewsView,SwipeRefreshLayout.OnRefreshListener {
	public static Context mainContext;

	//public View view;
	/** 标题 */
	private TextView title_show;
	private RelativeLayout title_show_click;
	
	/** 折线图按钮 */
	private Button line_chart_button;
	/** 柱状图按钮 */
	private Button histogram_button;
	/** 饼图图按钮 */
	private Button pie_graph_button;

	// 查询条件按键
	private ArrayList<String> dateList;
	private ArrayList<String> emotionList;
	private ArrayList<String> sourceFromList = new ArrayList<String>();
	private View queryDate;
	private View queryEmotion;
	private View querySourceFrom;
	private QueryConditionPopWindow queryPopWindow;

	/** 当前选中的列表项位置 */
	int clickPsition = -1;
	/** 本地存储 */
	private SharedPreferences sp;
	/** 全局变量 */
	private MyApplication myApplication;
	/** get参数对象 */
	private GetParameters para = new GetParameters();
	// url部分
	private String uri;
	private String urlpart = "/ares/restful/sensitives/sensitives?";
	private String rulesUrlPart = "/ares/restful/sensitives/sensrules?";
	/** 会变动的完整url */
	private String url;
	/** 带userId和orderId的url */
	private String finalURL;
	/** 带userId和orderId的规则url */
	private String finalRulesUrl;

	/** 图表需要的时间默认值=30 */
	private String queryDay = "30";
	/** 首页其他按钮 */
	private ImageButton first_add;
	private ImageButton first_search;

	private ThemePopUpWindow mPopUpWindow;
	/** 重点网民集合 */
	private List<Sensrule> attendStr = new ArrayList<Sensrule>();

	// 定义ViewPager对象
	private ViewPager viewPager;
	// 定义ViewPager适配器
	private ViewPageAdapt viewPageAdapt;
	// 定义一个ArrayList来存放View
	private ArrayList<View> views;
	// 底部小点的图片
	private ImageView[] points;
	// text数组
	private TextView[] texts;
	private TextView currentText;
	private RelativeLayout relativeLayout;
	// 记录当前选中位置
	private int currentIndex;
	// 页数
	private int pageNum;
	// 当前页
	private int count = 1;
	private int pageStartRow = 0;// 每页的起始数
	private int pageEndRow = 0; // 每页显示数据的终止数

	/** 新闻列表集合 */
	private LinkedList<NewsListEntity> entitys = new LinkedList<NewsListEntity>();
	private LinkedList<NewsListEntity> mEntitys = new LinkedList<NewsListEntity>();

	/** 主列表页的适配器 */
	private MyAdapt myAdapt;
	/** 页数 */
	private int page = 2;

	/** 图片旋转相关 */
	private ImageView title_arrow_image;
	private Animation operatingAnim;
	private LinearInterpolator lin;

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

	private SensitiveNewsListAdapter adapter;

	private NewsPresenter presenter;

	int lastPosition;       //last visible position
	int firstPosition;      //first visible position
	private static final int PRELOAD_COUNT = 1;

	private int newType = API.TYPE_SENSITIVE;






	@Override
	protected void initLayoutId() {
		layoutId = R.layout.main_tab_01;

	}

	@Override
	protected void initData() {
		presenter = new NewsDataPresenter(this, (BaseActivity) getActivity());
		onRefresh();

	}

	private void onListScrolled() {
		firstPosition = layoutManager.findFirstVisibleItemPosition();
		lastPosition = layoutManager.findLastVisibleItemPosition();
		if (lastPosition + PRELOAD_COUNT == adapter.getItemCount()) {
			presenter.loadBefore(newType,url);
		}

	}

	@Override
	protected void initViews() {
		mActivity = (BaseActivity) getActivity();
		layoutManager = new LinearLayoutManager(mActivity);
		//recyclerView.setHasFixedSize(true);
		swipeRefresh.setColorSchemeResources(R.color.colorPrimary,
				R.color.colorPrimaryDark, R.color.colorAccent);
		swipeRefresh.setOnRefreshListener(this);
		recyclerView.addItemDecoration(new RecycleViewDivider(mActivity, LinearLayoutManager.VERTICAL));
		recyclerView.setLayoutManager(layoutManager);
		adapter = new SensitiveNewsListAdapter(this, mActivity,newType);
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





		mainContext = getActivity();

		// 监听网络
		initReceive();
		no_network_rl = (RelativeLayout) view.findViewById(R.id.net_view_rl);
		EventBus.getDefault().register(this);

		/** url */
		myApplication = (MyApplication) getActivity().getApplication();
		String userId = myApplication.getInformation().getUserId();
		String orgId = myApplication.getInformation().getOrgId();

		sp = getActivity().getSharedPreferences("userInfo",
				Context.MODE_PRIVATE);
		uri = sp.getString("URI", "");
		// 初始化url
		url = uri + urlpart + "userId=" + userId + "&orgId=" + orgId;
		finalURL = uri + urlpart + "userId=" + userId + "&orgId=" + orgId;
		finalRulesUrl = uri + rulesUrlPart + "userId=" + userId + "&orgId="
				+ orgId;
		//Log.d("---------------------------", url);

		/** 查询按键点击 */
		dateList = getDateList();
		emotionList = getEmotionList();

		List<UserDataSource> datasource = myApplication.getInformation()
				.getDataSourceList();
		for (UserDataSource userDataSource : datasource) {
			sourceFromList.add(userDataSource.getSelectName());
		}
		sourceFromList.add(0, "全部");

		queryDate = view.findViewById(R.id.query_time);
		queryEmotion = view.findViewById(R.id.query_emotion);
		querySourceFrom = view.findViewById(R.id.source_from);
		queryDate.setOnClickListener(this);
		queryEmotion.setOnClickListener(this);
		querySourceFrom.setOnClickListener(this);

		/** 折线图按钮点击 */
		line_chart_button = (Button) view.findViewById(R.id.line_chart_button);
		line_chart_button.setOnClickListener(this);

		histogram_button = (Button) view.findViewById(R.id.histogram_button);
		histogram_button.setOnClickListener(this);

		pie_graph_button = (Button) view.findViewById(R.id.pie_graph_button);
		pie_graph_button.setOnClickListener(this);

		/** 首页其它点击事件 */

		getActivity().findViewById(R.id.first_add).setVisibility(View.VISIBLE);
		first_add = (ImageButton) getActivity().findViewById(R.id.first_add);
		first_search = (ImageButton) getActivity().findViewById(R.id.first_search);
		first_add.setOnClickListener(this);
		first_search.setOnClickListener(this);

		/** 旋转的图片 */
		title_arrow_image = (ImageView) getActivity()
				.findViewById(R.id.title_arrow_image);
		title_arrow_image.setVisibility(View.VISIBLE);

		/** 标题点击事件 */
		title_show = (TextView) getActivity().findViewById(R.id.title_show);
		title_show.setText("舆情");

		title_show_click = (RelativeLayout) getActivity().findViewById(R.id.title_show_click);
		title_show_click.setClickable(true);
		title_show_click.setOnClickListener(this);



		/** Fragment的Touch事件 */
		view.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				if (mPopUpWindow != null && mPopUpWindow.isShowing()) {

					operatingAnim = AnimationUtils.loadAnimation(getActivity(),
							R.anim.image_above_rote);
					// lin = new LinearInterpolator();
					operatingAnim.setInterpolator(lin);
					if (operatingAnim != null) {
						title_arrow_image.startAnimation(operatingAnim);
						operatingAnim.setFillAfter(true);
					}
					mPopUpWindow.dismiss();
					// mPopUpWindow=null;
				}
				return false;
			}
		});

	}






	@Override
	public void onDestroyView() {
		OkHttpUtils.getInstance().cancelTag(mActivity);
		getActivity().unregisterReceiver(mReceiver);
		EventBus.getDefault().unregister(this);
		super.onDestroyView();
	}

	/** 初始化网络的广播接收器 */
	private void initReceive() {
		mReceiver = new NetReceiver();
		IntentFilter mFilter = new IntentFilter();
		mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		getActivity().registerReceiver(mReceiver, mFilter);
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
					NetUtils.startToSettings(getActivity());
				}
			});
		}
	}

	/** 为弹出窗口(PopUpWindow)实现监听类 */
	private OnClickListener itemsOnClick = new OnClickListener() {

		public void onClick(View v) {
			// mPopUpWindow.dismiss();
			switch (v.getId()) {
			// case R.id.ll_below_detail:
			// mPopUpWindow.dismiss();
			// break;
			default:
				break;
			}

		}

	};

	@Override
	public void onListFragmentInteraction(RecyclerView.ViewHolder viewHolder) {
		if (viewHolder instanceof SensitiveNewsListAdapter.ItemViewHolder){
			SensitiveNewsListAdapter.ItemViewHolder holder =(SensitiveNewsListAdapter.ItemViewHolder)viewHolder;
			Intent intent = new Intent(getActivity(), StoryActivity.class);
			intent.putExtra(Constants.ID,holder.newsContent.getDocId());
			intent.putExtra(Constants.KEY_WORDS,holder.newsContent.getKeywords());
			startActivity(intent);
			getActivity().overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);


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
					getActivity());
		}
	}

	@Override
	public void hideProgress() {
		showProgress(false);

	}

	@Override
	public void loadFailed(String msg) {
		if (isAlive()) {
			FunctionUtils.showCustomDialog("没有查询到数据",
					getActivity());
			return;
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






	/** 标题弹出窗的AsyncTask */
	public class PopUpWindowTask extends AsyncTask<Void, Void, Sensrules> {

		@Override
		protected Sensrules doInBackground(Void... arg0) {
			String url = finalRulesUrl;
			String jsonStr = HttpUtils.getJsonStr(url);
			Sensrules sensrules = HttpUtils.SensrulesFromJson(jsonStr);
			return sensrules;
		}

		@Override
		protected void onPostExecute(Sensrules sensrules) {
			attendStr = sensrules.getSensruleList();
			Sensrule sRule = new Sensrule("all", "全部");
			attendStr.add(0, sRule);

			mPopUpWindow = new ThemePopUpWindow(getActivity(), itemsOnClick);

			View actionbarToolbar = getActivity().findViewById(R.id.actionbarToolbar);
			// 弹出框在标题下方
			mPopUpWindow.showAsDropDown(actionbarToolbar);
			View popView = mPopUpWindow.getContentView();

			// 弹出框外暗色部分
			LinearLayout ll_below_detail = (LinearLayout) popView
					.findViewById(R.id.ll_below_detail);
			ll_below_detail.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					operatingAnim = AnimationUtils.loadAnimation(getActivity(),
							R.anim.image_above_rote);
					// lin = new LinearInterpolator();
					operatingAnim.setInterpolator(lin);
					if (operatingAnim != null) {
						title_arrow_image.startAnimation(operatingAnim);
						operatingAnim.setFillAfter(true);
					}
					mPopUpWindow.dismiss();

				}
			});

			// 用来放点的布局
			ViewGroup group = (ViewGroup) popView.findViewById(R.id.viewGroup);
			// 实例化ViewPager
			viewPager = (ViewPager) popView.findViewById(R.id.viewPager);
			views = new ArrayList<View>();

			// 获取布局inflater用来加载RelativeLayout
			LayoutInflater inflater1 = LayoutInflater.from(getActivity()
					.getApplicationContext());

			if (attendStr.size() % 8 == 0) {
				pageNum = attendStr.size() / 8;
			} else {
				pageNum = attendStr.size() / 8 + 1;
			}

			for (int x = 0; x < pageNum; x++) {
				if (count * 8 < attendStr.size()) {// 判断是否为最后一页
					pageEndRow = count * 8;
					pageStartRow = pageEndRow - 8;
				} else {
					pageEndRow = attendStr.size();
					pageStartRow = 8 * (pageNum - 1);
				}
				relativeLayout = (RelativeLayout) inflater1.inflate(
						R.layout.popattention, null);
				List<Sensrule> subList = new ArrayList<Sensrule>();
				subList = attendStr.subList(pageStartRow, pageEndRow);
				//Log.d("++++++++++++++subList", subList.size() + "");
				texts = new TextView[subList.size()];
				for (int i = 0; i < subList.size(); i++) {
					texts[i] = (TextView) relativeLayout.getChildAt(i);
					texts[i].setVisibility(View.VISIBLE);
					texts[i].setText(subList.get(i).getSelectName());
					texts[i].setTag(subList.get(i).getSelectId());
					texts[i].setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View arg0) {
							currentText = (TextView) arg0;
							currentText.setBackgroundColor(Color.rgb(100, 149,
									237));
							currentText.setTextColor(Color.WHITE);
							String tag = (String) currentText.getTag();
							if (tag.equals("all")) {
								para.setSensruleId("");
							} else {
								para.setSensruleId("&sensruleId=" + tag);
							}
							url = finalURL + para.getSensruleId();


							onRefresh();
							// new AnalysisTask(url).execute();

							new Handler().postDelayed(new Runnable() {
								public void run() {
									operatingAnim = AnimationUtils
											.loadAnimation(getActivity(),
													R.anim.image_above_rote);
									// lin = new LinearInterpolator();
									operatingAnim.setInterpolator(lin);
									if (operatingAnim != null) {
										title_arrow_image
												.startAnimation(operatingAnim);
										operatingAnim.setFillAfter(true);
									}
									mPopUpWindow.dismiss();
								}
							}, 500);

						}
					});

				}
				views.add(relativeLayout);
				if (count < pageNum) {
					count = count + 1;
				} else {
					count = 1;
				}
			}

			// 将点点加入到ViewGroup中
			points = new ImageView[pageNum];
			for (int i = 0; i < pageNum; i++) {
				ImageView imageView = new ImageView(getActivity());
				imageView.setLayoutParams(new LayoutParams(15, 15));

				points[i] = imageView;
				points[i].setBackgroundResource(R.drawable.point);
				// 默认都设为灰色
				points[i].setEnabled(true);

				group.setPadding(0,0,0,10);

				group.addView(imageView);
			}

			// 设置当面默认的位置
			currentIndex = 0;
			// 设置为白色，即选中状态
			points[currentIndex].setEnabled(false);

			// 实例化ViewPager适配器
			viewPageAdapt = new ViewPageAdapt(views);
			// 设置Adapter
			viewPager.setAdapter(viewPageAdapt);

			// changeListener = new ThemeOnPageChangeListener(mImageViews,tips);
			// 设置监听，主要是设置点点的背景
			viewPager.setOnPageChangeListener(new OnPageChangeListener() {

				@Override
				public void onPageSelected(int arg0) {
					// 设置底部小点选中状态
					setCurDot(arg0);

				}

				@Override
				public void onPageScrolled(int arg0, float arg1, int arg2) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onPageScrollStateChanged(int arg0) {

				}

				/**
				 * 设置当前的小点的位置
				 */
				private void setCurDot(int positon) {
					if (positon < 0 || positon > pageNum - 1
							|| currentIndex == positon) {
						return;
					}
					points[positon].setEnabled(false);
					points[currentIndex].setEnabled(true);

					currentIndex = positon;
				}

			});
			title_show_click.setEnabled(true);

		}
	}

	/** 点击监听 */
	@Override
	public void onClick(View viewArg) {
		if(FunctionUtils.isFastDoubleClick()){
			return;
		}
		Intent intent = new Intent();
		switch (viewArg.getId()) {
		case R.id.line_chart_button:
			isConnected = NetUtils.isNetworkConnected(getActivity());
			if (isConnected == false) {
				FunctionUtils.showCustomDialog("网络已断开，\n请检查网络...",
						getActivity());
				return;
			}
			intent.putExtra("queryDay", queryDay);
			intent.setClass(getActivity(), LineChartsActivity.class);
			break;
		case R.id.histogram_button:
			isConnected = NetUtils.isNetworkConnected(getActivity());
			if (isConnected == false) {
				FunctionUtils.showCustomDialog("网络已断开，\n请检查网络...",
						getActivity());
				return;
			}
			intent.putExtra("queryDay", queryDay);
			intent.setClass(getActivity(), BarChartsActivity.class);
			break;
		case R.id.pie_graph_button:
			isConnected = NetUtils.isNetworkConnected(getActivity());
			if (isConnected == false) {
				FunctionUtils.showCustomDialog("网络已断开，\n请检查网络...",
						getActivity());
				return;
			}
			intent.putExtra("queryDay", queryDay);
			intent.setClass(getActivity(), PieChartsActivity.class);
			break;
		case R.id.query_time:
			isConnected = NetUtils.isNetworkConnected(getActivity());
			if (isConnected == false) {
				FunctionUtils.showCustomDialog("网络已断开，\n请检查网络...",
						getActivity());
				return;
			}

			ListView lvDate = queryConditonPopWindow(queryDate, R.id.query_pop,
					dateList);
			lvDate.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					String date = dateList.get(position);
					// String mUrl ="";
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
							+ para.getNegativeStatus() + para.getSensruleId();
					onRefresh();
					if (clickPsition != position) {
						clickPsition = position;
					}
					queryPopWindow.dismiss();

				}

			});
			return;
		case R.id.query_emotion:
			isConnected = NetUtils.isNetworkConnected(getActivity());
			if (isConnected == false) {
				FunctionUtils.showCustomDialog("网络已断开，\n请检查网络...",
						getActivity());
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
									+ para.getNegativeStatus()
									+ para.getSensruleId();
							onRefresh();
							if (clickPsition != position) {
								clickPsition = position;
							}
							queryPopWindow.dismiss();

						}

					});
			return;
		case R.id.source_from:
			isConnected = NetUtils.isNetworkConnected(getActivity());
			if (isConnected == false) {
				FunctionUtils.showCustomDialog("网络已断开，\n请检查网络...",
						getActivity());
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
									+ para.getNegativeStatus()
									+ para.getSensruleId();

							onRefresh();

							if (clickPsition != position) {
								clickPsition = position;
							}
							queryPopWindow.dismiss();
						}
					});
			return;
		case R.id.title_show_click:
			title_show_click.setEnabled(false);
			isConnected = NetUtils.isNetworkConnected(getActivity());
			if (isConnected == false) {
				FunctionUtils.showCustomDialog("网络已断开，\n请检查网络...",
						getActivity());
				return;
			}
			operatingAnim = AnimationUtils.loadAnimation(getActivity(),
					R.anim.image_bottom_rote);
			lin = new LinearInterpolator();
			operatingAnim.setInterpolator(lin);
			if (operatingAnim != null) {
				title_arrow_image.startAnimation(operatingAnim);
				operatingAnim.setFillAfter(true);
			}
			if (mPopUpWindow != null && mPopUpWindow.isShowing()) {
				mPopUpWindow.dismiss();
				title_show_click.setEnabled(true);
			} else {
				new PopUpWindowTask().execute();
			}
			return;
		case R.id.first_add:
			intent.setClass(getActivity(), AddAttention.class);
			break;

		case R.id.first_search:
			intent.setClass(getActivity(), SearchActivity.class);
			//getActivity().findViewById(R.id.ly_main_tab_bottom).setVisibility(View.GONE);
			break;

		}

		startActivity(intent);

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
		queryPopWindow = new QueryConditionPopWindow(getActivity(),
				itemsOnClick);
		int w = view.getMeasuredWidth();
		queryPopWindow.setWidth(w);
		WindowManager.LayoutParams lp = getActivity().getWindow()
				.getAttributes();
		lp.alpha = 0.4f;
		getActivity().getWindow().setAttributes(lp);

		queryPopWindow.setOutsideTouchable(true);
		// queryPopWindow.setFocusable(true);
		queryPopWindow.setOnDismissListener(new OnDismissListener() {

			// 在dismiss中恢复透明度
			public void onDismiss() {
				WindowManager.LayoutParams lp = getActivity().getWindow()
						.getAttributes();
				lp.alpha = 1f;
				getActivity().getWindow().setAttributes(lp);
			}
		});
		queryPopWindow.showAsDropDown(view);
		View queryPopView = queryPopWindow.getContentView();
		ListView lv = (ListView) queryPopView.findViewById(id);
		lv.setAdapter(new QueryConditonAdapt(getActivity(), list));
		return lv;

	}

	/**
	 * 设置添加屏幕的背景透明度
	 * 
	 * @param bgAlpha
	 */
	public void backgroundAlpha(float bgAlpha) {
		WindowManager.LayoutParams lp = getActivity().getWindow()
				.getAttributes();
		lp.alpha = bgAlpha; // 0.0-1.0
		getActivity().getWindow().setAttributes(lp);
	}






}
