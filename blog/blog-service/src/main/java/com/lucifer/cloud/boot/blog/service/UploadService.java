package com.lucifer.cloud.boot.blog.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucifer.cloud.boot.blog.domin.bo.Upload;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author lucifer
 * @date 2023/12/21 15:23
 */
public interface UploadService extends IService<Upload> {

    Map upload(HttpServletRequest request, String path,String file_name,MultipartFile file);


}
