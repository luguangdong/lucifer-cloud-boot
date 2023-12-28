package com.lucifer.cloud.boot.blog.domin.dto.notice;
import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.lucifer.cloud.boot.blog.domin.bo.Notice;
import com.lucifer.cloud.boot.blog.util.GenerateUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author lucifer
 * @date 2023/12/28 9:48
 */
public class NoticeConverter {

    public static NoticeDto converterList2Dto(List<Notice> noticeList,Long total){
        List<NoticeInfo> list = Optional.ofNullable(noticeList).orElse(Lists.newArrayList())
                .stream()
                .map(notice -> {
                    NoticeInfo noticeInfo = new NoticeInfo();
                    noticeInfo.setUid(String.valueOf(notice.getUid()));
                    noticeInfo.setUser_id(String.valueOf(notice.getUser_id()));
                    noticeInfo.setContent(notice.getContent());
                    return noticeInfo;
                }).collect(Collectors.toList());

        return NoticeDto.builder().count(total).infos(list).build();

    }


    public static Notice converterReq2Notice(String content,Long userId){
        Notice notice = new Notice();
        notice = (Notice) GenerateUtils.generateFiledValue(notice);
        notice.setContent(content);
        notice.setUser_id(userId);
        return notice;
    }
}
