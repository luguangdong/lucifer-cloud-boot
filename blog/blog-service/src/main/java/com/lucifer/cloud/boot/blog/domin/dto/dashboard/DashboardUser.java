package com.lucifer.cloud.boot.blog.domin.dto.dashboard;

import com.lucifer.cloud.boot.blog.domin.dto.user.UserInfo;
import lombok.Data;

/**
 * @author lucifer
 * @date 2023/12/27 15:13
 */
@Data
public class DashboardUser {

    private Integer following;

    private Integer like;

    private Integer publish;

    private Integer thumbs_up;


    private UserInfo dashboard_user;

}
