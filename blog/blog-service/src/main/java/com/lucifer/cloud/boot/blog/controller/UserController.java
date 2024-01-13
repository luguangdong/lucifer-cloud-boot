package com.lucifer.cloud.boot.blog.controller;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserDetailDto;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserInfoDto;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserReq;
import com.lucifer.cloud.boot.blog.service.UserService;
import com.lucifer.cloud.commons.model.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author lucifer
 * @date 2023/12/21 15:31
 */
@RestController
@RequestMapping("users")
public class UserController {

    @Resource
    private HttpServletRequest request;

    @Resource
    private UserService userService;

    @GetMapping("info")
    public Result<UserInfoDto> userInfo(
            @RequestParam(value = "_t",required = false) Long _t) {
        return Result.success(userService.userInfo(request,_t));
    }

    @PostMapping("update")
    public Result<Boolean> userUpdate(
            @RequestBody UserReq userReq) {
        return Result.success(userService.userUpdate(userReq));
    }


    @PostMapping("update/password")
    public Result<Boolean> passwordUpdate(
            @RequestBody Map map) {
        String password = (String)map.get("password");
        return Result.success(userService.passwordUpdate(request,password));
    }


    @GetMapping("detail")
    public Result<UserDetailDto> userDetail(
            @RequestParam(value = "uid",required = false) String uid,
            @RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
            @RequestParam(value = "limit",required = false,defaultValue = "1") Integer limit,
            @RequestParam(value = "type",required = false) Integer type
    ) {

        return Result.success(userService.userDetail(request,uid,page,limit,type));
    }
}
