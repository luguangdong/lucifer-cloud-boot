package com.lucifer.cloud.boot.ums.entity.blog.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author lucifer
 * @date 2023/12/21 16:35
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Detail {
    private Integer publish;
    private Integer likes;
    private Integer follows;
    private Integer thumbs_up;
}
