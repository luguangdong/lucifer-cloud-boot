package com.lucifer.cloud.boot.blog.service.impl;

import com.lucifer.cloud.boot.blog.domin.dto.admin.AdminExhibitionDto;
import com.lucifer.cloud.boot.blog.domin.dto.admin.AdminStatDto;
import com.lucifer.cloud.boot.blog.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

/**
 * @author lucifer
 * @date 2023/12/28 10:51
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Override
    public AdminStatDto stat(HttpServletRequest request) {
        return AdminStatDto.builder().build();
    }

    @Override
    public AdminExhibitionDto exhibitions(HttpServletRequest request, Integer page, Integer limit, Integer type, String sort) {
        return AdminExhibitionDto.builder().build();
    }
}
