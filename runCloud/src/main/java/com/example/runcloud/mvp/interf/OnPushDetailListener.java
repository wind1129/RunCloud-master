package com.example.runcloud.mvp.interf;


import com.example.runcloud.entity.NewsContentMessage;
import com.example.runcloud.entity.PushDetail;

/**
 * when detail loaded, this interface is called
 */
public interface OnPushDetailListener {
    void onDetailSuccess(PushDetail pushDetail);

    void onFailure(String msg, Exception e);

}
