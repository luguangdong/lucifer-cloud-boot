package com.lucifer.cloud.boot.ums.controller.blog;
import com.lucifer.cloud.boot.ums.entity.blog.dto.user.UserInfoDto;
import com.lucifer.cloud.boot.ums.entity.blog.dto.user.UserReq;
import com.lucifer.cloud.boot.ums.service.blog.UserService;
import com.lucifer.cloud.commons.model.Result;
import jakarta.annotation.Resource;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author lucifer
 * @date 2023/12/21 15:31
 */
@RestController
@RequestMapping("users")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("info")
    public Result<UserInfoDto> userInfo(
            RequestEntity request,
            @RequestParam(value = "_t",required = false) Long _t) {
        return Result.success(userService.userInfo(request,_t));
    }

    @PostMapping("update")
    public Result<Boolean> userUpdate(
            @RequestBody UserReq userReq) {
        return Result.success(userService.userUpdate(userReq));
    }
}
