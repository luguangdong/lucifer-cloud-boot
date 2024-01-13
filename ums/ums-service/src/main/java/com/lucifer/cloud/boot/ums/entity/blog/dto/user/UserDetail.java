package com.lucifer.cloud.boot.ums.entity.blog.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author lucifer
 * @date 2023/12/21 16:35
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetail {

    /**
     * 发布数
     */
    private Integer publish;

    /**
     * 收藏数
     */
    private Integer likes;

    /**
     * 粉丝数
     */
    private Integer follows;

    /**
     * 获赞数
     */
    private Integer thumbs_up;
}
