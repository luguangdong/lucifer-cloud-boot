package com.lucifer.cloud.boot.ums.entity.blog.dto.blog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author lucifer
 * @date 2023/12/24 13:39
 */
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlogInfoDto {

    private Integer count;

    private List<BlogInfo> blog_info;

}
