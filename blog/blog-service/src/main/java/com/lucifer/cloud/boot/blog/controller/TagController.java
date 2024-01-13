package com.lucifer.cloud.boot.blog.controller;
import com.lucifer.cloud.boot.blog.domin.dto.tag.TagDto;
import com.lucifer.cloud.boot.blog.service.TagService;
import com.lucifer.cloud.commons.model.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lucifer
 * @date 2023/12/21 15:31
 */
@RestController
@RequestMapping("tags")
public class TagController {

    @Resource
    private HttpServletRequest request;
    @Resource
    private TagService tagService;


    @GetMapping("info")
    public Result<TagDto> info(
            @RequestParam(value = "type",required = false) String type,
            @RequestParam(value = "uids",required = false) String uid
    ) {
        return Result.success(tagService.info(request,type,uid));
    }


    @PostMapping("create")
    public Result<Boolean> create(
            @RequestParam(value = "type",required = false) String type,
           @RequestParam(value = "name",required = false) String name
    ) {
        return Result.success(tagService.create(request,type,name));
    }

}
