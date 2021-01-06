package com.aixiya.framework.backend.log.starter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author wangqun865@163.com
 */
@ConfigurationProperties(prefix = "aixiya.platform.log.filter")
public class PlatformLogFilterProperties {

    /**
     * 是否开启日志过滤
     */
    private Boolean enable;

    /**
     * 只过滤 不执行打印日志
     */
    private String annoUrlPatterns;


    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getAnnoUrlPatterns() {
        return annoUrlPatterns;
    }

    public void setAnnoUrlPatterns(String annoUrlPatterns) {
        this.annoUrlPatterns = annoUrlPatterns;
    }


    @Override
    public String toString() {
        return "PlatformLogFilterProperties{" +
                "enable=" + enable +
                ", annoUrlPatterns='" + annoUrlPatterns + '\'' +
                '}';
    }
}
