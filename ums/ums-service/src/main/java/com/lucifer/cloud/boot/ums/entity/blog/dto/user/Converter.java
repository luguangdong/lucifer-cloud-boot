package com.lucifer.cloud.boot.ums.entity.blog.dto.user;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONObject;
import com.lucifer.cloud.boot.ums.entity.blog.bo.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author lucifer
 * @date 2023/12/21 16:53
 */
public class Converter {

    public static Info convertInfo(User user){
        Info info = new Info();
        info.setId(user.getId());
        info.setUid(user.getUid());
        info.setUsername(user.getUsername());
        info.setGender(user.getGender());
        info.setAvatar_url(user.getAvatar_url());
        info.setAge(user.getAge());
        info.setEmail(user.getEmail());
        info.setTel(user.getTel());
        info.setQq(user.getQq());
        info.setWechat(user.getWechat());
        info.setGit_hub(user.getGit_hub());
        info.setBackground_image(user.getBackground_image());
        info.setMotto(user.getMotto());
        info.setRole(user.getRole());
        return info;
    }


    public static User convertJson2User(JSONObject jsonUser){
        User user = new User();
        Integer inId = (Integer) jsonUser.get("id");
        Long id = inId.longValue();
        user.setId(id);
        user.setUid(IdUtil.getSnowflakeNextId());
        user.setUsername((String) jsonUser.get("name"));
        String strMobile = (String) jsonUser.get("mobile");
        long mobile = Long.parseLong(strMobile);
        user.setTel(mobile);
        user.setEmail((String) jsonUser.get("email"));
        Long createTime = (Long) jsonUser.get("createTime");
        Long updateTime = (Long) jsonUser.get("updateTime");
        user.setCreated(createTime);
        user.setUpdated(updateTime);
        LocalDateTime created_at = LocalDateTime.ofInstant(Instant.ofEpochMilli(createTime), ZoneId.systemDefault());
        LocalDateTime updated_at = LocalDateTime.ofInstant(Instant.ofEpochMilli(updateTime), ZoneId.systemDefault());
        user.setCreated_at(created_at);
        user.setUpdated_at(updated_at);
        return user;
    }
}
