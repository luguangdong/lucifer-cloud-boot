package com.lucifer.cloud.boot.ums.controller.blog;
import com.lucifer.cloud.boot.ums.service.blog.UploadService;
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
 * @date 2023/12/21 15:31
 */
@RestController
@RequestMapping("cos")
public class UploadController {

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
