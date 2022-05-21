package com.example.chickensoup.controller;

import com.example.chickensoup.entity.UserEntity;
import com.example.chickensoup.form.TestDto;
import com.example.chickensoup.form.UserDto;
import com.example.chickensoup.service.AnswerService;
import com.example.chickensoup.service.ClassService;
import com.example.chickensoup.service.TestService;
import com.example.chickensoup.service.UserService;
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
@RequestMapping("/admin")
@Api(tags = "管理员控制器")
public class AdminController {//对于管理员的超级权限
    @Autowired
    UserService userService;
    @Autowired
    ClassService classService;
    @Autowired
    AnswerService answerService;
    @Autowired
    TestService testService;

    @GetMapping("/modify-user")
    @ApiOperation(value = "id寻找管理员")
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
            map.put("msg","删除成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/delete-test")
    public Map<String , Object> deleteTest(@RequestBody Integer testId){
        Map<String,Object> map = new HashMap<>();
        try{
            String result = testService.deleteTest(testId);
            map.put("result",result);
            map.put("msg","删除成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/cancel-test")
    public Map<String , Object> cancelTest(@RequestBody Integer testId){
        Map<String,Object> map = new HashMap<>();
        try{
            String result = testService.cancelTest(testId);
            map.put("result",result);
            map.put("msg","取消成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/modify-test")
    public Map<String , Object> modifyTest(@RequestBody TestDto testDto){
        Map<String,Object> map = new HashMap<>();
        try{
            String result = testService.modifyTestAdmin(testDto);
            map.put("result",result);
            map.put("msg","修改成功");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/end-test")
    public Map<String , Object> endTest(@RequestBody Integer testId){
        Map<String,Object> map = new HashMap<>();
        try{
            String result = testService.endTest(testId);
            map.put("result",result);
            map.put("msg","成功结束");
        }catch (Exception e){
            map.put("result","fail");
            map.put("msg",e.getMessage());
        }
        return map;
    }

}
