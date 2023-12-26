package com.lucifer.cloud.boot.blog.domin.dto.user;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONObject;
import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.lucifer.cloud.boot.blog.domin.bo.Blog;
import com.lucifer.cloud.boot.blog.domin.bo.Follow;
import com.lucifer.cloud.boot.blog.domin.bo.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author lucifer
 * @date 2023/12/21 16:53
 */
public class UserConverter {

    public static UserInfo convertInfo(User user){
        UserInfo info = new UserInfo();
        if(Objects.nonNull(user)){
            info.setId(String.valueOf(user.getId()));
            info.setUid(String.valueOf(user.getUid()));
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
        }
        return info;
    }


    public static UserDetail convertBlog2Detail(List<Blog> blogList, List<Follow> followList){
        UserDetail detail = new UserDetail();
        int publish = blogList.size();
        int likes = Optional.ofNullable(blogList).orElse(Lists.newArrayList())
                .stream().mapToInt(Blog::getCollection).sum();
        int thumbs_up = Optional.ofNullable(blogList).orElse(Lists.newArrayList())
                .stream().mapToInt(Blog::getThumbs_up).sum();
        int follows = followList.size();
        detail.setPublish(publish);
        detail.setLikes(likes);
        detail.setFollows(follows);
        detail.setThumbs_up(thumbs_up);
        return detail;
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
