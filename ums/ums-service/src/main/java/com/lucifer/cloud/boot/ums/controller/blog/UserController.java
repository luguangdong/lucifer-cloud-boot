package com.lucifer.cloud.boot.ums.controller.blog;

import com.lucifer.cloud.boot.ums.entity.blog.bo.Article;
import com.lucifer.cloud.boot.ums.entity.blog.dto.user.UserInfoDto;
import com.lucifer.cloud.boot.ums.service.blog.ArticleService;
import com.lucifer.cloud.boot.ums.service.blog.UserService;
import com.lucifer.cloud.commons.model.Result;
import jakarta.annotation.Resource;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lucifer
 * @date 2023/12/21 15:31
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("info")
    public Result<UserInfoDto> userInfo(RequestEntity request,@RequestParam("_t") Long _t) {
        return Result.success(userService.userInfo(request,_t));
    }
}
