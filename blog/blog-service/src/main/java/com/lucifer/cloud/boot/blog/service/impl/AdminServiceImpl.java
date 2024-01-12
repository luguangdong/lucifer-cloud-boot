package com.lucifer.cloud.boot.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucifer.cloud.boot.blog.config.UserSystem;
import com.lucifer.cloud.boot.blog.domin.bo.Exhibition;
import com.lucifer.cloud.boot.blog.domin.bo.User;
import com.lucifer.cloud.boot.blog.domin.dto.admin.AdminExhibitionDto;
import com.lucifer.cloud.boot.blog.domin.dto.admin.AdminStatDto;
import com.lucifer.cloud.boot.blog.domin.dto.exhitition.ExhibitionConverter;
import com.lucifer.cloud.boot.blog.domin.dto.exhitition.ExhibitionUser;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserConverter;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserInfo;
import com.lucifer.cloud.boot.blog.mapper.ExhibitionMapper;
import com.lucifer.cloud.boot.blog.mapper.UserMapper;
import com.lucifer.cloud.boot.blog.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public AdminStatDto stat(HttpServletRequest request) {
        return AdminStatDto.builder().build();
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
