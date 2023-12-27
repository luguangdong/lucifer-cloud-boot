package com.lucifer.cloud.boot.blog.service;
import com.lucifer.cloud.boot.blog.domin.dto.dashboard.DashboardDto;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author lucifer
 * @date 2023/12/27 15:25
 */
public interface DashboardService {
    DashboardDto dashboard(HttpServletRequest request);
}
