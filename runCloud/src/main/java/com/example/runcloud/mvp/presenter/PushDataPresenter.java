package com.example.runcloud.mvp.presenter;

import com.example.runcloud.entity.PushDatas;
import com.example.runcloud.mvp.interf.OnLoadDataListener;
import com.example.runcloud.mvp.interf.OnPushDataListener;
import com.example.runcloud.mvp.interf.PushDataContract;
import com.example.runcloud.mvp.model.PushDataModel;
import com.example.runcloud.net.API;

/**
 * Created by Wind1129 on 16/11/25.
 */

public class PushDataPresenter implements PushDataContract.Presenter,OnPushDataListener<PushDatas> {
    private PushDataContract.View view;
    private PushDataContract.Model model;

    public PushDataPresenter(PushDataContract.View view) {
        this.view = view;
        model = new PushDataModel();
    }

    @Override
    public void onrefresh(String url) {
        view.showRefreshing();
        model.getStory(this,url,API.TYPE_LATEST);

    }

    @Override
    public void loadMore(String url) {
        model.getStory(this,url,API.TYPE_BEFORE);

    }


    @Override
    public void onSuccess(PushDatas pushDatas,int type) {
        if(type == API.TYPE_LATEST) {
            view.showStory(pushDatas);
        }else{
            view.appendStory(pushDatas);
        }
        view.hideRefreshing();
    }

    @Override
    public void onFailure(String msg) {
        view.hideRefreshing();
        view.loadFailed(msg);

    }
}
