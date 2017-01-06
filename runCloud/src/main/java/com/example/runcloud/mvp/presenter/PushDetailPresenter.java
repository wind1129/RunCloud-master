package com.example.runcloud.mvp.presenter;


import android.util.Log;

import com.example.runcloud.entity.NewsContentMessage;
import com.example.runcloud.entity.PushDetail;
import com.example.runcloud.mvp.interf.OnLoadDetailListener;
import com.example.runcloud.mvp.interf.OnPushDetailListener;
import com.example.runcloud.mvp.interf.OnStarDetailListener;
import com.example.runcloud.mvp.interf.PushDetailContract;
import com.example.runcloud.mvp.interf.StoryContract;
import com.example.runcloud.mvp.model.PushDetailModel;
import com.example.runcloud.mvp.model.StoryModel;


public class PushDetailPresenter implements PushDetailContract.Presenter, OnPushDetailListener,OnStarDetailListener {

    private PushDetailContract.Model newsModel;
    private PushDetailContract.View newsDetailView;

    public PushDetailPresenter(PushDetailContract.View newsDetailView) {
        this.newsModel = new PushDetailModel();
        this.newsDetailView = newsDetailView;
    }

    @Override
    public void refresh(String url) {
        newsDetailView.showProgressBar();
        newsModel.getNewsDetail(url, this);
    }

    @Override
    public void pushStar(String star_url, String userId, String orgId, int pushId) {
        newsModel.postStar(star_url, userId, orgId, pushId,this);
    }


    @Override
    public void onDetailSuccess(PushDetail pushDetail) {
        Log.d("++++++",pushDetail.getMessage());
        if(pushDetail!=null&&pushDetail.getPushData()!=null) {

            newsDetailView.showStory(pushDetail.getPushData());
            newsDetailView.hideProgressBar();
        }
    }

    @Override
    public void onFailure(String msg, Exception e) {
        newsDetailView.showError();
        newsDetailView.hideProgressBar();
    }


    @Override
    public void onStarSuccess(String msg) {
        newsDetailView.showStarMessage(msg);
    }

    @Override
    public void onStarFailure(String msg, Exception e) {

    }
}
