package com.aixiya.framework.backend.common.api;


import com.aixiya.framework.backend.common.constant.AixiyaFwConstant;

import java.util.HashMap;

/**
 * @author wangqun865@163.com
 */
public class AixiyaFwResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;

    public AixiyaFwResponse data(String code,Object data, String message) {
        this.put("code",code);
        this.put("message",message);
        this.put("data", data);
        return this;
    }
    public AixiyaFwResponse data(Object data, String message) {
        this.data("200",data,message);
        return this;
    }

    public AixiyaFwResponse data(Object data) {
        return data(data, AixiyaFwConstant.DEFAULT_SUCCESS_MESSAGE);
    }

    public AixiyaFwResponse fail(String code,Object data, String message) {
        this.data(code,data,message);
        return this;
    }

    public AixiyaFwResponse fail(Object data) {
        return fail("400",data, AixiyaFwConstant.DEFAULT_FAILURE_MESSAGE);
    }

    public AixiyaFwResponse fail(String message) {
        return fail("400",null, message);
    }


    public AixiyaFwResponse success(String code,Object data, String message) {
        this.data(code,data,message);
        return this;
    }

    public AixiyaFwResponse success(Object data) {
        return fail("200",data, AixiyaFwConstant.DEFAULT_SUCCESS_MESSAGE);
    }

    public AixiyaFwResponse success(String message) {
        return fail("200",null, message);
    }

    @Override
    public AixiyaFwResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }


    public String getMessage() {
        return String.valueOf(get("message"));
    }

    public Object getData() {
        return get("data");
    }

    public Object getCode() {
        return get("code");
    }
}
