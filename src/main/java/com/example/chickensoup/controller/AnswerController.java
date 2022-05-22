package com.example.chickensoup.controller;

import com.example.chickensoup.form.AnswerSheetDto;
import com.example.chickensoup.form.UserDto;
import com.example.chickensoup.service.AnswerService;
import com.example.chickensoup.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "答案管理")
public class AnswerController {
    @Autowired
    AnswerService answerService;
    @GetMapping("/submit")
    @ApiOperation(value = "提交答卷")
    public Map<String , Object> submit(@RequestBody AnswerSheetDto answerSheetDto){
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
    @ApiOperation(value = "查找答卷号")
    public Map<String , Object> searchId(@RequestBody Integer answerSheetId){
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
    @ApiOperation(value = "查找某学生某次考试")
    public Map<String , Object> searchByStudentTest(@RequestBody Map<String,Integer> input){
        Map<String,Object> map = new HashMap<>();
        try{
            AnswerSheetDto result = answerService.searchAnswerSheetByStudentAndTest(
                    input.get("studentId"), input.get("testId"));
            map.put("result",result);
            map.put("msg","查询成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/search-test")
    @ApiOperation(value = "查找某次考试所有答卷")
    public Map<String , Object> searchByTest(@RequestBody Integer testId){
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
    @ApiOperation(value = "查找某学生所有答卷")
    public Map<String , Object> searchByStudent(@RequestBody Integer studentId){
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
