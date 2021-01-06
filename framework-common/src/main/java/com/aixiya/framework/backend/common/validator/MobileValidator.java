package com.aixiya.framework.backend.common.validator;


import com.aixiya.framework.backend.common.annotation.IsMobile;
import com.aixiya.framework.backend.common.constant.RegexpConstant;
import com.aixiya.framework.backend.common.utils.AixiyaFwUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 校验是否为合法的手机号码
 *
 * @author wangqun865@163.com
 */
public class MobileValidator implements ConstraintValidator<IsMobile, String> {

    @Override
    public void initialize(IsMobile isMobile) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (StringUtils.isBlank(s)) {
                return true;
            } else {
                String regex = RegexpConstant.MOBILE;
                return AixiyaFwUtil.match(regex, s);
            }
        } catch (Exception e) {
            return false;
        }
    }
}
