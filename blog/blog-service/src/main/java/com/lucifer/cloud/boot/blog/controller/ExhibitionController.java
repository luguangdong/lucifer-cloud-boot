package com.lucifer.cloud.boot.blog.controller;
import com.lucifer.cloud.boot.blog.domin.dto.blog.BlogReq;
import com.lucifer.cloud.boot.blog.domin.dto.exhitition.ExhibitionDto;
import com.lucifer.cloud.boot.blog.domin.dto.exhitition.ExhibitionReq;
import com.lucifer.cloud.boot.blog.service.ExhibitionService;
import com.lucifer.cloud.commons.model.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author lucifer
 * @date 2023/12/21 15:31
 */
@RestController
@RequestMapping("exhibition")
public class ExhibitionController {

    @Resource
    private HttpServletRequest request;
    @Resource
    private ExhibitionService exhibitionService;


    @GetMapping("info")
    public Result<ExhibitionDto> Info(
            @RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
            @RequestParam(value = "limit",required = false,defaultValue = "1") Integer limit,
            @RequestParam(value = "type",required = false) Integer type,
             @RequestParam(value = "sort",required = false) String sort
    ) {

        return Result.success(exhibitionService.info(request,page,limit,type,sort));
    }


    @PostMapping("create")
    public Result<Boolean> create(
            @RequestBody ExhibitionReq exhibitionReq
    ) {
        return Result.success(exhibitionService.create(request,exhibitionReq));
    }

}
