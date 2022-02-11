package com.jam.handler;

import com.jam.anno.OptDate;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.logging.SimpleFormatter;

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
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        OptDate annotation = method.getAnnotation(OptDate.class);
        log.info("获取到注解中的方法{}和参数{}",annotation.toString(),annotation.value());
        Integer timestamp = annotation.value().getTimestamp();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!Objects.isNull(annotation.date())){
            long time = format.parse(annotation.date()).getTime();
            key = new Date(time + timestamp);
            System.out.println(key);
        }else {
            Long time = System.currentTimeMillis() / 1000;
            key =new Date(time+timestamp);
            System.out.println(key);
        }
    }
}
