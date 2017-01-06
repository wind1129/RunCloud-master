package com.example.runcloud.mvp.model;

import com.example.runcloud.entity.PushDatas;
import com.example.runcloud.mvp.interf.OnLoadDataListener;
import com.example.runcloud.mvp.interf.OnPushDataListener;
import com.example.runcloud.mvp.interf.PushDataContract;
import com.example.runcloud.net.API;
import com.example.runcloud.net.Json;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Wind1129 on 16/11/25.
 */

public class PushDataModel implements PushDataContract.Model{
    private int mType;
    private int page;

    @Override
    public void getStory(final OnPushDataListener<PushDatas> listener, final String url,final int type) {
        this.mType = type;
        final Callback<PushDatas> callback = new Callback<PushDatas>() {
            @Override
            public PushDatas parseNetworkResponse(Response response, int id) throws Exception {
                PushDatas pushDatas = Json.parsePushDatas(response.body().string());
                return pushDatas;
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                listener.onFailure("加载新闻失败");
            }

            @Override
            public void onResponse(PushDatas response, int id) {
                listener.onSuccess(response,type);
            }
        };

        page++;
        getData(callback, url);
    }


    private void getData(Callback callback, String url) {
        if (mType == API.TYPE_LATEST) {
            OkHttpUtils.get().url(url)
                    .build().execute(callback);
            page = 1;
        } else if (mType == API.TYPE_BEFORE) {
            //Log.i("page++",page+"");
            OkHttpUtils.get().url(url)
                    .addParams("pageNo", String.valueOf(page))
                    .build().execute(callback);
        }
    }
}
