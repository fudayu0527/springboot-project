package com.xiaojiangtun.util.http;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 *
 */
public class HttpClientFactory {
    public static HttpClient createHttpClient() {
        return HttpClients.createMinimal();
    }
    private static RequestConfig config = null;

    public static HttpClient createHttpClient(int maxTotal, int maxPerRoute) {
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
        connManager.setMaxTotal(maxTotal);
        connManager.setDefaultMaxPerRoute(maxPerRoute);
        config = RequestConfig.custom()
                .setConnectTimeout(3 * 1000)         //连接超时时间
                .setSocketTimeout(4 * 1000)          //读超时时间（等待数据超时时间）
                .setConnectionRequestTimeout(500)    //从池中获取连接超时时间
                .setStaleConnectionCheckEnabled(true)//检查是否为陈旧的连接，默认为true，类似testOnBorrow
                .build();

        CloseableHttpClient httpClient = HttpClients.custom()
            .setConnectionManager(connManager)
            .setDefaultRequestConfig(config).build(); //.createMinimal(connManager);

        return httpClient;
    }
}
