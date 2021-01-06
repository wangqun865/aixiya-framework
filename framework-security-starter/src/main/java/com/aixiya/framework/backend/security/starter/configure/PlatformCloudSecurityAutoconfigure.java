package com.aixiya.framework.backend.security.starter.configure;


import com.aixiya.framework.backend.common.constant.AixiyaFwConstant;
import com.aixiya.framework.backend.common.utils.AixiyaFwUtil;
import com.aixiya.framework.backend.security.starter.handler.PlatformAccessDeniedHandler;
import com.aixiya.framework.backend.security.starter.handler.PlatformAuthExceptionEntryPoint;
import com.aixiya.framework.backend.security.starter.properties.PlatformCloudSecurityProperties;
import feign.RequestInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Base64Utils;

/**
 * @author wangqun865@163.com
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(PlatformCloudSecurityProperties.class)
@ConditionalOnProperty(value = "aixiya.platform.security.enable", havingValue = "true", matchIfMissing = true)
public class PlatformCloudSecurityAutoconfigure {

    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public PlatformAccessDeniedHandler accessDeniedHandler() {
        return new PlatformAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public PlatformAuthExceptionEntryPoint authenticationEntryPoint() {
        return new PlatformAuthExceptionEntryPoint();
    }

    @Bean
    @ConditionalOnMissingBean(value = PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PlatformCloudSecurityInteceptorConfigure platformCloudSecurityInteceptorConfigure() {
        return new PlatformCloudSecurityInteceptorConfigure();
    }

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return requestTemplate -> {
            String gatewayToken = new String(Base64Utils.encode(AixiyaFwConstant.GATEWAY_TOKEN_VALUE.getBytes()));
            requestTemplate.header(AixiyaFwConstant.GATEWAY_TOKEN_HEADER, gatewayToken);
            String authorizationToken = AixiyaFwUtil.getCurrentTokenValue();
            if (StringUtils.isNotBlank(authorizationToken)) {
                requestTemplate.header(HttpHeaders.AUTHORIZATION, AixiyaFwConstant.OAUTH2_TOKEN_TYPE + authorizationToken);
            }
        };
    }
}
