package com.lucifer.cloud.boot.blog.domin.dto.exhitition;
import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.lucifer.cloud.boot.blog.domin.bo.Exhibition;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserInfo;
import com.lucifer.cloud.boot.blog.util.GenerateUtils;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author lucifer
 * @date 2023/12/27 11:19
 */
public class ExhibitionConverter {

    public static ExhibitionDto converter2Dto(List<Exhibition> exhibitionList, Long count,  UserInfo user_info,List<String> likes_ids,List<String> star_ids){
        List<ExhibitionUser> exhibitionUserList = Optional.ofNullable(exhibitionList).orElse(Lists.newArrayList())
                .stream()
                .map(exhibition -> {
                    ExhibitionUser exhibitionUser = new ExhibitionUser();
                    BeanUtils.copyProperties(exhibition, exhibitionUser);
                    exhibitionUser.setUser_id(String.valueOf(exhibition.getUser_id()));
                    exhibitionUser.setUid(String.valueOf(exhibition.getUid()));
                    exhibitionUser.setUser_info(user_info);
                    return exhibitionUser;
                }).collect(Collectors.toList());


        return ExhibitionDto.builder().exhibitions(exhibitionUserList).count(count).likes_ids(likes_ids).star_ids(star_ids).build();
    }

    public static Exhibition convertReq2Exhibition(ExhibitionReq exhibitionReq, Long userId) {
        Exhibition exhibition = new Exhibition();
        exhibition = (Exhibition) GenerateUtils.generateFiledValue(exhibition);
        exhibition.setUser_id(userId);
        BeanUtils.copyProperties(exhibitionReq,exhibition);
        return exhibition;
    }
}
