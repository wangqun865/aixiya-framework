package com.aixiya.framework.backend.common.entity.auditLog;


import com.aixiya.framework.backend.common.exception.AuditLogBeanException;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author wangqun865@163.com
 */
public class ClientAuditLogBusinessVo extends ClientAuditLogBaseVo implements Serializable {
    private static final long serialVersionUID = -8535490456215022832L;
/**
* 必填项begin
*/
    /**
     * 操作用户id
     */
    private String userId;

    /**
     *操作用户姓名
     */
    private String userLoginName;

    /**
     *来源是否是原sso 0:云平台 1:sso
     */
    private String ssopFlag ;

    /**
     * 操作组织id
     */
    private String orgId;

    /**
     * 操作组织名称
     */
    private String orgName;

    /**
     * 业务类型  业务系统自定义
     */
    private String businessType;

    /**
     * 日志内容
     */
    private String businessData;


    /**
     * 业务系统自定义时间
     */
    private Date businessDate;
/**
 * 必填项begin
 */


    /**
     *  构造方法
     * @param userId
     * @param userLoginName
     * @param ssopFlag
     * @param orgId
     * @param orgName
     * @param businessType
     * @param businessData
     * @param businessDate
     */
    private ClientAuditLogBusinessVo(String userId , String userLoginName ,String ssopFlag ,String orgId,
                                     String orgName, String businessType,String businessData , Date businessDate ) {
        this.userId = userId;
        this.userLoginName = userLoginName;
        this.ssopFlag = ssopFlag;
        this.orgId = orgId;
        this.orgName = orgName;
        this.businessType = businessType;
        this.businessData = businessData;
        this.businessDate = businessDate;
    }

    /**
     *
     * @param userId 用户id
     * @param userLoginName
     * @param ssopFlag
     * @param orgId
     * @param orgName
     * @param businessType
     * @param businessData
     * @param businessDate
     * @return
     * @throws AixiyaFwException
     */
    public static ClientAuditLogBusinessVo buildClientAuditLogBusinessVo(String userId , String userLoginName ,String ssopFlag ,String orgId,
                                                                  String orgName, String businessType,String businessData , Date businessDate) throws AuditLogBeanException{

        ClientAuditLogBusinessVo result = new ClientAuditLogBusinessVo( userId ,  userLoginName , ssopFlag , orgId,
                orgName,  businessType, businessData ,  businessDate);
        if (StringUtils.isEmpty(userId)) {
            throw new AuditLogBeanException("buildClientAuditLogBusinessVo-->userId is null" , result);
        }
        if (StringUtils.isEmpty(userLoginName)) {
            throw new AuditLogBeanException("buildClientAuditLogBusinessVo-->userLoginName is null" , result);
        }
        if (StringUtils.isEmpty(ssopFlag)) {
            throw new AuditLogBeanException("buildClientAuditLogBusinessVo-->ssopFlag is null" , result);
        }
        if (StringUtils.isEmpty(orgId)) {
            throw new AuditLogBeanException("buildClientAuditLogBusinessVo-->orgId is null" , result);
        }
        if (StringUtils.isEmpty(orgName)) {
            throw new AuditLogBeanException("buildClientAuditLogBusinessVo-->orgName is null" , result);
        }
        if (StringUtils.isEmpty(businessType)) {
            throw new AuditLogBeanException("buildClientAuditLogBusinessVo-->businessType is null" , result);
        }
        if (StringUtils.isEmpty(businessData)) {
            throw new AuditLogBeanException("buildClientAuditLogBusinessVo-->businessData is null" , result);
        }
        if (businessDate == null) {
            throw new AuditLogBeanException("buildClientAuditLogBusinessVo-->businessDate is null" , result);
        }
        return result;
    }

/**
 * 选填项begin
  */
    /**
     * 系统自定义 方法
     */
    private String actionName;

    /**
     * C:create  U:update D:delete
     */
    private String actionType;

    /**
     * success/fail
     */
    private String actionResulType;

    /**
     * 业务返回内容
     */
    private String businessResultData;

    /**
     * 预留字段 备注1
     */
    private String remark1;

    /**
     * 预留字段 备注2
     */
    private String remark_2;

    /**
     * 预留字段 备注3
     */
    private String remark_3;

    /**
     * 预留字段 查询关键字1
     */
    private String searchKey1;

    /**
     * 预留字段 查询关键字2
     */
    private String searchKey2;

    /**
     * 预留字段 查询关键字3
     */
    private String searchKey3;

/**
 * 选填项end
 */


/**
 * getter begin
 */
    public String getUserId() {
        return userId;
    }

    public String getUserLoginName() {
        return userLoginName;
    }

    public String getSsopFlag() {
        return ssopFlag;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getBusinessType() {
        return businessType;
    }

    public String getBusinessData() {
        return businessData;
    }

    public Date getBusinessDate() {
        return businessDate;
    }

    public String getActionName() {
        return actionName;
    }

    public String getActionType() {
        return actionType;
    }

    public String getActionResulType() {
        return actionResulType;
    }

    public String getBusinessResultData() {
        return businessResultData;
    }

    public String getRemark1() {
        return remark1;
    }

    public String getRemark_2() {
        return remark_2;
    }

    public String getRemark_3() {
        return remark_3;
    }

    public String getSearchKey1() {
        return searchKey1;
    }

    public String getSearchKey2() {
        return searchKey2;
    }

    public String getSearchKey3() {
        return searchKey3;
    }


/*setter begin*/

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public void setActionResulType(String actionResulType) {
        this.actionResulType = actionResulType;
    }

    public void setBusinessResultData(String businessResultData) {
        this.businessResultData = businessResultData;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public void setRemark_2(String remark_2) {
        this.remark_2 = remark_2;
    }

    public void setRemark_3(String remark_3) {
        this.remark_3 = remark_3;
    }

    public void setSearchKey1(String searchKey1) {
        this.searchKey1 = searchKey1;
    }

    public void setSearchKey2(String searchKey2) {
        this.searchKey2 = searchKey2;
    }

    public void setSearchKey3(String searchKey3) {
        this.searchKey3 = searchKey3;
    }
}
