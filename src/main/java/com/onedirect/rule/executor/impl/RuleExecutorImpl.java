package com.onedirect.rule.executor.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.onedirect.rule.executor.bean.BasicRuleCondition;
import com.onedirect.rule.executor.bean.RuleBean;
import com.onedirect.rule.executor.bean.RuleCondition;
import com.onedirect.rule.executor.interfaces.PredicateEvaluator;
import com.onedirect.rule.executor.interfaces.RuleExecutor;
import com.onedirectrule.executor.enums.OperatorEnum;

/**
 *  
 * 
 * This is generic RuleExector library which can be used to across all the OneDirect rule services such as  ticketing-rule,assignment-rule,automation-rules..,
 * This class takes list of rules and data  as HashMap<String, List<String>>  additionally  to handle rules having business hours 
 *  another HashMap<String, List<String>> is passed in the constructor 
 *  
 *  A generic JSON template  for rule condition is created to evaluate any kind of the rules. Sample JSON Formats for Rule Conditions can be found in resources folder 
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
	
	private PredicateEvaluator predicteEvaluator;
	
	private RuleBean matchedRule;
	
	
	/**
	 * 
	 * @param rules -- List of the rules on which the data mapping should be evaluated 
	 * @param dataEvaluationMap --  Data mapping to be evaluated  Key: condition fields as ,String Value :List of Strings 
	 * @param bzHrsMap --  mapping to handle business hours case. Key : id as String ,Value : List of time stamps as strings .
	 *                     Ideally two values having startTime and endTime  
	 */
	public RuleExecutorImpl(List<RuleBean> rules ,HashMap<String, List<String>> dataEvaluationMap,HashMap<String , List<String>> bzHrsMap) {
		// TODO Auto-generated constructor stub
		this.rules = rules;
		this.dataEvaluationMap = dataEvaluationMap;
		this.bzHrsMap = bzHrsMap;
	    predicteEvaluator =  new PredicateEvaluatorImpl(dataEvaluationMap, bzHrsMap);

	}
	
	
	/**
	 *  This is used for Testing to evaluate the Rule conditions . 
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
	 * This method executes the rules on the data Mapping and return the Matching rule
	 */
	public RuleBean execute() {
		// TODO Auto-generated method stub
		if(rules == null && rules.isEmpty()) return null;
		
		for (RuleBean ruleBean : rules) {
			RuleCondition ruleConditions = ruleBean.getConditions();
			int sz = ruleConditions.getRules().size();
			execute(ruleConditions, 0, sz);
			matchedRule = ruleBean;
			return ruleBean;
		}
		
		Boolean  flag = execute(ruleConditions, 0, ruleConditions.getRules().size());//remove later
		System.out.println(flag);
		
		return null;
	}
	/**
	 * Recursive function to execute the rule condition 
	 * @param ruleCondition
	 * @param low
	 * @param high
	 * @return boolean
	 */
	public boolean execute(RuleCondition ruleCondition,int low,int high ){
		
		if(ruleCondition.getCondition() != null && ruleCondition.getRules() != null
				&& !ruleCondition.getRules().isEmpty() && low == ruleCondition.getRules().size()) return true;
		
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
	/**
	 *  Function to execute the basicRule based on the operator 
	 * @param basicRule 
	 * @return boolean  
	 */
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

	
	public PredicateEvaluator getPredicteEvaluator() {
		return predicteEvaluator;
	}


	public void setPredicteEvaluator(PredicateEvaluator predicteEvaluator) {
		this.predicteEvaluator = predicteEvaluator;
	}


	public RuleBean getMatchedRule() {
		return matchedRule;
	}


	public List<RuleBean> getRules() {
		return rules;
	}


	public HashMap<String, List<String>> getDataEvaluationMap() {
		return dataEvaluationMap;
	}


	public HashMap<String, List<String>> getBzHrsMap() {
		return bzHrsMap;
	}
	
	
	
	

}
