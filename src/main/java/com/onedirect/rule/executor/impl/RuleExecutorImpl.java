package com.onedirect.rule.executor.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.onedirect.rule.executor.bean.BasicRuleCondition;
import com.onedirect.rule.executor.bean.RuleBean;
import com.onedirect.rule.executor.bean.RuleCondition;
import com.onedirectrule.executor.PredicateEvaluator;
import com.onedirectrule.executor.RuleExecutor;
import com.onedirectrule.executor.enums.OperatorEnum;

/**
 *  
 * 
 * This is generic RuleExector library which can be used to across all the  OneDirect rule services like ticketing-rule,assignment-rule,automation-rules..,
 * This class takes list of rules and data  as hahsmap on whcih the 
 *
 */

public class RuleExecutorImpl implements RuleExecutor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final List<RuleBean> rules ;
	private final HashMap<String , List<String>> dataEvaluationMap;
	private final HashMap<String , List<String>> bzHrsMap;
	
	private  RuleCondition ruleConditions ;
	
	PredicateEvaluator predicteEvaluator;
	
	/**
	 * 
	 * @param rules
	 * @param dataEvaluationMap
	 * @param bzHrsMap
	 */
	public RuleExecutorImpl(List<RuleBean> rules ,HashMap<String, List<String>> dataEvaluationMap,HashMap<String , List<String>> bzHrsMap) {
		// TODO Auto-generated constructor stub
		this.rules = rules;
		this.dataEvaluationMap = dataEvaluationMap;
		this.bzHrsMap = bzHrsMap;
	    predicteEvaluator =  new PredicateEvaluatorImpl(dataEvaluationMap, bzHrsMap);

	}
	
	
	/**
	 * 
	 * @param ruleCondtions
	 * @param dataEvaluationMap
	 * @param bzHrsMap
	 */
	public RuleExecutorImpl(RuleCondition ruleCondtions ,HashMap<String, List<String>> dataEvaluationMap,HashMap<String , List<String>> bzHrsMap) {
		// TODO Auto-generated constructor stub
		this.ruleConditions = ruleCondtions;
		this.dataEvaluationMap = dataEvaluationMap;
		this.bzHrsMap=bzHrsMap;
		 predicteEvaluator =  new PredicateEvaluatorImpl(dataEvaluationMap, bzHrsMap);
		this.rules= new ArrayList<RuleBean>();//remove later
		ArrayList<String> a1 = new ArrayList<String>();
		ArrayList<String> a2 = new ArrayList<String>();
		ArrayList<String> a3 = new ArrayList<String>();
		ArrayList<String> a4 = new ArrayList<String>();
		ArrayList<String> a5 = new ArrayList<String>();
		a1.add("1");//a1.add("2");a1.add("3");
		a2.add("2");//a2.add("5");a2.add("6");
		a3.add("High");
		a4.add("110");
		a5.add("testing the e ticket description while tagging ");//a5.add("service");
		
		dataEvaluationMap.put("source", a1);
		dataEvaluationMap.put("subSource", a2);
		dataEvaluationMap.put("priority", a3);
		dataEvaluationMap.put("followersCount",a4);
		dataEvaluationMap.put("description", a5);
		
	}
	
	
	/**
	 * 
	 */
	public RuleBean execute() {
		// TODO Auto-generated method stub
		if(rules == null && rules.isEmpty()) return null;
		
		for (RuleBean ruleBean : rules) {
			RuleCondition ruleConditions = ruleBean.getConditions();
			int sz = ruleConditions.getRules().size();
			boolean matchedRule =execute(ruleConditions, 0, sz);
			return ruleBean;
		}
		
		Boolean  flag = execute(ruleConditions, 0, ruleConditions.getRules().size());//remove later
		System.out.println(flag);
		
		return null;
	}
	/**
	 * 
	 * @param ruleCondition
	 * @param low
	 * @param high
	 * @return
	 */
	public boolean execute(RuleCondition ruleCondition,int low,int high ){
		
		if(ruleCondition.getCondition() != null && ruleCondition.getRules() != null
				&& !ruleCondition.getRules().isEmpty() && low==ruleCondition.getRules().size()) return true;
		
		if(ruleCondition.getCondition() == null){
			return executeBasicRule(ruleCondition.getBasicRule());
		}else if(ruleCondition.getCondition().equalsIgnoreCase("and")){
			return execute(ruleCondition.getRules().get(low),0,ruleCondition.getRules().get(low).getRules().size())  && 
					execute(ruleCondition, low+1, high);
		}else{
			return execute(ruleCondition.getRules().get(low),0,ruleCondition.getRules().get(low).getRules().size())  ||  
					execute(ruleCondition, low+1, high);
		}
		
		
	
	}
	
	private boolean executeBasicRule(BasicRuleCondition basicRule){
		//PredicateEvaluator predicteEvaluator =  new PredicateEvaluatorImpl(basicRule, map, bzHrsMap);
		
		switch (basicRule.getOperator()) {
		
		case OperatorEnum.IS:
		case OperatorEnum.EQUALTO:
			return predicteEvaluator.equalPredicate(basicRule);
			
		case OperatorEnum.ISNOT:
		case OperatorEnum.NOTEQUALTO:
			 return predicteEvaluator.notEqualPredicate(basicRule);
			 
		case OperatorEnum.LESSTHAN:
			return predicteEvaluator.lessThanPredicate(basicRule);
			
		case OperatorEnum.GREATERTHAN:
			return predicteEvaluator.greaterThanPredicate(basicRule);
			
		case OperatorEnum.CONTAINSANY:
			return predicteEvaluator.containsAny(basicRule);
			
		case OperatorEnum.CONTAINSALL:
			return predicteEvaluator.containsAll(basicRule);
			
		case OperatorEnum.DOESNOTCONTAIN:
			return predicteEvaluator.notContainsAny(basicRule);
			
		case OperatorEnum.CONTAINSANYMATCH:
			return predicteEvaluator.matches(basicRule);
			
		case OperatorEnum.NOTCONTAINSANYMATCH:
			return predicteEvaluator.notMatches(basicRule);
		default:
			break;
		}
		return false;
	}

}
