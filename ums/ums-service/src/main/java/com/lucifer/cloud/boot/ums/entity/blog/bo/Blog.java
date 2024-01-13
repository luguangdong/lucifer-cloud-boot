package com.lucifer.cloud.boot.ums.entity.blog.bo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author lucifer
 * @date 2023/12/23 21:05
 * 博客系统-博客表
 */
@Data
@JsonSerialize
@TableName("blog")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Blog extends BaseBo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 博客标题
     */
    private String title;

    /**
     * 博客副标题
     */
    private String sub_title;

    /**
     * 博客内容
     */
    private String content;

    /**
     * 背景图片
     */
    private String cover;


    /**
     * 博客作者
     */
    private Long user_id;

    /**
     * 博客标签
     */
    private String tag;

    /**
     * 点赞数
     */
    private Integer thumbs_up;


    /**
     * 点赞id集合
     */
    private String thumbs_up_list;

    /**
     * 收藏数
     */
    private Integer collection;

    /**
     * 评论数
     */
    private Integer comment;



}
