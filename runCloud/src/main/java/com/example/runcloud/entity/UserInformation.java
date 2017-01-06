package com.example.runcloud.entity;

import java.util.List;

public class UserInformation {
private String message;
private String resultCode;
private String userId;
private String orgId;
private List<UserDataSource> dataSourceList;
private List<UserWechatGroup> wechatGroupList;
private String defaultTopicId;
private List<String> defaultSensLevelId;
private String defaultNetizenGroupId;
private String alias;


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
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getOrgId() {
	return orgId;
}
public void setOrgId(String orgId) {
	this.orgId = orgId;
}
public List<UserDataSource> getDataSourceList() {
	return dataSourceList;
}
public void setDataSourceList(List<UserDataSource> dataSourceList) {
	this.dataSourceList = dataSourceList;
}
public List<UserWechatGroup> getWechatGroupList() {
	return wechatGroupList;
}
public void setWechatGroupList(List<UserWechatGroup> wechatGroupList) {
	this.wechatGroupList = wechatGroupList;
}
public String getDefaultTopicId() {
	return defaultTopicId;
}
public void setDefaultTopicId(String defaultTopicId) {
	this.defaultTopicId = defaultTopicId;
}
public List<String> getDefaultSensLevelId() {
	return defaultSensLevelId;
}
public void setDefaultSensLevelId(List<String> defaultSensLevelId) {
	this.defaultSensLevelId = defaultSensLevelId;
}
public String getDefaultNetizenGroupId() {
	return defaultNetizenGroupId;
}
public void setDefaultNetizenGroupId(String defaultNetizenGroupId) {
	this.defaultNetizenGroupId = defaultNetizenGroupId;
}
public String getAlias() {
	return alias;
}
public void setAlias(String alias) {
	this.alias = alias;
}







}
