package com.lucifer.cloud.boot.blog.domin.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

/**
 * @author lucifer
 * @date 2023/12/28 10:56
 */
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminStatDto {

    private Integer article_count;

    private Integer blog_count;

    private Integer image_count;

    private Integer image_download_count;
    private Integer user_count;


}
