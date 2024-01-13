package com.lucifer.cloud.boot.ums.entity.blog.dto.blog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lucifer.cloud.boot.ums.entity.blog.dto.user.UserInfo;
import lombok.Data;

/**
 * @author lucifer
 * @date 2023/12/24 13:43
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlogInfo {
    private String uid;
    private String created;
    private String updated;

    private String title;

    private String sub_title;

    private String content;

    private String cover;

    /**
     * 点赞数
     */
    private Integer thumbs_up;

    /**
     * 收藏数
     */
    private Integer collection;


    /**
     * 博客作者
     */
    private String user_id;

    /**
     * 博客标签
     */
    private String tag;


    private UserInfo user_info;
}
