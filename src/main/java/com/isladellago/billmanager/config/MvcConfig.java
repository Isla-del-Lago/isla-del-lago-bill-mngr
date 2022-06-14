package com.isladellago.billmanager.config;

import com.isladellago.billmanager.util.PathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("com.isladellago.billmanager.controller")
public class MvcConfig implements WebMvcConfigurer {

    private RequestInterceptor requestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor)
                .addPathPatterns(PathUtils.BASE_PATH_BILL + "/**")
                .excludePathPatterns(PathUtils.BASE_PATH_BILL + PathUtils.HEALTH_PATH);
    }

    @Autowired
    public void setRequestInterceptor(RequestInterceptor requestInterceptor) {
        this.requestInterceptor = requestInterceptor;
    }
}
