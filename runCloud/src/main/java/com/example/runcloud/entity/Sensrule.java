package com.example.runcloud.entity;

public class Sensrule {
	private String selectId;
	private String selectName;
	
	
	
	
	public Sensrule(String selectId, String selectName) {
		super();
		this.selectId = selectId;
		this.selectName = selectName;
	}
	
	public String getSelectId() {
		return selectId;
	}
	public void setSelectId(String selectId) {
		this.selectId = selectId;
	}
	public String getSelectName() {
		return selectName;
	}
	public void setSelectName(String selectName) {
		this.selectName = selectName;
	}
	
	

}
