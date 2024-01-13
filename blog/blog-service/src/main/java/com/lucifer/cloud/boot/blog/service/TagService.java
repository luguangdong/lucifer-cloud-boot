package com.lucifer.cloud.boot.blog.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucifer.cloud.boot.blog.domin.bo.Tag;
import com.lucifer.cloud.boot.blog.domin.dto.tag.TagDto;
import jakarta.servlet.http.HttpServletRequest;
/**
 * @author lucifer
 * @date 2023/12/21 15:23
 */
public interface TagService extends IService<Tag> {
    TagDto info(HttpServletRequest request, String type,String uid);

    Boolean create(HttpServletRequest request, String type, String name);
}
