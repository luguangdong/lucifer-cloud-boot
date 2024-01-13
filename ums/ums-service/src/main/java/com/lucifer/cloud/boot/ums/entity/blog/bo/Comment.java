package com.lucifer.cloud.boot.ums.entity.blog.bo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author lucifer
 * @date 2023/12/23 21:04
 * 博客系统-评论表
 */
@Data
@JsonSerialize
@TableName("comment")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment extends BaseBo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * image blog article
     */
    private String type;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 被评论 id
     */
    private Long content_id;

    /**
     * 评论者 id
     */
    private Long user_id;


    /**
     * 该条评论 点赞数
     */
    private Integer thumbs_up;
}
