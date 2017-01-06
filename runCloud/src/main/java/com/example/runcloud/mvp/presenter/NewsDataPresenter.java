package com.example.runcloud.mvp.presenter;

import com.example.runcloud.BaseActivity;
import com.example.runcloud.net.API;

import com.example.runcloud.mvp.interf.NewsModel;
import com.example.runcloud.mvp.interf.NewsPresenter;
import com.example.runcloud.mvp.interf.NewsView;
import com.example.runcloud.mvp.interf.OnLoadDataListener;
import com.example.runcloud.mvp.model.NewsDataModel;

/**
 * Created by Summers on 2016/9/1.
 */
public class NewsDataPresenter implements NewsPresenter, OnLoadDataListener {
    private NewsView mNewsView;
    private NewsModel mNewsModel;

    public NewsDataPresenter(NewsView newsView, BaseActivity activity) {
        this.mNewsView = newsView;
        mNewsModel = new NewsDataModel(activity);
    }

    @Override
    public void loadNews(int newsType, String url) {
        mNewsView.showProgress();
        mNewsModel.getNews(API.TYPE_LATEST, this, newsType, url);
    }

    @Override
    public void loadBefore(int newsType, String url) {
        mNewsModel.getNews(API.TYPE_BEFORE, this, newsType, url);

    }

    @Override
    public void onSuccess() {
        mNewsView.addNews(null);
        mNewsView.hideProgress();

    }

    @Override
    public void onFailure(String msg) {
        mNewsView.hideProgress();
        mNewsView.loadFailed(msg);
    }
}
