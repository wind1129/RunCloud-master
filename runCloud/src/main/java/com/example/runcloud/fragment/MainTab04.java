package com.example.runcloud.fragment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.example.runcloud.BaseActivity;
import com.example.runcloud.R;
import com.example.runcloud.activity.ContentActivity;
import com.example.runcloud.activity.SearchActivity;
import com.example.runcloud.activity.StoryActivity;
import com.example.runcloud.adapt.MyAdapt;
import com.example.runcloud.adapt.QueryConditonAdapt;
import com.example.runcloud.application.MyApplication;
import com.example.runcloud.base.BaseFragment;
import com.example.runcloud.entity.GetParameters;
import com.example.runcloud.entity.NetEvent;
import com.example.runcloud.entity.NewsListEntity;
import com.example.runcloud.entity.UserWechatGroup;
import com.example.runcloud.extra.QueryConditionPopWindow;
import com.example.runcloud.net.API;
import com.example.runcloud.receiver.NetReceiver;
import com.example.runcloud.util.FunctionUtils;
import com.example.runcloud.util.NetUtils;


import butterknife.Bind;
import de.greenrobot.event.EventBus;

import com.example.runcloud.mvp.adapt.WechatNewsListAdapter;
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
import android.net.ConnectivityManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.PopupWindow.OnDismissListener;

@SuppressLint("NewApi")
public class MainTab04 extends BaseFragment implements OnClickListener,OnListFragmentInteract,NewsView,SwipeRefreshLayout.OnRefreshListener{
	/** 标题 */
	private TextView title_show;
	private ImageButton other_search;
	/** 查询条件按键 */
	private ArrayList<String> dateList;
	private ArrayList<String> emotionList;
	private ArrayList<String> sourceFromList = new ArrayList<String>();
	private View queryDate;
	private View queryEmotion;
	private View querySourceFrom;
	private QueryConditionPopWindow queryPopWindow;
	// 当前选中的列表项位置
	private int clickPsition = -1;
	/** 本地存储 */
	private SharedPreferences sp;
	/** 全局变量 */
	private MyApplication myApplication;
	/** get参数对象 */
	private GetParameters para = new GetParameters();
	/** url部分 */
	private String uri;
	private String urlpart = "/ares/restful/wechat/wechatDatas?";
	private String rulesUrlPart = "/ares/restful/abroad/abroadSiteGroups?";
	private String url;
	/** 带userId和orderId的url */
	private String finalURL;
	/** 带userId和orderId的规则url */
	private String finalRulesUrl;
	/** 首页其他按钮 */
	private ImageButton first_add;
	private ImageButton first_search;

	/** 新闻列表集合 */
	private LinkedList<NewsListEntity> entitys = new LinkedList<NewsListEntity>();
	private LinkedList<NewsListEntity> mEntitys = new LinkedList<NewsListEntity>();

	/** 主列表页的适配器 */
	private MyAdapt myAdapt;
	/** 页数 */
	private int page = 2;

	/** 图片旋转相关 */
	private ImageView title_arrow_image;

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

	private WechatNewsListAdapter adapter;

	private NewsPresenter presenter;

	int lastPosition;       //last visible position
	int firstPosition;      //first visible position
	private static final int PRELOAD_COUNT = 1;

	private int newType = API.TYPE_WECHAT;

	private RelativeLayout title_show_click;


	@Override
	protected void initLayoutId() {
		layoutId = R.layout.main_tab_04;
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
		adapter = new WechatNewsListAdapter(this, mActivity,newType);
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

		List<UserWechatGroup> datasource = myApplication.getInformation()
				.getWechatGroupList();
		for (UserWechatGroup userWechatGroup : datasource) {
			sourceFromList.add(userWechatGroup.getSelectName());
		}
		sourceFromList.add(0, "全部");

		queryDate = view.findViewById(R.id.query_time);
		queryEmotion = view.findViewById(R.id.query_emotion);
		querySourceFrom = view.findViewById(R.id.source_from);
		queryDate.setOnClickListener(this);
		queryEmotion.setOnClickListener(this);
		querySourceFrom.setOnClickListener(this);

		/** 首页其它点击事件 */
		title_show = (TextView) getActivity().findViewById(R.id.title_show);
		title_show.setText("微信");


		getActivity().findViewById(R.id.first_add).setVisibility(View.GONE);
		first_search = (ImageButton) getActivity().findViewById(R.id.first_search);
		first_search.setOnClickListener(this);

		title_show_click = (RelativeLayout) getActivity().findViewById(R.id.title_show_click);
		title_show_click.setClickable(false);

		/** 旋转的图片 */
		title_arrow_image = (ImageView) getActivity()
				.findViewById(R.id.title_arrow_image);
		title_arrow_image.setVisibility(View.GONE);

	}

	@Override
	protected void initData() {
		presenter = new NewsDataPresenter(this, (BaseActivity) getActivity());
		onRefresh();
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

	public void showProgress(final boolean refreshState) {
		if (null != swipeRefresh) {
			swipeRefresh.setRefreshing(refreshState);
		}
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
	}
	

	@Override
	public void onDestroyView() {
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
			case R.id.ll_below_detail:
				// mPopUpWindow.dismiss();
				break;
			default:
				break;
			}

		}

	};

	@Override
	public void onListFragmentInteraction(RecyclerView.ViewHolder viewHolder) {
		if (viewHolder instanceof WechatNewsListAdapter.ItemViewHolder){
			WechatNewsListAdapter.ItemViewHolder holder =(WechatNewsListAdapter.ItemViewHolder)viewHolder;
			Intent intent = new Intent(getActivity(), StoryActivity.class);
			intent.putExtra(Constants.ID,holder.newsContent.getDocId());
			startActivity(intent);
			getActivity().overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
			/*holder.newslistContext.setTextColor(WechatNewsListAdapter.textGrey);
			holder.newslistTitle.setTextColor(WechatNewsListAdapter.textGrey);*/

		}
	}

	@Override
	public void onRefresh() {
		presenter.loadNews(newType,url);
	}



	@Override
	public void onClick(View viewArg) {
		if(FunctionUtils.isFastDoubleClick()){
			return;
		}
		Intent intent = new Intent();
		switch (viewArg.getId()) {
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
					switch (position) {
					case 0:
						para.setQueryDay("");
						break;
					case 1:
						para.setQueryDay("&queryDay=1");
						break;
					case 2:
						para.setQueryDay("&queryDay=3");
						break;
					case 3:
						para.setQueryDay("&queryDay=7");
						break;
					case 4:
						para.setQueryDay("&queryDay=30");
						break;
					}
					url = finalURL + para.getQueryDay()
							+ para.getNegativeStatus() + para.getChannelName();
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
									+ para.getNegativeStatus()
									+ para.getChannelName();
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
							// String mUrl ="";
							if (sourceFrom.equals("全部")) {
								para.setChannelName("");
							} else if (sourceFrom.equals("新闻")) {
								para.setChannelName("&channelName=新闻");
							} else if (sourceFrom.equals("财经")) {
								para.setChannelName("&channelName=财经");
							} else if (sourceFrom.equals("人社")) {
								para.setChannelName("&channelName=人社");
							} else if (sourceFrom.equals("卫生")) {
								para.setChannelName("&channelName=卫生");
							} else if (sourceFrom.equals("企业")) {
								para.setChannelName("&channelName=企业");
							} else if (sourceFrom.equals("机构")) {
								para.setChannelName("&channelName=机构");
							} else if (sourceFrom.equals("旅游")) {
								para.setChannelName("&channelName=旅游");
							} else if (sourceFrom.equals("军事")) {
								para.setChannelName("&channelName=军事");
							} else if (sourceFrom.equals("交通")) {
								para.setChannelName("&channelName=交通");
							} else if (sourceFrom.equals("环保")) {
								para.setChannelName("&channelName=环保");
							} else if (sourceFrom.equals("政府")) {
								para.setChannelName("&channelName=政府");
							} else if (sourceFrom.equals("宣传")) {
								para.setChannelName("&channelName=宣传");
							} else if (sourceFrom.equals("其他")) {
								para.setChannelName("&channelName=其他");
							}
							url = finalURL + para.getQueryDay()
									+ para.getNegativeStatus()
									+ para.getChannelName();
							//Log.d("+++++++weixinurl", url);
							onRefresh();
							if (clickPsition != position) {
								clickPsition = position;
							}
							queryPopWindow.dismiss();

						}

					});
			return;
		case R.id.first_search:
			intent = new Intent(getActivity(), SearchActivity.class);
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
}
