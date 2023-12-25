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
 * 博客系统-公告表
 */
@Data
@JsonSerialize
@TableName("notice")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Notice extends BaseBo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long user_id;


    /**
     * 公告内容
     */
    private String content;
}
