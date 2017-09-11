package com.onedirect.rule.executor.bean;

import java.io.Serializable;
import java.util.List;


public class RuleActionBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    
    private String relation;
    
    private RuleValueBean values;
    
    private boolean isTeam ;
    
    private boolean login;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

/*	public List<RuleValue> getValues() {
		return values;
	}

	public void setValues(List<RuleValue> values) {
		this.values = values;
	}
*/
	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

    
	public boolean getIsTeam() {
		return isTeam;
	}

    
	public void setIsTeam(boolean isTeam) {
		this.isTeam = isTeam;
	}

	public RuleValueBean getValues() {
		return values;
	}

	public void setValues(RuleValueBean values) {
		this.values = values;
	}
    
}