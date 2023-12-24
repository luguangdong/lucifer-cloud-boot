package com.lucifer.cloud.boot.ums.entity.blog.dto.blog;

import lombok.Data;

/**
 * @author lucifer
 * @date 2023/12/24 14:18
 */
@Data
public class BlogReq {

    private String title;

    private String sub_title;

    private String content;

    private String cover;

    private String tag;
}
