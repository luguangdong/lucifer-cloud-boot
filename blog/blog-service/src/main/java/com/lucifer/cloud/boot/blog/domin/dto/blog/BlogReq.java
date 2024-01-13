package com.lucifer.cloud.boot.blog.domin.dto.blog;

import lombok.Data;

/**
 * @author lucifer
 * @date 2023/12/24 14:18
 */
@Data
public class BlogReq {

    private String uid;

    private String title;

    private String sub_title;

    private String content;

    private String cover;

    private String tags;

    /**
     * 博客背景图片ID
     */
    private String file_id;
}
