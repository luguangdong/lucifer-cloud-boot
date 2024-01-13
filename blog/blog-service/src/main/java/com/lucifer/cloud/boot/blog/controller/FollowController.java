package com.lucifer.cloud.boot.blog.controller;
import com.lucifer.cloud.boot.blog.service.FollowService;
import com.lucifer.cloud.commons.model.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author lucifer
 * @date 2023/12/21 15:31
 */
@RestController
public class FollowController {

    @Resource
    private HttpServletRequest request;
    @Resource
    private FollowService followService;

    @GetMapping("follow")
    public Result follow(
            @RequestParam(value = "follow_id",required = false) String follow_id,
            @RequestParam(value = "follow_type",required = false) Integer follow_type
    ) {
        return followService.follow(request,follow_id,follow_type);
    }

}
