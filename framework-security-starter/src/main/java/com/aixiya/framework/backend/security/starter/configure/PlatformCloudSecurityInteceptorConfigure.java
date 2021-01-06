package com.aixiya.framework.backend.security.starter.configure;


import com.aixiya.framework.backend.security.starter.interceptor.PlatformServerProtectInterceptor;
import com.aixiya.framework.backend.security.starter.properties.PlatformCloudSecurityProperties;
import com.aixiya.framework.backend.security.starter.properties.PlatformSpringProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wangqun865@163.com
 */
@Configuration
public class PlatformCloudSecurityInteceptorConfigure implements WebMvcConfigurer {

    private PlatformCloudSecurityProperties properties;

    private PlatformSpringProperties springProperties;

    @Autowired
    public void setProperties(PlatformCloudSecurityProperties properties) {
        this.properties = properties;
    }

    @Bean
    public HandlerInterceptor platformServerProtectInterceptor() {
        PlatformServerProtectInterceptor platformServerProtectInterceptor = new PlatformServerProtectInterceptor();
        platformServerProtectInterceptor.setProperties(properties);
        return platformServerProtectInterceptor;
    }

    /*@Bean(name = "FileUploadInterceptor" , value = "FileUploadInterceptor")
    public HandlerInterceptor fileUploadInterceptor() {
        FileUploadInterceptor fileUploadInterceptor = new FileUploadInterceptor();
        fileUploadInterceptor.setProperties(springProperties);
        return fileUploadInterceptor;
    }*/

    @Override
    @SuppressWarnings("all")
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(platformServerProtectInterceptor());
        //registry.addInterceptor(fileUploadInterceptor()).order(0);
    }
}
