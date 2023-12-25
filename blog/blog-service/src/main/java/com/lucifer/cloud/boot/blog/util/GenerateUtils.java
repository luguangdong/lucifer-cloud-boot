package com.lucifer.cloud.boot.blog.util;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ReflectUtil;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

/**
 * @author lucifer
 * @date 2023/12/24 15:13
 */
public class GenerateUtils {
    public static <T> T generateFiledValue(Object obj) {
        long id = IdUtil.getSnowflakeNextId();
        long uid = IdUtil.getSnowflakeNextId();
        LocalDateTime now = LocalDateTime.now();
        long currentTimeMillis = System.currentTimeMillis();
        LocalDateTime created_at = now;
        LocalDateTime updated_at = now;
        Long created = currentTimeMillis;
        Long updated = currentTimeMillis;
        Class<?> aClass = obj.getClass();
        Field id_field = ReflectUtil.getField(aClass, "id");
        id_field.setAccessible(true);
        Field uid_field = ReflectUtil.getField(aClass, "uid");
        uid_field.setAccessible(true);
        Field created_at_field = ReflectUtil.getField(aClass, "created_at");
        created_at_field.setAccessible(true);
        Field updated_at_field = ReflectUtil.getField(aClass, "updated_at");
        updated_at_field.setAccessible(true);
        Field created_field = ReflectUtil.getField(aClass, "created");
        created_field.setAccessible(true);
        Field updated_field = ReflectUtil.getField(aClass, "updated");
        updated_field.setAccessible(true);
        try {
            id_field.set(obj,id);
            uid_field.set(obj,uid);
            created_at_field.set(obj,created_at);
            updated_at_field.set(obj,updated_at);
            created_field.set(obj,created);
            updated_field.set(obj,updated);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return (T) obj;
    }
}
