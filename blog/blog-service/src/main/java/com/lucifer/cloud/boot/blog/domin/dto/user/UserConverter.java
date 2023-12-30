package com.lucifer.cloud.boot.blog.domin.dto.user;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONObject;
import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.lucifer.cloud.boot.blog.domin.bo.Blog;
import com.lucifer.cloud.boot.blog.domin.bo.Follow;
import com.lucifer.cloud.boot.blog.domin.bo.User;
import org.apache.commons.lang3.StringUtils;

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
        if(Objects.nonNull(jsonUser.get("uniqueId"))){
            String uniqueId = (String) jsonUser.get("uniqueId");
            user.setUniqueId(uniqueId);
        }
        user.setUsername((String) jsonUser.get("name"));
        if(Objects.nonNull(jsonUser.get("mobile"))){
            String strMobile = (String) jsonUser.get("mobile");
            long mobile = Long.parseLong(strMobile);
            user.setTel(mobile);
        }
        if(Objects.nonNull(jsonUser.get("email"))){
            user.setEmail((String) jsonUser.get("email"));
        }
        if(Objects.nonNull(jsonUser.get("createTime"))){
            Long createTime = (Long) jsonUser.get("createTime");
            user.setCreated(createTime);
            LocalDateTime created_at = LocalDateTime.ofInstant(Instant.ofEpochMilli(createTime), ZoneId.systemDefault());
            user.setCreated_at(created_at);
        }
        if(Objects.nonNull(jsonUser.get("updateTime"))){
            Long updateTime = (Long) jsonUser.get("updateTime");
            user.setUpdated(updateTime);
            LocalDateTime updated_at = LocalDateTime.ofInstant(Instant.ofEpochMilli(updateTime), ZoneId.systemDefault());
            user.setUpdated_at(updated_at);
        }
        user.setAvatar_url("0/avatar/avatar.jpg");
        user.setBackground_image("0/images/background_image.jpg");
        return user;
    }


    public static LambdaUpdateWrapper<User> convertUpdateWrapper(UserReq userReq){
        LambdaUpdateWrapper<User> update = new LambdaUpdateWrapper<>();
        update.eq(User::getUid, userReq.getUid());
        if(StringUtils.isNotBlank(userReq.getUsername())){
            update.set(User::getUsername,userReq.getUsername());
        }
        if(StringUtils.isNotBlank(userReq.getAvatar_url())){
            update.set(User::getAvatar_url,userReq.getAvatar_url());
        }
        if(StringUtils.isNotBlank(userReq.getBackground_image())){
            update.set(User::getBackground_image,userReq.getBackground_image());
        }
        if(Objects.nonNull(userReq.getTel())){
            update.set(User::getTel,userReq.getTel());
        }
        if(StringUtils.isNotBlank(userReq.getMotto())){
            update.set(User::getMotto,userReq.getMotto());
        }
        if(StringUtils.isNotBlank(userReq.getWechat())){
            update.set(User::getWechat,userReq.getWechat());
        }
        if(StringUtils.isNotBlank(userReq.getQq())){
            update.set(User::getQq,userReq.getQq());
        }
        if(StringUtils.isNotBlank(userReq.getGit_hub())){
            update.set(User::getGit_hub,userReq.getGit_hub());
        }
        if(Objects.nonNull(userReq.getGender())){
            update.set(User::getGender,userReq.getGender());
        }
        if(StringUtils.isNotBlank(userReq.getGit_hub())){
            update.set(User::getGit_hub,userReq.getGit_hub());
        }
        return update;
    }
}
