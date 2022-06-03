package com.example.chickensoup.controller;

import com.example.chickensoup.entity.UserEntity;
import com.example.chickensoup.exception.ServiceException;
import com.example.chickensoup.form.UserDto;
import com.example.chickensoup.service.UserService;
import com.example.chickensoup.utils.Constants;
import com.example.chickensoup.utils.JWTUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/user")
@Api(tags = "问题编辑器")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/login")
    @ApiOperation(value = "登录")
    public Map<String, Object> login(@RequestBody Map<String, String> loginMap) {
        Map<String, Object> map = new HashMap<>();
        try {
            UserEntity user = userService.login(Integer.valueOf(loginMap.get("userId")), loginMap.get("password"));
            if (user.getUserType().equals(Constants.USER_CANCELLATION))
                throw new ServiceException("该用户已注销");
            Map<String, String> payload = new HashMap<>();//创建令牌
            payload.put("userId", String.valueOf(user.getId()));
            payload.put("userName", user.getUserName());
            if (user.getUserType().equals("superAdmin"))
                payload.put("userType", "100");
            else if (user.getUserType().equals(Constants.USER_ADMIN))
                payload.put("userType", "3");
            else if (user.getUserType().equals(Constants.USER_TEACHER))
                payload.put("userType", "2");
            else if (user.getUserType().equals(Constants.USER_STUDENT))
                payload.put("userType", "1");
            else
                throw new ServiceException("该用户类型非法");
            String token = JWTUtils.getToken(payload);//生成加密token密码
            map.put("result", "success");
            map.put("msg", "登录成功");
            map.put("token", token);//相应token
        } catch (Exception e) {
            map.put("result", "fail");
            map.put("msg", e.getMessage());
        }
        return map;
    }

    @GetMapping("/modify")
    @ApiOperation(value = "修改信息（不包括密码）")
    public Map<String, Object> modifyUser(@RequestBody UserDto userDto) {//不得改变用户类型
        Map<String, Object> map = new HashMap<>();
        try {
            String result = userService.modifyUser(userDto);
            map.put("result", result);
            map.put("msg", "修改成功");
        } catch (Exception e) {
            map.put("result", "fail");
            map.put("msg", e.getMessage());
        }
        return map;
    }

    @GetMapping("/create")
    @ApiOperation(value = "注册用户")
    public Map<String, Object> createUser(@RequestBody UserDto userDto) {
        Map<String, Object> map = new HashMap<>();
        try {
            System.out.println("recevied");
            Integer id = userService.addUser(userDto);
            map.put("result", id);
            map.put("msg", "创建成功");
        } catch (Exception e) {
            map.put("result", "fail");
            map.put("msg", e.toString());
        }
        return map;
    }

    @GetMapping("/search")
    @ApiOperation(value = "查找用户")
    public Map<String, Object> searchUser(@RequestParam int userId) {
        Map<String, Object> map = new HashMap<>();
        try {
            UserDto user = userService.searchUser(userId);
            map.put("result", user);
            map.put("msg", "查找用戶成功");
        } catch (Exception e) {
            map.put("result", "fail");
            map.put("msg", e.getMessage());
        }
        return map;
    }

}
