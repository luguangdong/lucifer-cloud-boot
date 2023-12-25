package com.lucifer.cloud.boot.ums.controller.blog;
import com.alibaba.nacos.shaded.com.google.common.collect.Maps;
import com.lucifer.cloud.boot.ums.config.OSSUtil;
import com.lucifer.cloud.commons.model.Result;
import jakarta.annotation.Resource;
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
    OSSUtil ossUtil;

    @PostMapping("upload")
    public Result<Map> upload(
            String file_name,
            String path,
            MultipartFile file
    ) {
        String fileName = path +"/"+file_name;
        String url = ossUtil.putImgObject(file, fileName);
        Map<String, Object> map = Maps.newHashMap();
        map.put("url",url);
        map.put("key",fileName);
        map.put("file_id",fileName);
        return Result.success(map);
    }
}
