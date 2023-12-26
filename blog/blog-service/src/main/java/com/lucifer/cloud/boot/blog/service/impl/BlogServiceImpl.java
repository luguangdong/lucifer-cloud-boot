package com.lucifer.cloud.boot.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucifer.cloud.boot.blog.domin.bo.Blog;
import com.lucifer.cloud.boot.blog.domin.bo.Tag;
import com.lucifer.cloud.boot.blog.domin.bo.User;
import com.lucifer.cloud.boot.blog.domin.dto.blog.BlogConverter;
import com.lucifer.cloud.boot.blog.domin.dto.blog.BlogInfo;
import com.lucifer.cloud.boot.blog.domin.dto.blog.BlogInfoDto;
import com.lucifer.cloud.boot.blog.domin.dto.blog.BlogReq;
import com.lucifer.cloud.boot.blog.domin.dto.tag.TagConverter;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserConverter;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserInfo;
import com.lucifer.cloud.boot.blog.mapper.BlogMapper;
import com.lucifer.cloud.boot.blog.mapper.TagMapper;
import com.lucifer.cloud.boot.blog.mapper.UserMapper;
import com.lucifer.cloud.boot.blog.service.BlogService;
import com.lucifer.cloud.boot.blog.config.UserSystem;
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

    @Resource
    private TagMapper tagMapper;

    @Override
    public BlogInfoDto blogInfo(HttpServletRequest request, Long _t, Integer page, Integer limit) {
        Long userId = userSystem.userId(request);
        User user = userMapper.selectById(userId);
        UserInfo user_info = UserConverter.convertInfo(user);
        Page<Blog> rowPage = new Page<>(page, limit);
        Page<Blog> blogPage = this.baseMapper.selectPage(rowPage, Wrappers.lambdaQuery(Blog.class).eq(Blog::getUser_id, userId));
        List<Blog> blogList = blogPage.getRecords();
        long count = blogPage.getTotal();
        List<BlogInfo> blogInfoList = BlogConverter.convertList2InfoList(blogList, user_info);
        BlogInfoDto blogInfoDto = BlogInfoDto.builder().count(count).blog_info(blogInfoList).build();
        return blogInfoDto;
    }

    @Override
    public Boolean blogCreate(HttpServletRequest request, BlogReq blogReq) {
        Long userId = userSystem.userId(request);
        Blog blog = BlogConverter.convertReq2blog(blogReq,userId);
        Tag tag = TagConverter.convertBlogReq2Tag(blogReq,userId);
        tagMapper.insert(tag);
        return save(blog);
    }

    @Override
    public Boolean blogUpdate(HttpServletRequest request, BlogReq blogReq) {
        Blog blog = BlogConverter.convertReq2Eeditblog(blogReq);
        return update(Wrappers.lambdaUpdate(Blog.class)
                .set(Blog::getTitle,blog.getTitle())
                .set(Blog::getSub_title,blog.getSub_title())
                .set(Blog::getContent,blog.getContent())
                .eq(Blog::getUid, blog.getUid())
        );
    }

    @Override
    public Boolean blogDelete(HttpServletRequest request, String uid) {
        Long userId = userSystem.userId(request);
        return remove(Wrappers.lambdaQuery(Blog.class)
                .eq(Blog::getUid,Long.parseLong(uid))
                .eq(Blog::getUser_id,userId)
        );
    }

}
