package com.aixiya.framework.backend.log.starter.configure;

import com.aixiya.framework.backend.log.starter.filter.AixiyaLogFilter;
import com.aixiya.framework.backend.log.starter.properties.PlatformLogFilterProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @Author wangqun865@163.com
 */
@Configuration
@EnableConfigurationProperties({PlatformLogFilterProperties.class})
@ConditionalOnClass(FilterRegistrationBean.class)
@ConditionalOnWebApplication(
        type = ConditionalOnWebApplication.Type.SERVLET
)
@ConditionalOnProperty(
        value = {"aixiya.platform.log.filter.enable"},
        havingValue= "true",
        matchIfMissing = false
)
public class PlatformLogFilterAutoConfiguration {
    @Bean
    @ConditionalOnClass(AixiyaLogFilter.class)
    @ConditionalOnProperty(prefix = "aixiya.platform.log.filter", name = "enable", havingValue = "true")
    public FilterRegistrationBean<AixiyaLogFilter> filterAixiyaLogFilter(PlatformLogFilterProperties properties) {
        /*
         * 增加Profile功能的Filter。
         */
        FilterRegistrationBean<AixiyaLogFilter> result =
                new FilterRegistrationBean<>();
        result.setName("aixiyaLogFilter");
        AixiyaLogFilter aixiyaLogFilter = new AixiyaLogFilter(properties);

        result.setFilter(aixiyaLogFilter);
        result.setUrlPatterns(
                Arrays.asList("/*")
        );
        result.setOrder(1);
        return result;
    }

}
