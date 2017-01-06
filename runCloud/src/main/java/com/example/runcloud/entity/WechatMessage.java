package com.example.runcloud.entity;

import io.realm.RealmList;
import io.realm.RealmObject;
import com.example.runcloud.mvp.other.Data;

public class WechatMessage extends RealmObject implements Data {
	private String resultCode;
	private RealmList<WechatData> wechatDataList;
	private String message;
	
	
	
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public RealmList<WechatData> getWechatDataList() {
		return wechatDataList;
	}

	public void setWechatDataList(RealmList<WechatData> wechatDataList) {
		this.wechatDataList = wechatDataList;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
	/*public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public List<DataList> getDatalists() {
		return datalists;
	}
	public void setDatalists(List<DataList> datalists) {
		this.datalists = datalists;
	}*/
	
	

}
