package com.lucifer.cloud.boot.blog.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucifer.cloud.boot.blog.domin.bo.Follow;
import com.lucifer.cloud.commons.model.Result;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author lucifer
 * @date 2023/12/21 15:23
 */
public interface FollowService extends IService<Follow> {


    Result follow(HttpServletRequest request, String followId, Integer followType);
}
