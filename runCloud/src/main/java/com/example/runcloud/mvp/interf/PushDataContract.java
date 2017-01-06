package com.example.runcloud.mvp.interf;

import com.example.runcloud.entity.PushDatas;

/**
 * Created by Wind1129 on 16/11/25.
 */

public interface PushDataContract {
    public interface Presenter{
        void onrefresh(String url);

        void loadMore(String url);
    }

    public interface View{
        void hideRefreshing();

        void showRefreshing();

        void showStory(PushDatas pushDatas);

        void appendStory(PushDatas pushDatas);

        void loadFailed(String msg);

    }

    public interface Model{
        void getStory(OnPushDataListener<PushDatas> listener,String url,int type);
    }
}
