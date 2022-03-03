package com.jam.utils;

import org.springframework.util.DigestUtils;

/**
 * @program: SpringCloudStudy
 * @description: MD5加密
 * @author: Mr.Pu
 * @create: 2022-02-24 20:52
 **/

public class MD5Utils {

    public static final String salt = "jam5577";

    public static String getMd5(String src){
        return DigestUtils.md5DigestAsHex(src.getBytes());
    }

    public static String encryptInput(String input){
        String src = salt.charAt(2)+salt.charAt(6)+salt.charAt(1)+input;
        return getMd5(src);
    }

    public static String encryptOutput(String output){
        String src = salt.charAt(3)+salt.charAt(4)+output+salt.charAt(1);
        return getMd5(src);
    }

    public static String toDBPass(String inputPass){
        String encryptInput = encryptInput(inputPass);
        return encryptOutput(encryptInput);
    }
}
