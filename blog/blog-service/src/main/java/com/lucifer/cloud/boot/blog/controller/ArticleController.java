package com.lucifer.cloud.boot.blog.controller;
import com.lucifer.cloud.boot.blog.domin.dto.article.ArticleDto;
import com.lucifer.cloud.boot.blog.domin.dto.article.ArticleReq;
import com.lucifer.cloud.boot.blog.service.ArticleService;
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
@RequestMapping("article")
public class ArticleController {

    @Resource
    private HttpServletRequest request;

    @Resource
    private ArticleService articleService;

    @GetMapping("info")
    public Result<ArticleDto> info(
            @RequestParam(value = "page",required = false) Integer page,
            @RequestParam(value = "limit",required = false) Integer limit,
            @RequestParam(value = "type",required = false) Integer type,
            @RequestParam(value = "sort",required = false) String sort
    ) {

        return Result.success(articleService.info(request,page,limit,type,sort));
    }

    @PostMapping("create")
    public Result<Boolean> create(
            @RequestBody ArticleReq articleReq
    ) {

        return Result.success(articleService.create(request,articleReq));
    }



}
