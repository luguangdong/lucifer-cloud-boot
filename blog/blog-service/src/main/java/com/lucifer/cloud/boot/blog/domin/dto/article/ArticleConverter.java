package com.lucifer.cloud.boot.blog.domin.dto.article;

import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.lucifer.cloud.boot.blog.domin.bo.Article;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserInfo;
import com.lucifer.cloud.boot.blog.util.GenerateUtils;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author lucifer
 * @date 2024/1/12 16:48
 */
public class ArticleConverter {

    public static Article converterReq2Article(ArticleReq articleReq,Long userId){
        Article article = new Article();
        article = (Article)GenerateUtils.generateFiledValue(article);
        BeanUtils.copyProperties(articleReq,article);
        article.setUser_id(userId);
        return article;
    }


    public static ArticleDto converter2Dto(List<Article> articleList, UserInfo user_info,Long userId){
        List<ArticleInfo> article_info = Optional.ofNullable(articleList).orElse(Lists.newArrayList())
                .stream().map(article -> {
                    ArticleInfo articleInfo = new ArticleInfo();
                    BeanUtils.copyProperties(article,articleInfo);
                    articleInfo.setUser_info(user_info);
                    articleInfo.setUser_id(String.valueOf(userId));
                    articleInfo.setUid(String.valueOf(article.getUid()));
                    return articleInfo;
                }).collect(Collectors.toList());
        CardInfo cardInfo = new CardInfo();
        return ArticleDto.builder().article_info(article_info).card_info(cardInfo).count(articleList.stream().count()).build();
    }
}
