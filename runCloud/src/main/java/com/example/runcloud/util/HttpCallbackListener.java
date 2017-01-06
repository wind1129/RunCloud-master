package com.example.runcloud.util;

public interface HttpCallbackListener {
	void onFinish(String response);

	void onError(Exception e);

}
