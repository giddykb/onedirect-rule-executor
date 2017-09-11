package com.onedirectrule.executor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.onedirect.rule.executor.bean.RuleBean;

@Component
public interface RuleExecutor extends Serializable {

	public RuleBean execute();
}
