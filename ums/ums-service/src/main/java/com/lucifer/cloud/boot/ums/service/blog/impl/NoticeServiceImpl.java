package com.lucifer.cloud.boot.ums.service.blog.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucifer.cloud.boot.ums.entity.blog.bo.Notice;
import com.lucifer.cloud.boot.ums.mapper.blog.NoticeMapper;
import com.lucifer.cloud.boot.ums.service.blog.NoticeService;
import org.springframework.stereotype.Service;

/**
 * @author lucifer
 * @date 2023/12/21 15:24
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {


}
