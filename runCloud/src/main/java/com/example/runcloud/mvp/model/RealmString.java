package com.example.runcloud.mvp.model;

import io.realm.RealmObject;

/**
 * Created by Summers on 2016/9/1.
 * To make realmObject 'support' List<String>
 */
public class RealmString extends RealmObject {

    private String val;

    public RealmString() {
    }

    public RealmString(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
