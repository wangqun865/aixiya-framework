package com.aixiya.framework.backend.security.starter.interceptor;


import com.aixiya.framework.backend.security.starter.properties.PlatformSpringProperties;
import lombok.Data;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author wangqun865@163.com
 */
@Data
public class FileUploadInterceptor implements HandlerInterceptor {

    private PlatformSpringProperties properties;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request!=null && ServletFileUpload.isMultipartContent(request)) {
            ServletRequestContext ctx = new ServletRequestContext(request);
            long requestSize = ctx.contentLength();
            Long maxSize = 0L;
            if (properties == null || properties.getMaxSize() == null) {
                maxSize = 1L * 1024 * 1024;
            } else {
                maxSize = properties.getMaxSize() * 1024 * 1024;
            }
            if (requestSize > maxSize) {
                //在这里捕捉异常，可以在这里定义异常的处理
                throw new MaxUploadSizeExceededException(maxSize);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }


}
