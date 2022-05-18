package com.example.chickensoup.controller;

import com.example.chickensoup.entity.UserEntity;
import com.example.chickensoup.form.UserDto;
import com.example.chickensoup.service.UserService;
import com.example.chickensoup.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/login")
    public Map<String , Object>login(@RequestBody Map<String, String> loginMap){
        Map<String,Object> map = new HashMap<>();
        try{
            UserEntity user = userService.login(Integer.valueOf(loginMap.get("userId")),loginMap.get("password"));
            Map<String,String> payload =new HashMap<>();//创建令牌
            payload.put("userId",String.valueOf(user.getId()));
            payload.put("userName",user.getUserName());
            payload.put("userType",user.getUserType());
            String token = JWTUtils.getToken(payload);//生成加密token密码
            map.put("result","success");
            map.put("msg","登录成功");
            map.put("token",token);//相应token
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/modify")
    public Map<String , Object> modifyUser(@RequestBody UserDto userDto){//不得改变用户类型
        Map<String,Object> map = new HashMap<>();
        try{
            String result = userService.modifyUser(userDto);
            map.put("result",result);
            map.put("msg","修改成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/create")
    public Map<String , Object> createUser(@RequestBody UserDto userDto){
        Map<String,Object> map = new HashMap<>();
        try{
            Integer id = userService.addUser(userDto);
            map.put("result",id);
            map.put("msg","创建成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }

    @GetMapping("/search")
    public Map<String , Object> searchUser(@RequestBody int userId){
        Map<String,Object> map = new HashMap<>();
        try{
            UserDto user = userService.searchUser(userId);
            map.put("result",user);
            map.put("msg","注销成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }

}
