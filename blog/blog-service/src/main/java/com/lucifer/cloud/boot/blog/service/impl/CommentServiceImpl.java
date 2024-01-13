package com.lucifer.cloud.boot.blog.service.impl;
import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucifer.cloud.boot.blog.config.UserSystem;
import com.lucifer.cloud.boot.blog.domin.bo.Comment;
import com.lucifer.cloud.boot.blog.domin.bo.User;
import com.lucifer.cloud.boot.blog.domin.dto.comment.CommentConverter;
import com.lucifer.cloud.boot.blog.domin.dto.comment.CommentDto;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserConverter;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserInfo;
import com.lucifer.cloud.boot.blog.mapper.CommentMapper;
import com.lucifer.cloud.boot.blog.mapper.UserMapper;
import com.lucifer.cloud.boot.blog.service.CommentService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author lucifer
 * @date 2023/12/21 15:24
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    private UserSystem userSystem;

    @Resource
    private UserMapper userMapper;


    @Override
    public CommentDto info(HttpServletRequest request,String content_id, Integer page, Integer limit, String type) {

        Long userId = userSystem.userId(request);
        User user = userMapper.selectById(userId);
        UserInfo user_info = UserConverter.convertInfo(user);
        List<Comment> commentList = Lists.newArrayList();

        if(Objects.isNull(page) && Objects.isNull(limit)){
            commentList = list(Wrappers.lambdaQuery(Comment.class)
                    .eq(StringUtils.isNotBlank(content_id),Comment::getContent_id,content_id)
                    .eq(Comment::getUser_id, userId)
                    .eq(Comment::getType,type)
            );
        }else {
            Page<Comment> rowPage = new Page<>(page, limit);
            Page<Comment> commentPage = this.baseMapper.selectPage(rowPage,Wrappers.lambdaQuery(Comment.class)
                    .eq(StringUtils.isNotBlank(content_id),Comment::getContent_id,content_id)
                    .eq(Comment::getUser_id,userId)
                    .eq(Comment::getType,type)

            );
            commentList = commentPage.getRecords();
        }
        return CommentConverter.converter2Dto(commentList,user_info,userId);
    }

    @Override
    public Boolean create(HttpServletRequest request, String contentId, String content, String type) {
        Long userId = userSystem.userId(request);
        Comment comment = CommentConverter.converterReq2Comment(contentId, content, type, userId);
        return save(comment);
    }
}
