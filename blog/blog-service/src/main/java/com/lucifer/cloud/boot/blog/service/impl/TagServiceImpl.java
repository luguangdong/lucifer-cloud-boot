package com.lucifer.cloud.boot.blog.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucifer.cloud.boot.blog.domin.bo.Tag;
import com.lucifer.cloud.boot.blog.mapper.TagMapper;
import com.lucifer.cloud.boot.blog.service.TagService;
import org.springframework.stereotype.Service;

/**
 * @author lucifer
 * @date 2023/12/21 15:24
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {


}
