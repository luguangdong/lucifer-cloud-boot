package com.lucifer.cloud.boot.ums.service.blog.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucifer.cloud.boot.ums.entity.blog.bo.Tag;
import com.lucifer.cloud.boot.ums.mapper.blog.TagMapper;
import com.lucifer.cloud.boot.ums.service.blog.TagService;
import org.springframework.stereotype.Service;

/**
 * @author lucifer
 * @date 2023/12/21 15:24
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {


}
