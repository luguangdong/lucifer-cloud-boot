package com.lucifer.cloud.boot.blog.service.impl;

import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucifer.cloud.boot.blog.config.UserSystem;
import com.lucifer.cloud.boot.blog.domin.bo.Blog;
import com.lucifer.cloud.boot.blog.domin.bo.Exhibition;
import com.lucifer.cloud.boot.blog.domin.bo.User;
import com.lucifer.cloud.boot.blog.domin.dto.admin.AdminExhibitionDto;
import com.lucifer.cloud.boot.blog.domin.dto.admin.AdminStatDto;
import com.lucifer.cloud.boot.blog.domin.dto.exhitition.ExhibitionConverter;
import com.lucifer.cloud.boot.blog.domin.dto.exhitition.ExhibitionUser;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserConverter;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserInfo;
import com.lucifer.cloud.boot.blog.mapper.BlogMapper;
import com.lucifer.cloud.boot.blog.mapper.ExhibitionMapper;
import com.lucifer.cloud.boot.blog.mapper.UserMapper;
import com.lucifer.cloud.boot.blog.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author lucifer
 * @date 2023/12/28 10:51
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private UserSystem userSystem;

    @Resource
    private UserMapper userMapper;

    @Resource
    private ExhibitionMapper exhibitionMapper;

    @Resource
    private BlogMapper blogMapper;



    @Override
    public AdminStatDto stat(HttpServletRequest request) {
        Long user_count = userMapper.selectCount(Wrappers.lambdaQuery(User.class));
        Long blog_count = blogMapper.selectCount(Wrappers.lambdaQuery(Blog.class));
        List<Exhibition> exhibitionList = exhibitionMapper.selectList(Wrappers.lambdaQuery(Exhibition.class));
        Long image_count = exhibitionList.stream().count();
        Long image_download_count = Optional.ofNullable(exhibitionList).orElse(Lists.newArrayList())
                .stream()
                .mapToLong(Exhibition::getDownload).sum();
        return AdminStatDto.builder().user_count(user_count).blog_count(blog_count).article_count(0L).image_count(image_count).image_download_count(image_download_count).build();
    }

    @Override
    public AdminExhibitionDto exhibitions(HttpServletRequest request, Integer page, Integer limit, Integer type, String sort) {
        Long userId = userSystem.userId(request);
        User user = userMapper.selectById(userId);
        UserInfo user_info = UserConverter.convertInfo(user);
        Page<Exhibition> rowPage = new Page<>(page, limit);
        Page<Exhibition> exhibitionPage = exhibitionMapper.selectPage(rowPage, Wrappers.lambdaQuery(Exhibition.class)
                 .eq(Exhibition::getStatus,type)
                .last(StringUtils.isNotBlank(sort),"order by "+ sort)
        );
        List<Exhibition> exhibitionList = exhibitionPage.getRecords();
        Long count = exhibitionPage.getTotal();
        List<ExhibitionUser> exhibitions = ExhibitionConverter.converter2ExhibitionUserList(exhibitionList, user_info);
        return AdminExhibitionDto.builder().exhibitions(exhibitions).count(count).build();
    }
}
