package com.lucifer.cloud.boot.ums.entity.blog.dto.user;

import com.lucifer.cloud.boot.ums.entity.blog.bo.User;

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
}
