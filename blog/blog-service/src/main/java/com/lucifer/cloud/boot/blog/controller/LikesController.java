package com.lucifer.cloud.boot.blog.controller;
import com.lucifer.cloud.boot.blog.service.LikesService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author lucifer
 * @date 2023/12/21 15:31
 */
@RestController
@RequestMapping("likes")
public class LikesController {
    @Resource
    private LikesService likesService;

}
