package com.example.runcloud.net;


import com.example.runcloud.entity.AbroadMessage;
import com.example.runcloud.entity.NetizenMessage;
import com.example.runcloud.entity.NewsContentMessage;
import com.example.runcloud.entity.PushDatas;
import com.example.runcloud.entity.PushDetail;
import com.example.runcloud.entity.SearchMessage;
import com.example.runcloud.entity.SensitiveMessage;
import com.example.runcloud.entity.WechatMessage;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.lang.reflect.Type;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.internal.IOException;

import com.example.runcloud.mvp.model.RealmString;

/**
 * Json parser util.
 */
public class Json {
    public Json() {
    }

    public static Type token = new TypeToken<RealmList<RealmString>>() {
    }.getType();


    public static Gson mGson = new GsonBuilder().
            setExclusionStrategies(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes f) {
                    return f.getDeclaringClass().equals(RealmObject.class);
                }

                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    return false;
                }
            })
            .registerTypeAdapter(token, new TypeAdapter<RealmList<RealmString>>() {

                @Override
                public void write(JsonWriter out, RealmList<RealmString> value) throws IOException {
                    // Ignore
                }

                @Override
                public RealmList<RealmString> read(JsonReader in) throws IOException, java.io.IOException {
                    RealmList<RealmString> list = new RealmList<>();
                    in.beginArray();
                    while (in.hasNext()) {
                        list.add(new RealmString(in.nextString()));
                    }
                    in.endArray();
                    return list;
                }
            }).create();


    public static SensitiveMessage parseSensitiveMessage(String latest) {
        return mGson.fromJson(latest, SensitiveMessage.class);
    }

    public static NetizenMessage parseNetizenMessage(String latest) {
        return mGson.fromJson(latest, NetizenMessage.class);
    }

    public static AbroadMessage parseAbroadMessage(String latest) {
        return mGson.fromJson(latest, AbroadMessage.class);
    }

    public static WechatMessage parseWechatMessage(String latest) {
        return mGson.fromJson(latest, WechatMessage.class);
    }

    public static NewsContentMessage parseNewsContentMessage(String latest) {
        return mGson.fromJson(latest, NewsContentMessage.class);
    }

    public static SearchMessage parseSearchMessage(String latest) {
        return mGson.fromJson(latest, SearchMessage.class);
    }

    public static PushDatas parsePushDatas(String latest) {
        return mGson.fromJson(latest, PushDatas.class);
    }

    public static PushDetail parsePushDetail(String latest) {
        return mGson.fromJson(latest, PushDetail.class);
    }


}
