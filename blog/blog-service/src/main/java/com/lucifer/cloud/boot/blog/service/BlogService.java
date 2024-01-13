package com.lucifer.cloud.boot.blog.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucifer.cloud.boot.blog.domin.bo.Blog;
import com.lucifer.cloud.boot.blog.domin.dto.blog.BlogInfoDto;
import com.lucifer.cloud.boot.blog.domin.dto.blog.BlogReq;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author lucifer
 * @date 2023/12/21 15:23
 */
public interface BlogService extends IService<Blog> {


    BlogInfoDto info(HttpServletRequest request, Long _t,String uid, Integer page, Integer limit);


    Boolean create(HttpServletRequest request, BlogReq blogReq);

    Boolean update(HttpServletRequest request,BlogReq blogReq);

    Boolean delete(HttpServletRequest request,String uid);

}
