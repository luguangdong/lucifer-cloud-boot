package com.lucifer.cloud.boot.blog.domin.dto.follow;

import com.lucifer.cloud.boot.blog.domin.bo.Follow;
import com.lucifer.cloud.boot.blog.util.GenerateUtils;

/**
 * @author lucifer
 * @date 2024/1/11 13:16
 */
public class FollowConverter {

    public static Follow converterReq2Follow(Long userId,String followId, Integer followType){
        Follow follow = new Follow();
        follow = (Follow)GenerateUtils.generateFiledValue(follow);
        follow.setUser_id(userId);
        follow.setFollow_user_id(Long.parseLong(followId));
        follow.setFollow_type(followType==1?true:false);
        return follow;
    }
}
