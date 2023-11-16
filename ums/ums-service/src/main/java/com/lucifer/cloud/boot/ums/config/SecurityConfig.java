package com.lucifer.cloud.boot.ums.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.web.filter.CorsFilter;

/**
 *  客户端一些安全配置
 *  配置客户端跨域访问
 *  配置客户端不需要认证的放行路径
 */
@EnableWebSecurity
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
public class SecurityConfig {
    private final CorsFilter corsFilter;
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(HttpSecurity http) {
        http.addFilter(corsFilter);
        return (web) -> web.ignoring().requestMatchers("/app");
    }
}
