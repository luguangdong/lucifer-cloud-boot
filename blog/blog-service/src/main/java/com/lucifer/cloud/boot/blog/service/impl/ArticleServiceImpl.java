package com.lucifer.cloud.boot.blog.service.impl;
import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucifer.cloud.boot.blog.config.UserSystem;
import com.lucifer.cloud.boot.blog.domin.bo.Article;
import com.lucifer.cloud.boot.blog.domin.bo.Comment;
import com.lucifer.cloud.boot.blog.domin.bo.Follow;
import com.lucifer.cloud.boot.blog.domin.bo.Likes;
import com.lucifer.cloud.boot.blog.domin.bo.Star;
import com.lucifer.cloud.boot.blog.domin.bo.User;
import com.lucifer.cloud.boot.blog.domin.dto.article.ArticleConverter;
import com.lucifer.cloud.boot.blog.domin.dto.article.ArticleDto;
import com.lucifer.cloud.boot.blog.domin.dto.article.ArticleReq;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserConverter;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserInfo;
import com.lucifer.cloud.boot.blog.mapper.ArticleMapper;
import com.lucifer.cloud.boot.blog.mapper.CommentMapper;
import com.lucifer.cloud.boot.blog.mapper.FollowMapper;
import com.lucifer.cloud.boot.blog.mapper.LikesMapper;
import com.lucifer.cloud.boot.blog.mapper.StarMapper;
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

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private StarMapper starMapper;

    @Resource
    private FollowMapper followMapper;

    @Resource
    private LikesMapper  likesMapper;

    @Override
    public ArticleDto info(HttpServletRequest request,String uid, Integer page, Integer limit, Integer type, String sort) {

        Long userId = userSystem.userId(request);
        User user = userMapper.selectById(userId);
        UserInfo user_info = UserConverter.convertInfo(user);

        List<Article> articleList = Lists.newArrayList();
        if(Objects.isNull(page) && Objects.isNull(limit)){
            articleList = list(Wrappers.lambdaQuery(Article.class)
                    .eq(Article::getUser_id, userId)
                    .eq(StringUtils.isNotBlank(uid),Article::getUid,uid)
                    .last(StringUtils.isNotBlank(sort), "order by " + sort));
        }else {
            Page<Article> rowPage = new Page<>(page, limit);
            Page<Article> articlePage = this.baseMapper.selectPage(rowPage,Wrappers.lambdaQuery(Article.class)
                    .eq(Article::getUser_id,userId)
                    .eq(StringUtils.isNotBlank(uid),Article::getUid,uid)
                    .last(StringUtils.isNotBlank(sort),"order by "+ sort)
            );
            articleList = articlePage.getRecords();
        }

        List<Comment> commentList = commentMapper.selectList(Wrappers.lambdaQuery(Comment.class).eq(Comment::getUser_id, userId));
        List<Star> starList = starMapper.selectList(Wrappers.lambdaQuery(Star.class).eq(Star::getUser_id, userId).eq(Star::getStar_type,Boolean.TRUE));
        List<Follow> followList = followMapper.selectList(Wrappers.lambdaQuery(Follow.class).eq(Follow::getFollow_user_id, uid).eq(Follow::getFollow_type,Boolean.TRUE));
        List<Likes>  likesList = likesMapper.selectList(Wrappers.lambdaQuery(Likes.class).eq(Likes::getUser_id, userId).eq(Likes::getLikes_type,Boolean.TRUE));
        List<Article> articles = list(Wrappers.lambdaQuery(Article.class).eq(Article::getUser_id, userId));


        return ArticleConverter.converter2Dto(articleList,user_info,userId,commentList,starList,followList,likesList, articles);
    }

    @Override
    public Boolean create(HttpServletRequest request, ArticleReq articleReq) {
        Long userId = userSystem.userId(request);
        Article article = ArticleConverter.converterReq2Article(articleReq,userId);
        return save(article);
    }

    @Override
    public Boolean delete(HttpServletRequest request, String uid) {
        Long userId = userSystem.userId(request);
        boolean remove = remove(Wrappers.lambdaQuery(Article.class).eq(Article::getUid, uid).eq(Article::getUser_id, userId));
        if (remove){
            commentMapper.delete(Wrappers.lambdaQuery(Comment.class).eq(Comment::getContent_id,uid).eq(Comment::getUser_id,userId));
            starMapper.delete(Wrappers.lambdaQuery(Star.class).eq(Star::getStar_id,uid).eq(Star::getUser_id,userId));
        }
        return remove;
    }
}
