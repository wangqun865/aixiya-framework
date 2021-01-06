package com.aixiya.framework.backend.security.starter.interceptor;

import com.aixiya.framework.backend.common.api.AixiyaFwResponse;
import com.aixiya.framework.backend.common.constant.AixiyaFwConstant;
import com.aixiya.framework.backend.common.utils.AixiyaFwUtil;
import com.aixiya.framework.backend.security.starter.properties.PlatformCloudSecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangqun865@163.com
 */
public class PlatformServerProtectInterceptor implements HandlerInterceptor {

    private PlatformCloudSecurityProperties properties;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws IOException {
        if (!properties.getOnlyFetchByGateway()) {
            return true;
        }
        String token = request.getHeader(AixiyaFwConstant.GATEWAY_TOKEN_HEADER);
        String gatewayToken = new String(Base64Utils.encode(AixiyaFwConstant.GATEWAY_TOKEN_VALUE.getBytes()));
        if (StringUtils.equals(gatewayToken, token)) {
            return true;
        } else {
            AixiyaFwResponse aixiyaFwResponse = new AixiyaFwResponse();
            AixiyaFwUtil.makeJsonResponse(response, HttpServletResponse.SC_FORBIDDEN, aixiyaFwResponse.fail("请通过网关获取资源"));
            return false;
        }
    }

    public void setProperties(PlatformCloudSecurityProperties properties) {
        this.properties = properties;
    }
}
