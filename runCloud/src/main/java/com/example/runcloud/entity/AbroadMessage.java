package com.example.runcloud.entity;

import io.realm.RealmList;
import io.realm.RealmObject;
import com.example.runcloud.mvp.other.Data;

public class AbroadMessage extends RealmObject implements Data {
	private String resultCode;
	private RealmList<AbroadData> abroadDataList;
	private String message;
	
	
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public RealmList<AbroadData> getAbroadDataList() {
		return abroadDataList;
	}

	public void setAbroadDataList(RealmList<AbroadData> abroadDataList) {
		this.abroadDataList = abroadDataList;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}


