package com.ksl.common.core.domain;

import lombok.Data;

/**
 * 用于layui的数据表格的响应
 * @author ksl
 * @data 2021-04-13
 */

@Data
public class TableDto {
    private Integer code;
    private String msg;
    private long count;
    private Object data;
}