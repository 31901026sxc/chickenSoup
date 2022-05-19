package com.example.chickensoup.controller;

import com.example.chickensoup.form.OptionDto;
import com.example.chickensoup.service.OptionService;
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
@RequestMapping("/option")
public class OptionController {//teacher,student
    @Autowired
    OptionService optionService;
    @GetMapping("/add")
    public Map<String , Object> addOption(@RequestBody OptionDto optionDto){//改变用户类型
        Map<String,Object> map = new HashMap<>();
        try{
            Integer result = optionService.addOption(optionDto);
            map.put("result",result);
            map.put("msg","创建选项成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/delete")
    public Map<String , Object> deleteOption(@RequestBody Integer optionId){//改变用户类型
        Map<String,Object> map = new HashMap<>();
        try{
            String result = optionService.deleteOption(optionId);
            map.put("result",result);
            map.put("msg","删除选项成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/modify")
    public Map<String , Object> modifyOption(@RequestBody OptionDto optionDto){//改变用户类型
        Map<String,Object> map = new HashMap<>();
        try{
            String result = optionService.modifyOption(optionDto);
            map.put("result",result);
            map.put("msg","修改选项成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/search")
    public Map<String , Object> modifyOption(@RequestBody Integer questionId){//改变用户类型
        Map<String,Object> map = new HashMap<>();
        try{
            List<OptionDto> result = optionService.searchOptionByQuestion(questionId);
            map.put("result",result);
            map.put("msg","查询成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
}
