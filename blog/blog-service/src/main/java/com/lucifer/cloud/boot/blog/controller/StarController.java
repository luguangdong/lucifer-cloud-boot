package com.lucifer.cloud.boot.blog.controller;
import com.lucifer.cloud.boot.blog.service.StarService;
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
public class StarController {

    @Resource
    private HttpServletRequest request;

    @Resource
    private StarService starService;

    @PostMapping("star")
    public Result<Boolean> star(
            @RequestParam(value = "uid",required = false) String uid,
            @RequestParam(value = "star_type",required = false) String star_type,
            @RequestParam(value = "type",required = false) String type
    ) {
        return Result.success(starService.star(request,uid,star_type,type));
    }

}
