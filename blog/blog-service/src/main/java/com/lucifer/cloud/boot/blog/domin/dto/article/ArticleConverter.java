package com.lucifer.cloud.boot.blog.domin.dto.article;

import com.lucifer.cloud.boot.blog.domin.bo.Article;
import com.lucifer.cloud.boot.blog.util.GenerateUtils;
import org.springframework.beans.BeanUtils;

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
}
