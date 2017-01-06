package com.example.runcloud.entity;

import java.util.List;

public class AbroadSites {
	private String message;
	private String resultCode;
	private List<AbroadSite> abroadSitesList;
	
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
	public List<AbroadSite> getAbroadSitesList() {
		return abroadSitesList;
	}
	public void setAbroadSitesList(List<AbroadSite> abroadSitesList) {
		this.abroadSitesList = abroadSitesList;
	}
	
	
	
	
	
}
