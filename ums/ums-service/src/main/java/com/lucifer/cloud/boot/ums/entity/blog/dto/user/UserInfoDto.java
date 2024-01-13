package com.lucifer.cloud.boot.ums.entity.blog.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

/**
 * @author lucifer
 * @date 2023/12/21 16:24
 */
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoDto {

    private UserInfo user_info;

    private UserDetail user_detail;
}
