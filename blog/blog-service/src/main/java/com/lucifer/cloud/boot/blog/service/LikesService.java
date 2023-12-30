package com.lucifer.cloud.boot.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucifer.cloud.boot.blog.domin.bo.Likes;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author lucifer
 * @date 2023/12/21 15:23
 */
public interface LikesService extends IService<Likes> {


    Boolean like(HttpServletRequest request, String uid, String likesType, String type);
}
