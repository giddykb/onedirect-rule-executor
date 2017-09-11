package com.onedirect.rule.executor.interfaces;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onedirect.rule.executor.bean.RuleCondition;
import com.onedirect.rule.executor.impl.RuleExecutorImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws JsonParseException, JsonMappingException, IOException
    {
        System.out.println( "Hello World!" );
        ObjectMapper mapper = new ObjectMapper();
        //String input ="";
        String input = "{  \"condition\": null,  \"rules\": [      ],  \"basicRule\": {    \"key\": \"description\",    \"operator\": \"not_matches\",    \"value\": [      \"e-ticket\",      \"e ticket \",      \"e-wallet \",      \"e wallet\"    ]  }}";
        RuleCondition ruleCondition = mapper.readValue(input, RuleCondition.class);
        
        HashMap<String , List<String>> map = new HashMap<String, List<String>>();
        
        RuleExecutor ruleExecutor = new RuleExecutorImpl(ruleCondition, map,map);
        ruleExecutor.execute();
        
    }
}
