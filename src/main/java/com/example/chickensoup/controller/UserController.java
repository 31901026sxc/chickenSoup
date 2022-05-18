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
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/user/login")
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
    @GetMapping("/user/modify")
    public Map<String , Object> modifyUser(UserDto userDto){
        Map<String,Object> map = new HashMap<>();
        try{
            String result = userService.modifyUser(userDto);
            Map<String,String> payload =new HashMap<>();//创建令牌
            map.put("result",result);
            map.put("msg","修改成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @PostMapping("/user/cancel")
    public Map<String , String> cancelUser(int userId){
        Map<String,String> map = new HashMap<>();
        try{
            String result = userService.cancelUser(userId);
            map.put("result",result);
            map.put("msg","修改成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }

}
