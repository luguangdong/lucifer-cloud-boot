package com.lucifer.cloud.boot.blog.domin.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lucifer.cloud.boot.blog.domin.dto.exhitition.ExhibitionUser;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author lucifer
 * @date 2023/12/28 10:53
 */
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminExhibitionDto {

    private Long count;

    private List<ExhibitionUser> exhibitions;
}
