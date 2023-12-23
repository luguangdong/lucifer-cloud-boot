package com.lucifer.cloud.boot.ums.service.blog.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucifer.cloud.boot.ums.entity.blog.bo.Blog;
import com.lucifer.cloud.boot.ums.mapper.blog.BlogMapper;
import com.lucifer.cloud.boot.ums.service.blog.BlogService;
import org.springframework.stereotype.Service;

/**
 * @author lucifer
 * @date 2023/12/21 15:24
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {


}
