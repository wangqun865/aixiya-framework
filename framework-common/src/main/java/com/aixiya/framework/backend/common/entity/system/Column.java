package com.aixiya.framework.backend.common.entity.system;

import lombok.Data;

/**
 * @author wangqun865@163.com
 */
@Data
public class Column {
    /**
     * 名称
     */
    private String name;
    /**
     * 是否为主键
     */
    private Boolean isKey;
    /**
     * 类型
     */
    private String type;
    /**
     * 注释
     */
    private String remark;
    /**
     * 属性名称
     */
    private String field;

    /**
     * 属性名称
     */
    private int dataprecision;


    /**
     * 属性名称
     */
    private int datascale;
}
