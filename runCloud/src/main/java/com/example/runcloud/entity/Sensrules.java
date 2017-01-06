package com.example.runcloud.entity;

import java.util.List;

public class Sensrules {
	private String message;
	private String resultCode;
	private List<Sensrule> sensruleList;
	
	
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
	public List<Sensrule> getSensruleList() {
		return sensruleList;
	}
	public void setSensruleList(List<Sensrule> sensruleList) {
		this.sensruleList = sensruleList;
	}
	
	

}
