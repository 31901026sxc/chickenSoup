package com.example.chickensoup.controller;

import com.example.chickensoup.entity.UserEntity;
import com.example.chickensoup.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/user/login")
    public Map<String , Object>login(int userId, String password){
        Map<String,Object> map = new HashMap<>();
        try{
            UserEntity user = userService.login(userId,password);
            map.put("result","success");
            map.put("msg","登录成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }

}
