package com.lucifer.cloud.boot.blog.domin.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lucifer.cloud.boot.blog.domin.dto.exhitition.ExhibitionUser;
import com.lucifer.cloud.boot.blog.domin.dto.exhitition.ExhibitionUserDetail;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author lucifer
 * @date 2024/1/11 10:29
 */
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetailDto {

    private Long count;

    private Long download;

    private List<ExhibitionUserDetail> exhibition;

    private Long follow;

    private List<String> follow_ids;

    private Long like;

    private Long star;

    private Long upload;

    private UserInfo user_info;

}
