package com.lucifer.cloud.boot.blog.service.impl;
import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucifer.cloud.boot.blog.config.UserSystem;
import com.lucifer.cloud.boot.blog.domin.bo.Tag;
import com.lucifer.cloud.boot.blog.domin.dto.tag.TagConverter;
import com.lucifer.cloud.boot.blog.domin.dto.tag.TagDto;
import com.lucifer.cloud.boot.blog.domin.dto.tag.TagInfo;
import com.lucifer.cloud.boot.blog.mapper.TagMapper;
import com.lucifer.cloud.boot.blog.service.TagService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author lucifer
 * @date 2023/12/21 15:24
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {


    @Resource
    private UserSystem userSystem;

    @Override
    public TagDto info(HttpServletRequest request, String type) {
        Long userId = userSystem.userId(request);
        List<Tag> tagList = list(Wrappers.lambdaQuery(Tag.class).eq(Tag::getUser_id, userId).eq(Tag::getType, type));
        List<TagInfo> tagInfoList = Optional.ofNullable(tagList).orElse(Lists.newArrayList())
                .stream()
                .map(tag -> {
                    return TagConverter.convertTag2TagInfo(tag);
                }).collect(Collectors.toList());
        return TagDto.builder().tags_info(tagInfoList).build();
    }

    @Override
    public Boolean create(HttpServletRequest request, String type, String name) {
        Long userId = userSystem.userId(request);
        Tag tag = TagConverter.convertTagReq2Tag(name, userId);
        return save(tag);
    }
}
