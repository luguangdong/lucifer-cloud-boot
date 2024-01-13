package com.lucifer.cloud.boot.blog.service.impl;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucifer.cloud.boot.blog.config.BlogConstant;
import com.lucifer.cloud.boot.blog.config.UserSystem;
import com.lucifer.cloud.boot.blog.domin.bo.Exhibition;
import com.lucifer.cloud.boot.blog.domin.bo.Star;
import com.lucifer.cloud.boot.blog.domin.dto.star.StarConverter;
import com.lucifer.cloud.boot.blog.mapper.ExhibitionMapper;
import com.lucifer.cloud.boot.blog.mapper.StarMapper;
import com.lucifer.cloud.boot.blog.service.StarService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author lucifer
 * @date 2023/12/21 15:24
 */
@Service
public class StarServiceImpl extends ServiceImpl<StarMapper, Star> implements StarService {

    @Resource
    private UserSystem userSystem;

    @Resource
    private ExhibitionMapper exhibitionMapper;

    @Override
    public Boolean star(HttpServletRequest request, String uid, String star_type, String type) {
        Long userId = userSystem.userId(request);

        Boolean starRes = Boolean.FALSE;

        Star sr = getOne(Wrappers.lambdaQuery(Star.class).eq(Star::getUser_id, userId).eq(Star::getStar_id, uid));
        if (Objects.isNull(sr)){
            Star star = StarConverter.converterReq2Star(userId,uid, star_type, type);
            starRes = save(star);
        }else {
            starRes = update(Wrappers.lambdaUpdate(Star.class)
                    .set(Star::getUpdated_at, LocalDateTime.now())
                    .set(Star::getStar_type,star_type)
                    .eq(Star::getUser_id,userId)
                    .eq(Star::getStar_id,uid)
                    .eq(Star::getType,type));
        }
        exhibitionMapper.update(new Exhibition(),Wrappers.lambdaUpdate(Exhibition.class)
                .set(Exhibition::getUpdated_at,LocalDateTime.now())
                .setSql(BlogConstant.STAR_TYPE.equals(star_type) ?  "`thumbs_up`=`thumbs_up`+1":"`thumbs_up`=`thumbs_up`-1").eq(Exhibition::getUid,uid)
                .eq(Exhibition::getUser_id,userId));

        return starRes;
    }
}
