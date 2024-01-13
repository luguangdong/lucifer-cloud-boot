package com.lucifer.cloud.boot.blog.service.impl;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucifer.cloud.boot.blog.config.UserSystem;
import com.lucifer.cloud.boot.blog.domin.bo.Follow;
import com.lucifer.cloud.boot.blog.domin.dto.follow.FollowConverter;
import com.lucifer.cloud.boot.blog.mapper.FollowMapper;
import com.lucifer.cloud.boot.blog.service.FollowService;
import com.lucifer.cloud.commons.model.Result;
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
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements FollowService {

    @Resource
    private UserSystem userSystem;


    @Override
    public Result follow(HttpServletRequest request, String followId, Integer followType) {
        Long uid = userSystem.uid(request);
        Long userId = userSystem.userId(request);
        if(uid == Long.parseLong(followId)){
            return Result.error(1,"不能关注自己");
        }
        Follow fl = getOne(Wrappers.lambdaQuery(Follow.class).eq(Follow::getUser_id, userId).eq(Follow::getFollow_user_id, followId));
        if (Objects.isNull(fl)){
            Follow follow = FollowConverter.converterReq2Follow(userId, followId, followType);
            save(follow);
            return Result.success();
        }else {
            update(Wrappers.lambdaUpdate(Follow.class)
                    .set(Follow::getCreated_at, LocalDateTime.now())
                    .set(Follow::getFollow_type,followType)
                    .eq(Follow::getUser_id,userId)
                    .eq(Follow::getFollow_user_id,followId));
            return Result.success();
        }
    }
}
