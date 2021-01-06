package com.aixiya.framework.backend.log.starter.service;

import com.aixiya.framework.backend.common.entity.auditLog.AuditLogBusiness;
import com.aixiya.framework.backend.common.entity.auditLog.ClientAuditLogBusinessVo;
import com.aixiya.framework.backend.common.exception.AuditLogBeanException;
import com.aixiya.framework.backend.log.starter.common.util.AuditLogUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Date;
import java.util.UUID;

/**
 * @Author wangqun865@163.com
 */
@Slf4j
public class PlatformAuditLogService {
    private static final String AuditLogTopic = "bpfunc-auditlog";

    @Autowired
    private Environment environment;

    @Autowired
    @Qualifier("auditLogKafkaTemplate")
    private KafkaTemplate<String,Object> kafkaTemplate;


    public void sendAuditLog(ClientAuditLogBusinessVo clientAuditLogBusinessVo) throws AuditLogBeanException {
        validateClientAuditLogBusinessVo(clientAuditLogBusinessVo);

        AuditLogBusiness auditLogBusiness = new AuditLogBusiness();
        BeanUtils.copyProperties(clientAuditLogBusinessVo, auditLogBusiness);

        try {
            String centerName = AuditLogUtil.getApplicationName(environment);
            if (StringUtils.isEmpty(centerName)) {
                throw new AuditLogBeanException("validateClientAuditLogBusinessVo-->centerName is null" , clientAuditLogBusinessVo);
            }
            auditLogBusiness.setCenterName(centerName);
        } catch (Exception e) {
            log.error("sendAuditLog 获取项目名称失败" , e);
            throw new AuditLogBeanException("sendAuditLog 获取项目名称失败" , clientAuditLogBusinessVo);
        }
        String batchUuid = UUID.randomUUID().toString();
        auditLogBusiness.setBatchUuid(batchUuid);
        auditLogBusiness.setSendDate(new Date());

        String sendJson = JSONObject.toJSONString(auditLogBusiness);

        kafkaTemplate.send(AuditLogTopic, sendJson).addCallback( success -> {
                } , failure -> {
            log.error("发送kafka失败 ! topic:" + AuditLogTopic + "stackTrace :" + failure.getStackTrace().toString() +
                    "message:" + failure.getMessage() + "data:" +sendJson );
            //todo 报警邮件
            //todo this partition is very important!
                }
        );


    }


    private void validateClientAuditLogBusinessVo(ClientAuditLogBusinessVo clientAuditLogBusinessVo) throws AuditLogBeanException {
        if (clientAuditLogBusinessVo == null) {
            throw new AuditLogBeanException("validateClientAuditLogBusinessVo-->userId is null" , clientAuditLogBusinessVo);
        }
        if (StringUtils.isEmpty(clientAuditLogBusinessVo.getUserId())) {
            throw new AuditLogBeanException("validateClientAuditLogBusinessVo-->userId is null" , clientAuditLogBusinessVo);
        }
        if (StringUtils.isEmpty(clientAuditLogBusinessVo.getUserLoginName())) {
            throw new AuditLogBeanException("validateClientAuditLogBusinessVo-->userLoginName is null" , clientAuditLogBusinessVo);
        }
        if (StringUtils.isEmpty(clientAuditLogBusinessVo.getSsopFlag())) {
            throw new AuditLogBeanException("validateClientAuditLogBusinessVo-->ssopFlag is null" , clientAuditLogBusinessVo);
        }
        if (StringUtils.isEmpty(clientAuditLogBusinessVo.getOrgId())) {
            throw new AuditLogBeanException("validateClientAuditLogBusinessVo-->orgId is null" , clientAuditLogBusinessVo);
        }
        if (StringUtils.isEmpty(clientAuditLogBusinessVo.getOrgName())) {
            throw new AuditLogBeanException("validateClientAuditLogBusinessVo-->orgName is null" , clientAuditLogBusinessVo);
        }
        if (StringUtils.isEmpty(clientAuditLogBusinessVo.getBusinessType())) {
            throw new AuditLogBeanException("validateClientAuditLogBusinessVo-->businessType is null" , clientAuditLogBusinessVo);
        }
        if (StringUtils.isEmpty(clientAuditLogBusinessVo.getBusinessData())) {
            throw new AuditLogBeanException("validateClientAuditLogBusinessVo-->businessData is null" , clientAuditLogBusinessVo);
        }
        if (clientAuditLogBusinessVo.getBusinessDate() == null) {
            throw new AuditLogBeanException("validateClientAuditLogBusinessVo-->businessDate is null" , clientAuditLogBusinessVo);
        }

    }


}
