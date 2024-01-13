package com.lucifer.cloud.boot.blog.config;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
/**
 * @author lucifer
 * @date 2024/1/12 11:12
 */
@Getter
public enum ImageStatusType {

    UPLOAD(1,"Upload","上传"),
    HEART(2,"Heart","收藏"),
    OPEN(3,"Open","公开");

    private final Integer index;

    private final String code;

    private final String name;


    ImageStatusType(@NotNull Integer index, @NotNull String code, @NotNull String name) {
        this.index = index;
        this.code = code;
        this.name = name;
    }
}
