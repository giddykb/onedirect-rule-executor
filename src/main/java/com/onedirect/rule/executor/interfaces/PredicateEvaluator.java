package com.onedirect.rule.executor.interfaces;

import com.onedirect.rule.executor.bean.BasicRuleCondition;
import com.onedirect.rule.executor.exception.BusinessHrsException;

public interface PredicateEvaluator {

	 boolean equalPredicate(BasicRuleCondition basicRule);
	 boolean notEqualPredicate(BasicRuleCondition basicRule);
	 boolean lessThanPredicate(BasicRuleCondition basicRule);
	 boolean greaterThanPredicate(BasicRuleCondition basicRule);
	 boolean containsAny(BasicRuleCondition basicRule);
	 boolean containsAll(BasicRuleCondition basicRule);
	 boolean notContainsAny(BasicRuleCondition basicRule);
	 boolean notContainsAll(BasicRuleCondition basicRule);
	 boolean beforePredicate(BasicRuleCondition basicRule);
	 boolean onPredicate(BasicRuleCondition basicRule);
	 boolean afterPredicate(BasicRuleCondition basicRule);
	 boolean  betweenPredicate(BasicRuleCondition basicRule) throws BusinessHrsException;
	 boolean during(BasicRuleCondition basicRule) throws BusinessHrsException;
	 boolean matches(BasicRuleCondition basicRule);
	 boolean notMatches (BasicRuleCondition basicRule);
}
