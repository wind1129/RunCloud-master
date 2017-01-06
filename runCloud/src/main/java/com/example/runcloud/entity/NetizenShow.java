package com.example.runcloud.entity;

import java.util.List;

public class NetizenShow {
	private String message;
	private String resultCode;
	private List<NetizenShowBasic> accountList;
	
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
	public List<NetizenShowBasic> getAccountList() {
		return accountList;
	}
	public void setAccountList(List<NetizenShowBasic> accountList) {
		this.accountList = accountList;
	}
	
	

}
