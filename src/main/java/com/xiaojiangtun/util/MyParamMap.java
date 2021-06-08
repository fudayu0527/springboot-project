package com.xiaojiangtun.util;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author xiayaoming
 * @version 1.0.0
 * @since 2019-07-31
 */
public class MyParamMap extends HashMap {

    public static MyParamMap newOne() {
        return new MyParamMap();
    }

    @Override
    public MyParamMap put(Object key, Object value) {
        super.put(key, value);
        return this;
    }

    public MyParamMap putNotNull(Object key, Object value) {
        if (Objects.nonNull(value)) {
            super.put(key, value);
        }
        return this;
    }

    public MyParamMap putNotBlank(Object key, String valueStr) {
        if (StringUtils.isNotBlank(valueStr)) {
            super.put(key, valueStr);
        }
        return this;
    }
}
