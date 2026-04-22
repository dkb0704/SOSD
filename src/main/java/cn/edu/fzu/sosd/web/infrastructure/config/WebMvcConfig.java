package cn.edu.fzu.sosd.web.infrastructure.config;

import cn.edu.fzu.sosd.web.infrastructure.security.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * class name: WebMvcConfig
 *
 * @author: dkb
 * @description: Spring MVC 配置类，负责注册全局拦截器
 * @date: 2026/4/22 16:22
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        // 用户登录接口放行
                        "/user/login"


                );
    }
}