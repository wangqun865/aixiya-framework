package com.aixiya.framework.backend.log.starter.filter;

import com.aixiya.framework.backend.log.starter.properties.PlatformLogFilterProperties;
import com.aixiya.framework.backend.log.starter.wrapper.AixiyaRequestWrapper;
import com.aixiya.framework.backend.log.starter.wrapper.AixiyaResponseWrapper;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author wangqun865@163.com
 */
@Slf4j
public class AixiyaLogFilter implements Filter {
    private PlatformLogFilterProperties properties;

    public AixiyaLogFilter(PlatformLogFilterProperties properties) {
        this.properties = properties;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException  {
        long start = System.currentTimeMillis();

        boolean chainFlag = false;
        HttpServletRequest httpServletRequest = null;
        try {
            httpServletRequest = (HttpServletRequest) request;
            String path = httpServletRequest.getRequestURI();

            if (path != null && StringUtils.isNotEmpty(properties.getAnnoUrlPatterns())) {
                String AnnoUrlPatternsString = properties.getAnnoUrlPatterns();
                String[] AnnoUrlPatterns = AnnoUrlPatternsString.split(",");
                for (int i = 0 ; i < AnnoUrlPatterns.length ; i++) {
                    String urlPattern = AnnoUrlPatterns[i];
                    if (path.contains(urlPattern)) {
                        chainFlag = true;
                        break;
                    }
                }
            }
            String contentType = httpServletRequest.getContentType();
            if (contentType != null &&
                    contentType.contains("x-www-form-urlencoded")) {
                //如果是application/x-www-form-urlencoded, 参数值在request body中以 a=1&b=2&c=3...形式存在，
                //      若直接构造BodyReaderHttpServletRequestWrapper，在将流读取并存到copy字节数组里之后,
                //      httpRequest.getParameterMap()将返回空值！
                //      若运行一下 httpRequest.getParameterMap(), body中的流将为空! 所以两者是互斥的！
                httpServletRequest.getParameterMap();
            }

            if (StringUtils.isNotEmpty(contentType) && contentType.contains("multipart/form-data")) {
                chainFlag = true;
            }
        } catch (Exception e) {
            log.error("生成日志error", e);
            throw new ServletException("log-filter1 exception" + e.getMessage());
        }

        if (chainFlag) {
            log.info("Resttemplate url:{} 进入dofilter-debug 之前 消耗时间{}毫秒", httpServletRequest.getRequestURI(),System.currentTimeMillis() - start);
            chain.doFilter(request, response);
            log.info("Resttemplate url:{} 出dofilter-debug 之前 消耗时间{}毫秒", httpServletRequest.getRequestURI(),System.currentTimeMillis() - start);
        } else {
            AixiyaRequestWrapper requestWrapper ;
            AixiyaResponseWrapper responseWrapper ;
            HttpServletResponse httpServletResponse ;
            try {
                requestWrapper = new AixiyaRequestWrapper(httpServletRequest);

                httpServletResponse = (HttpServletResponse) response;
                responseWrapper = new AixiyaResponseWrapper(httpServletResponse);

                String method = httpServletRequest.getMethod();
                String body = requestWrapper.getBody();
                String parameterStr =
                        "请求. 地址:" + httpServletRequest.getRequestURI() + "Method-Type:"  + method
                                + "request ContentType: " + httpServletRequest.getContentType() + ". 参数:" + body;

                parameterStr = parameterStr + JSONObject.toJSONString(httpServletRequest.getParameterMap());

                log.info(parameterStr);
            } catch (Exception e) {
                log.error("生成日志error", e);
                throw new ServletException("log-filter2 exception" + e.getMessage());
            }

            log.info("Resttemplate url:{} 进入dofilter-debug 之前 消耗时间{}毫秒", httpServletRequest.getRequestURI(),System.currentTimeMillis() - start);
            chain.doFilter(requestWrapper, responseWrapper);
            log.info("Resttemplate url:{} 出dofilter-debug 之前 消耗时间{}毫秒", httpServletRequest.getRequestURI(),System.currentTimeMillis() - start);
                //todo
            try {
                String responseContentType = httpServletResponse.getContentType();
                if (responseContentType != null && (responseContentType.contains("application/octet-stream")
                        || responseContentType.contains("image"))){
                    log.info("返回. contentType is file" );
                } else {
                    String val = null;
                    byte[] bytes = responseWrapper.getBytes();
                    val = new String(bytes, "UTF-8");

                    log.info("返回. 地址:" +  httpServletRequest.getRequestURI() + ". 参数:" + val);
                    log.info("Resttemplate url:{} 消耗时间{}毫秒", httpServletRequest.getRequestURI(),System.currentTimeMillis() - start);
                    //将数据 再写到 response 中
                    response.getOutputStream().write(bytes);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
            } catch (Exception e) {
                log.error("生成日志error", e);
                throw new ServletException("log-filter3 exception" + e.getMessage());
            }
        }

    }

    @Override
    public void destroy() {

    }
}
