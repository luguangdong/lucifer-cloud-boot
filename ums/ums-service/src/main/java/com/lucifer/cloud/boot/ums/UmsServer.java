package com.lucifer.cloud.boot.ums;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lucifer
 * @date 2023/10/8 17:49
 */
@EnableDiscoveryClient
@SpringBootApplication
public class UmsServer {
    public static void main(String[] args) {
        SpringApplication.run(UmsServer.class, args);
    }
}
