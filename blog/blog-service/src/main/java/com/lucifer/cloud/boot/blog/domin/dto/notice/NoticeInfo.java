package com.lucifer.cloud.boot.blog.domin.dto.notice;

import lombok.Data;

/**
 * @author lucifer
 * @date 2023/12/28 10:12
 */
@Data
public class NoticeInfo {

    private String uid;

    /**
     * 用户ID
     */
    private String user_id;


    /**
     * 公告内容
     */
    private String content;
}
