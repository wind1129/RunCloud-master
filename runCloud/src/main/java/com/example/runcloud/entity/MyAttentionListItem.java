package com.example.runcloud.entity;

public class MyAttentionListItem {
	private String myAttentionItemText;
	private int myAttentionItemEditImage;
	
	
	
	
	public MyAttentionListItem(String myAttentionItemText,
			int myAttentionItemEditImage) {
		super();
		this.myAttentionItemText = myAttentionItemText;
		this.myAttentionItemEditImage = myAttentionItemEditImage;
	}
	
	public String getMyAttentionItemText() {
		return myAttentionItemText;
	}
	public void setMyAttentionItemText(String myAttentionItemText) {
		this.myAttentionItemText = myAttentionItemText;
	}
	public int getMyAttentionItemEditImage() {
		return myAttentionItemEditImage;
	}
	public void setMyAttentionItemEditImage(int myAttentionItemEditImage) {
		this.myAttentionItemEditImage = myAttentionItemEditImage;
	}
	
	
	

}
