package com.lucifer.cloud.boot.ums.entity.blog.bo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author lucifer
 * @date 2023/12/23 20:22
 * 博客系统-关注表
 */

@Data
@JsonSerialize
@TableName("follow")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Follow extends BaseBo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 关注者 id
     */
    private Long user_id;

    /**
     * 被关注者 id
     */
    private Long follow_user_id;

    /**
     * true 关注 false 取消关注
     */
    private Boolean likes_type;

}
