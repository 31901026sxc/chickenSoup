package com.example.chickensoup.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.chickensoup.utils.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTInterceptorAdmin implements HandlerInterceptor {//被这个拦截器拦下来的学生一律拒绝访问
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, Object> map = new HashMap<>();

        String token = request.getHeader("token");
        // 捕获刚刚JWT中抛出的异常,并封装对应的返回信息
        try {
            JWTUtils.verify(token);
            if (!JWTUtils.getUserType(token).equals("admin"))//只有管理员才有该权限
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
        map.put("code", 40);
        String json = new ObjectMapper().writeValueAsString(map);//返回json文件
        response.setContentType("application/json;charset=GB2312");
        response.getWriter().println(json);

        return false;
    }

}

