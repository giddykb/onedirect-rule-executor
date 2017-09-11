package com.onedirectrule.executor;

import com.onedirect.rule.executor.bean.BasicRuleCondition;

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
	 boolean  betweenPredicate(BasicRuleCondition basicRule);
	 boolean during(BasicRuleCondition basicRule);
	 boolean matches(BasicRuleCondition basicRule);
	 boolean notMatches (BasicRuleCondition basicRule);
}
