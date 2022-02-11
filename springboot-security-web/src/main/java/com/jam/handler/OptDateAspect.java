package com.jam.handler;

import com.jam.anno.OptDate;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @program: SpringCloudStudy
 * @description: 注解实现类
 * @author: Mr.Pu
 * @create: 2022-01-26 00:09
 **/
@Slf4j
@Aspect
@Component
public class OptDateAspect {

    @Pointcut("@annotation(com.jam.anno.OptDate)")
    public void OptDatePointCut(){
    }

    @Async
//    @Transactional(rollbackFor = Exception.class)
    @AfterReturning(value = "OptDatePointCut()" , returning = "key")
    public void optDate(JoinPoint joinPoint, Object key) throws Exception{
//        String fileName = joinPoint.getSourceLocation().getFileName();
//        System.out.println(fileName);
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String name = signature.getMethod().getName();
        log.info("使用注解的方法为{}",name);
        System.out.println(name);
        String name1 = signature.getReturnType().getName();
        log.info("使用注解方法的返回类型为{}",name1);
        System.out.println(name1);
        String name2 = signature.getMethod().getClass().getName();
        log.info("使用注解方法的类名为{}",name2);
        System.out.println(name2);
        String name3 = signature.getMethod().getDeclaringClass().getName();
        log.info("使用注解方法的声明类名为{}",name3);
        System.out.println(name3);
        OptDate annotation = method.getAnnotation(OptDate.class);
        log.info("获取到注解中的方法{}和参数{}",annotation.toString(),annotation.value());
        Integer timestamp = annotation.value().getTimestamp();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (Objects.isNull(annotation.date())){
            long time = format.parse(annotation.date()).getTime();
            System.out.println(time);
            key = new Date(time + timestamp.longValue());
            System.out.println(format.format(key));
        }else {
            long time = System.currentTimeMillis();
            System.out.println(time);
            key = new Date(Long.sum(time,timestamp.longValue()*1000));

            System.out.println(format.format(key));
            System.out.println(Long.sum(time,timestamp.longValue()));
//            System.out.println();
        }
    }
}
