package com.xiaojiangtun.util;

import static org.apache.http.impl.client.HttpClients.createDefault;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import com.google.gson.Gson;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
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
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

/**
 * 发送Http请求工具类
 *
 * @Author: hukaishen
 * @Date: 2019/4/26 14:19
 */
public class HttpClientUtil {
    private static final int DEFAULT_TIMEOUT = 5000;
    private static final int MAX_TOTAL = 1000;
    private static final int MAX_PERROUTE = 500;
    private static final Log logger = LogFactory.getLog(HttpClientUtil.class);

    private static PoolingHttpClientConnectionManager cm = null;

    private static RequestConfig defaultRequestConfig = null;

    private static final Gson GSON = new Gson();

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
        cm.setDefaultMaxPerRoute(MAX_PERROUTE);
        //设置请求和传输超时时间
        defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(DEFAULT_TIMEOUT)
                .setConnectTimeout(DEFAULT_TIMEOUT)
                .build();
    }

    /**
     * 发送key_value形式的post请求
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String postKeyValue(String url, Map<String, String> params) throws IOException {
        if (StringUtils.isEmpty(url)) {
            return null;
        }

        CloseableHttpClient httpClient = createDefault();
        CloseableHttpResponse response = null;
        String result = null;

        try {
            httpClient = HttpClientBuilder.create().setDefaultRequestConfig(defaultRequestConfig).setConnectionManager(cm).build();
            HttpPost httpPost = new HttpPost(url);

            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            if (MapUtils.isNotEmpty(params)) {
                List<BasicNameValuePair> basicNameValuePairs = new ArrayList<>();
                for (Map.Entry<String, String> entity : params.entrySet()) {
                    basicNameValuePairs.add(new BasicNameValuePair(entity.getKey(), entity.getValue()));
                }
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(basicNameValuePairs, Consts.UTF_8);
                httpPost.setEntity(urlEncodedFormEntity);
            }

            response = httpClient.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, Consts.UTF_8);
            if(result.length() < 1000) {
                logger.info(String.format("response data: %s", result));
            }else {
                logger.info(String.format("response data: %s", result.substring(0, 100)));
            }
            return result;

        } catch (IOException e) {
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
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = null;

        try {
            httpClient = HttpClientBuilder.create().setDefaultRequestConfig(defaultRequestConfig).setConnectionManager(cm).build();
            HttpPost httpPost = new HttpPost(url);
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {//请求头
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }

            StringEntity urlEncodedFormEntity = new StringEntity(params, Consts.UTF_8);
            httpPost.setEntity(urlEncodedFormEntity);
            logger.info(String.format("request %s url: %s", headerMap.get("trade_id"), url));
            logger.info(String.format("request headers: %s", headerMap));
            logger.info(String.format("request %s params: %s", headerMap.get("trade_id"), params));
            response = httpClient.execute(httpPost);

            StatusLine statusLine = response.getStatusLine();
			logger.info(String.format("response status: %s", statusLine.getStatusCode()));

            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, Consts.UTF_8);
            if(result.length() < 1000) {
                logger.info(String.format("response %s data: %s", headerMap.get("tradeId"), result));
            }else {
                logger.info(String.format("response %s data: %s", headerMap.get("tradeId"), result.substring(0, 100)));
            }

            return result;
        } catch (Exception e) {
        	logger.error(String.format("调用接口失败，request url: %s", url), e);
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


    /**
     * 发送json形式的post请求
     *
     * @param url
     * @param headerMap
     * @return
     * @throws IOException
     */
    public static String postNoBody(String url, Map<String, String> headerMap) throws Exception {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = null;

        try {
            httpClient = HttpClientBuilder.create().setDefaultRequestConfig(defaultRequestConfig).setConnectionManager(cm).build();
            HttpPost httpPost = new HttpPost(url);
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {//请求头
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
            logger.info(String.format("request url: %s", url));
            logger.info(String.format("request headers: %s", headerMap));
            response = httpClient.execute(httpPost);

            StatusLine statusLine = response.getStatusLine();
            logger.info(String.format("response status: %s", statusLine.getStatusCode()));

            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, Consts.UTF_8);
            if(result.length() < 1000) {
                logger.info(String.format("response data: %s", result));
            }else {
                logger.info(String.format("response data: %s", result.substring(0, 100)));
            }

            return result;
        } catch (Exception e) {
            logger.error(String.format("调用接口失败，request url: %s", url), e);
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


    /**
     * 发送json形式的post请求
     *
     * @param url
     * @param params
     * @param headerMap
     * @return
     * @throws IOException
     */
    public static String postOrderJsonString(String url, String params, Map<String, String> headerMap) throws Exception {
        if (StringUtils.isEmpty(url) || params == null || params.isEmpty()) {
            return null;
        }
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = null;

        try {
            httpClient = HttpClientBuilder.create().setDefaultRequestConfig(defaultRequestConfig).setConnectionManager(cm).build();
            HttpPost httpPost = new HttpPost(url);
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {//请求头
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }

            StringEntity urlEncodedFormEntity = new StringEntity(params, Consts.UTF_8);
            httpPost.setEntity(urlEncodedFormEntity);
            logger.info(String.format("request url: %s, params: %s", url, JSON.toJSONString(params)));
            response = httpClient.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();

            logger.info(String.format("response status: %s", statusLine.getStatusCode()));


            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, Consts.UTF_8);
            if(result.length() < 1000) {
                logger.info(String.format("response data: %s", result));
            }else {
                logger.info(String.format("response data: %s", result.substring(0, 100)));
            }
            //BaseResponse baseResponse = new BaseResponse();
            return result;

        } catch (Exception e) {
            logger.error(String.format("调用接口失败，request url: %s", url), e);
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

    public static String postOrderCancelJsonString(String url, String params, Map<String, String> headerMap) throws Exception {
        if (StringUtils.isEmpty(url) || params == null || params.isEmpty()) {
            return null;
        }
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = null;

        try {
            httpClient = HttpClientBuilder.create().setDefaultRequestConfig(defaultRequestConfig).setConnectionManager(cm).build();
            HttpPost httpPost = new HttpPost(url);
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {//请求头
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }

            StringEntity urlEncodedFormEntity = new StringEntity(params, Consts.UTF_8);
            httpPost.setEntity(urlEncodedFormEntity);
            logger.info(String.format("request url: %s, params: %s", url, JSON.toJSONString(params)));
            response = httpClient.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();

            logger.info(String.format("response status: %s", statusLine.getStatusCode()));


            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, Consts.UTF_8);
            if(result.length() < 1000) {
                logger.info(String.format("response data: %s", result));
            }else {
                logger.info(String.format("response data: %s", result.substring(0, 100)));
            }
            //BaseResponse baseResponse = new BaseResponse();
            return result;

        } catch (Exception e) {
            logger.error(String.format("调用接口失败，request url: %s", url), e);
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


    public static String postOrderCancelApplyJsonString(String url, String params, Map<String, String> headerMap) throws Exception {
        if (StringUtils.isEmpty(url) || params == null || params.isEmpty()) {
            return null;
        }
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = null;

        try {
            httpClient = HttpClientBuilder.create().setDefaultRequestConfig(defaultRequestConfig).setConnectionManager(cm).build();
            HttpPost httpPost = new HttpPost(url);
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {//请求头
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }

            StringEntity urlEncodedFormEntity = new StringEntity(params, Consts.UTF_8);
            httpPost.setEntity(urlEncodedFormEntity);
            logger.info(String.format("request url: %s, params: %s", url, JSON.toJSONString(params)));
            response = httpClient.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();

            logger.info(String.format("response status: %s", statusLine.getStatusCode()));


            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, Consts.UTF_8);
            if(result.length() < 1000) {
                logger.info(String.format("response data: %s", result));
            }else {
                logger.info(String.format("response data: %s", result.substring(0, 100)));
            }
            //BaseResponse baseResponse = new BaseResponse();
            return result;

        } catch (Exception e) {
            logger.error(String.format("调用接口失败，request url: %s", url), e);
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

    /**
     * get 方法
     *
     * @param url
     * @param headerMap
     * @return
     * @throws IOException
     */
    public static String doGet(String url, Map<String, String> headerMap) throws Exception {
        // 通过址默认配置创建一个httpClient实例
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = null;
        try {

            // 创建httpGet远程连接实例
            HttpGet httpGet = new HttpGet(url);

            // 设置配置请求参数
            httpClient = HttpClientBuilder.create().setDefaultRequestConfig(defaultRequestConfig).setConnectionManager(cm).build();

            for (Map.Entry<String, String> entry : headerMap.entrySet()) {//请求头
                httpGet.setHeader(entry.getKey(), entry.getValue());
            }
            // 执行get请求得到返回对象
            response = httpClient.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            logger.info(String.format("request url: %s, response status: %s",
                    url, statusLine.getStatusCode()));
            // 通过返回对象获取返回数据
            HttpEntity entity = response.getEntity();
            // 通过EntityUtils中的toString方法将结果转换为字符串
            result = EntityUtils.toString(entity, Consts.UTF_8);
            if(result.length() < 1000) {
                logger.info(String.format("response data: %s", result));
            }else {
                logger.info(String.format("response data: %s", result.substring(0, 100)));
            }
        } catch (Exception e) {
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
        return result;
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
    public static String sapPostJsonString(String url, String params, Map<String, String> headerMap) throws Exception {
        if (StringUtils.isEmpty(url) || params == null || params.isEmpty()) {
            return null;
        }
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = null;

        try {
            httpClient = HttpClientBuilder.create().setDefaultRequestConfig(defaultRequestConfig).setConnectionManager(cm).build();
            HttpPost httpPost = new HttpPost(url);
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {//请求头
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }

            StringEntity urlEncodedFormEntity = new StringEntity(params, Consts.UTF_8);
            httpPost.setEntity(urlEncodedFormEntity);
            logger.info(String.format("request %s url: %s", headerMap.get("tradeId"), url));
            logger.info(String.format("request headers: %s", headerMap));
            logger.info(String.format("request %s params: %s", headerMap.get("tradeId"), params));
            response = httpClient.execute(httpPost);

            StatusLine statusLine = response.getStatusLine();
            logger.info(String.format("response status: %s", statusLine.getStatusCode()));

            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, Consts.UTF_8);
            if(result.length() < 1000) {
                logger.info(String.format("response %s data: %s", headerMap.get("tradeId"), result));
            }else {
                logger.info(String.format("response %s data: %s", headerMap.get("tradeId"), result.substring(0, 100)));
            }
            return result;
        } catch (Exception e) {
            logger.error(String.format("调用接口失败，request url: %s", url), e);
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
