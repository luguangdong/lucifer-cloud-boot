package com.lucifer.cloud.boot.blog.domin.dto.upload;

import com.alibaba.nacos.shaded.com.google.common.collect.Maps;
import com.lucifer.cloud.boot.blog.domin.bo.Upload;
import com.lucifer.cloud.boot.blog.util.GenerateUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author lucifer
 * @date 2023/12/25 13:41
 */
public class UploadConverter {
    public static Upload convertReq2Upload(Long userId, String file_name, String path, MultipartFile file, Map<String, String> imgResult){
        Upload upload = new Upload();
        upload = (Upload) GenerateUtils.generateFiledValue(upload);
        upload.setHash(imgResult.get("tag"));
        upload.setFile_name(file_name);
        upload.setFile_path(path);
        String ext = file_name.substring(file_name.lastIndexOf("."));
        upload.setExt(ext);
        upload.setSize(file.getSize());
        upload.setUser_id(userId);
        return upload;
    }


    public static Map convertImgRes2Res(Map<String, String> imgResult){
        Map<String, Object> map = Maps.newHashMap();
        map.put("url",imgResult.get("url"));
        map.put("key",imgResult.get("fileName"));
        map.put("file_id",imgResult.get("url"));
        return map;
    }
}
