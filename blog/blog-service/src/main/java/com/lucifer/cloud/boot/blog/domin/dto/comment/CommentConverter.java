package com.lucifer.cloud.boot.blog.domin.dto.comment;

import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.lucifer.cloud.boot.blog.domin.bo.Comment;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserInfo;
import com.lucifer.cloud.boot.blog.util.GenerateUtils;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author lucifer
 * @date 2024/1/13 10:01
 */
public class CommentConverter {

    public static CommentDto converter2Dto(List<Comment> commentList, UserInfo user_info,Long userId){
        List<CommentInfo> infos = Optional.ofNullable(commentList).orElse(Lists.newArrayList())
                .stream()
                .map(comment -> {
                    CommentInfo commentInfo = new CommentInfo();
                    BeanUtils.copyProperties(comment, commentInfo);
                    commentInfo.setUser_info(user_info);
                    commentInfo.setUid(String.valueOf(comment.getUid()));
                    commentInfo.setUser_id(String.valueOf(userId));
                    return commentInfo;
                }).collect(Collectors.toList());

        return CommentDto.builder().infos(infos).count(commentList.stream().count()).build();
    }


    public static Comment converterReq2Comment(String contentId, String content, String type,Long userId){
        Comment comment = new Comment();
        comment = (Comment)GenerateUtils.generateFiledValue(comment);
        comment.setContent(content);
        comment.setType(type);
        comment.setUser_id(userId);
        comment.setContent_id(Long.valueOf(contentId));
        return comment;
    }
}
