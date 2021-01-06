package com.aixiya.framework.backend.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wangqun865@163.com
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ControllerEndpoint {

    String operation() default "";

    String resourceId() default "";

    String exceptionMessage() default "系统内部异常";
}
