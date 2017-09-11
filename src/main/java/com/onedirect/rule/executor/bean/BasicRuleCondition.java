package com.onedirect.rule.executor.bean;

import java.util.List;

public class BasicRuleCondition {
	
	private   String key; 
	private   String operator;
	private   List<String> value;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public List<String> getValue() {
		return value;
	}
	public void setValue(List<String> value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "BasicRuleCondition [key=" + key + ", operator=" + operator + ", value=" + value + "]";
	}
	
	
	
	
	

}
