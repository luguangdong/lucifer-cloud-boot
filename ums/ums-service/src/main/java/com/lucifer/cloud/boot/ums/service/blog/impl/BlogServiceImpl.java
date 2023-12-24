package com.lucifer.cloud.boot.ums.service.blog.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucifer.cloud.auth.model.response.Oauth2UserinfoResult;
import com.lucifer.cloud.boot.ums.entity.blog.bo.Blog;
import com.lucifer.cloud.boot.ums.entity.blog.bo.User;
import com.lucifer.cloud.boot.ums.entity.blog.dto.blog.BlogConverter;
import com.lucifer.cloud.boot.ums.entity.blog.dto.blog.BlogInfo;
import com.lucifer.cloud.boot.ums.entity.blog.dto.blog.BlogInfoDto;
import com.lucifer.cloud.boot.ums.entity.blog.dto.blog.BlogReq;
import com.lucifer.cloud.boot.ums.entity.blog.dto.user.UserConverter;
import com.lucifer.cloud.boot.ums.entity.blog.dto.user.UserInfo;
import com.lucifer.cloud.boot.ums.mapper.blog.BlogMapper;
import com.lucifer.cloud.boot.ums.mapper.blog.UserMapper;
import com.lucifer.cloud.boot.ums.service.blog.BlogService;
import com.lucifer.cloud.boot.ums.util.UserSystem;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lucifer
 * @date 2023/12/21 15:24
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {


    @Resource
    private UserSystem userSystem;

    @Resource
    private UserMapper userMapper;

    @Override
    public BlogInfoDto blogInfo(HttpServletRequest request, Long _t, Integer page, Integer limit) {
        Long userId = userSystem.userId(request);
        User user = userMapper.selectById(userId);
        UserInfo user_info = UserConverter.convertInfo(user);
        List<Blog> blogList = list(Wrappers.lambdaQuery(Blog.class).eq(Blog::getUser_id, userId));
        int count = blogList.size();
        List<BlogInfo> blogInfoList = BlogConverter.convertList2InfoList(blogList, user_info);
        BlogInfoDto blogInfoDto = BlogInfoDto.builder().count(count).blog_info(blogInfoList).build();
        return blogInfoDto;
    }

    @Override
    public Boolean blogCreate(HttpServletRequest request,BlogReq blogReq) {
        Long userId = userSystem.userId(request);
        Blog blog = BlogConverter.convertReq2blog(blogReq,userId);
        return save(blog);
    }

}
