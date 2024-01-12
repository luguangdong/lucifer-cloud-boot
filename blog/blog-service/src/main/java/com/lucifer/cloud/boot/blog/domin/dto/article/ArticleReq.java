package com.lucifer.cloud.boot.blog.domin.dto.article;

import lombok.Data;

/**
 * @author lucifer
 * @date 2023/12/24 14:18
 */
@Data
public class ArticleReq {

    private String title;

    private String sub_title;

    private String cover;

    private String content;

    private String tag;

    /**
     * 博客背景图片ID
     */
    private String images;
}
