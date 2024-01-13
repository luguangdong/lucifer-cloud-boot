package com.lucifer.cloud.boot.blog.controller;
import com.lucifer.cloud.boot.blog.domin.dto.comment.CommentDto;
import com.lucifer.cloud.boot.blog.service.CommentService;
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
@RequestMapping("comment")
public class CommentController {

    @Resource
    private HttpServletRequest request;

    @Resource
    private CommentService commentService;


    @GetMapping("info")
    public Result<CommentDto> info(
            @RequestParam(value = "page",required = false) Integer page,
            @RequestParam(value = "limit",required = false) Integer limit,
            @RequestParam(value = "type",required = false) String type
    ) {
        return Result.success(commentService.info(request,page,limit,type));
    }


    @PostMapping("create")
    public Result<Boolean> create(
            @RequestParam(value = "content_id",required = false) String content_id,
            @RequestParam(value = "content",required = false) String content,
            @RequestParam(value = "type",required = false) String type
    ) {
        return Result.success(commentService.create(request,content_id,content,type));
    }

}
