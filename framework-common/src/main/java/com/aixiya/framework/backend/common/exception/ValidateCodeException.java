package com.aixiya.framework.backend.common.exception;

/**
 * 验证码类型异常
 *
 * @author wangqun865@163.com
 */
public class ValidateCodeException extends Exception {

    private static final long serialVersionUID = 7514854456967620043L;

    public ValidateCodeException(String message) {
        super(message);
    }
}
