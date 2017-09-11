package com.onedirect.rule.executor.interfaces;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.onedirect.rule.executor.bean.RuleBean;


public interface RuleExecutor extends Serializable {

	 RuleBean execute();
}
