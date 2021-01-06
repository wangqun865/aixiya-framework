package com.aixiya.framework.backend.security.starter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author wangqun865@163.com
 */
@ConfigurationProperties(prefix = "aixiya.platform.common")
@Data
public class PlatformSpringProperties {
    private Long maxSize ;
}
