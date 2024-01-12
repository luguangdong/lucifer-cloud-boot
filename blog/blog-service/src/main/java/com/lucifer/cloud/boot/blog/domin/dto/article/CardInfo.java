package com.lucifer.cloud.boot.blog.domin.dto.article;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * @author lucifer
 * @date 2024/1/12 16:47
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardInfo {
    private Long article;

    private Long comment;

    private Long fans;

    private List<String> follow_ids;

    private List<String> star_ids;

    private Long thumb;


}
