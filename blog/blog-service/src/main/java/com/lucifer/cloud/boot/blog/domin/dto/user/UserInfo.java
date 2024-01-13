package com.lucifer.cloud.boot.blog.domin.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author lucifer
 * @date 2023/12/21 16:35
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo {
    private String id;
    private String uid;
    private String username;
    private Integer gender;
    private String avatar_url;
    private Integer age;
    private String email;
    private String address;
    private Long tel;
    private Long qq;
    private String wechat;
    private String git_hub;
    private String background_image;
    private String motto;
    private String role;
}
