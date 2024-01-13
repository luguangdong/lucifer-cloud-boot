package com.lucifer.cloud.boot.blog.domin.dto.article;

import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.lucifer.cloud.boot.blog.domin.bo.Article;
import com.lucifer.cloud.boot.blog.domin.bo.Comment;
import com.lucifer.cloud.boot.blog.domin.bo.Follow;
import com.lucifer.cloud.boot.blog.domin.bo.Likes;
import com.lucifer.cloud.boot.blog.domin.bo.Star;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserInfo;
import com.lucifer.cloud.boot.blog.util.GenerateUtils;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Map;
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


    public static ArticleDto converter2Dto(List<Article> articleList, UserInfo user_info, Long userId, List<Comment> commentList, List<Star> starList, List<Follow> followList, List<Likes>  likesList,List<Article> articles){
        Map<Long, Long> commentCountMap = Optional.ofNullable(commentList).orElse(Lists.newArrayList())
                .stream()
                .collect(Collectors.groupingBy(Comment::getContent_id, Collectors.counting()));

        Map<Long, Long> starCountMap = Optional.ofNullable(starList).orElse(Lists.newArrayList())
                .stream()
                .collect(Collectors.groupingBy(Star::getStar_id, Collectors.counting()));

        List<ArticleInfo> article_info = Optional.ofNullable(articleList).orElse(Lists.newArrayList())
                .stream().map(article -> {
                    ArticleInfo articleInfo = new ArticleInfo();
                    BeanUtils.copyProperties(article,articleInfo);
                    articleInfo.setUser_info(user_info);
                    articleInfo.setUser_id(String.valueOf(userId));
                    articleInfo.setUid(String.valueOf(article.getUid()));
                    articleInfo.setComment(commentCountMap.get(article.getUid()));
                    articleInfo.setThumbs_up(starCountMap.get(article.getUid()));
                    return articleInfo;
                }).collect(Collectors.toList());

        List<String> follow_ids = Optional.ofNullable(followList).orElse(Lists.newArrayList())
                .stream().map(Follow::getUser_id).map(String::valueOf).collect(Collectors.toList());

        List<String> star_ids = Optional.ofNullable(starList).orElse(Lists.newArrayList())
                .stream().map(Star::getUser_id).map(String::valueOf).collect(Collectors.toList());

        CardInfo cardInfo = new CardInfo();
        cardInfo.setArticle(articles.stream().count());
        cardInfo.setComment(commentList.stream().count());
        cardInfo.setFans(likesList.stream().count());
        cardInfo.setFollow_ids(follow_ids);
        cardInfo.setStar_ids(star_ids);
        cardInfo.setFollow(followList.stream().count());
        cardInfo.setThumb(starList.stream().count());
        return ArticleDto.builder().article_info(article_info).card_info(cardInfo).count(articleList.stream().count()).build();
    }
}
