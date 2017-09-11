package com.onedirect.rule.executor.bean;

import java.io.Serializable;

public class RuleValue implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String value;

	public RuleValue() {

	}





	public String getId() {
		return id;
	}





	public void setId(String id) {
		this.id = id;
	}





	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}





	public RuleValue(String id, String value) {
		super();
		this.id = id;
		this.value = value;
	}





	@Override
	public String toString() {
		return "RuleValue [id=" + id + ", value=" + value + "]";
	}

	
	
}
