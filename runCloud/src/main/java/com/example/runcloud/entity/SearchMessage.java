package com.example.runcloud.entity;

import io.realm.RealmList;
import io.realm.RealmObject;
import com.example.runcloud.mvp.other.Data;

public class SearchMessage extends RealmObject implements Data {
	private String resultCode;
	private RealmList<SearchData> searchDataList;
	private String message;
	
	
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public RealmList<SearchData> getSearchDataList() {
		return searchDataList;
	}

	public void setSearchDataList(RealmList<SearchData> searchDataList) {
		this.searchDataList = searchDataList;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}


