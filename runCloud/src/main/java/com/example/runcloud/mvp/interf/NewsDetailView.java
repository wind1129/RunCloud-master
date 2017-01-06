package com.example.runcloud.mvp.interf;


/**
 * fragment or activity need to implement this to show news detail.
 */
public interface NewsDetailView {
    void showProgress();

    void showDetail();

    void hideProgress();

    void showLoadFailed(String msg);
}
