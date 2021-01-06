package com.aixiya.framework.backend.common.handler;


import com.aixiya.framework.backend.common.api.AixiyaFwResponse;
import com.aixiya.framework.backend.common.exception.AixiyaFwException;
import com.aixiya.framework.backend.common.exception.AixiyaFwRuntimeException;
import com.aixiya.framework.backend.common.exception.FileDownloadException;
import com.aixiya.framework.backend.common.utils.AixiyaFwUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.List;
import java.util.Set;

/**
 * @author wangqun865@163.com
 */
@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AixiyaFwResponse handleException(Exception e) {
        String message = AixiyaFwUtil.containChinese(e.getMessage()) ? e.getMessage() : "系统内部异常";
        log.error(message, e);
        return new AixiyaFwResponse().fail(message);
    }

    @ExceptionHandler(value = AixiyaFwException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AixiyaFwResponse handleAixiyaFwException(AixiyaFwException e) {
        log.error("系统异常", e);
        return new AixiyaFwResponse().fail(e.getMessage());
    }

    @ExceptionHandler(value = AixiyaFwRuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AixiyaFwResponse handleAixiyaFwRuntimeException(AixiyaFwRuntimeException e) {
        log.error("系统运行异常", e);
        return new AixiyaFwResponse().fail(e.getMessage());
    }

    /**
     * 统一处理请求参数校验(实体对象传参)
     *
     * @param e BindException
     * @return AixiyaFwResponse
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AixiyaFwResponse handleBindException(BindException e) {
        BindingResult bindResult = e.getBindingResult();
        List<ObjectError> errors = bindResult.getAllErrors();
        StringBuilder message = new StringBuilder();
        for (ObjectError error : errors) {
            if (message.length() > 0) {
                message.append(";");
            }
            message.append(error.getDefaultMessage());
        }
        log.error(message.toString());
        return new AixiyaFwResponse().fail(message.toString());
    }

    /**
     * 统一处理请求参数校验(普通传参)
     *
     * @param e ConstraintViolationException
     * @return AixiyaFwResponse
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AixiyaFwResponse handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            Path path = violation.getPropertyPath();
            String[] pathArr = StringUtils.splitByWholeSeparatorPreserveAllTokens(path.toString(), ".");
            message.append(pathArr[1]).append(violation.getMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        log.error(message.toString());
        return new AixiyaFwResponse().fail(message.toString());
    }

    /**
     * 统一处理请求参数校验(json)
     *
     * @param e ConstraintViolationException
     * @return AixiyaFwResponse
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AixiyaFwResponse handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder message = new StringBuilder();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            message.append(error.getDefaultMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        log.error(message.toString());
        return new AixiyaFwResponse().fail(message.toString());
    }

    @ExceptionHandler(value = FileDownloadException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleFileDownloadException(FileDownloadException e) {

        log.error("FileDownloadException", e);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public AixiyaFwResponse handleAccessDeniedException() {
        return new AixiyaFwResponse().fail("没有权限访问该资源");
    }

    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AixiyaFwResponse handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        String message = "该方法不支持" + StringUtils.substringBetween(e.getMessage(), "'", "'") + "媒体类型";
        log.error(message);
        return new AixiyaFwResponse().fail(message);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AixiyaFwResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        String message = "该方法不支持" + StringUtils.substringBetween(e.getMessage(), "'", "'") + "请求方法";
        log.error(message);
        return new AixiyaFwResponse().fail(message);
    }

    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AixiyaFwResponse handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        String message = "文件不能超过" + e.getMessage() + "M";
        log.error(message , e);
        return new AixiyaFwResponse().fail(message);
    }




}
