package com.lucifer.cloud.boot.blog.domin.dto.common;
import com.alibaba.nacos.shaded.com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author lucifer
 * @date 2024/1/10 17:32
 */
public class CommonConverter {

    public static Map convertSort(String sort,Class clas){
        if(StringUtils.isBlank(sort)){
            return Maps.newHashMap();
        }
        if(sort.contains(" ")){
            String[] split = sort.split(" ");
            String column = split[0];
            Field field = null;
            try {
                field = clas.getField(column);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }

            Map<String,Object> map = Maps.newHashMap();
            map.put("columns",field);
            map.put("isAsc", "asc".equals(split[1])? true:false);
            return map;
        }else {
            return Maps.newHashMap();
        }
    }
}
