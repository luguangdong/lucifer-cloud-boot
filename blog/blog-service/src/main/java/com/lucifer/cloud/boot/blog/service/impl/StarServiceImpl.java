package com.lucifer.cloud.boot.blog.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucifer.cloud.boot.blog.domin.bo.Star;
import com.lucifer.cloud.boot.blog.mapper.StarMapper;
import com.lucifer.cloud.boot.blog.service.StarService;
import org.springframework.stereotype.Service;

/**
 * @author lucifer
 * @date 2023/12/21 15:24
 */
@Service
public class StarServiceImpl extends ServiceImpl<StarMapper, Star> implements StarService {


}
