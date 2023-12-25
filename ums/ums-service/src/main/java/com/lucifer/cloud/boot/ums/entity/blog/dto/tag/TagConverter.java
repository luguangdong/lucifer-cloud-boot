package com.lucifer.cloud.boot.ums.entity.blog.dto.tag;
import com.lucifer.cloud.boot.ums.config.BlogType;
import com.lucifer.cloud.boot.ums.entity.blog.bo.Tag;
import com.lucifer.cloud.boot.ums.entity.blog.dto.blog.BlogReq;
import com.lucifer.cloud.boot.ums.util.GenerateUtils;

/**
 * @author lucifer
 * @date 2023/12/25 11:46
 */
public class TagConverter {

    public static Tag convertBlogReq2Tag(BlogReq blogReq,Long userId){
        Tag tag = new Tag();
        tag = (Tag) GenerateUtils.generateFiledValue(tag);
        tag.setName(blogReq.getTags());
        tag.setType(BlogType.BLOG.getCode());
        tag.setUser_id(userId);
        return tag;
    }
}
