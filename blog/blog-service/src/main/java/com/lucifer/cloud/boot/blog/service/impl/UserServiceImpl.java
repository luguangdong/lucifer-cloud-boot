package com.lucifer.cloud.boot.blog.service.impl;

import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucifer.cloud.boot.blog.config.BlogType;
import com.lucifer.cloud.boot.blog.domin.bo.Blog;
import com.lucifer.cloud.boot.blog.domin.bo.Exhibition;
import com.lucifer.cloud.boot.blog.domin.bo.Follow;
import com.lucifer.cloud.boot.blog.domin.bo.Likes;
import com.lucifer.cloud.boot.blog.domin.bo.Star;
import com.lucifer.cloud.boot.blog.domin.bo.User;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserConverter;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserDetail;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserDetailDto;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserInfo;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserInfoDto;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserReq;
import com.lucifer.cloud.boot.blog.mapper.BlogMapper;
import com.lucifer.cloud.boot.blog.mapper.ExhibitionMapper;
import com.lucifer.cloud.boot.blog.mapper.FollowMapper;
import com.lucifer.cloud.boot.blog.mapper.UserMapper;
import com.lucifer.cloud.boot.blog.service.FollowService;
import com.lucifer.cloud.boot.blog.service.LikesService;
import com.lucifer.cloud.boot.blog.service.StarService;
import com.lucifer.cloud.boot.blog.service.UserService;
import com.lucifer.cloud.boot.blog.config.UserSystem;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


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

    @Resource
    private ExhibitionMapper exhibitionMapper;

    @Resource
    private LikesService likesService;

    @Resource
    private StarService starService;

    @Resource
    private FollowService followService;
    @Resource
    private UserSystem userSystem;

    @Override
    public UserInfoDto userInfo(HttpServletRequest request, Long _t) {
        Long userId = userSystem.userId(request);
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

//        UpdateWrapper wrapper = new UpdateWrapper();
//        wrapper = (UpdateWrapper)GenerateUtils.generateUpdateWrapper(userReq, wrapper, "uid");
//        return update(wrapper);
        return update(Wrappers.lambdaUpdate(User.class)
                .set(StringUtils.isNotBlank(userReq.getAvatar_url()),User::getAvatar_url,userReq.getAvatar_url())
                .set(Objects.nonNull(userReq.getGender()),User::getGender,userReq.getGender())
                .set(StringUtils.isNotBlank(userReq.getGit_hub()),User::getGit_hub,userReq.getGit_hub())
                .set(StringUtils.isNotBlank(userReq.getMotto()),User::getMotto,userReq.getMotto())
                .set(StringUtils.isNotBlank(userReq.getQq()),User::getQq,userReq.getQq())
                .set(Objects.nonNull(userReq.getTel()),User::getTel,userReq.getTel())
                .set(StringUtils.isNotBlank(userReq.getUsername()),User::getUsername,userReq.getUsername())
                .set(StringUtils.isNotBlank(userReq.getWechat()),User::getWechat,userReq.getWechat())
                .set(StringUtils.isNotBlank(userReq.getBackground_image()),User::getBackground_image,userReq.getBackground_image())
                .eq(User::getUid,userReq.getUid())
        );


    }

    @Override
    public Boolean passwordUpdate(HttpServletRequest request,String password) {
        Long userId = userSystem.userId(request);
        User user = new User();
        user.setId(userId.longValue());
        user.setPassword(password);
        return  updateById(user);
    }

    @Override
    public UserDetailDto userDetail(HttpServletRequest request, String uid, Integer page, Integer limit, Integer type) {
        Long userId = userSystem.userId(request);
        User user = getById(userId);
        UserInfo user_info = UserConverter.convertInfo(user);
        Page<Exhibition> rowPage = new Page<>(page, limit);
        Page<Exhibition> exhibitionPage = exhibitionMapper.selectPage(rowPage,Wrappers.lambdaQuery(Exhibition.class)
                .eq(Exhibition::getUser_id,userId)
                //.eq(StringUtils.isNotBlank(uid),Exhibition::getUid,uid)
        );
        List<Exhibition> exhibitionList = exhibitionPage.getRecords();
        long count = exhibitionPage.getTotal();
        List<Likes> likesList = likesService.list(Wrappers.lambdaQuery(Likes.class)
                .eq(Likes::getUser_id, userId)
                .eq(Likes::getType, BlogType.IMAGE.getIndex())
                .eq(Likes::getLikes_type,Boolean.TRUE)
        );

        List<Star> starList = starService.list(Wrappers.lambdaQuery(Star.class)
                .eq(Star::getUser_id, userId)
                .eq(Star::getType, BlogType.IMAGE.getIndex())
                .eq(Star::getStar_type,Boolean.TRUE)
        );

        List<Follow> followList = followService.list(Wrappers.lambdaQuery(Follow.class)
                .eq(Follow::getFollow_user_id, userId)
                .eq(Follow::getFollow_type,Boolean.TRUE)
        );

        return UserConverter.convert2UserDetailDto(user_info,exhibitionList,count,likesList,starList,followList);
    }


}
