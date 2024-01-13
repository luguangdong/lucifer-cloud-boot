package com.lucifer.cloud.boot.blog.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucifer.cloud.boot.blog.domin.bo.Comment;
import com.lucifer.cloud.boot.blog.domin.dto.comment.CommentDto;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author lucifer
 * @date 2023/12/21 15:23
 */
public interface CommentService extends IService<Comment> {


    CommentDto info(HttpServletRequest request,String content_id, Integer page, Integer limit, String type);

    Boolean create(HttpServletRequest request, String contentId, String content, String type);
}
