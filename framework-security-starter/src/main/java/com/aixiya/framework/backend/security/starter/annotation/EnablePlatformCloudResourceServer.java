package com.aixiya.framework.backend.security.starter.annotation;

import com.aixiya.framework.backend.security.starter.configure.PlatformCloudResourceServerConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author wangqun865@163.com
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(PlatformCloudResourceServerConfigure.class)
public @interface EnablePlatformCloudResourceServer {
}
