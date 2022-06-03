package com.example.chickensoup.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils {

    private static final String SING = "MIO_KATAMIYA";//生成秘钥,不可泄露

    //生成token
    public static String getToken(Map<String, String> map) {//不用object作为value类型是因为withClaim没有string和object的重载方法
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 1);
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k, v) -> {//输入加密数据
            builder.withClaim(k, v);
        });
        String token = builder.withExpiresAt(instance.getTime())//指定令牌过期时间
                .sign(Algorithm.HMAC256(SING));//加密方法HMAC256
        return token;
    }

    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }

    public static String getUserType(String token) {
        DecodedJWT jwtVerifier = JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
        return jwtVerifier.getClaim("userType").asString();
    }
}

