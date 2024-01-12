package com.lucifer.cloud.boot.blog.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucifer.cloud.boot.blog.config.UserSystem;
import com.lucifer.cloud.boot.blog.domin.bo.Article;
import com.lucifer.cloud.boot.blog.domin.dto.article.ArticleConverter;
import com.lucifer.cloud.boot.blog.domin.dto.article.ArticleDto;
import com.lucifer.cloud.boot.blog.domin.dto.article.ArticleReq;
import com.lucifer.cloud.boot.blog.mapper.ArticleMapper;
import com.lucifer.cloud.boot.blog.service.ArticleService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

/**
 * @author lucifer
 * @date 2023/12/21 15:24
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private UserSystem userSystem;

    @Override
    public ArticleDto info(HttpServletRequest request, Integer page, Integer limit, Integer type, String sort) {
        return ArticleDto.builder().build();
    }

    @Override
    public Boolean create(HttpServletRequest request, ArticleReq articleReq) {
        Long userId = userSystem.userId(request);
        Article article = ArticleConverter.converterReq2Article(articleReq,userId);
        return save(article);
    }
}
