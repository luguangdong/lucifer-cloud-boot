package com.lucifer.cloud.boot.blog.domin.bo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author lucifer
 * @date 2023/12/23 21:04
 * 博客系统-点赞表
 */
@Data
@JsonSerialize
@TableName("star")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Star extends BaseBo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 点赞者 id
     */
    private Long user_id;

    /**
     *  收藏图片或博客的 uid
     */
    private Long star_id;

    /**
     *  0 取消star 1 star
     */
    private Boolean star_type;

    /**
     * 1 图片 2 博客
     */
    private Integer type;



}
