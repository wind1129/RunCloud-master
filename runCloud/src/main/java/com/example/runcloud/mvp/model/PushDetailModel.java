package com.example.runcloud.mvp.model;

import android.util.Log;

import com.example.runcloud.application.MyApplication;
import com.example.runcloud.entity.NewsContentMessage;
import com.example.runcloud.entity.PushDetail;
import com.example.runcloud.mvp.interf.OnLoadDetailListener;
import com.example.runcloud.mvp.interf.OnPushDetailListener;
import com.example.runcloud.mvp.interf.OnStarDetailListener;
import com.example.runcloud.mvp.interf.PushDetailContract;
import com.example.runcloud.mvp.interf.StoryContract;
import com.example.runcloud.net.Json;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Response;

/**
 * Created by Wind1129 on 16/11/29.
 */

public class PushDetailModel implements PushDetailContract.Model {

    @Override
    public void getNewsDetail(String url, final OnPushDetailListener listener) {

        Callback<PushDetail> callback = new Callback<PushDetail>() {
            @Override
            public PushDetail parseNetworkResponse(Response response, int id) throws Exception {
                //response.body().string()只能调用一次，不然会报错
                String ss = response.body().string();
                return Json.parsePushDetail(ss);
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                e.printStackTrace();

            }

            @Override
            public void onResponse(PushDetail response, int id) {
                listener.onDetailSuccess(response);
            }


        };

        OkHttpUtils.get().url(url)
                .build().execute(callback);

    }

    @Override
    public void postStar(String star_url, String userId, String orgId, int pushId, final OnStarDetailListener listener) {
        JSONObject param = new JSONObject();
        try {
            param.put("userId",userId);
            param.put("orgId",orgId);
            param.put("pushId",pushId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("111111",star_url);
        Log.d("111111",param.toString());
        Callback<String> callback = new Callback<String>() {
            @Override
            public String parseNetworkResponse(Response response, int id) throws Exception {

                Log.d("111111",response.body().string());
                //return response.body().string();
                return "11111";
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                e.printStackTrace();

            }

            @Override
            public void onResponse(String response, int id) {
                listener.onStarSuccess("成功点赞");

            }
        };

        OkHttpUtils.postString()
                .url(star_url)
                .content(param.toString())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(callback);
    }
}
