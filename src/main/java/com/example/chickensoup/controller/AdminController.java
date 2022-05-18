package com.example.chickensoup.controller;

import com.example.chickensoup.entity.UserEntity;
import com.example.chickensoup.form.UserDto;
import com.example.chickensoup.service.ClassService;
import com.example.chickensoup.service.UserService;
import com.example.chickensoup.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {//对于管理员的超级权限
    @Autowired
    UserService userService;
    @Autowired
    ClassService classService;
    @GetMapping("/modify-user")
    public Map<String , Object> modifyUserType(@RequestBody UserDto userDto){//改变用户类型
        Map<String,Object> map = new HashMap<>();
        try{
            String result = userService.modifyUserType(userDto);
            map.put("result",result);
            map.put("msg","修改成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/delete-user")
    public Map<String , Object> deleteUser(@RequestBody Integer userId){
        Map<String,Object> map = new HashMap<>();
        try{
            String result = userService.deleteUser(userId);
            map.put("result",result);
            map.put("msg","删除成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/cancel")
    public Map<String , Object> cancelUser(@RequestBody int userId){
        Map<String,Object> map = new HashMap<>();
        try{
            String result = userService.cancelUser(userId);
            map.put("result",result);
            map.put("msg","注销成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/search-user")
    public Map<String , Object> searchUser(@RequestBody int userId){
        Map<String,Object> map = new HashMap<>();
        try{
            UserDto user = userService.adminSearchUser(userId);
            map.put("result",user);
            map.put("msg","查找成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/delete-class")
    public Map<String , Object> deleteClass(@RequestBody int classId){
        Map<String,Object> map = new HashMap<>();
        try{
            String result = classService.deleteClass(classId);
            map.put("result",result);
            map.put("msg","查找成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
}
