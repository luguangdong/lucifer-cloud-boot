package com.lucifer.cloud.boot.ums.entity.blog.bo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lucifer
 * @date 2023/12/21 15:05
 */
@Data
@JsonSerialize
@TableName("user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends BaseBo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 文章标题
     */
    private String username;

    /**
     * 文章副标题
     */
    private String password;

    /**
     * 文章内容
     */
    private Integer gender;

    /**
     * 背景图片
     */
    private Integer age;

    /**
     * 背景图片
     */
    private Long birthday;

    /**
     * 背景图片
     */
    private String address;

    /**
     * 文章标签
     */
    private Long tel;

    /**
     * 点赞数
     */
    private String email;

    /**
     * 评论数
     */
    private Integer qq;

    /**
     * 评论数
     */
    private String wechat;


    /**
     * 评论数
     */
    private String git_hub;

    /**
     * 评论数
     */
    private String avatar_url;


    /**
     * 评论数
     */
    private String background_image;


    /**
     * 评论数
     */
    private String motto;

    /**
     * 评论数
     */
    private String role;



}
