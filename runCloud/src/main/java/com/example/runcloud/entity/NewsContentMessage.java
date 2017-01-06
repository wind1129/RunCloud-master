package com.example.runcloud.entity;

import io.realm.RealmObject;
import com.example.runcloud.mvp.other.NewsDetail;

public class NewsContentMessage {
	private String resultCode;
	private Doc doc;

	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public Doc getDoc() {
		return doc;
	}
	public void setDoc(Doc doc) {
		this.doc = doc;
	}
	
	
	

}
