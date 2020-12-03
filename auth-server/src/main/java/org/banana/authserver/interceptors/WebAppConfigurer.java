package org.banana.authserver.interceptors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * @author yuanrui@live.cn
 * @since 2020/11/30 15:11
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 可添加多个
        registry.addInterceptor(new CheckAuthInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/logout")
                .excludePathPatterns(Arrays.asList("/favicon.ico","/css/**", "/images/**", "/js/**", "/layui/**"))
        ;
    }

}
