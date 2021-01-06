package com.aixiya.framework.backend.security.starter.configure;


import com.aixiya.framework.backend.security.starter.handler.PlatformAccessDeniedHandler;
import com.aixiya.framework.backend.security.starter.handler.PlatformAuthExceptionEntryPoint;
import com.aixiya.framework.backend.security.starter.properties.PlatformCloudSecurityProperties;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author wangqun865@163.com
 */
@EnableResourceServer
@EnableAutoConfiguration(exclude = UserDetailsServiceAutoConfiguration.class)
public class PlatformCloudResourceServerConfigure extends ResourceServerConfigurerAdapter {

    private PlatformCloudSecurityProperties properties;
    private PlatformAccessDeniedHandler accessDeniedHandler;
    private PlatformAuthExceptionEntryPoint exceptionEntryPoint;

    @Autowired
    public void setProperties(PlatformCloudSecurityProperties properties) {
        this.properties = properties;
    }

    @Autowired
    public void setAccessDeniedHandler(PlatformAccessDeniedHandler accessDeniedHandler) {
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Autowired
    public void setExceptionEntryPoint(PlatformAuthExceptionEntryPoint exceptionEntryPoint) {
        this.exceptionEntryPoint = exceptionEntryPoint;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUris(), ",");
        if (ArrayUtils.isEmpty(anonUrls)) {
            anonUrls = new String[]{};
        }

        http.csrf().disable()
                .requestMatchers().antMatchers(properties.getAuthUri())
                .and()
                .authorizeRequests()
                .antMatchers(anonUrls).permitAll()
                .antMatchers(properties.getAuthUri()).authenticated()
                .and()
                .httpBasic();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(exceptionEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }
}
