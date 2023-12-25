package com.lucifer.cloud.boot.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lucifer
 * @date 2023/12/25 14:25
 */
@EnableDiscoveryClient
@SpringBootApplication
public class BlogServer {
    public static void main(String[] args) {
        SpringApplication.run(BlogServer.class, args);
    }
}
