package com.lucifer.cloud.boot.blog.controller;
import com.lucifer.cloud.boot.blog.domin.dto.blog.BlogInfoDto;
import com.lucifer.cloud.boot.blog.domin.dto.blog.BlogReq;
import com.lucifer.cloud.boot.blog.service.BlogService;
import com.lucifer.cloud.commons.model.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
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
@RequestMapping("blog")
public class BlogController {

    @Resource
    private HttpServletRequest request;

    @Resource
    private BlogService blogService;

    @GetMapping("info")
    public Result<BlogInfoDto> info(
            @RequestParam(value = "_t",required = false) Long _t,
            @RequestParam(value = "uids",required = false) String uid,
            @RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
            @RequestParam(value = "limit",required = false,defaultValue = "1") Integer limit
    ) {

        return Result.success(blogService.info(request,_t,uid,page,limit));
    }


    @PostMapping("create")
    public Result<Boolean> create(
            @RequestBody BlogReq blogReq
    ) {
        return Result.success(blogService.create(request,blogReq));
    }

    @PostMapping("update")
    public Result<Boolean> update(
            @RequestBody BlogReq blogReq
    ) {
        return Result.success(blogService.update(request,blogReq));
    }

    @GetMapping("delete")
    public Result<Boolean> delete(
            @RequestParam(value = "uid",required = false) String uid
    ) {
        return Result.success(blogService.delete(request,uid));
    }
}
