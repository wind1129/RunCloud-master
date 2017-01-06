package com.example.runcloud.mvp.model;

import com.example.runcloud.entity.NewsContentMessage;
import com.example.runcloud.mvp.interf.OnLoadDetailListener;
import com.example.runcloud.mvp.interf.StoryContract;
import com.example.runcloud.net.DB;
import com.example.runcloud.net.Json;
import com.example.runcloud.net.Net;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Wind1129 on 16/11/29.
 */

public class StoryModel implements StoryContract.Model {
    @Override
    public void getNewsDetail(String url, final OnLoadDetailListener listener) {

        Callback<NewsContentMessage> callback = new Callback<NewsContentMessage>() {
            @Override
            public NewsContentMessage parseNetworkResponse(Response response, int id) throws Exception {
                return Json.parseNewsContentMessage(response.body().string());
            }

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(NewsContentMessage response, int id) {
                listener.onDetailSuccess(response);
            }


        };

        OkHttpUtils.get().url(url)
                .build().execute(callback);

    }
}
