package com.lucifer.cloud.boot.blog.domin.dto.star;

import com.lucifer.cloud.boot.blog.config.BlogConstant;
import com.lucifer.cloud.boot.blog.domin.bo.Star;
import com.lucifer.cloud.boot.blog.util.GenerateUtils;

/**
 * @author lucifer
 * @date 2023/12/30 15:36
 */
public class StarConverter {

    public static Star converterReq2Star(Long userId, String uid, String star_type, String type){
        Star star = new Star();
        star = (Star)GenerateUtils.generateFiledValue(star);
        star.setUser_id(userId);
        star.setStar_id(Long.parseLong(uid));
        star.setStar_type(BlogConstant.STAR_TYPE.equals(star_type) ? true:null);
        star.setType(Integer.valueOf(type));
        return star;
    }
}
