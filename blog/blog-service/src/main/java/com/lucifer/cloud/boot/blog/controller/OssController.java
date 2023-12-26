package com.lucifer.cloud.boot.blog.controller;

import com.lucifer.cloud.boot.blog.service.UploadService;
import com.lucifer.cloud.commons.model.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author lucifer
 * @date 2023/12/26 10:58
 */
@RestController
@RequestMapping("cos")
public class OssController {

    @Resource
    private HttpServletRequest request;

    @Resource
    private UploadService uploadService;

    @PostMapping("upload")
    public Result<Map> upload(
            String file_name,
            String path,
            MultipartFile file
    ) {
        return Result.success(uploadService.upload(request,path,file_name,file));
    }
}
