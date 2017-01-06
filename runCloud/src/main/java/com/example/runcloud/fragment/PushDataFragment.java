package com.example.runcloud.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.runcloud.R;
import com.example.runcloud.entity.PushDatas;
import com.example.runcloud.mvp.adapt.PushDataAdapter;
import com.example.runcloud.mvp.interf.PushDataContract;
import com.example.runcloud.mvp.presenter.PushDataPresenter;
import com.example.runcloud.util.FunctionUtils;
import com.example.runcloud.view.LoadMoreRecyclerView;
import com.example.runcloud.view.RollViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Wind1129 on 16/11/25.
 */
public class PushDataFragment extends Fragment implements PushDataContract.View{
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recyclerView)
    LoadMoreRecyclerView recyclerView;

    PushDataContract.Presenter presenter;
    private PushDataAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    private String url = "http://10.100.100.51:8080/ares/restful/push/pushDatas?userId=1";
    private boolean isDataLoaded;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new PushDataPresenter(this);
        mAdapter = new PushDataAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View rootView = inflater.inflate(R.layout.push_data_list,container,false);
        ButterKnife.bind(this,rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        Log.i("+++1",recyclerView.toString());

        recyclerView.setLayoutManager(mLinearLayoutManager);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                R.color.colorPrimaryDark, R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onrefresh(url);
            }
        });

        recyclerView.setLoadMoreListener(new LoadMoreRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                presenter.loadMore(url);
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setAdapter(mAdapter);
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                    presenter.onrefresh(url);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (recyclerView != null) {
            View view = recyclerView.findViewById(R.id.viewPager);
            if (view != null) {
                ((RollViewPager) view).startAutoScroll();
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (recyclerView != null) {
            View view = recyclerView.findViewById(R.id.viewPager);
            if (view != null) {
                ((RollViewPager) view).stopAutoScroll();
            }
        }
    }

    @Override
    public void hideRefreshing() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        };
        getActivity().runOnUiThread(runnable);
    }



    @Override
    public void showRefreshing() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        };
        getActivity().runOnUiThread(runnable);

    }

    @Override
    public void showStory(PushDatas pushDatas) {
        if(pushDatas == null){
            isDataLoaded = false;
        }else{
            isDataLoaded = true;
            mAdapter.setList(pushDatas);


        }
    }

    @Override
    public void appendStory(PushDatas pushDatas) {
        mAdapter.appendList(pushDatas);
    }

    @Override
    public void loadFailed(String msg) {
        FunctionUtils.showCustomDialog("没有查询到数据",
                getActivity());
        return;
    }
}
