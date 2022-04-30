package cn.itcast.haoke.dubbo.api.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ExecutionInput;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("graphql")
public class GraphQLController {
    
    @Autowired
    private GraphQL graphQL;
    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    @GetMapping
    @ResponseBody
    public Map<String, Object> query(@RequestParam("query") String query,
                                     @RequestParam(value = "variables", required = false) String variablesJson,
                                     @RequestParam(value = "operationName", required = false) String operationName) {
    
        try {
        
            Map variables = MAPPER.readValue(variablesJson,
                    MAPPER.getTypeFactory().constructMapType(HashMap.class, String.class,
                            Object.class));
            return this.executeGraphqlQuery(query, operationName, variables);
        } catch (IOException e) {
            e.printStackTrace();
        
        }
        Map<String, Object> error = new HashMap<>();
        error.put("status", 500);
        error.put("msg", "查询出错");
        return error;
    
    
    }
    @PostMapping
    @ResponseBody
    public Map<String, Object> postQuery(@RequestBody Map<String,Object> map){
        try{
            String query = (String) map.get("query");
            if (null==query) {
                query="";
            }
            String operationName = (String) map.get("operationName");
            Map variables = (Map) map.get("variables");
            if (variables==null) {
                variables= Collections.EMPTY_MAP;
            }
            return this.executeGraphqlQuery(query, operationName, variables);
        }catch (Exception e){
            e.printStackTrace();
        }
        Map<String, Object> error = new HashMap<>();
        error.put("status", 500);
        error.put("msg", "查询出错");
        return error;
    }
    
    
    private Map<String,Object> executeGraphqlQuery(String query,String operationName,Map<String,Object> variables){
        ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                .query(query).
                operationName(operationName)
                .variables(variables)
                .build();
        return this.graphQL.execute(executionInput).toSpecification();
    }
}
