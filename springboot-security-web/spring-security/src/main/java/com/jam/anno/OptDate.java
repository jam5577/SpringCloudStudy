package com.jam.anno;

import com.jam.adapter.enums.OptDateEnum;

import java.lang.annotation.*;

/**
 * 自定义注解，使用在方法上，可以拦截请求api的方法并打印输出
 * 注解传入参数为枚举类，0、3、5、7可供选择，将打印输出从现在时刻起对应时间后的标准时间
 *
 * @author Jam
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface OptDate {

    OptDateEnum value() default OptDateEnum.NONE;

    String date() default "";

}
