package com.example.runcloud.mvp.interf;


import com.example.runcloud.entity.PushDetail;

/**
 * when detail loaded, this interface is called
 */
public interface OnStarDetailListener {
    void onStarSuccess(String msg);

    void onStarFailure(String msg, Exception e);

}
