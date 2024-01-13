package com.lucifer.cloud.boot.blog.domin.dto.tag;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

/**
 * @author lucifer
 * @date 2023/12/27 14:22
 */
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TagInfo {
    private String name;

    private String type;

    private String uid;
}
