package com.lucifer.cloud.boot.blog.domin.bo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author lucifer
 * @date 2023/12/23 21:04
 * 博客系统-文件上传表
 */
@Data
@JsonSerialize
@TableName("upload")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Upload extends BaseBo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 文件哈希
     */
    private String hash;

    /**
     * 文件名称
     */
    private String file_name;

    /**
     * 文件路径
     */
    private String file_path;

    /**
     * 文件后缀
     */
    private String ext;


    /**
     * 文件大小
     */
    private Long size;


    /**
     * 用户id
     */
    private Long user_id;


    /**
     * 使用这个图片的博客ID
     */
    private Long blog_id;
}
