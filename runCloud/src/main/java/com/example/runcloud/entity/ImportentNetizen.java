package com.example.runcloud.entity;

public class ImportentNetizen {
	private String selectId;
	private String selectName;
	private int editImage;
	
	
	
	public ImportentNetizen(String selectId, String selectName) {
		super();
		this.selectId = selectId;
		this.selectName = selectName;
	}
	
	public ImportentNetizen(String selectName, int editImage) {
		super();
		this.selectName = selectName;
		this.editImage = editImage;
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


	public int getEditImage() {
		return editImage;
	}


	public void setEditImage(int editImage) {
		this.editImage = editImage;
	}
	
	
	

}
