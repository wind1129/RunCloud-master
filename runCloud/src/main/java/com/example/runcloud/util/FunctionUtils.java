package com.example.runcloud.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.os.Handler;

import com.example.runcloud.extra.TipDialog;

public class FunctionUtils {
	public static String timesOne(String time){
	SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd");
	long lcc = Long.valueOf(time);
	String times = sdr.format(new Date(lcc));
	return times;

}


/**
 * 是否匹配
 * 
 * @param url
 * @param urlRegx
 * @return
 */
public static boolean matchs(String url, String urlRegx) {
	Pattern pattern = Pattern.compile(urlRegx);
	Matcher matcher = pattern.matcher(url);
	if (matcher.find())
		return true;
	return false;
}

/**
 * 获取匹配
 * 
 * @param input
 * @param inputPattern
 * @return
 */
public static Matcher getMatcher(String input, String inputPattern) {
	Matcher matcher = null;
	try {
		Pattern pattern = Pattern.compile(inputPattern);
		matcher = pattern.matcher(input);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return matcher;
}



public static void showCustomDialog(String warmInfo, Context context) {  
   TipDialog.Builder customBuilder = new TipDialog.Builder(context);  
    customBuilder.setMessage(warmInfo);  
    final TipDialog customDialog = customBuilder.create();  
    customDialog.show();  
    Handler handler = new Handler();  
    handler.postDelayed(new Runnable() {  
        public void run() {  
            customDialog.dismiss();  
        }  
    }, 1000);  
}


private static long lastClickTime;  
public static boolean isFastDoubleClick() {  
    long time = System.currentTimeMillis();  
    long timeD = time - lastClickTime;  
    if ( 0 < timeD && timeD < 800) {     
        return true;     
    }     
    lastClickTime = time;     
    return false;     
}  
     
}		

