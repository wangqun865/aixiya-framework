package com.aixiya.framework.backend.common.exception;

import com.aixiya.framework.backend.common.entity.auditLog.ClientAuditLogBusinessVo;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author wangqun865@163.com
 */
@Slf4j
public class AuditLogBeanException extends Exception {

    private static final long serialVersionUID = -8639136545710603975L;

    public AuditLogBeanException(String message , ClientAuditLogBusinessVo clientAuditLogBusinessVo) {
        super(message);
        if (clientAuditLogBusinessVo != null) {
            String json = JSONObject.toJSONString(clientAuditLogBusinessVo);
            log.error("AuditLogBean error! json :" + json  + "message : " + message);
        }
        //String centerName = AuditLogUtil.getApplicationName(environment);
        //todo 报警邮件
    }
}
