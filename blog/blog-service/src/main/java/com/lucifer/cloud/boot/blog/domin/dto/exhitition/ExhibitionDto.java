package com.lucifer.cloud.boot.blog.domin.dto.exhitition;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author lucifer
 * @date 2023/12/27 11:18
 */
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExhibitionDto {

    private Long count;

    private List<ExhibitionUser> exhibitions;

    // 审核中
    private Long in_review;

    // 审核通过
    private Long approved;

    // 未通过审核
    private Long review_rjection;

    // 公开
    private Long open;

    private List<String> likes_ids;

    private List<String> star_ids;

}
