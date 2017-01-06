package com.example.runcloud.base;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import com.example.runcloud.mvp.other.Data;

/**
 * Created by Summers on 2016/8/24.
 */
public abstract class BaseFragment extends Fragment {
    protected View view;
    protected int layoutId;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        if (view == null) {
            initLayoutId();
            view = inflater.inflate(layoutId, container, false);
            ButterKnife.bind(this, view);
            initViews();
        }
        AlwaysInit();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    protected abstract void initLayoutId();

    protected abstract void initViews();

    protected abstract void initData();

    protected void AlwaysInit() {
        ButterKnife.bind(this, view);
    }

    public abstract void addNews(Data news);

    public boolean isAlive() {
        return getActivity() != null;
    }
}
