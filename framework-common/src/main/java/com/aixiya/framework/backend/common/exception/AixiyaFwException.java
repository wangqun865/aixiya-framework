package com.aixiya.framework.backend.common.exception;

/**
 * 系统异常
 *
 * @author wangqun865@163.com
 */
public class AixiyaFwException extends Exception {

    private static final long serialVersionUID = -6916154462432027437L;

    public AixiyaFwException(String message) {
        super(message);
    }
}
