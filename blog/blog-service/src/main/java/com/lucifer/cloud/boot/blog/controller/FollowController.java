package com.lucifer.cloud.boot.blog.controller;
import com.lucifer.cloud.boot.blog.service.FollowService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author lucifer
 * @date 2023/12/21 15:31
 */
@RestController
@RequestMapping("follow")
public class FollowController {
    @Resource
    private FollowService followService;

}
