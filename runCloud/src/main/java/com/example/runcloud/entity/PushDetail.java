package com.example.runcloud.entity;

/**
 * Created by Wind1129 on 16/11/28.
 */

public class PushDetail {
    private String message;
    private String resultCode;
    private PushData pushData;

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

    public PushData getPushData() {
        return pushData;
    }

    public void setPushData(PushData pushData) {
        this.pushData = pushData;
    }
}
