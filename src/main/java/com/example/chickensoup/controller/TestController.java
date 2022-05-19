package com.example.chickensoup.controller;

import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.TestDto;
import com.example.chickensoup.form.UserDto;
import com.example.chickensoup.service.ClassService;
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
@RequestMapping("/test")
public class TestController {
    @Autowired
    TestService testService;

    @GetMapping("/add")
    public Map<String , Object> addTest(@RequestBody TestDto testDto){//改变用户类型
        Map<String,Object> map = new HashMap<>();
        try{
            TestDto result = testService.addTest(testDto);
            map.put("result",result);
            map.put("msg","创建成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/modify")
    public Map<String , Object> modifyTest(@RequestBody TestDto testDto){//改变用户类型
        Map<String,Object> map = new HashMap<>();
        try{
            TestDto result = testService.modifyTest(testDto);
            map.put("result",result);
            map.put("msg","修改成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/search-creator")
    public Map<String , Object> searchByCreator(@RequestBody Integer creatorId){//改变用户类型
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
}
