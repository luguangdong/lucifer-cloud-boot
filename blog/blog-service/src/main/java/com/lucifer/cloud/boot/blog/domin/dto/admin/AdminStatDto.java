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

    private Long article_count;

    private Long blog_count;

    private Long image_count;

    private Long image_download_count;
    private Long user_count;


}
