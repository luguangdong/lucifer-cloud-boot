package com.lucifer.cloud.boot.ums.service.blog;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucifer.cloud.boot.ums.entity.blog.bo.User;
import com.lucifer.cloud.boot.ums.entity.blog.dto.user.UserInfoDto;
import com.lucifer.cloud.boot.ums.entity.blog.dto.user.UserReq;
import org.springframework.http.RequestEntity;

/**
 * @author lucifer
 * @date 2023/12/21 15:23
 */
public interface UserService extends IService<User> {

    UserInfoDto userInfo(RequestEntity request, Long _t);


    Boolean userUpdate(UserReq userReq);
}
