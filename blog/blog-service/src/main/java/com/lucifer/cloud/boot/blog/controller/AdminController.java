package com.lucifer.cloud.boot.blog.controller;

import com.lucifer.cloud.boot.blog.domin.dto.admin.AdminExhibitionDto;
import com.lucifer.cloud.boot.blog.domin.dto.admin.AdminStatDto;
import com.lucifer.cloud.boot.blog.service.AdminService;
import com.lucifer.cloud.commons.model.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lucifer
 * @date 2023/12/28 10:48
 */
@RestController
@RequestMapping("admin")
public class AdminController {

    @Resource
    private HttpServletRequest request;

    @Resource
    private AdminService adminService;

    @GetMapping("stat")
    public Result<AdminStatDto> stat() {
        return Result.success(adminService.stat(request));
    }

    @GetMapping("exhibitions")
    public Result<AdminExhibitionDto> exhibitions(
            @RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
            @RequestParam(value = "limit",required = false,defaultValue = "1") Integer limit,
            @RequestParam(value = "type",required = false) Integer type,
            @RequestParam(value = "sort",required = false) String sort
    ) {
        return Result.success(adminService.exhibitions(request,page,limit,type,sort));
    }
}
