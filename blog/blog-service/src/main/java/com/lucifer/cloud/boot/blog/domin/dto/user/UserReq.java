package com.lucifer.cloud.boot.blog.domin.dto.user;

import lombok.Data;

/**
 * @author lucifer
 * @date 2023/12/23 23:37
 */
@Data
public class UserReq {

    private String uid;

    private String username;

    private String avatar_url;

    private Long tel;

    private String motto;

    private String wechat;

    private String qq;

    private String git_hub;

    private Integer gender;

}
