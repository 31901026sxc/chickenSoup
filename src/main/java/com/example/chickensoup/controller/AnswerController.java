package com.example.chickensoup.controller;

import com.example.chickensoup.form.AnswerSheetDto;
import com.example.chickensoup.form.UserDto;
import com.example.chickensoup.service.AnswerService;
import com.example.chickensoup.service.TestService;
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
@RequestMapping("/answer")
public class AnswerController {
    @Autowired
    AnswerService answerService;
    @GetMapping("/submit")
    public Map<String , Object> modifyUserType(@RequestBody AnswerSheetDto answerSheetDto){//改变用户类型
        Map<String,Object> map = new HashMap<>();
        try{
            Integer result = answerService.submitAnswerSheet(answerSheetDto);
            map.put("result",result);
            map.put("msg","提交成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/search-id")
    public Map<String , Object> searchId(@RequestBody Integer answerSheetId){//改变用户类型
        Map<String,Object> map = new HashMap<>();
        try{
            AnswerSheetDto result = answerService.searchAnswerSheetById(answerSheetId);
            map.put("result",result);
            map.put("msg","查询成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/search-one")
    public Map<String , Object> searchByStudentTest(@RequestBody Map<String,Integer> input){//改变用户类型
        Map<String,Object> map = new HashMap<>();
        try{
            AnswerSheetDto result = answerService.searchAnswerSheetByStudentAndTest(
                    (Integer) map.get("studentId"), (Integer) map.get("testId"));
            map.put("result",result);
            map.put("msg","查询成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/search-test")
    public Map<String , Object> searchByTest(@RequestBody Integer testId){//改变用户类型
        Map<String,Object> map = new HashMap<>();
        try{
            List<AnswerSheetDto> result = answerService.searchAllAnswerSheets(testId);
            map.put("result",result);
            map.put("msg","查询成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/search-student")
    public Map<String , Object> searchByStudent(@RequestBody Integer studentId){//改变用户类型
        Map<String,Object> map = new HashMap<>();
        try{
            List<AnswerSheetDto> result = answerService.searchAllStudentSheets(studentId);
            map.put("result",result);
            map.put("msg","查询成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
}
