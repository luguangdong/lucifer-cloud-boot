package com.lucifer.cloud.boot.blog.domin.dto.tag;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author lucifer
 * @date 2023/12/27 13:52
 */
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TagDto {

    private List<TagInfo> tags_info;


}
