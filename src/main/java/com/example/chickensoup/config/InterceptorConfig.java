package com.example.chickensoup.config;

import com.example.chickensoup.interceptor.JWTInterceptor;
import com.example.chickensoup.interceptor.JWTInterceptorAdmin;
import com.example.chickensoup.interceptor.JWTInterceptorTeacher;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/user/**")
                .addPathPatterns("/test/**")
                .excludePathPatterns("/user/login");
        registry.addInterceptor(new JWTInterceptorTeacher())//教师级权限
                .addPathPatterns("user/modify")
                .addPathPatterns("/user/add");
        registry.addInterceptor(new JWTInterceptorAdmin())//管理员级权限
                .addPathPatterns("/user/cancel");
    }
}
