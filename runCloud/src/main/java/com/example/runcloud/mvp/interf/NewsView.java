package com.example.runcloud.mvp.interf;

import com.example.runcloud.mvp.other.Data;

/**
 * Created by Summers on 2016/9/1.
 */
public interface NewsView<T extends Data> {
    void showProgress();

    void addNews(T news);

    void hideProgress();

    void loadFailed(String msg);
}
