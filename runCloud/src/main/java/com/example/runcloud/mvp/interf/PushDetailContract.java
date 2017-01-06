package com.example.runcloud.mvp.interf;

import com.example.runcloud.entity.Doc;
import com.example.runcloud.entity.PushData;
import com.example.runcloud.entity.PushDetail;

/**
 * Created by Wind1129 on 16/11/29.
 */

public interface PushDetailContract {
    interface View  {
        void showStarMessage(String message);

        void showStory(PushData data);

        void showError();

        void showProgressBar();

        void hideProgressBar();
    }

    interface Presenter  {

        void refresh(String url);

        //void pushStar(String url,int pushId);

        void pushStar(String star_url, String userId, String orgId, int pushId);
    }

    interface Model{
        void getNewsDetail(String url, OnPushDetailListener listener);

        void postStar(String star_url, String userId, String orgId, int pushId,OnStarDetailListener listener);
    }
}
