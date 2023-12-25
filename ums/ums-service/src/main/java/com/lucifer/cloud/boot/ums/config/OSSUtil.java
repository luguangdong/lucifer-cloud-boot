package com.lucifer.cloud.boot.ums.config;

import com.alibaba.nacos.shaded.com.google.common.collect.Maps;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyun.oss.model.VoidResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Map;

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


    public static void main(String[] args) {
        String endpoint ="https://oss-cn-beijing.aliyuncs.com";
        String accessKeyId ="";
        String accessKeySecret ="";
        String bucket ="lucifer-cloud";
        String dir ="blog";
        String objectName  = "blog/1738512444349272064/blog/aa.jpg";
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        VoidResult result = ossClient.deleteObject(bucket, objectName);
        ossClient.shutdown();
    }


    /**
     * 删除文件
     * @param objectName
     * 填写objectName完整路径，例如exampledir/exampleobject.txt。objectName完整路径中不能包含Bucket名称。
     */
    public void deleteObject(String objectName) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            ossClient.deleteObject(bucket, objectName);
        } catch (OSSException oe) {
            log.error("服务端OSS删除文件={}失败。Host ID={},Request ID={},Error Code={},Error Message={}",
                    objectName,oe.getHostId(),oe.getRequestId(),oe.getErrorCode(),oe.getMessage()
                    );
        } catch (ClientException ce) {
            log.error("客户端Client删除文件={}失败。Error Message={}",
                    objectName,ce.getMessage()
            );
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }


    /**
     * 上传文件
     */
    public String putObject(String fileName) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            InputStream inputStream = new FileInputStream(fileName);
            ossClient.putObject(bucket, dir + "/" +fileName, inputStream);
            String url = "https://" + bucket + "." + endpoint + "/" + dir + "/" + fileName;
            return url;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (OSSException oe) {
            log.error("服务端OSS上传文件={}失败。Host ID={},Request ID={},Error Code={},Error Message={}",
                    fileName,oe.getHostId(),oe.getRequestId(),oe.getErrorCode(),oe.getMessage()
            );
        } catch (ClientException ce) {
            log.error("客户端Client删除文件={}失败。Error Message={}",
                    fileName,ce.getMessage()
            );
        }  finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return null;
    }

    /**
     * 上传图片
     */
    public  Map<String, String> putImgObject(String path,String file_name,MultipartFile file){
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            String fileName = path +"/"+file_name;
            InputStream inputStream = file.getInputStream();
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setObjectAcl(CannedAccessControlList.PublicRead);
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getContentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            PutObjectResult result = ossClient.putObject(bucket, dir + "/" + fileName, inputStream, objectMetadata);
            String tag = result.getETag();
            String url = "https://" + bucket + "." + endpoint + "/" + dir + "/" + fileName;
            Map<String, String> map = Maps.newHashMap();
            map.put("tag",tag);
            map.put("url",url);
            map.put("fileName",fileName);
            return map;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (OSSException oe) {
            log.error("服务端OSS上传图片文件={}失败。Host ID={},Request ID={},Error Code={},Error Message={}",
                    file_name,oe.getHostId(),oe.getRequestId(),oe.getErrorCode(),oe.getMessage()
            );
        } catch (ClientException ce) {
            log.error("客户端Client删除图片文件={}失败。Error Message={}",
                    file_name,ce.getMessage()
            );
        }  finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return null;
    }



    /**
     * Description: 判断OSS服务文件上传时文件的contentType
     */
    public static String getContentType(String filenameExtension) {
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
