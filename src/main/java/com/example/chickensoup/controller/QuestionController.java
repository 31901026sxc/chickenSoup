package com.example.chickensoup.controller;

import com.example.chickensoup.form.QuestionDto;
import com.example.chickensoup.form.UserDto;
import com.example.chickensoup.service.ClassService;
import com.example.chickensoup.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("/add")
    public Map<String , Object> addQuestion(@RequestBody QuestionDto questionDto){//改变用户类型
        Map<String,Object> map = new HashMap<>();
        try{
            Integer result = questionService.addQuestion(questionDto);
            map.put("result",result);
            map.put("msg","创建成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/modify")
    public Map<String , Object> modifyQuestion(@RequestBody QuestionDto questionDto){//改变用户类型
        Map<String,Object> map = new HashMap<>();
        try{
            String result = questionService.modifyQuestion(questionDto);
            map.put("result",result);
            map.put("msg","修改成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/search-id")
    public Map<String , Object> searchQuestionById(@RequestBody Integer questionId){//改变用户类型
        Map<String,Object> map = new HashMap<>();
        try{
            QuestionDto result = questionService.searchQuestion(questionId);
            map.put("result",result);
            map.put("msg","修改成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/search-keyword")
    public Map<String , Object> searchQuestionByKeyword(@RequestBody String keyword){//改变用户类型
        Map<String,Object> map = new HashMap<>();
        try{
            List<QuestionDto> result = questionService.searchQuestionByKeyword(keyword);
            map.put("result",result);
            map.put("msg","修改成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }

}
