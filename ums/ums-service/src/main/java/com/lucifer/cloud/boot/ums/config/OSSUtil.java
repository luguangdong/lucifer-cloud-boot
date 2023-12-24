package com.lucifer.cloud.boot.ums.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * @author lucifer
 * @date 2023/12/24 16:15
 */
@Slf4j
@Configuration
public class OSSUtil {

    @Value("${oss.aliyun.endpoint}")
    private String endpoint;

    @Value("${oss.aliyun.accessKeyId}")
    private String accessKeyId;

    @Value("${oss.aliyun.accessKeySecret}")
    private String accessKeySecret;

    @Value("${oss.aliyun.bucket}")
    private String bucket;

    @Value("${oss.aliyun.dir}")
    private String dir;





    /**
     * 删除单个图片
     */
    public void deleteImg(String url) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        url = dir + url;
        ossClient.deleteObject(bucket, url);
        ossClient.shutdown();
    }

    /**
     * 上传图片
     */
    public String uploadImg2Oss(MultipartFile file, String fileName){
        try {
            InputStream inputStream = file.getInputStream();
            this.uploadFile2OSS(inputStream, fileName);
            String url = "https://" + bucket + "." + endpoint + "/" + dir + fileName;
            return url;
        } catch (Exception e) {
            log.error("图片上传失败{}",e);
        }
        return null;
    }

    /**
     * 上传到OSS服务器 如果同名文件会覆盖服务器上的
     */
    public String uploadFile2OSS(InputStream instream, String fileName) {
        String ret = "";
        try {
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setObjectAcl(CannedAccessControlList.PublicRead);
            objectMetadata.setContentLength(instream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            // 上传文件
            PutObjectResult putResult = ossClient.putObject(bucket, dir + "/" +fileName, instream, objectMetadata);
            ret = putResult.getETag();
        } catch (IOException e) {
            log.error("文件上传失败{}",e);
        } finally {
            try {
                if (instream != null) {
                    instream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * Description: 判断OSS服务文件上传时文件的contentType
     */
    public static String getcontentType(String filenameExtension) {
        if (filenameExtension.equalsIgnoreCase("bmp")) {
            return "image/bmp";
        }
        if (filenameExtension.equalsIgnoreCase("gif")) {
            return "image/gif";
        }
        if (filenameExtension.equalsIgnoreCase("jpeg") || filenameExtension.equalsIgnoreCase("jpg")
                || filenameExtension.equalsIgnoreCase("png")) {
            return "image/jpg";
        }
        if (filenameExtension.equalsIgnoreCase("html")) {
            return "text/html";
        }
        if (filenameExtension.equalsIgnoreCase("txt")) {
            return "text/plain";
        }
        if (filenameExtension.equalsIgnoreCase("vsd")) {
            return "application/vnd.visio";
        }
        if (filenameExtension.equalsIgnoreCase("pptx") || filenameExtension.equalsIgnoreCase("ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (filenameExtension.equalsIgnoreCase("docx") || filenameExtension.equalsIgnoreCase("doc")) {
            return "application/msword";
        }
        if (filenameExtension.equalsIgnoreCase("xml")) {
            return "text/xml";
        }
        return "image/jpg";
    }

    /**
     * 获得url链接
     *
     * @param key
     * @return
     */
    public String getUrl(String key) {
        // 设置URL过期时间为10年 3600l* 1000*24*365*10
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(bucket, key, expiration);
        // url = "https://" + bucketName + ".oss-cn-beijing.aliyuncs.com/" + bucketName+"/"+ key;
        if (url != null) {
            String host = "https://" + url.getHost() + url.getPath();
            return host;
        }
        return "";
    }

}
