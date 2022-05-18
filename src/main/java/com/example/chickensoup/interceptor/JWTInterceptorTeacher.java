package com.example.chickensoup.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.chickensoup.utils.Constants;
import com.example.chickensoup.utils.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTInterceptorTeacher implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, Object> map = new HashMap<>();

        String token = request.getHeader("token");
        // 捕获刚刚JWT中抛出的异常,并封装对应的返回信息
        try {
            JWTUtils.verify(token);
            if (JWTUtils.getUserType(token).equals(Constants.USER_STUDENT))//学生没有该权限
                map.put("msg","你没有这个权限");
            else
                return true;
        }catch (SignatureVerificationException e){
            map.put("msg", "无效签名");
        }catch (TokenExpiredException e){
            map.put("msg", "已过期");
        }catch (AlgorithmMismatchException e){
            map.put("msg", "算法不一致");
        }catch (Exception e){
            map.put("msg", "无效身份信息");
        }
        // 封装返回值
        map.put("code", 500);
        String json = new ObjectMapper().writeValueAsString(map);//返回json文件
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);

        return false;
    }

}

