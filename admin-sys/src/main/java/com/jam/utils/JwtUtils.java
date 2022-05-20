package com.jam.utils;

import com.jam.adapter.constants.TokenConstraints;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;

/**
 * @program: SpringCloudStudy
 * @description: jwt工具
 * @author: Mr.Pu
 * @create: 2022-04-29 21:29
 **/

public class JwtUtils {

    /**
     * 解析token数据
     *
     * @param token jwt的token
     * @return 返回解析出来的信息
     */
    public static Claims decode(String token) {
        return Jwts.parser()
                .setSigningKey(TokenConstraints.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 简单传入用户名生成一个7小时过期的token
     *
     * @param username 用户名
     * @return token
     */
    public static String generate(String username) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.HOUR, TokenConstraints.EXPIRE_TIME);
        return Jwts.builder()
                .setHeaderParam(TokenConstraints.JWT_HEADER, TokenConstraints.JWT_HEADER)
                .setSubject(TokenConstraints.USERNAME)
                .claim(TokenConstraints.USERNAME, username)
                .setExpiration(instance.getTime())
                .signWith(SignatureAlgorithm.HS256, TokenConstraints.JWT_SECRET)
                .compact();
    }

    /**
     * 简单传入用户名生成一个7小时过期的token
     *
     * @param username 用户名
     * @return token
     */
    public static String generate(String username, Integer time) {
        Calendar instance = Calendar.getInstance();
        instance.add(time, TokenConstraints.EXPIRE_TIME);
        return Jwts.builder()
                .setHeaderParam(TokenConstraints.JWT_HEADER, TokenConstraints.JWT_HEADER)
                .setSubject(TokenConstraints.USERNAME)
                .claim(TokenConstraints.USERNAME, username)
                .setExpiration(instance.getTime())
                .signWith(SignatureAlgorithm.HS256, TokenConstraints.JWT_SECRET)
                .compact();
    }

    /**
     * 验证token是否过期
     *
     * @param token 传入token信息
     * @return 返回是否过期，过期为false，没过期为true
     */
    public static boolean verify(String token) {
        return decode(token).getExpiration().before(new Date());
    }
}
