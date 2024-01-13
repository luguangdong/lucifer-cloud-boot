package com.lucifer.cloud.boot.blog.domin.dto.comment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author lucifer
 * @date 2024/1/13 9:51
 */
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentDto {

    private Long count;

    private List<CommentInfo> infos;
}
