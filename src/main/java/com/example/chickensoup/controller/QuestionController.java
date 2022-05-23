package com.example.chickensoup.controller;

import com.example.chickensoup.form.QuestionDto;
import com.example.chickensoup.form.UserDto;
import com.example.chickensoup.service.ClassService;
import com.example.chickensoup.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/question")
@Api(tags = "问题编辑器")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("/add")
    @ApiOperation(value = "加一个问题")
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
    @ApiOperation(value = "修改一个问题")
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
    @ApiOperation(value = "根据id查一个问题")
    public Map<String , Object> searchQuestionById(@RequestParam Integer questionId){//改变用户类型
        Map<String,Object> map = new HashMap<>();
        try{
            QuestionDto result = questionService.searchQuestion(questionId);
            map.put("result",result);
            map.put("msg","查找成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/search-keyword")
    @ApiOperation(value = "根据id查一个问题")
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
