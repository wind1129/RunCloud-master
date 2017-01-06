package com.example.runcloud.net;

/**
 * Created by Summers on 2016/9/1.
 */
public class API {
    public static final int TYPE_LATEST = 0;
    public static final int TYPE_BEFORE = 1;

    public static final int TYPE_SENSITIVE = 1;
    public static final int TYPE_NETIZEN = 3;
    public static final int TYPE_ABROAD = 2;
    public static final int TYPE_WECHAT = 4;
    public static final int TYPE_SEARCH = 5;

    public static final String sensitivesURI ="/ares/restful/sensitives/sensitives";
    public static final String netizensURI ="/ares/restful/netizen/netizenDatas";
    public static final String abroadsURI ="/ares/restful/abroad/abroads";
    public static final String wechatsURI ="/ares/restful/wechat/wechatDatas";
    public static final String contentURI ="/ares/restful/details/detail?docId=";
}
