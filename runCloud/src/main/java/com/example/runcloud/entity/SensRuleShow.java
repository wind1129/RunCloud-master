package com.example.runcloud.entity;

import java.util.List;

public class SensRuleShow {
	private String message;
	private String resultCode;
	private SensRuleShowBasic sensrule;
	private List<SubSensRulesShow> subSensRules;
	
	
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
	public SensRuleShowBasic getSensrule() {
		return sensrule;
	}
	public void setSensrule(SensRuleShowBasic sensrule) {
		this.sensrule = sensrule;
	}
	public List<SubSensRulesShow> getSubSensRules() {
		return subSensRules;
	}
	public void setSubSensRules(List<SubSensRulesShow> subSensRules) {
		this.subSensRules = subSensRules;
	}
	
	
	

}
