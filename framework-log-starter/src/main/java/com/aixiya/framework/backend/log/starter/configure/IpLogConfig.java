package com.aixiya.framework.backend.log.starter.configure;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author wangqun865@163.com
 */
@Slf4j
public class IpLogConfig extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error("ip地址获取失败 " , e);
        }
        return null;
    }
}
