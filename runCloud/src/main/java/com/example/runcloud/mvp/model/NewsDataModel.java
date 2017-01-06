package com.example.runcloud.mvp.model;

import com.example.runcloud.BaseActivity;
import com.example.runcloud.application.MyApplication;
import com.example.runcloud.entity.AbroadData;
import com.example.runcloud.entity.AbroadMessage;
import com.example.runcloud.entity.DataList;
import com.example.runcloud.entity.NetizenData;
import com.example.runcloud.entity.NetizenMessage;
import com.example.runcloud.entity.NewsContentMessage;
import com.example.runcloud.entity.SearchData;
import com.example.runcloud.entity.SearchMessage;
import com.example.runcloud.entity.SensitiveMessage;
import com.example.runcloud.entity.WechatData;
import com.example.runcloud.entity.WechatMessage;
import com.example.runcloud.net.API;
import com.example.runcloud.net.DB;
import com.example.runcloud.net.Json;
import com.example.runcloud.net.Net;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import io.realm.Realm;

import com.example.runcloud.mvp.interf.NewsModel;
import com.example.runcloud.mvp.interf.OnLoadDataListener;
import com.example.runcloud.mvp.interf.OnLoadDetailListener;
import com.example.runcloud.mvp.other.Data;
import com.example.runcloud.mvp.utils.Constants;
import com.example.runcloud.mvp.utils.SPUtil;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Summers on 2016/9/1.
 */
public class NewsDataModel implements NewsModel {
    public static final int GET_DURATION = 2000;
    /**
     * 全局变量
     */
    private MyApplication myApplication;
    private BaseActivity mActivity;
    private Realm realm;
    private int type;

    private long lastGetTime;

    private int page = 2;

    private String baseUri;

    public NewsDataModel(BaseActivity activity) {
        this.mActivity = activity;
        realm = mActivity.mRealm;

    }

    @Override
    public void getNews(final int type, final OnLoadDataListener listener, final int newsType, final String url) {
        this.type = type;
        lastGetTime = System.currentTimeMillis();
        final Callback<Data> callback = new Callback<Data>() {
            @Override
            public Data parseNetworkResponse(Response response, int id) throws Exception {
                if (newsType == API.TYPE_SENSITIVE) {
                    //Log.i("sensitive",response.body().string());
                    SensitiveMessage messageJson = Json.parseSensitiveMessage(response.body().string());
                    Thread.sleep(002);
                    return messageJson;
                } else if (newsType == API.TYPE_ABROAD) {
                    AbroadMessage messageJson = Json.parseAbroadMessage(response.body().string());
                    Thread.sleep(200);
                    return messageJson;
                } else if (newsType == API.TYPE_NETIZEN) {
                    NetizenMessage messageJson = Json.parseNetizenMessage(response.body().string());
                    Thread.sleep(200);
                    return messageJson;
                } else if (newsType == API.TYPE_WECHAT) {
                    WechatMessage messageJson = Json.parseWechatMessage(response.body().string());
                    Thread.sleep(200);
                    return messageJson;
                } else if (newsType == API.TYPE_SEARCH) {
                    //Log.i("search",response.body().string());
                    SearchMessage messageJson = Json.parseSearchMessage(response.body().string());
                    Thread.sleep(200);
                    return messageJson;
                }
                return null;
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                if (System.currentTimeMillis() - lastGetTime < GET_DURATION) {
                    getData(this, url);
                    return;
                }
                e.printStackTrace();
                listener.onFailure("加载新闻失败");

            }

            @Override
            public void onResponse(Data response, int id) {
                saveNews(response);
                SPUtil.save(Constants.PAGE, page);
                listener.onSuccess();
            }
        };
        page++;
        getData(callback, url);

    }

    private void saveNews(final Data message) {
        if (null != message) {
            realm.beginTransaction();
            if (type == API.TYPE_LATEST) {
                if (message instanceof SensitiveMessage) {
                    realm.where(DataList.class).findAll().clear();
                } else if (message instanceof AbroadMessage) {
                    realm.where(AbroadData.class).findAll().clear();
                } else if (message instanceof NetizenMessage) {
                    realm.where(NetizenData.class).findAll().clear();
                } else if (message instanceof WechatMessage) {
                    realm.where(WechatData.class).findAll().clear();
                } else if (message instanceof SearchMessage) {
                    realm.where(SearchData.class).findAll().clear();
                }
            }
            //realm.copyToRealmOrUpdate(message);
            if (message instanceof SensitiveMessage) {
                realm.copyToRealm((SensitiveMessage) message);
            } else if (message instanceof AbroadMessage) {
                realm.copyToRealm((AbroadMessage) message);
            } else if (message instanceof NetizenMessage) {
                realm.copyToRealm((NetizenMessage) message);
            } else if (message instanceof WechatMessage) {
                realm.copyToRealm((WechatMessage) message);
            } else if (message instanceof SearchMessage) {
                realm.copyToRealm((SearchMessage) message);
            }
            realm.commitTransaction();
        }
    }


    private void getData(Callback callback, String url) {
        if (type == API.TYPE_LATEST) {
            Net.get(url, callback, mActivity);
            page = 1;
        } else if (type == API.TYPE_BEFORE) {
            //Log.i("page++",page+"");
            OkHttpUtils.get().url(url)
                    .addParams("pageNo", String.valueOf(page))
                    .tag(mActivity)
                    .build().execute(callback);
        }
    }

}
