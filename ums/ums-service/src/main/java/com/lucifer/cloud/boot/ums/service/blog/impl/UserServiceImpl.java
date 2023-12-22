package com.lucifer.cloud.boot.ums.service.blog.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucifer.cloud.auth.api.UserApi;
import com.lucifer.cloud.auth.model.response.Oauth2UserinfoResult;
import com.lucifer.cloud.boot.ums.entity.blog.bo.User;
import com.lucifer.cloud.boot.ums.entity.blog.dto.user.Converter;
import com.lucifer.cloud.boot.ums.entity.blog.dto.user.Info;
import com.lucifer.cloud.boot.ums.entity.blog.dto.user.UserInfoDto;
import com.lucifer.cloud.boot.ums.mapper.blog.UserMapper;
import com.lucifer.cloud.boot.ums.service.blog.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author lucifer
 * @date 2023/12/21 15:24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @DubboReference(version = "1.0.1")
    private UserApi userApi;

    @Override
    public UserInfoDto userInfo(RequestEntity request, Long _t) {
        HttpHeaders headers = request.getHeaders();
        String token = null;
        if(Objects.nonNull(headers.get("Authorization"))){
            String authorization = headers.get("Authorization").get(0);
            String[] split = authorization.split("Bearer ");
            token = split[1];
        }

        Oauth2UserinfoResult loginUserInfo = userApi.getLoginUserInfo();

        Long id = 123L;
        User user = getById(id);
        Info user_info = Converter.convertInfo(user);
        UserInfoDto userInfoDto = UserInfoDto.builder().user_info(user_info).build();
        return userInfoDto;
    }


}
