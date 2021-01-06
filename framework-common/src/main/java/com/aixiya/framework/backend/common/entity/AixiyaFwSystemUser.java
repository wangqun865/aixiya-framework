package com.aixiya.framework.backend.common.entity;

import java.util.Date;

/**
 * @Author wangqun865@163.com
 */
public class AixiyaFwSystemUser {
    /**
     * 创建时间。
     */
    private Date cdt;


    /**
     * 联系邮箱地址。
     */
    private String contactEmail;

    /**
     * 联系手机号码。
     */
    private String contactMob;

    /**
     * 联系固话号码。
     */
    private String contactTel;

    /**
     * 创建操作人。
     逻辑外键：t_user->login_name
     或：t_organization_mgr->login_name。
     */
    private String createdOperator;


    /**
     * 数据主键。
     */
    private Long did;

    /**
     * 是否禁用。
     0 - 否；
     1 - 是。
     */
    private Byte disabled;

    /**
     * 禁用时间。
     */
    private Date disabledDt;

    /**
     * 禁用操作人。
     逻辑外键：t_user->login_name。
     */
    private String disabledOperator;

    /**
     * 禁用原因。
     */
    private String disabledReason;

    /**
     * 是否废弃（逻辑删除）。
     0 - 否；
     1 - 是。
     */
    private Byte discarded;

    /**
     * 废弃时间。
     */
    private Date discardedDt;

    /**
     * 废弃操作人。
     逻辑外键：t_user->login_name。
     */
    private String discardedOperator;

    /**
     * 废弃原因。
     */
    private String discardedReason;

    /**
     * 身份证件号码。
     */
    private String idCardNo;

    /**
     * 身份证件类型。
     1 – 身份证；
     2 – 护照；
     3 – 外国人居留证；
     4 – 港澳居民来往内地通行证；
     5 – 台湾居民来往内地通行证。
     */
    private String idCardType;

    /**
     * 登录用户名。
     */
    private String loginName;

    /**
     * 登录密码，须加盐加密存储。
     */
    private String loginPwd;

    /**
     * 中文名称。
     */
    private String nameCn;

    /**
     * 英文名称。
     */
    private String nameEn;

    /**
     * 备注。
     */
    private String remark;

    /**
     * 性别。
     M - 男；
     F - 女。
     */
    private String sex;

    /**
     * 原SSOP中用户id。
     */
    private String ssopId;

    /**
     * 更新时间。
     */
    private Date udt;

    /**
     * 更新操作人。
     逻辑外键：t_user->login_name
     或：t_organization_mgr->login_name。
     */
    private String updatedOperator;

    /**
     * 数据版本。
     */
    private Integer ver;









}
