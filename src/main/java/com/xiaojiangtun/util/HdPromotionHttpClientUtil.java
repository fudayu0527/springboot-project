package com.xiaojiangtun.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @Author: zhangyong
 * @Date: 2020/5/20 1:21
 */
public class HdPromotionHttpClientUtil {
    private static final Log logger = LogFactory.getLog(HdPromotionHttpClientUtil.class);

    private static final int DEFAULT_TIMEOUT = 5000;
    private static final int MAX_TOTAL = 1000;
    private static final int MAX_PER_ROUTE = 500;

    private static PoolingHttpClientConnectionManager cm;
    private static RequestConfig defaultRequestConfig;

    static {
        LayeredConnectionSocketFactory sslsf = null;
        try {
            sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault());
        } catch (NoSuchAlgorithmException e) {
            logger.error("init SSLConnectionSocketFactory is error", e);
        }
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("https", sslsf)
                .register("http", new PlainConnectionSocketFactory())
                .build();
        cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        cm.setMaxTotal(MAX_TOTAL);
        cm.setDefaultMaxPerRoute(MAX_PER_ROUTE);
        //设置请求和传输超时时间
        defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(DEFAULT_TIMEOUT)
                .setConnectTimeout(DEFAULT_TIMEOUT)
                .build();
    }

    /**
     * 发送json形式的post请求
     *
     * @param url
     * @param params
     * @param headerMap
     * @return
     * @throws IOException
     */
    public static String postJsonString(String url, String params, Map<String, String> headerMap) throws Exception {
        if (StringUtils.isEmpty(url) || params == null || params.isEmpty()) {
            return null;
        }

        CloseableHttpResponse response = null;
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(defaultRequestConfig).setConnectionManager(cm).build();
            HttpPost httpPost = new HttpPost(url);
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {//请求头
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }

            StringEntity urlEncodedFormEntity = new StringEntity(params, Consts.UTF_8);
            httpPost.setEntity(urlEncodedFormEntity);
            logger.info(String.format("海鼎促销请求地址: %s", url));
            logger.info(String.format("海鼎促销请求头: %s", headerMap));
            logger.info(String.format("海鼎促销请求内容: %s", params));
            response = httpClient.execute(httpPost);

            StatusLine statusLine = response.getStatusLine();
            logger.info(String.format("海鼎促销响应状态: %s", statusLine.getStatusCode()));

            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, Consts.UTF_8);
            logger.info(String.format("海鼎促销响应数据: %s", result));

            return result;
        } catch (Exception e) {
            logger.error(String.format("海鼎促销响应失败, 请求地址: %s", url), e);
            throw e;
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                logger.error("close http client failed", e);
            }
        }
    }
}
