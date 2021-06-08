package com.xiaojiangtun.util.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 字符内容响应处理器
 *
 * 2016/5/16.
 */
public class StringResponseHandler {
    private static final Logger log = LoggerFactory.getLogger(StringResponseHandler.class);


    @SuppressWarnings("unchecked")
    public static ResponseHandler<String> createResponseHandler() {
        return (HttpResponse response) -> {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                String str = EntityUtils.toString(entity);
                log.info("result: {}",str);
                return str;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
    }
}
