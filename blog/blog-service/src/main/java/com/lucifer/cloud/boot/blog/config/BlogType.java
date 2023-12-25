package com.lucifer.cloud.boot.blog.config;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author lucifer
 * @date 2023/12/25 11:52
 */
@Getter
public enum BlogType {

    IMAGE(0L,"image","图片"),
    BLOG(1L,"blog","博客"),
    ARTICLE(2L,"article","文章");


    private static final Map<String, BlogType> CODE_MAPPING = Collections.unmodifiableMap(
            Arrays.stream(BlogType.values()).collect(Collectors.toMap(BlogType::getCode, Function.identity()))
    );

    @NotNull
    public static Optional<BlogType> codeOf(@Nullable Long code) {
        return Optional.ofNullable(code).map(CODE_MAPPING::get);
    }

    private final Long index;

    private final String code;

    private final String name;

    BlogType(@NotNull Long index, @NotNull String code, @NotNull String name) {
        this.index = index;
        this.code = code;
        this.name = name;
    }
}
