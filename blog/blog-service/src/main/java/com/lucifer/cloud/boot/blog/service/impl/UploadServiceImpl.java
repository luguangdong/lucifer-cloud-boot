package com.lucifer.cloud.boot.blog.service.impl;

import com.alibaba.nacos.shaded.com.google.common.collect.Maps;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucifer.cloud.boot.blog.config.OSSUtil;
import com.lucifer.cloud.boot.blog.domin.bo.Upload;
import com.lucifer.cloud.boot.blog.domin.dto.upload.UploadConverter;
import com.lucifer.cloud.boot.blog.mapper.UploadMapper;
import com.lucifer.cloud.boot.blog.service.UploadService;
import com.lucifer.cloud.boot.blog.util.UserSystem;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author lucifer
 * @date 2023/12/21 15:24
 */
@Service
public class UploadServiceImpl extends ServiceImpl<UploadMapper, Upload> implements UploadService {

    @Resource
    OSSUtil ossUtil;

    @Resource
    private UserSystem userSystem;

    @Override
    public Map upload(HttpServletRequest request,String path,String file_name,MultipartFile file) {
        Long userId = userSystem.userId(request);
        Map<String, String> imgResult = ossUtil.putImgObject(path,file_name,file);
        Upload upload = UploadConverter.convertReq2Upload(userId, file_name, path, file, imgResult);
        boolean save = save(upload);
        if (save){
           return UploadConverter.convertImgRes2Res(imgResult);
        }
        return Maps.newHashMap();
    }
}
