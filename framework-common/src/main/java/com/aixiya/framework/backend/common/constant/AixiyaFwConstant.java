package com.aixiya.framework.backend.common.constant;

/**
 * 系统常量类
 *
 * @author wangqun865@163.com
 */
public interface AixiyaFwConstant {

    /**
     * 排序规则：降序
     */
    String ORDER_DESC = "descending";
    /**
     * 排序规则：升序
     */
    String ORDER_ASC = "ascending";

    /**
     * Gateway请求头TOKEN名称（不要有空格）
     */
    String GATEWAY_TOKEN_HEADER = "GatewayToken";
    /**
     * Gateway请求头TOKEN值
     */
    String GATEWAY_TOKEN_VALUE = "aixiya:gateway:123456";

    /**
     * 允许下载的文件类型，根据需求自己添加（小写）
     */
    String[] VALID_FILE_TYPE = {"xlsx", "zip"};

    /**
     * 验证码 key前缀
     */
    String CODE_PREFIX = "aixiya.captcha.";

    /**
     * 异步线程池名称
     */
    String ASYNC_POOL = "aixiyaAsyncThreadPool";

    /**
     * OAUTH2 令牌类型 https://oauth.net/2/bearer-tokens/
     */
    String OAUTH2_TOKEN_TYPE = "bearer";
    /**
     * Java默认临时目录
     */
    String JAVA_TEMP_DIR = "java.io.tmpdir";
    /**
     * utf-8
     */
    String UTF8 = "utf-8";
    /**
     * 注册用户角色ID
     */
    Long REGISTER_ROLE_ID = 2L;

    String LOCALHOST = "localhost";
    String LOCALHOST_IP = "127.0.0.1";
    /**
	 * 默认成功消息
	 */
    String DEFAULT_SUCCESS_MESSAGE = "操作成功";
    /**
     * 默认失败消息
     */
    String DEFAULT_FAILURE_MESSAGE = "操作失败";
}
