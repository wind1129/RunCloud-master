package com.example.runcloud.mvp.interf;

/**
 * helps to present news page.
 */
public interface NewsPresenter {
    void loadNews(int type, String url);

    void loadBefore(int type, String url);

}
