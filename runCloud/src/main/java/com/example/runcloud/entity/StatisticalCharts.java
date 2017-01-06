package com.example.runcloud.entity;

import java.util.List;

public class StatisticalCharts {
	private List<Chart> charts;
	private String message;
	private String resultCode;
	
	
	public List<Chart> getCharts() {
		return charts;
	}
	public void setCharts(List<Chart> charts) {
		this.charts = charts;
	}
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
	
	
	

}
