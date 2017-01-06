package com.example.runcloud.mvp.interf;

import com.example.runcloud.entity.PushDatas;

import java.util.Objects;

/**
 * when news loaded, this interface is called
 */
public interface OnPushDataListener<T extends Object> {
    void onSuccess(T object,int type);

    void onFailure(String msg);
}
