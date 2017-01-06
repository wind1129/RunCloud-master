package com.example.runcloud.mvp.presenter;


import com.example.runcloud.BaseActivity;

import com.example.runcloud.entity.NewsContentMessage;
import com.example.runcloud.mvp.interf.NewsDetailPresenter;
import com.example.runcloud.mvp.interf.NewsDetailView;
import com.example.runcloud.mvp.interf.NewsModel;
import com.example.runcloud.mvp.interf.OnLoadDetailListener;
import com.example.runcloud.mvp.interf.StoryContract;
import com.example.runcloud.mvp.model.NewsDataModel;
import com.example.runcloud.mvp.model.StoryModel;


public class NewsDocDetailPresenter implements StoryContract.Presenter, OnLoadDetailListener {

    private StoryContract.Model newsModel;
    private StoryContract.View newsDetailView;

    public NewsDocDetailPresenter(StoryContract.View newsDetailView) {
        this.newsModel = new StoryModel();
        this.newsDetailView = newsDetailView;
    }

    @Override
    public void refresh(String url) {
        newsDetailView.showProgressBar();
        newsModel.getNewsDetail(url, this);
    }

    @Override
    public void onDetailSuccess(NewsContentMessage contentMessage) {
        if(contentMessage!=null&&contentMessage.getDoc()!=null) {
            newsDetailView.showStory(contentMessage.getDoc());
            newsDetailView.hideProgressBar();
        }
    }

    @Override
    public void onFailure(String msg, Exception e) {
        newsDetailView.showError();
        newsDetailView.hideProgressBar();
    }


}
