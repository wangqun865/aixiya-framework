package com.aixiya.framework.backend.common.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

/**
 * @author wangqun865@163.com
 */
@Data
@SuppressWarnings("all")
//@EqualsAndHashCode(callSuper = true)
public class AixiyaFwAuthUser extends User {

    private static final long serialVersionUID = -6411066541689297219L;

    private String contactEmail;

    private String contactMob;

    private String contactTel;

    private String createdOperator;

    private Long did;

    private Byte disabled;

    private Date disabledDt;

    private String disabledOperator;

    private String disabledReason;

    private Byte discarded;

    private Date discardedDt;

    private String discardedOperator;

    private String discardedReason;

    private String idCardNo;

    private String idCardType;

    private String loginName;

    private String nameCn;

    private String nameEn;

    private String remark;

    private String sex;

    private String ssopId;


    private String orgId;

    private String orgName;

    private String orgSsopId;

    private String manager;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AixiyaFwAuthUser that = (AixiyaFwAuthUser) o;
        return Objects.equals(loginName, that.loginName) &&
                Objects.equals(orgId, that.orgId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), loginName, orgId);
    }

    public AixiyaFwAuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public AixiyaFwAuthUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
