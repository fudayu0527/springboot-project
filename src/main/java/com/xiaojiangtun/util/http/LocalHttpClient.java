package com.xiaojiangtun.util.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 本地Http连接池
 *
 * 2015/8/25.
 */
public class LocalHttpClient {

    protected static HttpClient httpClient = HttpClientFactory.createHttpClient(4000, 1000);

    private static final Logger log= LoggerFactory.getLogger(LocalHttpClient.class);

    /**
     * 初始化
     *
     * @param maxTotal
     * @param maxPerRoute
     */
    public static void init(int maxTotal, int maxPerRoute) {
        httpClient = HttpClientFactory.createHttpClient(maxTotal, maxPerRoute);
    }

    /**
     * 执行
     *
     * @param request
     * @return
     */
    public static HttpResponse execute(HttpUriRequest request) throws IOException {
        return httpClient.execute(request);
    }

    /**
     * 执行，并将响应转换成Bean
     *
     * @param request
     * @param responseHandler
     * @param <T>
     * @return
     */
    public static <T> T execute(HttpUriRequest request, ResponseHandler<T> responseHandler) {
        try {
            return httpClient.execute(request, responseHandler);
        } catch (Exception e) {
           log.error("execute request error {}",request.getURI(),e);
        }
        return null;
    }

    /**
     * 数据返回自动JSON对象解析
     *
     * @param request
     * @param clazz
     * @return
     */
    public static <T> T executeJsonResult(HttpUriRequest request, Class<T> clazz) {
        return execute(request, JsonResponseHandler.createResponseHandler(clazz));
    }

    /**
     * 不转编码处理结果
     * 数据返回自动JSON对象解析
     *
     * @param request
     * @param clazz
     * @return
     */
    public static <T> T executeJsonResultNoTranscoding(HttpUriRequest request, Class<T> clazz) {
        return execute(request, JsonResponseHandler.createResponseHandlerNoTranscoding(clazz));
    }



    /**
     * 数据返回自动XML对象解析
     *
     * @param request
     * @param clazz
     * @return
     */
    public static <T> T executeXmlResult(HttpUriRequest request, Class<T> clazz) {
        return execute(request, XmlResponseHandler.createResponseHandler(clazz));
    }

    /**
     * 数据返回文本内容
     *get
     * @param request
     * @return
     */
    public static String executeTextResult(HttpUriRequest request) {
        return execute(request, StringResponseHandler.createResponseHandler());
    }

}
