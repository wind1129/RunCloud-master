package com.example.runcloud.entity;

import java.util.List;

/**
 * Created by Wind1129 on 16/11/25.
 */

public class PushDatas {
    private String message;
    private String resultCode;
    private List<PushData> pushDataList;
    private List<PushData> topList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public List<PushData> getPushDataList() {
        return pushDataList;
    }

    public void setPushDataList(List<PushData> pushDataList) {
        this.pushDataList = pushDataList;
    }

    public List<PushData> getTopList() {
        return topList;
    }

    public void setTopList(List<PushData> topList) {
        this.topList = topList;
    }
}
