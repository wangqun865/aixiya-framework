package com.aixiya.framework.backend.security.starter.handler;

import com.aixiya.framework.backend.common.api.AixiyaFwResponse;
import com.aixiya.framework.backend.common.utils.AixiyaFwUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangqun865@163.com
 */
public class PlatformAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        AixiyaFwResponse aixiyaFwResponse = new AixiyaFwResponse();
        AixiyaFwUtil.makeJsonResponse(response, HttpServletResponse.SC_FORBIDDEN, aixiyaFwResponse.fail("没有权限访问该资源"));
    }
}
