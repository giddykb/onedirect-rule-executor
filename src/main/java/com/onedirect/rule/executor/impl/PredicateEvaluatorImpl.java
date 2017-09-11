package com.onedirect.rule.executor.impl;

import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.onedirect.rule.executor.bean.BasicRuleCondition;
import com.onedirectrule.executor.PredicateEvaluator;

public class PredicateEvaluatorImpl implements PredicateEvaluator {

	private final HashMap<String, List<String>> dataEvaluationMap;
	private final HashMap<String, List<String>> bzHrsMap;
	private final String tokenPattern = " \t\n\r\f,.:;?![]'";
	private BasicRuleCondition basicRule;
	private List<String> dataValues;

	public PredicateEvaluatorImpl(BasicRuleCondition basicRule, HashMap<String, List<String>> dataEvaluationMap,
			HashMap<String, List<String>> bzHrsMap) {
		// TODO Auto-generated constructor stub
		this.dataEvaluationMap = dataEvaluationMap;
		this.basicRule = basicRule;
		this.bzHrsMap = bzHrsMap;
	}

	public PredicateEvaluatorImpl(HashMap<String, List<String>> map, HashMap<String, List<String>> bzHrsMap) {
		// TODO Auto-generated constructor stub
		this.dataEvaluationMap = map;
		this.bzHrsMap = bzHrsMap;
	}

	@Override
	public boolean equalPredicate(BasicRuleCondition basicRule) {
		dataValues = dataEvaluationMap.get(basicRule.getKey());

		if (basicRule.getValue() != null && dataValues != null) {
			return basicRule.getValue().containsAll(dataValues);
		}

		return false;
	}

	@Override
	public boolean notEqualPredicate(BasicRuleCondition basicRule) {
		dataValues = dataEvaluationMap.get(basicRule.getKey());

		if (basicRule.getValue() != null && dataValues != null) {
			return !basicRule.getValue().containsAll(dataValues);
		}

		return false;

	}

	@Override
	public boolean lessThanPredicate(BasicRuleCondition basicRule) {
/*		dataValues = dataEvaluationMap.get(basicRule.getKey());
		if (dataValues != null && !dataValues.isEmpty() && basicRule.getValue() != null
				&& !basicRule.getValue().isEmpty()) {
			Integer mapValue = Integer.parseInt(dataValues.get(0).trim());
			Integer ruleValue = Integer.parseInt(basicRule.getValue().get(0).trim());
			return mapValue.compareTo(ruleValue) < 0;
		}
		return false;
*/
		return compare(basicRule);
	}

	@Override
	public boolean greaterThanPredicate(BasicRuleCondition basicRule) {
/*		dataValues = dataEvaluationMap.get(basicRule.getKey());
		if (dataValues != null && !dataValues.isEmpty() && basicRule.getValue() != null
				&& !basicRule.getValue().isEmpty()) {
			Integer mapValue = Integer.parseInt(dataValues.get(0).trim());
			Integer ruleValue = Integer.parseInt(basicRule.getValue().get(0).trim());
			return mapValue.compareTo(ruleValue) > 0;
		}
		return false;
		
*/	
		return !compare(basicRule);
		}
	
	
	private boolean compare(BasicRuleCondition basicRule){
		
		dataValues = dataEvaluationMap.get(basicRule.getKey());
		if (dataValues != null && !dataValues.isEmpty() && basicRule.getValue() != null
				&& !basicRule.getValue().isEmpty()) {
			Integer mapValue = Integer.parseInt(dataValues.get(0).trim());
			Integer ruleValue = Integer.parseInt(basicRule.getValue().get(0).trim());
			return mapValue.compareTo(ruleValue) < 0;
		}

		return false;
	}


	@Override
	public boolean containsAny(BasicRuleCondition basicRule) {
		dataValues = dataEvaluationMap.get(basicRule.getKey());
		List<String> ruleValues = basicRule.getValue();
		if (dataValues != null) {
			for (String value : dataValues) {
				if (ruleValues.contains(value.toLowerCase()))
					return true;
			}
		}

		return false;
	}

	@Override
	public boolean containsAll(BasicRuleCondition basicRule) {
		dataValues = dataEvaluationMap.get(basicRule.getKey());
		if (basicRule.getValue() != null && dataValues != null) {
			return dataValues.containsAll(basicRule.getValue());
		}
		return false;
	}

	@Override
	public boolean notContainsAny(BasicRuleCondition basicRule) {
		// TODO Auto-generated method stub
		dataValues = dataEvaluationMap.get(basicRule.getKey());
		List<String> ruleValues = basicRule.getValue();
		if (dataValues != null) {
			for (String value : dataValues) {
				if (ruleValues.contains(value))
					return false;
			}
		}

		return true;
	}

	@Override
	public boolean notContainsAll(BasicRuleCondition basicRule) {
		// TODO Auto-generated method stub
		dataValues = dataEvaluationMap.get(basicRule.getKey());
		return !dataValues.containsAll(basicRule.getValue());
	}

	@Override
	public boolean beforePredicate(BasicRuleCondition basicRule) {
		dataValues = dataEvaluationMap.get(basicRule.getKey());
		if (dataValues != null && !dataValues.isEmpty()) {
			Long mapValue = Long.parseLong(dataValues.get(0).trim());
			Long ruleValue = Long.parseLong(basicRule.getValue().get(0).trim());
			return mapValue.compareTo(ruleValue) < 0;
		}
		return false;
	}

	@Override
	public boolean onPredicate(BasicRuleCondition basicRule) {
		dataValues = dataEvaluationMap.get(basicRule.getKey());
		if (dataValues != null && !dataValues.isEmpty()) {
			Long mapValue = Long.parseLong(dataValues.get(0).trim());
			Long ruleValue = Long.parseLong(basicRule.getValue().get(0).trim());
			return mapValue.compareTo(ruleValue) == 0;
		}
		return false;
	}

	@Override
	public boolean afterPredicate(BasicRuleCondition basicRule) {
		if (dataValues != null && !dataValues.isEmpty()) {
			Long mapValue = Long.parseLong(dataValues.get(0).trim());
			Long ruleValue = Long.parseLong(basicRule.getValue().get(0).trim());
			return mapValue.compareTo(ruleValue) > 0;
		}
		return false;
	}

	@Override
	public boolean betweenPredicate(BasicRuleCondition basicRule) {
		dataValues = dataEvaluationMap.get(basicRule.getKey());
		if (dataValues != null && !dataValues.isEmpty()) {
			Long mapValue = Long.parseLong(dataValues.get(0).trim());
			Long startTime = Long.parseLong(basicRule.getValue().get(0).trim());
			Long endTime = Long.parseLong(basicRule.getValue().get(0).trim());
			return startTime.compareTo(mapValue) > 0 && endTime.compareTo(mapValue) < 0;
		}
		return false;
	}

	@Override
	public boolean during(BasicRuleCondition basicRule) {
		// TODO Auto-generated method stub
		dataValues = dataEvaluationMap.get(basicRule.getKey());

		List<String> bzHrsValues = bzHrsMap.get(basicRule.getValue().get(0));
		if (bzHrsValues != null && !bzHrsValues.isEmpty()) {

			Long startTime = Long.parseLong(bzHrsValues.get(0).trim());
			Long endTime = Long.parseLong(bzHrsValues.get(1).trim());
			Long evalutionValue = Long.parseLong(dataValues.get(0).trim());
			return startTime.compareTo(evalutionValue) > 0 && endTime.compareTo(evalutionValue) < 0;

		}
		return false;
	}

	@Override
	public boolean matches(BasicRuleCondition basicRule) {
		// TODO Auto-generated method stub
		return patternMatching(basicRule, true);
	}

	@Override
	public boolean notMatches(BasicRuleCondition basicRule) {
		// TODO Auto-generated method stub

		return patternMatching(basicRule, false);
	}

	private boolean patternMatching(BasicRuleCondition basicRule, boolean isMatching) {

		List<String> keywords = basicRule.getValue();
		dataValues = dataEvaluationMap.get(basicRule.getKey());
		String description = dataValues.get(0);

		StringTokenizer descTokenizer = new StringTokenizer(description, tokenPattern);

		for (String keyword : keywords) {
			StringTokenizer keywordTokenizer = new StringTokenizer(keyword, tokenPattern);

			if (keywordTokenizer.countTokens() > 1) {

				String pattern = "\\b" + keyword + "\\b";
				Pattern p = Pattern.compile(pattern);
				Matcher m = p.matcher(description);
				if (m.find())
					return isMatching;
			} else {
				while (descTokenizer.hasMoreElements()) {
					String currentToken = descTokenizer.nextToken().trim().toString();
					if (keyword.equalsIgnoreCase(currentToken.trim().replaceAll("\u00A0", ""))) {
						return isMatching;
					}

				}
			}
			descTokenizer = new StringTokenizer(description, tokenPattern);
		}
		return !isMatching;
	}
	
	

}
