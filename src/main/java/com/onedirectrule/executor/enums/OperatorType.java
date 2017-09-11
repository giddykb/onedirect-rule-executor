package com.onedirectrule.executor.enums;

public enum OperatorType {
	IS(0),
	is_not(1);
	
	private int type;
	
	OperatorType(int type){
		this.type=type;
	}
	
	public int getType() {
		return type;
	}
}
