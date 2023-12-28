package com.lucifer.cloud.boot.blog.controller;
import com.lucifer.cloud.boot.blog.domin.dto.notice.NoticeDto;
import com.lucifer.cloud.boot.blog.service.NoticeService;
import com.lucifer.cloud.commons.model.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author lucifer
 * @date 2023/12/21 15:31
 */
@RestController
@RequestMapping("notice")
public class NoticeController {


    @Resource
    private HttpServletRequest request;

    @Resource
    private NoticeService noticeService;


    @GetMapping("info")
    public Result<NoticeDto> info(
            @RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
            @RequestParam(value = "limit",required = false,defaultValue = "1") Integer limit
    ) {

        return Result.success(noticeService.info(request,page,limit));
    }


    @GetMapping("create")
    public Result<Boolean> create(
            @RequestParam(value = "content",required = false) String content
    ) {
        return Result.success(noticeService.create(request,content));
    }

}
