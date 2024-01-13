package com.lucifer.cloud.boot.blog.domin.dto.article;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserInfo;
import lombok.Data;

/**
 * @author lucifer
 * @date 2024/1/12 16:47
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleInfo {

    /**
     * 评论数
     */
    private Integer comment;


    /**
     * 文章内容
     */
    private String content;

    /**
     * 背景图片
     */
    private String cover;

    private Long created;

    /**
     * 背景图片
     */
    private String images;

    /**
     * 文章副标题
     */
    private String sub_title;

    /**
     * 文章标签
     */
    private String tags;

    /**
     * 点赞数
     */
    private Integer thumbs_up;

    /**
     * 文章标题
     */
    private String title;

    private String uid;

    private Long updated;

    /**
     * 用户ID
     */
    private String user_id;


    private UserInfo user_info;

}
