package com.lucifer.cloud.boot.blog.domin.dto.article;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author lucifer
 * @date 2024/1/12 16:47
 */
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleDto {

    private List<ArticleInfo> article_info;

    private CardInfo card_info;

    private Long count;
}
