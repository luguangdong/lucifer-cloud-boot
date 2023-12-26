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


    BlogInfoDto blogInfo(HttpServletRequest request, Long _t, Integer page, Integer limit);


    Boolean blogCreate(HttpServletRequest request, BlogReq blogReq);

    Boolean blogUpdate(HttpServletRequest request,BlogReq blogReq);

    Boolean blogDelete(HttpServletRequest request,String uid);

}
