package com.lucifer.cloud.boot.ums.controller.blog;
import com.alibaba.nacos.shaded.com.google.common.collect.Maps;
import com.lucifer.cloud.boot.ums.config.OSSUtil;
import com.lucifer.cloud.boot.ums.service.blog.UploadService;
import com.lucifer.cloud.boot.ums.util.UserSystem;
import com.lucifer.cloud.commons.model.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Resource
    OSSUtil ossUtil;

    @Resource
    private UserSystem userSystem;

    @GetMapping("upload")
    public Result<Map> upload(
            String file_name,
            String path) {
        String fileName = path + "/"+file_name;
        String url = "https://lucifer-cloud.oss-cn-beijing.aliyuncs.com/blog/"+fileName;
        Map<String, Object> map = Maps.newHashMap();
        map.put("url",url);
        map.put("key",file_name);
        map.put("file_id",url);
        return Result.success(map);
    }


    @PostMapping("upload")
    public void upload(
            String key,
            MultipartFile file) {
         ossUtil.uploadImg2Oss(file, key);
    }


}
