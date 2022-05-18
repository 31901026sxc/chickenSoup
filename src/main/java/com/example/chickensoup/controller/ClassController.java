package com.example.chickensoup.controller;

import com.example.chickensoup.form.ClassDto;
import com.example.chickensoup.form.UserDto;
import com.example.chickensoup.service.ClassService;
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
@RequestMapping("/class")
public class ClassController {
    @Autowired
    ClassService classService;
    @GetMapping("/create-empty")
    public Map<String , Object> createEmptyClass(@RequestBody ClassDto classDto){
        Map<String,Object> map = new HashMap<>();
        try{
            Integer classId = classService.addClassInfo(classDto);
            map.put("result","班级号"+classId);
            map.put("msg","登录成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/create")
    public Map<String , Object> createClass(@RequestBody ClassDto classDto){
        Map<String,Object> map = new HashMap<>();
        try{
            Integer classId = classService.addClass(classDto);
            map.put("result","班级号"+classId);
            map.put("msg","登录成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/add-one")
    public Map<String , Object> addAStudent(@RequestBody UserDto userDto, Integer classId){
        Map<String,Object> map = new HashMap<>();
        try{
            String result = classService.addAStudent(userDto , classId);
            map.put("result",result);
            map.put("msg","添加成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/add-many")
    public Map<String , Object> addStudents(@RequestBody List<UserDto> userList, Integer classId){
        Map<String,Object> map = new HashMap<>();
        try{
            String result = classService.addStudents(userList , classId);
            map.put("result",result);
            map.put("msg","添加成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/modify")
    public Map<String , Object> modifyClass(@RequestBody ClassDto classDto){
        Map<String,Object> map = new HashMap<>();
        try{
            String result = classService.modifyClass(classDto);
            map.put("result",result);
            map.put("msg","修改成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/search")
    public Map<String , Object> searchClass(@RequestBody Integer classId){
        Map<String,Object> map = new HashMap<>();
        try{
            ClassDto classDto = classService.searchClassById(classId);
            map.put("result",classDto);
            map.put("msg","修改成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/list")
    public Map<String , Object> listClass(){
        Map<String,Object> map = new HashMap<>();
        try{
            List<ClassDto> classes = classService.searchClasses();
            map.put("result",classes);
            map.put("msg","修改成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
}
