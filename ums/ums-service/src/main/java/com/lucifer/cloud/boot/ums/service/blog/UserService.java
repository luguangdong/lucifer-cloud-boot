package com.lucifer.cloud.boot.ums.service.blog;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucifer.cloud.boot.ums.entity.blog.bo.Article;
import com.lucifer.cloud.boot.ums.entity.blog.bo.User;
import com.lucifer.cloud.boot.ums.entity.blog.dto.user.UserInfoDto;

/**
 * @author lucifer
 * @date 2023/12/21 15:23
 */
public interface UserService extends IService<User> {
    UserInfoDto userInfo(Long _t);
}
