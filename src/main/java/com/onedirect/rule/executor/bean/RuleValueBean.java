package com.onedirect.rule.executor.bean;

import java.io.Serializable;
import java.util.List;

public class RuleValueBean  implements Serializable{

	private static final long serialVersionUID = 1L;

	List<RuleValue> agents;
	List<RuleValue> teams;
	
	public List<RuleValue> getAgents() {
		return agents;
	}
	
	public void setAgents(List<RuleValue> agents) {
		this.agents = agents;
	}
	public List<RuleValue> getTeams() {
		return teams;
	}
	
	public void setTeams(List<RuleValue> teams) {
		this.teams = teams;
	}
	
	
}
