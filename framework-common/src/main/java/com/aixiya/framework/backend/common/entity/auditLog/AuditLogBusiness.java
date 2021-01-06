package com.aixiya.framework.backend.common.entity.auditLog;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author wangqun865@163.com
 */
@Data
@TableName("t_audit_log_business")
public class AuditLogBusiness implements Serializable {

    private static final long serialVersionUID = 6334503989661431198L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    /**
     * 调用方名称
     */
    @TableField("center_name")
    private String centerName;

    /**
     * 操作用户id
     */
    @TableField("user_id")
    private String userId;

    /**
     *操作用户姓名
     */
    @TableField("user_login_name")
    private String userLoginName;

    /**
     *来源是否是原sso 0-云平台 1-sso
     */
    @TableField("ssop_flag")
    private String ssopFlag;

    /**
     * 操作组织id
     */
    @TableField("org_id")
    private String orgId;

    /**
     * 操作组织名称
     */
    @TableField("org_name")
    private String orgName;

    /**
     * 系统自定义 方法
     */
    @TableField("action_name")
    private String actionName;

    /**
     * C:create  U:update D:delete
     */
    @TableField("action_type")
    private String actionType;

    /**
     * success/fail
     */
    @TableField("action_result_type")
    private String actionResulType;

    /**
     * 业务类型  业务系统自定义
     */
    @TableField("business_type")
    private String businessType;

    /**
     * 日志内容
     */
    @TableField("business_data")
    private String businessData;

    /**
     * 业务返回内容
     */
    @TableField("business_result_data")
    private String businessResultData;

    /**
     * 一次发送uuid 与audit_log_receive关联
     */
    @TableField("batch_uuid")
    private String batchUuid;

    /**
     * 业务系统自定义时间
     */
    @TableField("business_date")
    private Date businessDate;

    /**
     * 发送消息时间
     */
    @TableField("send_date")
    private Date sendDate;

    /**
     * 创建时间
     */
    @TableField("create_date")
    private Date createDate;

    /**
     * 预留字段 备注1
     */
    @TableField("remark_1")
    private String remark1;

    /**
     * 预留字段 备注2
     */
    @TableField("remark_2")
    private String remark_2;

    /**
     * 预留字段 备注3
     */
    @TableField("remark_3")
    private String remark_3;

    /**
     * 预留字段 查询关键字1
     */
    @TableField("search_key_1")
    private String searchKey1;

    /**
     * 预留字段 查询关键字2
     */
    @TableField("search_key_2")
    private String searchKey2;

    /**
     * 预留字段 查询关键字3
     */
    @TableField("search_key_3")
    private String searchKey3;

}
