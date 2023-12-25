package com.lucifer.cloud.boot.blog.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucifer.cloud.boot.blog.domin.bo.Comment;
import com.lucifer.cloud.boot.blog.mapper.CommentMapper;
import com.lucifer.cloud.boot.blog.service.CommentService;
import org.springframework.stereotype.Service;

/**
 * @author lucifer
 * @date 2023/12/21 15:24
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {


}
