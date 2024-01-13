package com.lucifer.cloud.boot.blog.controller;
import com.lucifer.cloud.boot.blog.service.LikesService;
import com.lucifer.cloud.commons.model.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author lucifer
 * @date 2023/12/21 15:31
 */
@RestController
public class LikesController {

    @Resource
    private HttpServletRequest request;
    @Resource
    private LikesService likesService;

    @PostMapping("like")
    public Result<Boolean> like(
            @RequestParam(value = "uid",required = false) String uid,
            @RequestParam(value = "likes_type",required = false) String likes_type,
            @RequestParam(value = "type",required = false) String type
    ) {
        return Result.success(likesService.like(request,uid,likes_type,type));
    }

}
