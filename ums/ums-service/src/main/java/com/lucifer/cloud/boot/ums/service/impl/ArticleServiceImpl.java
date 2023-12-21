package com.lucifer.cloud.boot.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucifer.cloud.boot.ums.entity.bo.Article;
import com.lucifer.cloud.boot.ums.mapper.ArticleMapper;
import com.lucifer.cloud.boot.ums.service.ArticleService;
import org.springframework.stereotype.Service;

/**
 * @author lucifer
 * @date 2023/12/21 15:24
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {


}
