package com.lucifer.cloud.boot.blog.domin.dto.exhitition;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserInfo;
import lombok.Data;

/**
 * @author lucifer
 * @date 2023/12/27 11:36
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExhibitionUser {

    /**
     * 图片墙标题
     */
    private String title;

    /**
     * 图片墙副标题
     */
    private String sub_title;

    /**
     * 图片描述
     */
    private String des;

    /**
     * 图片上传路径
     */
    private String cover;

    /**
     * 图片标签
     */
    private String tags;

    /**
     * 点赞数
     */
    private Integer thumbs_up;


    /**
     * 下载数
     */
    private Integer download;




    /**
     * 图片状态 1待审核 2审核通过 3未通过审核 4 公开˚
     */
    private Integer status;

    /**
     * 评状态为3时 驳回原因
     */
    private String reject_res;


    /**
     *  图片大小
     */
    private Integer size;

    /**
     * 图片宽高
     */
    private String px;

    /**
     * 图片类型
     */
    private String type;

    /**
     * 图片主题色
     */
    private String rgb;

    /**
     * 调色板
     */
    private String palette;


    private Long updated;

    private String uid;

    /**
     * 该图片上传者 id
     */
    private String user_id;

    private UserInfo user_info;
}
