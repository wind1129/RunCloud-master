package com.example.runcloud.mvp.interf;

import com.example.runcloud.entity.Doc;

/**
 * Created by Wind1129 on 16/11/29.
 */

public interface StoryContract {
    interface View  {

        void showStory(Doc doc);

        void showError();

        void showProgressBar();

        void hideProgressBar();
    }

    interface Presenter  {

        void refresh(String url);
    }

    interface Model{
        void getNewsDetail(String url, OnLoadDetailListener listener);
    }
}
