package com.lucifer.cloud.boot.blog.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucifer.cloud.boot.blog.domin.bo.Article;
import com.lucifer.cloud.boot.blog.domin.dto.article.ArticleDto;
import com.lucifer.cloud.boot.blog.domin.dto.article.ArticleReq;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author lucifer
 * @date 2023/12/21 15:23
 */
public interface ArticleService extends IService<Article> {
    ArticleDto info(HttpServletRequest request,String uid, Integer page, Integer limit, Integer type, String sort);

    Boolean create(HttpServletRequest request, ArticleReq articleReq);

    Boolean delete(HttpServletRequest request, String uid);
}
