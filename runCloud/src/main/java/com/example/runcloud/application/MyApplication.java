package com.example.runcloud.application;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.jpush.android.api.JPushInterface;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import okhttp3.OkHttpClient;
import com.example.runcloud.entity.UserInformation;
import com.example.runcloud.util.HttpsUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;


/**
 * For developer startup JPush SDK
 * 
 * 一般建议在自定义 Application 类里初始化。也可以在主 Activity 里。
 */
public class MyApplication extends Application{
	/**用来存放Activity的集合*/
	private List<Activity> mList = new LinkedList();
	//private static MyApplication instance;
	private static final String TAG = "JPush";
	
	public static Context context;
	/**全局信息*/
	public UserInformation information;

	//private MyApplication() {} 
	
    /*public synchronized static MyApplication getInstance() { 
        if (null == instance) { 
            instance = new MyApplication(); 
        } 
        return instance; 
    } */

	public UserInformation getInformation() {
		return information;
	}

	public void setInformation(UserInformation information) {
		this.information = information;
	}
	
	
	
	 public static Context getContext() {
		return context;
	}







	@Override
	    public void onCreate() {    	     
	    	 Log.d(TAG, "[ExampleApplication] onCreate");
	         super.onCreate();
		     context = this;
	         //context = getApplicationContext();


		     setupRealm();






		//设置证书
		HttpsUtils.SSLParams sslParams = null;
		try {
			sslParams = HttpsUtils.getSslSocketFactory(
					new InputStream[]{getAssets().open("tomcat.cer")},
                    getAssets().open("clientkey.p12"),
                    "client4runc");

		} catch (Exception e) {
			e.printStackTrace();
		}
		OkHttpClient okHttpClient = new OkHttpClient.Builder().hostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String s, SSLSession sslSession) {
				return true;
			}
		})
				.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
				.connectTimeout(1500, TimeUnit.MILLISECONDS)
				.readTimeout(2000, TimeUnit.MILLISECONDS)
				//其他配置
				.build();
		OkHttpUtils.initClient(okHttpClient);



		JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
	         JPushInterface.init(this);     		// 初始化 JPush
	    }


	private void setupRealm() {
		RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
		Realm.setDefaultConfiguration(realmConfiguration);
	}

	 
	 
	// add Activity  
	    public void addActivity(Activity activity) { 
	        mList.add(activity); 
	    } 
	 
	 // exit All Activity 
	    public void exit() { 
	        try { 
	            for (Activity activity : mList) {
					if (activity != null)
						activity.finish();

				}
	        } catch (Exception e) { 
	            e.printStackTrace(); 
	        } finally { 
	            System.exit(0); 
	        } 
	    } 
	   
	    public void onLowMemory() { 
	        super.onLowMemory();     
	        System.gc(); 
	    }  
	


}
