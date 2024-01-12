package com.lucifer.cloud.boot.blog.service.impl;
import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucifer.cloud.boot.blog.config.UserSystem;
import com.lucifer.cloud.boot.blog.domin.bo.Article;
import com.lucifer.cloud.boot.blog.domin.bo.User;
import com.lucifer.cloud.boot.blog.domin.dto.article.ArticleConverter;
import com.lucifer.cloud.boot.blog.domin.dto.article.ArticleDto;
import com.lucifer.cloud.boot.blog.domin.dto.article.ArticleReq;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserConverter;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserInfo;
import com.lucifer.cloud.boot.blog.mapper.ArticleMapper;
import com.lucifer.cloud.boot.blog.mapper.UserMapper;
import com.lucifer.cloud.boot.blog.service.ArticleService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author lucifer
 * @date 2023/12/21 15:24
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private UserSystem userSystem;

    @Resource
    private UserMapper userMapper;

    @Override
    public ArticleDto info(HttpServletRequest request, Integer page, Integer limit, Integer type, String sort) {

        Long userId = userSystem.userId(request);
        User user = userMapper.selectById(userId);
        UserInfo user_info = UserConverter.convertInfo(user);

        List<Article> articleList = Lists.newArrayList();
        Long count = 0L;
        if(Objects.isNull(page) && Objects.isNull(limit)){
            articleList = list(Wrappers.lambdaQuery(Article.class)
                    .eq(Article::getUser_id, userId)
                    .last(StringUtils.isNotBlank(sort), "order by " + sort));
            count = Long.valueOf(articleList.size());
        }else {
            Page<Article> rowPage = new Page<>(page, limit);
            Page<Article> articlePage = this.baseMapper.selectPage(rowPage,Wrappers.lambdaQuery(Article.class)
                    .eq(Article::getUser_id,userId)
                    .last(StringUtils.isNotBlank(sort),"order by "+ sort)
            );
            articleList = articlePage.getRecords();
            count = articlePage.getTotal();
        }

        return ArticleConverter.converter2Dto(articleList,user_info,userId);
    }

    @Override
    public Boolean create(HttpServletRequest request, ArticleReq articleReq) {
        Long userId = userSystem.userId(request);
        Article article = ArticleConverter.converterReq2Article(articleReq,userId);
        return save(article);
    }
}
