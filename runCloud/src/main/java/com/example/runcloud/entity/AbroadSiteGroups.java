package com.example.runcloud.entity;

import java.util.List;

public class AbroadSiteGroups {
	private String message;
	private String resultCode;
	private List<AbroadSiteGroup> abroadSiteGroupsList;
	
	
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
	public List<AbroadSiteGroup> getAbroadSiteGroupsList() {
		return abroadSiteGroupsList;
	}
	public void setAbroadSiteGroupsList(List<AbroadSiteGroup> abroadSiteGroupsList) {
		this.abroadSiteGroupsList = abroadSiteGroupsList;
	}
	
	
}
