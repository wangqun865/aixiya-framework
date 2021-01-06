package com.aixiya.framework.backend.common.exception;

/**
 * @Author wangqun865@163.com
 */
public class AixiyaFwRuntimeException extends RuntimeException{

    private int status = 200;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public AixiyaFwRuntimeException() {
    }

    public AixiyaFwRuntimeException(String message, int status) {
        super(message);
        this.status = status;
    }

    public AixiyaFwRuntimeException(String message) {
        super(message);
    }

    public AixiyaFwRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public AixiyaFwRuntimeException(Throwable cause) {
        super(cause);
    }

    public AixiyaFwRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
