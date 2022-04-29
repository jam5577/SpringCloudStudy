package com.jam.adapter.constants;

/**
 * @program: SpringCloudStudy
 * @description: token相关的静态字段
 * @author: Mr.Pu
 * @create: 2022-04-29 21:31
 **/

public final class TokenConstraints {

    public static final String JWT_SECRET = "jam";

    public static final String ALGORITHM = "HmacSHA256";

    public static final String JWT_HEADER = "header";

    public static final String USERNAME = "username";

    public static final Integer EXPIRE_TIME = 7;
}
