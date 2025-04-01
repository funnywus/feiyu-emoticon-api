package com.emoticon.common.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SaTokenConfig implements WebMvcConfigurer {
    // 注册Sa-Token的拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册路由拦截器，自定义认证规则
        registry.addInterceptor(new SaInterceptor(handler -> {
            // 登录验证 -- 拦截所有路由，并排除/tb/auth/login 用于开放登录
            // 注意：登录接口不能拦截，否则会导致无法登录
        })).addPathPatterns("/tb/**")
          .excludePathPatterns("/tb/auth/login", "/tb/auth/register");
    }
}
