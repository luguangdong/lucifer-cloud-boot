package com.lucifer.cloud.boot.blog.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucifer.cloud.boot.blog.domin.bo.User;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserInfoDto;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserReq;
import jakarta.servlet.http.HttpServletRequest;
/**
 * @author lucifer
 * @date 2023/12/21 15:23
 */
public interface UserService extends IService<User> {

    UserInfoDto userInfo(HttpServletRequest request, Long _t);


    Boolean userUpdate(UserReq userReq);


    Boolean passwordUpdate(HttpServletRequest request,String password);
}
