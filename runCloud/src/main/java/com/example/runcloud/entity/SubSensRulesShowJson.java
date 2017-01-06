package com.example.runcloud.entity;

import java.util.List;

public class SubSensRulesShowJson {
	private String sensruleId;
	private String ruleName;
	private String sensLevelId;
	private String expireDate;
	private List<SubSensRulesShowPart> subSensRules;
	private String userId;
	private String orgId;
	private String defaultTopicId;
	
	
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getSensLevelId() {
		return sensLevelId;
	}
	public void setSensLevelId(String sensLevelId) {
		this.sensLevelId = sensLevelId;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public List<SubSensRulesShowPart> getSubSensRules() {
		return subSensRules;
	}
	public void setSubSensRules(List<SubSensRulesShowPart> subSensRules) {
		this.subSensRules = subSensRules;
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
	public String getSensruleId() {
		return sensruleId;
	}
	public void setSensruleId(String sensruleId) {
		this.sensruleId = sensruleId;
	}
	public String getDefaultTopicId() {
		return defaultTopicId;
	}
	public void setDefaultTopicId(String defaultTopicId) {
		this.defaultTopicId = defaultTopicId;
	}
	
	
	
	

}
