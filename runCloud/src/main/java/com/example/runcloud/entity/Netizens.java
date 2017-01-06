package com.example.runcloud.entity;

import java.util.List;

public class Netizens {
	private String message;
	private String resultCode;
	private List<ImportentNetizen> netizenList;
	
	
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
	public List<ImportentNetizen> getNetizenList() {
		return netizenList;
	}
	public void setNetizenList(List<ImportentNetizen> netizenList) {
		this.netizenList = netizenList;
	}
	
	

}
