package com.mercy.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * hwl
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //这里会对"/admin"的请求进行拦截
        registry.addInterceptor(new UserInterceptor()).addPathPatterns("/admin");
    }
}