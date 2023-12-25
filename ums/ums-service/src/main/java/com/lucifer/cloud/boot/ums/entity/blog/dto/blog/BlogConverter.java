package com.lucifer.cloud.boot.ums.entity.blog.dto.blog;
import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.lucifer.cloud.boot.ums.entity.blog.bo.Blog;
import com.lucifer.cloud.boot.ums.entity.blog.dto.user.UserInfo;
import com.lucifer.cloud.boot.ums.util.GenerateUtils;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * @author lucifer
 * @date 2023/12/24 13:59
 */
public class BlogConverter {

    public static List<BlogInfo> convertList2InfoList(List<Blog> blogList, UserInfo user_info){
        List<BlogInfo> blogInfoList = Optional.ofNullable(blogList).orElse(Lists.newArrayList())
                .stream()
                .map(blog -> {
                    BlogInfo info = new BlogInfo();
                    BeanUtils.copyProperties(blog,info);
                    String created = String.valueOf(blog.getCreated());
                    String updated = String.valueOf(blog.getUpdated());
                    String uid = String.valueOf(blog.getUid());
                    String user_id = String.valueOf(blog.getUser_id());
                    info.setCreated(created);
                    info.setUpdated(updated);
                    info.setUid(uid);
                    info.setUser_id(user_id);
                    info.setUser_info(user_info);
                    return info;
                }).collect(Collectors.toList());
        return blogInfoList;
    }


    public static Blog convertReq2blog(BlogReq blogReq, Long userId){
        Blog blog = new Blog();
        blog = (Blog)GenerateUtils.generateFiledValue(blog);
        blog.setUser_id(userId);
        BeanUtils.copyProperties(blogReq,blog);
        return blog;
    }


    public static Blog convertReq2Eeditblog(BlogReq blogReq){
        Blog blog = new Blog();
        long uid = Long.parseLong(blogReq.getUid());
        BeanUtils.copyProperties(blogReq,blog);
        blog.setUid(uid);
        return blog;
    }
}
