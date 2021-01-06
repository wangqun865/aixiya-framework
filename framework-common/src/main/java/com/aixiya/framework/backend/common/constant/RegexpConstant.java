package com.aixiya.framework.backend.common.constant;

import java.util.regex.Pattern;

/**
 * 正则常量
 *
 * @author wangqun865@163.com
 */
public interface RegexpConstant {

    /**
     * 简单手机号正则（这里只是简单校验是否为 11位，实际规则更复杂）
     */
    String MOBILE = "[1]\\d{10}";
    /**
     * 中文正则
     */
    Pattern CHINESE = Pattern.compile("[\u4e00-\u9fa5]");

}
