package com.example.chickensoup.controller;

import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.TestDto;
import com.example.chickensoup.form.TestSeedDto;
import com.example.chickensoup.form.UserDto;
import com.example.chickensoup.service.ClassService;
import com.example.chickensoup.service.TestService;
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
@RequestMapping("/test")
@Api(tags = "问题编辑器")
public class TestController {
    @Autowired
    TestService testService;

    @GetMapping("/add")
    @ApiOperation(value = "加一个考试")
    public Map<String , Object> addTest(@RequestBody TestSeedDto testSeed){//改变用户类型
        Map<String,Object> map = new HashMap<>();
        try{
            TestDto result = testService.addTest(testSeed);
            map.put("result",result);
            map.put("msg","创建成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/search-creator")
    @ApiOperation(value = "根据创建者id查考试")
    public Map<String , Object> searchByCreator(@RequestParam Integer creatorId){
        Map<String,Object> map = new HashMap<>();
        try{
            List<TestDto> result = testService.searchTestByCreator(creatorId);
            map.put("result",result);
            map.put("msg","修改成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/add-students")
    @ApiOperation(value = "往考试里加考生")
    public Map<String , Object> addStudents(@RequestBody List<Integer> students , Integer testId){
        Map<String,Object> map = new HashMap<>();
        try{
            String result = testService.addStudentsToTest(students,testId);
            map.put("result",result);
            map.put("msg","修改成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
}
