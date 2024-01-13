package com.lucifer.cloud.boot.ums.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lucifer
 * @date 2023/10/8 18:02
 */
@RestController
public class TestController {

    @GetMapping("/test01")
    @PreAuthorize("hasAuthority('SCOPE_message.read')")
    public String test01() {
        return "test01";
    }

    @GetMapping("/test02")
    public String test02() {
        return "test02";
    }

    @GetMapping("/app")
    @PreAuthorize("hasAuthority('app')")
    public String app() {
        return "app";
    }

}
