package com.lucifer.cloud.boot.blog.domin.bo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author lucifer
 * @date 2023/12/21 15:05
 * 博客系统-用户表
 */
@Data
@JsonSerialize
@TableName("user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends BaseBo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 第三方登录唯一ID
     */
    private String uniqueId;


    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 生日
     */
    private Long birthday;

    /**
     * 住址
     */
    private String address;

    /**
     * 电话
     */
    private Long tel;

    /**
     * 邮箱
     */
    private String email;

    /**
     * qq
     */
    private Long qq;

    /**
     * 微信
     */
    private String wechat;


    /**
     * gitHub
     */
    private String git_hub;

    /**
     * 头像图片
     */
    private String avatar_url;


    /**
     * 背景图片
     */
    private String background_image;


    /**
     * 座右铭
     */
    private String motto;

    /**
     * 权限
     */
    private String role;



}
