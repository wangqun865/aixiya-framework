package com.aixiya.framework.backend.redis.starter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wangqun865@163.com
 */
@ConfigurationProperties(prefix = "aixiya.lettuce.redis")
public class AixiyaFwLettuceRedisProperties {

    /**
     * 是否开启Lettuce Redis
     */
    private Boolean enable = true;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "AixiyaFwLettuceRedisProperties{" +
                "enable=" + enable +
                '}';
    }
}
