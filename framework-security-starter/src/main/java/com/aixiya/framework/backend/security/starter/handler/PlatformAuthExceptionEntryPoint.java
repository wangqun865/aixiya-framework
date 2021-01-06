package com.aixiya.framework.backend.security.starter.handler;


import com.aixiya.framework.backend.common.api.AixiyaFwResponse;
import com.aixiya.framework.backend.common.utils.AixiyaFwUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangqun865@163.com
 */
@Slf4j
public class PlatformAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        String requestUri = request.getRequestURI();
        int status = HttpServletResponse.SC_UNAUTHORIZED;
        String message = "访问令牌不合法";
        log.error("客户端访问{}请求失败: {}", requestUri, message, authException);
        AixiyaFwUtil.makeJsonResponse(response, status, new AixiyaFwResponse().fail(message));
    }
}
