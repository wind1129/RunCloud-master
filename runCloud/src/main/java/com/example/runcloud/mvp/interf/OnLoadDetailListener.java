package com.example.runcloud.mvp.interf;


import com.example.runcloud.entity.NewsContentMessage;

/**
 * when detail loaded, this interface is called
 */
public interface OnLoadDetailListener {
    void onDetailSuccess(NewsContentMessage contentMessage);

    void onFailure(String msg, Exception e);

}
