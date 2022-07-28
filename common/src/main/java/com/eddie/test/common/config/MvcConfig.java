package com.eddie.test.common.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Eddie
 * @Date 2022/7/26 17:58
 * @Version 1.0
 * @Description
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${spring.resources.static-locations}")
    private String path;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String[] split = path.split(",");

        // 解决swagger无法访问
        registry.addResourceHandler("/swagger-ui.html","/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        // 解决swagger的js文件无法访问
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/**").addResourceLocations(split);
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
