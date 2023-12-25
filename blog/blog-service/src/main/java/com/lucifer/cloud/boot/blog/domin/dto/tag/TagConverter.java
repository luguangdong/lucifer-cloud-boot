package com.lucifer.cloud.boot.blog.domin.dto.tag;


import com.lucifer.cloud.boot.blog.config.BlogType;
import com.lucifer.cloud.boot.blog.domin.bo.Tag;
import com.lucifer.cloud.boot.blog.domin.dto.blog.BlogReq;
import com.lucifer.cloud.boot.blog.util.GenerateUtils;

/**
 * @author lucifer
 * @date 2023/12/25 11:46
 */
public class TagConverter {

    public static Tag convertBlogReq2Tag(BlogReq blogReq, Long userId){
        Tag tag = new Tag();
        tag = (Tag) GenerateUtils.generateFiledValue(tag);
        tag.setName(blogReq.getTags());
        tag.setType(BlogType.BLOG.getCode());
        tag.setUser_id(userId);
        return tag;
    }
}
