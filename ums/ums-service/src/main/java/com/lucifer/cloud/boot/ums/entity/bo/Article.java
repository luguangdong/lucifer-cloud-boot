package com.lucifer.cloud.boot.ums.entity.bo;

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
@TableName("article")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Article implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long uid;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;


    /**
     * 删除时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime deletedAt;

    /**
     * 创建时间
     */
    private Long created;

    /**
     * 修改时间
     */
    private Long updated;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章副标题
     */
    private String subTitle;

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
    private Integer userId;

    /**
     * 文章标签
     */
    private String tag;

    /**
     * 点赞数
     */
    private Integer thumbsUp;

    /**
     * 评论数
     */
    private Integer comment;



}
