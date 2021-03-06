package com.example.chickensoup.controller;

import com.example.chickensoup.entity.UserEntity;
import com.example.chickensoup.form.TestDto;
import com.example.chickensoup.form.TestSeedDto;
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
import org.springframework.data.repository.query.Param;
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
    @ApiOperation(value = "修改用户信息（管理员权限）")
    public Map<String, Object> modifyUserType(@RequestBody UserDto userDto) {//改变用户类型
        Map<String, Object> map = new HashMap<>();
        try {
            String result = userService.modifyUserType(userDto);
            map.put("result", result);
            map.put("msg", "修改成功");
        } catch (Exception e) {
            map.put("result", "fail");
            map.put("msg", e.getMessage());
        }
        return map;
    }

    @GetMapping("/delete-user")
    @ApiOperation(value = "从数据库中删除用户")
    public Map<String, Object> deleteUser(@RequestParam Integer userId) {
        Map<String, Object> map = new HashMap<>();
        try {
            String result = userService.deleteUser(userId);
            map.put("result", result);
            map.put("msg", "删除成功");
        } catch (Exception e) {
            map.put("result", "fail");
            map.put("msg", e.getMessage());
        }
        return map;
    }

    @GetMapping("/cancel")
    @ApiOperation(value = "从数据库中注销用户")
    public Map<String, Object> cancelUser(@RequestParam int userId) {
        Map<String, Object> map = new HashMap<>();
        try {
            String result = userService.cancelUser(userId);
            map.put("result", result);
            map.put("msg", "注销成功");
        } catch (Exception e) {
            map.put("result", "fail");
            map.put("msg", e.getMessage());
        }
        return map;
    }

    @GetMapping("/search-user")
    @ApiOperation(value = "从数据库中获取用户全部信息（包括密码）")
    public Map<String, Object> searchUser(@RequestParam Integer userId) {
        Map<String, Object> map = new HashMap<>();
        try {
            UserDto user = userService.adminSearchUser(userId);
            map.put("result", user);
            map.put("msg", "查找成功");
        } catch (Exception e) {
            map.put("result", "fail");
            map.put("msg", e.getMessage());
        }
        return map;
    }

    @GetMapping("/delete-class")
    @ApiOperation(value = "从数据库中删除班级")
    public Map<String, Object> deleteClass(@RequestParam int classId) {
        Map<String, Object> map = new HashMap<>();
        try {
            String result = classService.deleteClass(classId);
            map.put("result", result);
            map.put("msg", "删除成功");
        } catch (Exception e) {
            map.put("result", "fail");
            map.put("msg", e.getMessage());
        }
        return map;
    }

    @GetMapping("/delete-test")
    @ApiOperation(value = "从数据库中删除考试")
    public Map<String, Object> deleteTest(@RequestParam Integer testId) {
        Map<String, Object> map = new HashMap<>();
        try {
            String result = testService.deleteTest(testId);
            map.put("result", result);
            map.put("msg", "删除成功");
        } catch (Exception e) {
            map.put("result", "fail");
            map.put("msg", e.getMessage());
        }
        return map;
    }

    @GetMapping("/cancel-test")
    @ApiOperation(value = "从数据库中取消考试")
    public Map<String, Object> cancelTest(@RequestParam Integer testId) {
        Map<String, Object> map = new HashMap<>();
        try {
            String result = testService.cancelTest(testId);
            map.put("result", result);
            map.put("msg", "取消成功");
        } catch (Exception e) {
            map.put("result", "fail");
            map.put("msg", e.getMessage());
        }
        return map;
    }

    @GetMapping("/end-test")
    @ApiOperation(value = "立刻终止考试")
    public Map<String, Object> endTest(@RequestParam Integer testId) {
        Map<String, Object> map = new HashMap<>();
        try {
            String result = testService.endTest(testId);
            map.put("result", result);
            map.put("msg", "成功结束");
        } catch (Exception e) {
            map.put("result", "fail");
            map.put("msg", e.getMessage());
        }
        return map;
    }

}
