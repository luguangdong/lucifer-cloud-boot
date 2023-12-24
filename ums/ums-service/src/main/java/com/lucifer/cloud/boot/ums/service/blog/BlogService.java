package com.lucifer.cloud.boot.ums.service.blog;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucifer.cloud.boot.ums.entity.blog.bo.Blog;
import com.lucifer.cloud.boot.ums.entity.blog.dto.blog.BlogInfoDto;
import org.springframework.http.RequestEntity;

/**
 * @author lucifer
 * @date 2023/12/21 15:23
 */
public interface BlogService extends IService<Blog> {


    BlogInfoDto blogInfo(RequestEntity request, Long _t,Integer page,Integer limit);


}
