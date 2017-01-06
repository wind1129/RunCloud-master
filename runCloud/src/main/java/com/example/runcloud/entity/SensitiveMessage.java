package com.example.runcloud.entity;

import io.realm.RealmList;
import io.realm.RealmObject;
import com.example.runcloud.mvp.other.Data;

/***
 *（舆情）舆情关注的列表页
 */


public class SensitiveMessage extends RealmObject implements Data {
	private String resultCode;
	private RealmList<DataList> sensitiveList;
	private String message;
	
	
	
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public RealmList<DataList> getSensitiveList() {
		return sensitiveList;
	}

	public void setSensitiveList(RealmList<DataList> sensitiveList) {
		this.sensitiveList = sensitiveList;
	}

	/*public List<DataList> getSensitiveList() {
            return sensitiveList;
        }
        public void setSensitiveList(List<DataList> sensitiveList) {
            this.sensitiveList = sensitiveList;
        }*/
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
