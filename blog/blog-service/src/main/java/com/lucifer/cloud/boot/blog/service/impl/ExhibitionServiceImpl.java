package com.lucifer.cloud.boot.blog.service.impl;
import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucifer.cloud.boot.blog.config.BlogType;
import com.lucifer.cloud.boot.blog.config.UserSystem;
import com.lucifer.cloud.boot.blog.domin.bo.Exhibition;
import com.lucifer.cloud.boot.blog.domin.bo.Likes;
import com.lucifer.cloud.boot.blog.domin.bo.Star;
import com.lucifer.cloud.boot.blog.domin.bo.User;
import com.lucifer.cloud.boot.blog.domin.dto.exhitition.ExhibitionConverter;
import com.lucifer.cloud.boot.blog.domin.dto.exhitition.ExhibitionDto;
import com.lucifer.cloud.boot.blog.domin.dto.exhitition.ExhibitionReq;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserConverter;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserInfo;
import com.lucifer.cloud.boot.blog.mapper.ExhibitionMapper;
import com.lucifer.cloud.boot.blog.mapper.UserMapper;
import com.lucifer.cloud.boot.blog.service.ExhibitionService;
import com.lucifer.cloud.boot.blog.service.LikesService;
import com.lucifer.cloud.boot.blog.service.StarService;
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
public class ExhibitionServiceImpl extends ServiceImpl<ExhibitionMapper, Exhibition> implements ExhibitionService {

    @Resource
    private UserSystem userSystem;

    @Resource
    private UserMapper userMapper;

    @Resource
    private LikesService likesService;

    @Resource
    private StarService starService;


    @Override
    public ExhibitionDto info(HttpServletRequest request, Integer page, Integer limit, Integer type, String sort,String keywords,String tags) {
        Long userId = userSystem.userId(request);
        User user = userMapper.selectById(userId);
        UserInfo user_info = UserConverter.convertInfo(user);
        Page<Exhibition> rowPage = new Page<>(page, limit);
        Page<Exhibition> exhibitionPage = this.baseMapper.selectPage(rowPage, Wrappers.lambdaQuery(Exhibition.class).eq(Exhibition::getUser_id, userId).like(Exhibition::getTitle,keywords));
        List<Exhibition> exhibitionList = exhibitionPage.getRecords();
        long count = exhibitionPage.getTotal();
        List<Likes> likesList = likesService.list(Wrappers.lambdaQuery(Likes.class)
                .eq(Likes::getUser_id, userId)
                .eq(Likes::getType, BlogType.IMAGE.getIndex())
                .eq(Likes::getLikes_type,Boolean.TRUE)
        );
        List<String> likes_ids = Optional.ofNullable(likesList).orElse(Lists.newArrayList())
                .stream().map(Likes::getLikes_id)
                .map(String::valueOf)
                .collect(Collectors.toList());

        List<Star> starList = starService.list(Wrappers.lambdaQuery(Star.class)
                .eq(Star::getUser_id, userId)
                .eq(Star::getType, BlogType.IMAGE.getIndex())
                .eq(Star::getStar_type,Boolean.TRUE)
        );
        List<String> star_ids = Optional.ofNullable(starList).orElse(Lists.newArrayList())
                .stream().map(Star::getStar_id)
                .map(String::valueOf)
                .collect(Collectors.toList());

        ExhibitionDto exhibitionDto = ExhibitionConverter.converter2Dto(exhibitionList, count, user_info,likes_ids,star_ids);
        return exhibitionDto;
    }

    @Override
    public Boolean create(HttpServletRequest request, ExhibitionReq exhibitionReq) {
        Long userId = userSystem.userId(request);
        Exhibition exhibition = ExhibitionConverter.convertReq2Exhibition(exhibitionReq, userId);
        return save(exhibition);
    }

    @Override
    public Boolean delete(HttpServletRequest request, String uid) {
        Long userId = userSystem.userId(request);
        boolean remove = remove(Wrappers.lambdaQuery(Exhibition.class).eq(Exhibition::getUser_id, userId).eq(Exhibition::getUid, uid));
        if (remove){
            likesService.remove(Wrappers.lambdaQuery(Likes.class).eq(Likes::getUser_id,userId).eq(Likes::getLikes_id,uid));
            starService.remove(Wrappers.lambdaQuery(Star.class).eq(Star::getUser_id,userId).eq(Star::getStar_id,uid));
        }
        return remove;
    }
}
