package com.example.runcloud.entity;

import java.util.List;

public class Chart {
	private String title;
	private String type;
	private List<ChartData> data;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<ChartData> getData() {
		return data;
	}
	public void setData(List<ChartData> data) {
		this.data = data;
	}
	
	
	

}
