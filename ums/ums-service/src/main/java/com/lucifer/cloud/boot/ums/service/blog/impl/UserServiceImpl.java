package com.lucifer.cloud.boot.ums.service.blog.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucifer.cloud.auth.api.UserApi;
import com.lucifer.cloud.auth.model.response.Oauth2UserinfoResult;
import com.lucifer.cloud.boot.ums.entity.blog.bo.Blog;
import com.lucifer.cloud.boot.ums.entity.blog.bo.Follow;
import com.lucifer.cloud.boot.ums.entity.blog.bo.User;
import com.lucifer.cloud.boot.ums.entity.blog.dto.user.UserConverter;
import com.lucifer.cloud.boot.ums.entity.blog.dto.user.UserDetail;
import com.lucifer.cloud.boot.ums.entity.blog.dto.user.UserInfo;
import com.lucifer.cloud.boot.ums.entity.blog.dto.user.UserInfoDto;
import com.lucifer.cloud.boot.ums.entity.blog.dto.user.UserReq;
import com.lucifer.cloud.boot.ums.mapper.blog.BlogMapper;
import com.lucifer.cloud.boot.ums.mapper.blog.FollowMapper;
import com.lucifer.cloud.boot.ums.mapper.blog.UserMapper;
import com.lucifer.cloud.boot.ums.service.blog.UserService;
import com.lucifer.cloud.boot.ums.util.TokenUtils;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author lucifer
 * @date 2023/12/21 15:24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private BlogMapper blogMapper;

    @Resource
    private FollowMapper followMapper;
    @DubboReference(version = "1.0.1")
    private UserApi userApi;

    @Override
    public UserInfoDto userInfo(RequestEntity request, Long _t) {
        String token = TokenUtils.getToken(request);
        Oauth2UserinfoResult loginUserInfo = userApi.getLoginUserInfo(token);
        Integer userId = loginUserInfo.getId();
        User user = getById(userId);
        UserInfo user_info = UserConverter.convertInfo(user);
        List<Blog> blogList = blogMapper.selectList(Wrappers.lambdaQuery(Blog.class).eq(Blog::getUser_id, userId));
        List<Follow> followList = followMapper.selectList(Wrappers.lambdaQuery(Follow.class).eq(Follow::getFollow_user_id, userId).eq(Follow::getFollow_type, true));
        UserDetail user_detail = UserConverter.convertBlog2Detail(blogList, followList);
        UserInfoDto userInfoDto = UserInfoDto.builder().user_info(user_info).user_detail(user_detail).build();
        return userInfoDto;
    }

    @Override
    public Boolean userUpdate(UserReq userReq) {
        return update(Wrappers.lambdaUpdate(User.class)
                .set(User::getUsername,userReq.getUsername())
                .set(User::getAvatar_url,userReq.getAvatar_url())
                .set(User::getTel,userReq.getTel())
                .set(User::getMotto,userReq.getMotto())
                .set(User::getWechat,userReq.getWechat())
                .set(User::getQq,userReq.getQq())
                .set(User::getGit_hub,userReq.getGit_hub())
                .set(User::getGender,userReq.getGender())
                .eq(User::getUid, userReq.getUid()));
    }

    @Override
    public Boolean passwordUpdate(RequestEntity request,String password) {
        String token = TokenUtils.getToken(request);
        Oauth2UserinfoResult loginUserInfo = userApi.getLoginUserInfo(token);
        Integer userId = loginUserInfo.getId();
        User user = new User();
        user.setId(userId.longValue());
        user.setPassword(password);
        return  updateById(user);
    }


}
