package com.lucifer.cloud.boot.blog.domin.dto.notice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author lucifer
 * @date 2023/12/28 9:47
 */
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class NoticeDto {

    private Long count;

    private List<NoticeInfo> infos;
}
