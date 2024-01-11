package com.lucifer.cloud.boot.blog.domin.dto.exhitition;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author lucifer
 * @date 2023/12/27 11:36
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExhibitionUserDetail {

    /**
     * 图片墙标题
     */
    private String title;

    /**
     * 图片墙副标题
     */
    private String sub_title;


    /**
     * 图片上传路径
     */
    private String cover;

    /**
     * 点赞数
     */
    private Integer thumbs_up;

    /**
     * 下载数
     */
    private Integer download;


    private Long created;

    private String uid;

}
