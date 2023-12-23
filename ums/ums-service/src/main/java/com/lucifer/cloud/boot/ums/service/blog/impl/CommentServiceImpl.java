package com.lucifer.cloud.boot.ums.service.blog.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucifer.cloud.boot.ums.entity.blog.bo.Comment;
import com.lucifer.cloud.boot.ums.mapper.blog.CommentMapper;
import com.lucifer.cloud.boot.ums.service.blog.CommentService;
import org.springframework.stereotype.Service;

/**
 * @author lucifer
 * @date 2023/12/21 15:24
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {


}
