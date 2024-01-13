package com.lucifer.cloud.boot.blog.controller;
import com.lucifer.cloud.boot.blog.service.UploadService;
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
@RequestMapping("upload")
public class UploadController {

    @Resource
    private HttpServletRequest request;

    @Resource
    private UploadService uploadService;


    @GetMapping("del")
    public Result<Boolean> delete(
            @RequestParam(value = "key",required = false)    String key
    ) {
        return Result.success("当前图片有其它引用",uploadService.delete(request,key));
    }
}
