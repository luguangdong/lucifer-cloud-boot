package com.lucifer.cloud.boot.blog.controller;
import com.lucifer.cloud.boot.blog.domin.dto.dashboard.DashboardDto;
import com.lucifer.cloud.boot.blog.service.DashboardService;
import com.lucifer.cloud.commons.model.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lucifer
 * @date 2023/12/27 15:07
 */
@RestController
public class DashboardController {

    @Resource
    private HttpServletRequest request;

    @Resource
    private DashboardService dashboardService;

    @GetMapping("dashboard")
    public Result<DashboardDto> dashboard() {
        return Result.success(dashboardService.dashboard(request));
    }
}
