package com.lucifer.cloud.boot.blog.service.impl;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucifer.cloud.boot.blog.config.UserSystem;
import com.lucifer.cloud.boot.blog.domin.bo.Notice;
import com.lucifer.cloud.boot.blog.domin.dto.notice.NoticeConverter;
import com.lucifer.cloud.boot.blog.domin.dto.notice.NoticeDto;
import com.lucifer.cloud.boot.blog.mapper.NoticeMapper;
import com.lucifer.cloud.boot.blog.service.NoticeService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lucifer
 * @date 2023/12/21 15:24
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Resource
    private UserSystem userSystem;


    @Override
    public NoticeDto info(HttpServletRequest request, Integer page, Integer limit) {
        Long userId = userSystem.userId(request);
        Page<Notice> rowPage = new Page<>(page, limit);
        Page<Notice> noticePage = this.baseMapper.selectPage(rowPage, Wrappers.lambdaQuery(Notice.class));
        List<Notice> noticeList = noticePage.getRecords();
        long total = noticePage.getTotal();
        return NoticeConverter.converterList2Dto(noticeList,total);
    }

    @Override
    public Boolean create(HttpServletRequest request, String content) {
        Long userId = userSystem.userId(request);
        Notice notice = NoticeConverter.converterReq2Notice(content, userId);
        return save(notice);
    }
}
