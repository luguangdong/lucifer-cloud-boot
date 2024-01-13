package com.lucifer.cloud.boot.blog.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucifer.cloud.boot.blog.domin.bo.Notice;
import com.lucifer.cloud.boot.blog.domin.dto.notice.NoticeDto;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author lucifer
 * @date 2023/12/21 15:23
 */
public interface NoticeService extends IService<Notice> {


    NoticeDto info(HttpServletRequest request, Integer page, Integer limit);

    Boolean create(HttpServletRequest request, String content);
}
