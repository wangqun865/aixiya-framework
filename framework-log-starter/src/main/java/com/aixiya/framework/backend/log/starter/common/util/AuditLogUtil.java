package com.aixiya.framework.backend.log.starter.common.util;

import org.springframework.core.env.Environment;

/**
 * @Author wangqun865@163.com
 */
public class AuditLogUtil {
    public static String getApplicationName(Environment environment) {
        return environment.getProperty("spring.application.name");
    }
}
