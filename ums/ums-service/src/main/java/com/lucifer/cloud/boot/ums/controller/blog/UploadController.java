package com.lucifer.cloud.boot.ums.controller.blog;
import com.lucifer.cloud.boot.ums.service.blog.UploadService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author lucifer
 * @date 2023/12/21 15:31
 */
@RestController
@RequestMapping("upload")
public class UploadController {
    @Resource
    private UploadService uploadService;

}
