package com.lucifer.cloud.boot.blog.domin.dto.dashboard;

import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.alibaba.nacos.shaded.com.google.common.collect.Maps;
import com.lucifer.cloud.boot.blog.domin.bo.Blog;
import com.lucifer.cloud.boot.blog.domin.bo.Exhibition;
import com.lucifer.cloud.boot.blog.domin.bo.Follow;
import com.lucifer.cloud.boot.blog.domin.bo.User;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserConverter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author lucifer
 * @date 2023/12/27 15:09
 */
public class DashboardConverter {

    public static DashboardDto converter2Dto(List<Blog> blogList, List<Exhibition> exhibitionList,List<Follow> followList, User user ){
        Map<String, Long> blogDateMap = Optional.ofNullable(blogList).orElse(Lists.newArrayList())
                .stream()
                .collect(Collectors.groupingBy(blog -> {
                    LocalDateTime createdAt = blog.getCreated_at();
                    return createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                }, Collectors.counting()));

        Map<String, Long> exhibitionDateMap = Optional.ofNullable(exhibitionList).orElse(Lists.newArrayList())
                .stream()
                .collect(Collectors.groupingBy(exhibition -> {
                    LocalDateTime createdAt = exhibition.getCreated_at();
                    return createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                }, Collectors.counting()));

        Map<String, Long> moreMap = blogDateMap.size() >= exhibitionDateMap.size() ? blogDateMap : exhibitionDateMap;
        Map<String, Long> lessMap = blogDateMap.size() < exhibitionDateMap.size() ? blogDateMap : exhibitionDateMap;

        List<DashboardInfo> dashboard = Optional.ofNullable(moreMap).orElse(Maps.newHashMap())
                .entrySet()
                .stream()
                .map(moreEntry -> {
                    DashboardInfo dashboardInfo = new DashboardInfo();
                    dashboardInfo.setName(moreEntry.getKey());
                    dashboardInfo.setBlog_publish_value(String.valueOf(moreEntry.getValue()));
                    Optional.ofNullable(lessMap).orElse(Maps.newHashMap())
                            .entrySet()
                            .stream()
                            .peek(lessEntry -> {
                                if (moreEntry.getKey().equals(lessEntry.getKey())) {
                                    dashboardInfo.setExhibitions_publish_value(String.valueOf(lessEntry.getValue()));
                                }
                            });
                    return dashboardInfo;
                }).collect(Collectors.toList());


        List<DashboardExhibition> exhibitions = Optional.ofNullable(exhibitionList).orElse(Lists.newArrayList())
                .stream()
                .map(exhibition -> {
                    DashboardExhibition dashboardExhibition = new DashboardExhibition();
                    dashboardExhibition.setDes(exhibition.getDes());
                    dashboardExhibition.setCover(exhibition.getCover());
                    dashboardExhibition.setTitle(exhibition.getTitle());
                    dashboardExhibition.setUid(String.valueOf(exhibition.getUid()));
                    dashboardExhibition.setUser_id(String.valueOf(exhibition.getUser_id()));
                    return dashboardExhibition;
                }).collect(Collectors.toList());

        DashboardUser dashboardUser = convertBlog2Detail(blogList, followList, user);


        return DashboardDto.builder().dashboard(dashboard).exhibitions(exhibitions).user_info(dashboardUser).build();
    }



    public static DashboardUser convertBlog2Detail(List<Blog> blogList,List<Follow> followList,User user){
        DashboardUser dashboardUser = new DashboardUser();
        int publish = blogList.size();
        int likes = Optional.ofNullable(blogList).orElse(Lists.newArrayList())
                .stream().mapToInt(Blog::getCollection).sum();
        int thumbs_up = Optional.ofNullable(blogList).orElse(Lists.newArrayList())
                .stream().mapToInt(Blog::getThumbs_up).sum();
        int follows = followList.size();
        dashboardUser.setPublish(publish);
        dashboardUser.setLike(likes);
        dashboardUser.setFollowing(follows);
        dashboardUser.setThumbs_up(thumbs_up);
        dashboardUser.setDashboard_user(UserConverter.convertInfo(user));
        return dashboardUser;
    }
}
