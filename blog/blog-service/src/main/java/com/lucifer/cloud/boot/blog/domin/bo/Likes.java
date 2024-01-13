package com.lucifer.cloud.boot.blog.domin.bo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author lucifer
 * @date 2023/12/23 20:06
 * 博客系统-收藏表
 */
@Data
@JsonSerialize
@TableName("likes")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Likes  extends BaseBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 收藏者 id
     */
    private Long user_id;

    /**
     *  收藏图片或博客的 uid
     */
    private Long likes_id;

    /**
     * true 收藏 false 取消收藏
     */
    private Boolean likes_type;

    /**
     * 1 图片 2 博客
     */
    private Integer type;

}
