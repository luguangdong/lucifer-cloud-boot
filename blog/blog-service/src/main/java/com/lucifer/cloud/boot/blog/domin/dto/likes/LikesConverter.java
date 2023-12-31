package com.lucifer.cloud.boot.blog.domin.dto.likes;

import com.lucifer.cloud.boot.blog.config.BlogConstant;
import com.lucifer.cloud.boot.blog.domin.bo.Likes;
import com.lucifer.cloud.boot.blog.util.GenerateUtils;

/**
 * @author lucifer
 * @date 2023/12/30 14:38
 */
public class LikesConverter {

    public static Likes converterReq2Likes(Long userId, String uid, String likesType, String type){
        Likes likes = new Likes();
        likes = (Likes)GenerateUtils.generateFiledValue(likes);
        likes.setUser_id(userId);
        likes.setLikes_id(Long.parseLong(uid));
        likes.setLikes_type(BlogConstant.LIKES_TYPE.equals(likesType) ? true:null);
        likes.setType(Integer.valueOf(type));
        return likes;
    }
}
