package com.example.runcloud.mvp.interf;


/**
 * deals with the data work
 */
public interface NewsModel {

    void getNews(int type, OnLoadDataListener listener, int newsType, String url);

    //void getNewsDetail(String url, OnLoadDetailListener listener);

}