package com.xiaojiangtun.util.http;

import com.alibaba.fastjson.JSON;
import com.xiaojiangtun.util.AESCBCUtil;
import com.xiaojiangtun.util.JSONUtil;
import com.xiaojiangtun.util.MD5;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @Author zhudiyuan
 * @Description
 * @Date 15:47 2019/3/28
 * @Version 1.0
 **/
public class HttpRequestClient {

    private static Logger Logger = LoggerFactory.getLogger(HttpRequestClient.class);


    /**
     * 处理get请求
     *
     * @param url
     * @param username
     * @param password
     * @return
     */
    public static String doGetTextResult(final String url, String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));

        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Accept", "application/json");
        httpGet.addHeader("Accpet-Encoding", "gzip");
        httpGet.addHeader("Content-Encoding", "UTF-8");
        httpGet.addHeader("Content-Type", "application/json; charset=UTF-8");
        httpGet.addHeader("Authorization", String.format("Basic %s", new String(encodedAuth)));
        return LocalHttpClient.executeTextResult(httpGet);
    }

    public static String doGetTextResult(final String url, Map<String, String> headers, Map<String, String> cookies) {
        HttpGet httpGet = new HttpGet(url);
        headers.forEach((k, v) -> {
            httpGet.addHeader(k, v);
        });
        if (cookies != null) {
            AtomicReference<String> cookieString = new AtomicReference<>();
            cookies.forEach((k, v) -> {
                if (StringUtils.isNotEmpty(cookieString.get())) {
                    cookieString.set(cookieString + k + "=" + v + ";");
                } else {
                    cookieString.set(k + "=" + v + ";");
                }
            });
            if (StringUtils.isNotEmpty(cookieString.get())) {
                httpGet.addHeader("Cookie", cookieString.get());
            }
        }
        return LocalHttpClient.executeTextResult(httpGet);
    }

    public static String doGetTextResult(final String url, Map<String, String> headers) {
        return doGetTextResult(url, headers, null);
    }

    /**
     * 多平台Basic认证post请求
     *
     * @param url  请求URL地址
     * @param data json数据
     */
    public static String platFormPost(String url, String data, String username, String password)
            throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        String auth = "";
        auth = String.join(":", username, password);
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
        headers.add("Accept", "application/json");
        headers.add("Accpet-Encoding", "gzip");
        headers.add("Content-Encoding", "UTF-8");
        headers.add("Content-Type", "application/json; charset=UTF-8");
        headers.add("Authorization", "Basic " + new String(encodedAuth));
        HttpEntity<String> formEntity = new HttpEntity<String>(data, headers);
        Assert.notNull(url, "请求url不能为空");
        String result = null;
        try {
            Logger.info("请求外部系统接口,URL:{}", url);
            result = new RestTemplate().postForObject(url, formEntity, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.error("请求外部系统接口异常:{}", e.getMessage());
            throw new Exception("请求外部系统接口异常:" + e.getMessage());
        }
        if (StringUtils.isNotBlank(result)) {
            result = result.replaceAll("\n", "");
        }
        return result;
    }

    /**
     * 有basic认证的post请求
     *
     * @param url
     * @param data
     * @param username
     * @param password
     * @param clazz
     * @param <T>
     * @return 返回对应的实体
     */
    public static <T> T postJsonResult(final String url, String data, String username, String password, Class<T> clazz) {
        HttpPost httpPost = getHttpPostByAuth(url, data, username, password);
        return LocalHttpClient.executeJsonResult(httpPost, clazz);
    }

    /**
     * 无basic认证的post请求
     *
     * @param url
     * @param data
     * @param clazz
     * @param <T>
     * @return 返回对应的实体
     */
    public static <T> T postJsonResultNoAuth(final String url, String data, Class<T> clazz) {
        HttpPost httpPost = getHttpPostByNoAuth(url, data);
        return LocalHttpClient.executeJsonResult(httpPost, clazz);
    }

    /**
     * 无basic认证有header的post请求
     *
     * @param url
     * @param data
     * @param clazz
     * @param <T>
     * @return 返回对应的实体
     */
    public static <T> T postJsonResultNoAuthByHeader(final String url, String data, Map<String, String> param, Class<T> clazz) {
        HttpPost httpPost = getHttpPostByNoAuth(url, data);
        param.forEach((k, v) -> {
            httpPost.addHeader(k, v);
        });
        return LocalHttpClient.executeJsonResult(httpPost, clazz);
    }

    /**
     * 有basic认证的post请求
     *
     * @param url
     * @param data
     * @param username
     * @param password
     * @return 返回json字符串
     */
    public static String postTextResult(final String url, String data, String username, String password) {
        HttpPost httpPost = getHttpPostByAuth(url, data, username, password);
        return LocalHttpClient.executeTextResult(httpPost);
    }

    public static String postTextResultByAuth(final String url, String data, String username, String password) {
        HttpPost httpPost = getHttpPostByAuthBase64(url, data, username, password);
        return LocalHttpClient.executeTextResult(httpPost);
    }

    // Base64加密
    private static HttpPost getHttpPostByAuthBase64(String url, String data, String username, String password) {
        Logger.info("url: {}, username: {}, password: {}, param: {}", url, username, password, data);
        String auth = org.apache.commons.codec.binary.Base64.encodeBase64String((username + ":" + password).getBytes());
        HttpPost httpPost = getHttpPostByNoAuth(url, data);
        httpPost.addHeader("Authorization", String.format("Basic %s", auth));
        return httpPost;
    }

    /**
     * 无basic认证post请求
     *
     * @param url
     * @param data
     * @return 返回json字符串
     */
    public static String postTextResult(final String url, String data) {
        HttpPost httpPost = getHttpPostByNoAuth(url, data);
        return LocalHttpClient.executeTextResult(httpPost);
    }

    /**
     * 无basic认证有header的post请求
     *
     * @param url
     * @param data
     * @return 返回json字符串
     */
    public static String postTextResult(final String url, String data, Map<String, String> param) {
        HttpPost httpPost = getHttpPostByNoAuth(url, data);
        param.forEach((k, v) -> {
            httpPost.addHeader(k, v);
        });
        return LocalHttpClient.executeTextResult(httpPost);
    }

    public static String postTextResult(final String url, String data, Map<String, String> param, Map<String, String> cookies) {
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(data, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);
        param.forEach((k, v) -> {
            httpPost.addHeader(k, v);
        });
        AtomicReference<String> cookieString = new AtomicReference<>();
        cookies.forEach((k, v) -> {
            if (StringUtils.isNotEmpty(cookieString.get())) {
                cookieString.set(cookieString + k + "=" + v + ";");
            } else {
                cookieString.set(k + "=" + v + ";");
            }
        });
        if (StringUtils.isNotEmpty(cookieString.get())) {
            httpPost.addHeader("Cookie", cookieString.get());
        }
        return LocalHttpClient.executeTextResult(httpPost);
    }

    /**
     * 无basic认证有header的post请求传param参数
     *
     * @param url
     * @param param
     * @param header
     * @return 返回json字符串
     */
    public static String postTextResult(final String url, Map<String, Object> param, Map<String, String> header) {
        HttpPost httpPost = getHttpPostByNoAuthToParam(url, param);
        header.forEach((k, v) -> {
            httpPost.addHeader(k, v);
        });
        return LocalHttpClient.executeTextResult(httpPost);
    }

    /**
     * get请求
     *
     * @param url
     * @param username
     * @param password
     * @return 返回json字符串
     */
    public static String getTextResult(final String url, String username, String password) {
        HttpGet httpget = new HttpGet(url);
        String auth = String.join(":", username, password);
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
        httpget.addHeader("Accept", "application/json");
        httpget.addHeader("Accpet-Encoding", "gzip");
        httpget.addHeader("Content-Encoding", "UTF-8");
        httpget.addHeader("Content-Type", "application/json; charset=UTF-8");
        httpget.addHeader("Authorization", String.format("Basic %s", new String(encodedAuth)));
        return LocalHttpClient.executeTextResult(httpget);
    }


    /**
     * 有基本认证
     *
     * @param url
     * @param data
     * @param username
     * @param password
     * @return
     */
    private static HttpPost getHttpPostByAuth(String url, String data, String username, String password) {
        Logger.info("url: {},username={},password={} param: {}", url, username, password, data);
        String auth = String.join(":", username, password);
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
        HttpPost httpPost = getHttpPostByNoAuth(url, data);
        httpPost.addHeader("Authorization", String.format("Basic %s", new String(encodedAuth)));
        StringEntity entity = new StringEntity(data, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);
        return httpPost;
    }

    /**
     * 无基本认证
     *
     * @param url
     * @param data
     * @return
     */
    private static HttpPost getHttpPostByNoAuth(String url, String data) {
        Logger.info("url: {}, param: {}", url, data);
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("Accpet-Encoding", "gzip");
        httpPost.addHeader("Content-Encoding", "UTF-8");
        httpPost.addHeader("Content-Type", "application/json; charset=UTF-8");
        StringEntity entity = new StringEntity(data, ContentType.APPLICATION_JSON);
        //设置传输编码
        entity.setContentType("application/json; charset=UTF-8");
        httpPost.setEntity(entity);
        return httpPost;
    }

    /**
     * 无基本认证 传param参数
     *
     * @param url
     * @param data
     * @return
     */
    private static HttpPost getHttpPostByNoAuthToParam(String url, Map<String, Object> data) {
        Logger.info("url: {}, param: {}", url, data);
        HttpPost httpPost = new HttpPost(url);
        //httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("Accpet-Encoding", "gzip");
        httpPost.addHeader("Content-Encoding", "UTF-8");
        //httpPost.addHeader("Content-Type", "application/json; charset=UTF-8");
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        data.forEach((k, v) -> {
            BasicNameValuePair param = new BasicNameValuePair(k, String.valueOf(v));
            list.add(param);
        });
        // 使用URL实体转换工具
        UrlEncodedFormEntity entityParam = null;
        try {
            entityParam = new UrlEncodedFormEntity(list, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Logger.error("UnsupportedEncodingException: ", e);
        }
        httpPost.setEntity(entityParam);
        return httpPost;
    }

    /**
     * 构造Basic Auth认证头信息
     *
     * @return
     */
    private static String getHeader(String name, String password) {
        String auth = String.join(":", name, password);
        byte[] encodedAuth = org.apache.commons.codec.binary.Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        return authHeader;
    }

    /**
     * 发送创纪云 form格式加密请求
     *
     * @param url
     * @param appSecret
     * @param aesSecret
     * @param params
     * @param cookies
     * @return
     * @throws Exception
     */
    public static String postMidUserFormResult(final String url, String appSecret, String aesSecret, Map<String, String> params, Map<String, String> cookies) throws Exception {
        String data = JSONUtil.toJSON(params);
        Logger.info("url: {}, param: {}", url, data);
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("Accpet-Encoding", "gzip");
        httpPost.addHeader("Content-Encoding", "UTF-8");
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded+cipher");
        long reqTime = System.currentTimeMillis();
        Random random = new Random(reqTime + 2019233);
        Long nonce = random.nextLong();
        String _iv = cookies.get("_iv");
        TreeMap<String, Object> treeMap = new TreeMap();
        if (MapUtils.isNotEmpty(params)) {
            List<String> paramList = params.entrySet().stream().map(a -> a.getKey() + "=" + a.getValue().toString()).collect(Collectors.toList());
            String paramBody = StringUtils.join(paramList, "&");
            String aesBody = AESCBCUtil.encrypt(paramBody, AESCBCUtil.DEFAULT_ENCODING_FORMAT, aesSecret, _iv);
            StringEntity entity = new StringEntity(aesBody, "UTF-8");
            httpPost.setEntity(entity);
            treeMap.putAll(params);
        }
        treeMap.put("nonce", nonce);
        treeMap.put("reqTime", reqTime);
        treeMap.putAll(cookies);
        StringBuilder sb = new StringBuilder(appSecret);
        treeMap.entrySet().forEach(b -> sb.append(b.getKey()).append(b.getValue()));
        String signStr = sb.toString();
        String sign = StringUtils.upperCase(MD5.MD5Encode(signStr));
        String cookie = StringUtils.join(cookies.entrySet().stream().map(a -> a.getKey() + "=" + a.getValue().toString()).collect(Collectors.toList()),";")
                + ";nonce=" + nonce
                + ";reqTime=" + reqTime
                + ";sign=" + sign;
        httpPost.addHeader("Cookie", cookie);
        return LocalHttpClient.executeTextResult(httpPost);
    }

    /**
     * 发送创纪云 json格式加密请求
     *
     * @param url
     * @param appSecret
     * @param aesSecret
     * @param params
     * @param cookies
     * @return
     * @throws Exception
     */
    public static String postMidUserJsonResult(final String url, String appSecret, String aesSecret, String paramData, Map<String, String> params, Map<String, String> cookies) throws Exception {
        Logger.info("postMidUserJsonResult url: {}, param: {}", url, paramData);
        String _iv = cookies.get("_iv");
        String body = AESCBCUtil.encrypt(paramData, AESCBCUtil.DEFAULT_ENCODING_FORMAT, aesSecret, _iv);
        Logger.info("发送数据为：" + body);
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("Accpet-Encoding", "gzip");
        httpPost.addHeader("Content-Encoding", "UTF-8");
        httpPost.addHeader("Content-Type", "application/json+cipher");
        long reqTime = System.currentTimeMillis();
        Random random = new Random(reqTime + 2019233);
        Long nonce = random.nextLong();
        TreeMap<String, Object> treeMap = new TreeMap();
        if (MapUtils.isNotEmpty(params)) {
            List<String> paramList = params.entrySet().stream().map(a -> a.getKey() + "=" + a.getValue().toString()).collect(Collectors.toList());
            String paramBody = StringUtils.join(paramList, "&");

        }
        StringEntity entity = new StringEntity(body, "UTF-8");
        httpPost.setEntity(entity);
        treeMap.put("nonce", nonce);
        treeMap.put("reqTime", reqTime);
        treeMap.putAll(cookies);
        StringBuilder sb = new StringBuilder(appSecret);
        treeMap.entrySet().forEach(b -> sb.append(b.getKey()).append(b.getValue()));
        //增加参数
        sb.append(body);
        String signStr = sb.toString();
        String sign = StringUtils.upperCase(MD5.MD5Encode(signStr));
        String cookie = StringUtils.join(cookies.entrySet().stream().map(a -> a.getKey() + "=" + a.getValue().toString()).collect(Collectors.toList()),";")
                + ";nonce=" + nonce
                + ";reqTime=" + reqTime
                + ";sign=" + sign;
        httpPost.addHeader("Cookie", cookie);
        return LocalHttpClient.executeTextResult(httpPost);
    }
}
