package com.lucifer.cloud.boot.ums.service.blog.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucifer.cloud.boot.ums.entity.blog.bo.Star;
import com.lucifer.cloud.boot.ums.mapper.blog.StarMapper;
import com.lucifer.cloud.boot.ums.service.blog.StarService;
import org.springframework.stereotype.Service;

/**
 * @author lucifer
 * @date 2023/12/21 15:24
 */
@Service
public class StarServiceImpl extends ServiceImpl<StarMapper, Star> implements StarService {


}
