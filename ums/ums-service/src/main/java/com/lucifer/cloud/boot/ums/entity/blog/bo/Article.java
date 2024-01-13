package com.lucifer.cloud.boot.ums.entity.blog.bo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author lucifer
 * @date 2023/12/21 15:05
 * 博客系统-文章表
 */
@Data
@JsonSerialize
@TableName("article")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Article extends BaseBo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章副标题
     */
    private String sub_title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 背景图片
     */
    private String cover;

    /**
     * 背景图片
     */
    private String images;

    /**
     * 背景图片
     */
    private Long user_id;

    /**
     * 文章标签
     */
    private String tag;

    /**
     * 点赞数
     */
    private Integer thumbs_up;

    /**
     * 评论数
     */
    private Integer comment;



}
