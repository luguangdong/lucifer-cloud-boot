package com.lucifer.cloud.boot.blog.service;

import com.lucifer.cloud.boot.blog.domin.dto.admin.AdminExhibitionDto;
import com.lucifer.cloud.boot.blog.domin.dto.admin.AdminStatDto;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author lucifer
 * @date 2023/12/28 10:51
 */
public interface AdminService {
    AdminStatDto stat(HttpServletRequest request);

    AdminExhibitionDto exhibitions(HttpServletRequest request, Integer page, Integer limit, Integer type, String sort);
}
