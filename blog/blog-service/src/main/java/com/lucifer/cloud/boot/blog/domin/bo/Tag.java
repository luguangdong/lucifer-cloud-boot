package com.lucifer.cloud.boot.blog.domin.bo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author lucifer
 * @date 2023/12/23 21:03
 * 博客系统-标签表
 */
@Data
@JsonSerialize
@TableName("tag")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tag extends BaseBo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 标签名称
     */
    private String name;

    /**
     * 标签类型  image blog article
     */
    private String type;

    /**
     * 创建该标签用户
     */
    private Long user_id;



}
