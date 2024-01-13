package com.lucifer.cloud.boot.blog.util.oss;

import com.lucifer.cloud.boot.blog.config.OSSUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @author lucifer
 * @date 2024/1/4 15:05
 */
@Slf4j
@Aspect
@Component
public class OssSignedUrlAspect {

    @Resource
    private OSSUtil ossUtil;

    @Pointcut("@annotation(com.lucifer.cloud.boot.blog.util.oss.OssSignedUrl)")
    public void ossSignedUrl() {}

    @Around("ossSignedUrl()")
    public Object around(ProceedingJoinPoint joinPoint) throws Exception {
        try {
            Object proceed = joinPoint.proceed();
            Field[] fields = proceed.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Signed.class)){
                    if (!field.isAccessible()){
                        field.setAccessible(true);
                    }
                    String url = (String)field.get(proceed);
                    String signedUrl = ossUtil.generateSignedUrl(url);
                    field.set(proceed, signedUrl);
                }
            }
            return proceed;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
