package com.lucifer.cloud.boot.blog.util;
import com.lucifer.cloud.auth.api.UserApi;
import com.lucifer.cloud.auth.model.response.Oauth2UserinfoResult;
import com.lucifer.cloud.boot.blog.domin.bo.User;
import com.lucifer.cloud.boot.blog.mapper.UserMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.util.Enumeration;

/**
 * @author lucifer
 * @date 2023/12/23 22:46
 */
@Component
public class UserSystem {

    @DubboReference(version = "1.0.1")
    private UserApi userApi;

    @Resource
    private UserMapper userMapper;

    public  String token(HttpServletRequest request){
        Enumeration<String> authorizations = request.getHeaders("Authorization");
        String token = null;
        while (authorizations.hasMoreElements()) {
            String element  = authorizations.nextElement();
            String[] split = element.split("Bearer ");
            token = split[1];
        }
        return token;
    }


    public Long userId(HttpServletRequest request){
        String token = token(request);
        Oauth2UserinfoResult loginUserInfo = userApi.getLoginUserInfo(token);
        Integer userId = loginUserInfo.getId();
        return userId.longValue();
    }


    public User user(HttpServletRequest request){
        String token = token(request);
        Oauth2UserinfoResult loginUserInfo = userApi.getLoginUserInfo(token);
        Integer userId = loginUserInfo.getId();
        return userMapper.selectById(userId);
    }



}
