package com.xiaojiangtun.util.http;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author chenzengnian
 * @version 1.0.0
 * @description
 * @date 2021/1/27 9:49
 * @copyright miniso
 */
public class RequestContextUtil {

    public static HttpServletRequest currentRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(requestAttributes)) {
            return null;
        }
        return requestAttributes.getRequest();
    }

    public static String getHeader(String headerName) {
        HttpServletRequest request = currentRequest();
        if (Objects.isNull(request)) {
            return null;
        }
        return request.getHeader(headerName);
    }

}
