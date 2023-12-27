package com.lucifer.cloud.boot.blog.service.impl;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucifer.cloud.boot.blog.config.UserSystem;
import com.lucifer.cloud.boot.blog.domin.bo.Exhibition;
import com.lucifer.cloud.boot.blog.domin.bo.User;
import com.lucifer.cloud.boot.blog.domin.dto.exhitition.ExhibitionConverter;
import com.lucifer.cloud.boot.blog.domin.dto.exhitition.ExhibitionDto;
import com.lucifer.cloud.boot.blog.domin.dto.exhitition.ExhibitionReq;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserConverter;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserInfo;
import com.lucifer.cloud.boot.blog.mapper.ExhibitionMapper;
import com.lucifer.cloud.boot.blog.mapper.UserMapper;
import com.lucifer.cloud.boot.blog.service.ExhibitionService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lucifer
 * @date 2023/12/21 15:24
 */
@Service
public class ExhibitionServiceImpl extends ServiceImpl<ExhibitionMapper, Exhibition> implements ExhibitionService {

    @Resource
    private UserSystem userSystem;

    @Resource
    private UserMapper userMapper;


    @Override
    public ExhibitionDto info(HttpServletRequest request, Integer page, Integer limit, Integer type, String sort) {
        Long userId = userSystem.userId(request);
        User user = userMapper.selectById(userId);
        UserInfo user_info = UserConverter.convertInfo(user);
        Page<Exhibition> rowPage = new Page<>(page, limit);
        Page<Exhibition> exhibitionPage = this.baseMapper.selectPage(rowPage, Wrappers.lambdaQuery(Exhibition.class).eq(Exhibition::getUser_id, userId));
        List<Exhibition> exhibitionList = exhibitionPage.getRecords();
        long count = exhibitionPage.getTotal();
        ExhibitionDto exhibitionDto = ExhibitionConverter.converter2Dto(exhibitionList, count, user_info);
        return exhibitionDto;
    }

    @Override
    public Boolean create(HttpServletRequest request, ExhibitionReq exhibitionReq) {
        Long userId = userSystem.userId(request);
        Exhibition exhibition = ExhibitionConverter.convertReq2Exhibition(exhibitionReq, userId);
        return save(exhibition);
    }
}
