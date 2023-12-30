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

    private Integer approved;

    private Long count;

    private List<ExhibitionUser> exhibitions;

    private Integer in_review;

    private List<String> likes_ids;

    private Integer open;

    private Integer review_rjection;

    private List<String> star_ids;

}
