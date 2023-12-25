package com.lucifer.cloud.boot.blog.controller;
import com.lucifer.cloud.boot.blog.domin.bo.Article;
import com.lucifer.cloud.boot.blog.service.ArticleService;
import com.lucifer.cloud.commons.model.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lucifer
 * @date 2023/12/21 15:31
 */
@RestController
@RequestMapping("article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @GetMapping("list")
    public Result<List<Article>> loadAll() {
        List<Article> list = articleService.list();
        return Result.success(list);
    }



}
