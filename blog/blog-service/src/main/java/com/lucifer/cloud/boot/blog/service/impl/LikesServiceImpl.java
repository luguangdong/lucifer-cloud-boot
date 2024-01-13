package com.lucifer.cloud.boot.blog.service.impl;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucifer.cloud.boot.blog.config.UserSystem;
import com.lucifer.cloud.boot.blog.domin.bo.Likes;
import com.lucifer.cloud.boot.blog.domin.dto.likes.LikesConverter;
import com.lucifer.cloud.boot.blog.mapper.LikesMapper;
import com.lucifer.cloud.boot.blog.service.LikesService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author lucifer
 * @date 2023/12/21 15:24
 */
@Service
public class LikesServiceImpl extends ServiceImpl<LikesMapper, Likes> implements LikesService {

    @Resource
    private UserSystem userSystem;


    @Override
    public Boolean like(HttpServletRequest request, String uid, String likesType, String type) {
        Long userId = userSystem.userId(request);
        Likes lk = getOne(Wrappers.lambdaQuery(Likes.class).eq(Likes::getUser_id, userId).eq(Likes::getLikes_id, uid));
        if (Objects.isNull(lk)){
            Likes likes = LikesConverter.converterReq2Likes(userId, uid, likesType, type);
            return save(likes);
        }else {
            return update(Wrappers.lambdaUpdate(Likes.class)
                    .set(Likes::getUpdated_at, LocalDateTime.now())
                    .set(Likes::getLikes_type,likesType)
                    .eq(Likes::getUser_id,userId)
                    .eq(Likes::getLikes_id,uid)
                    .eq(Likes::getType,type));
        }
    }
}
