package com.lucifer.cloud.boot.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lucifer.cloud.boot.blog.config.UserSystem;
import com.lucifer.cloud.boot.blog.domin.bo.Blog;
import com.lucifer.cloud.boot.blog.domin.bo.Exhibition;
import com.lucifer.cloud.boot.blog.domin.bo.Follow;
import com.lucifer.cloud.boot.blog.domin.bo.User;
import com.lucifer.cloud.boot.blog.domin.dto.dashboard.DashboardConverter;
import com.lucifer.cloud.boot.blog.domin.dto.dashboard.DashboardDto;
import com.lucifer.cloud.boot.blog.mapper.UserMapper;
import com.lucifer.cloud.boot.blog.service.BlogService;
import com.lucifer.cloud.boot.blog.service.DashboardService;
import com.lucifer.cloud.boot.blog.service.ExhibitionService;
import com.lucifer.cloud.boot.blog.service.FollowService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lucifer
 * @date 2023/12/27 15:26
 */
@Service
public class DashboardServiceImpl implements DashboardService {

    @Resource
    private BlogService blogService;

    @Resource
    private ExhibitionService exhibitionService;

    @Resource
    private FollowService followService;

    @Resource
    private UserSystem userSystem;

    @Resource
    private UserMapper userMapper;

    @Override
    public DashboardDto dashboard(HttpServletRequest request) {
        Long userId = userSystem.userId(request);
        User user = userMapper.selectById(userId);


        List<Blog> blogList = blogService.list(Wrappers.lambdaQuery(Blog.class).eq(Blog::getUser_id, userId));
        List<Exhibition> exhibitionList = exhibitionService.list(Wrappers.lambdaQuery(Exhibition.class).eq(Exhibition::getUser_id, userId));
        List<Follow> followList = followService.list(Wrappers.lambdaQuery(Follow.class).eq(Follow::getUser_id, userId));
        return DashboardConverter.converter2Dto(blogList,exhibitionList,followList,user);
    }
}
