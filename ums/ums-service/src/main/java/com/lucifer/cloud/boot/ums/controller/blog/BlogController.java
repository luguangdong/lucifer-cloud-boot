package com.lucifer.cloud.boot.ums.controller.blog;
import com.lucifer.cloud.boot.ums.entity.blog.dto.blog.BlogInfoDto;
import com.lucifer.cloud.boot.ums.service.blog.BlogService;
import com.lucifer.cloud.commons.model.Result;
import jakarta.annotation.Resource;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    private BlogService blogService;


    @GetMapping("info")
    public Result<BlogInfoDto> blogInfo(
            RequestEntity request,
            @RequestParam(value = "_t",required = false) Long _t,
            @RequestParam(value = "page",required = false) Integer page,
            @RequestParam(value = "limit",required = false) Integer limit
    ) {
        return Result.success(blogService.blogInfo(request,_t,page,limit));
    }

}
