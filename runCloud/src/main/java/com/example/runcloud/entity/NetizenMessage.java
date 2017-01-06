package com.example.runcloud.entity;

import io.realm.RealmList;
import io.realm.RealmObject;
import com.example.runcloud.mvp.other.Data;

public class NetizenMessage extends RealmObject implements Data {
	private String resultCode;
	private RealmList<NetizenData> netizenDataList;
	private String message;
	
	
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public RealmList<NetizenData> getNetizenDataList() {
		return netizenDataList;
	}

	public void setNetizenDataList(RealmList<NetizenData> netizenDataList) {
		this.netizenDataList = netizenDataList;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
