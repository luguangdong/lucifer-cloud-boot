package com.lucifer.cloud.boot.blog.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lucifer.cloud.boot.blog.domin.bo.Exhibition;
import com.lucifer.cloud.boot.blog.domin.dto.exhitition.ExhibitionDto;
import com.lucifer.cloud.boot.blog.domin.dto.exhitition.ExhibitionReq;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author lucifer
 * @date 2023/12/21 15:23
 */
public interface ExhibitionService extends IService<Exhibition> {


    ExhibitionDto info(HttpServletRequest request, Integer page, Integer limit, Integer type, String sort);


    Boolean create(HttpServletRequest request, ExhibitionReq exhibitionReq);

    Boolean delete(HttpServletRequest request, String uid);
}
