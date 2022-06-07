package com.jam.java.feature.anno;

import java.lang.annotation.*;

@Repeatable(MyAnnotations.class) //指定容器类，该注解可以指明此注解可以重复使用多次
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String value() default "Java 8";
}
