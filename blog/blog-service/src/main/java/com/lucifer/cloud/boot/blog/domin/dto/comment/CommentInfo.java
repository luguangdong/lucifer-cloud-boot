package com.lucifer.cloud.boot.blog.domin.dto.comment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserInfo;
import lombok.Data;

/**
 * @author lucifer
 * @date 2024/1/13 9:51
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentInfo {

    private String content;

    private Long created;

    private Long thumbs_up;

    private String uid;

    private String user_id;

    private UserInfo user_info;
}
