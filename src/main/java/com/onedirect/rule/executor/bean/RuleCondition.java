package com.onedirect.rule.executor.bean;

import java.util.List;

public class RuleCondition {

	String condition;
	List<RuleCondition> rules;
	BasicRuleCondition basicRule;
	
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public List<RuleCondition> getRules() {
		return rules;
	}
	public void setRules(List<RuleCondition> rules) {
		this.rules = rules;
	}
	public BasicRuleCondition getBasicRule() {
		return basicRule;
	}
	public void setBasicRule(BasicRuleCondition basicRule) {
		this.basicRule = basicRule;
	}
	
	
	
	
	
}
