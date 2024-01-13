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


    public static Tag convertTagReq2Tag(String name,Long userId,String type){
        Tag tag = new Tag();
        tag = (Tag) GenerateUtils.generateFiledValue(tag);
        tag.setName(name);
        tag.setType(type);
        tag.setUser_id(userId);
        return tag;
    }


    public static TagInfo convertTag2TagInfo(Tag tag){
       return TagInfo.builder()
                .name(tag.getName())
                .type(tag.getType())
                .uid(String.valueOf(tag.getUid()))
                .build();
    }
}
