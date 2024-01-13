package com.lucifer.cloud.boot.blog.domin.dto.dashboard;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author lucifer
 * @date 2023/12/27 15:09
 */
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DashboardDto {

    private List<DashboardInfo> dashboard;

     private List<DashboardExhibition> exhibitions;

    private DashboardUser user_info;
}
