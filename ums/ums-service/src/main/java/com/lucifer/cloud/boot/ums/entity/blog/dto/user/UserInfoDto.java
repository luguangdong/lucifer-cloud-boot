package com.lucifer.cloud.boot.ums.entity.blog.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author lucifer
 * @date 2023/12/21 16:24
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoDto {

    private Info user_info;

    private Detail user_detail;
}
