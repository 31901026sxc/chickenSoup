package com.example.chickensoup.controller;

import com.example.chickensoup.form.OptionDto;
import com.example.chickensoup.service.OptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@Slf4j
@RequestMapping("/option")
@Api(tags = "选项控制")
public class OptionController {//teacher,student
    @Autowired
    OptionService optionService;
    @GetMapping("/add")
    @ApiOperation(value = "加一个选项")
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
    @ApiOperation(value = "删除一个选项")
    public Map<String , Object> deleteOption(@RequestParam Integer optionId){
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
    @ApiOperation(value = "修改一个选项")
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
    @ApiOperation(value = "查找选项")
    public Map<String , Object> searchOption(@RequestParam Integer questionId){//改变用户类型
        Map<String,Object> map = new HashMap<>();
        try{
            Set<OptionDto> result = optionService.searchOptionByQuestion(questionId);
            map.put("result",result);
            map.put("msg","查询成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
}
