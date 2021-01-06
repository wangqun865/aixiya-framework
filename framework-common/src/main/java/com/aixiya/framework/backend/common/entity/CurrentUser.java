package com.aixiya.framework.backend.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author wangqun865@163.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentUser implements Serializable {

    private static long serialVersionUID = 761748087824726463L;

    @JsonIgnore
    private String password;
    private String username;
    @JsonIgnore
    private Set<GrantedAuthority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private String ciiUkeyNo;

    private String contactEmail;

    private String contactMob;

    private String contactTel;

    private String createdOperator;

    private String cusCardNo;

    private String cusCardPwd;

    private Long did;

    private Byte disabled;
    @JsonIgnore
    private Date disabledDt;

    private String disabledOperator;

    private String disabledReason;
    @JsonIgnore
    private Byte discarded;
    @JsonIgnore
    private Date discardedDt;

    private String discardedOperator;

    private String discardedReason;

    private String idCardNo;

    private String idCardType;

    private String loginName;

    private String loginNameSw;

    private String loginNameSwLn;

    private String msaRegMob;

    private String msaRegNo;

    private String nameCn;

    private String nameEn;

    private String remark;

    private String sex;

    private String ssopId;

    private String orgId;

    private String orgName;

    private String orgSsopId;

    private String manager;

}
