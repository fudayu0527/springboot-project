package com.xiaojiangtun.util;


import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SignUtils {

    public static final String JSON_STR = "JSON";

    /**
     * 生成API请求参数的sign对应的值
     * @param map
     * @param appSecret
     * @return
     */
    public static String getSignValue(Map<String,Object> map, String appSecret){

        String version = map.get("version").toString();
        String nonce = map.get("nonce").toString();
        String time = map.get("time").toString();
        StringBuffer sourceStr = new StringBuffer();
        sourceStr.append("#").append(version).append("#").append(appSecret)
        .append("#").append(time).append("#").append(nonce);
        return MD5.MD5Encode(sourceStr.toString()).toUpperCase();
    }

    public static String getSignStrValue(Map<String,String> map, String appSecret){
        String version = map.get("version");
        String nonce = map.get("nonce");
        String time = map.get("time");
        StringBuffer sourceStr = new StringBuffer();
        sourceStr.append("#").append(version).append("#").append(appSecret)
                .append("#").append(time).append("#").append(nonce);
        return MD5.MD5Encode(sourceStr.toString()).toUpperCase();
    }

    /**
     * 根据入参升序获取签名
     * @param signParam
     * @param appSecret
     * @return
     */
    public static String getSignStrValueByKeyOrder(Map<String,String> signParam, String appSecret) {
        List<String> sortedKeyList = signParam.entrySet().stream().map(e -> e.getKey()).sorted(Comparator.naturalOrder()).collect(Collectors.toList());

        StringBuffer sourceStr = new StringBuffer();
        sourceStr.append(appSecret);
        for(String key : sortedKeyList) {
            if(!JSON_STR.equals(key)) {
                sourceStr.append(key).append(signParam.get(key));
            }
        }
        if(StringUtils.isNoneEmpty(signParam.get(JSON_STR))) {
            sourceStr.append(signParam.get(JSON_STR));
        }
        return MD5.MD5Encode(sourceStr.toString()).toUpperCase();
    }

    public static void main(String[] args) {
        Integer status = 0;
        Short deliveryState = 6;
        int an =  deliveryState;
        System.out.println("=="+an);
        System.out.println(0 == status);
        Map<String,String> paramMap1 = new HashMap<>();
        paramMap1.put("version","storeexpress1.0");
        paramMap1.put("nonce","9jlr7o3q080x71jqixeeywifwtf7rakk");
        paramMap1.put("time", String.valueOf(System.currentTimeMillis()));
        System.out.println(paramMap1.get("time"));
        paramMap1.put(JSON_STR, "{\"skuCodeList\":[\"0200011801\",\"0100042201\"],\"hdStoreCode\":\"000126\"}");
        String sign = getSignStrValueByKeyOrder(paramMap1,"ffe232&t%4df!67sx55eas");
        System.out.println("sigen:==  "+sign);
        System.out.println("EE629F0722CCA27E497EEA991AAAF2D6");

        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("version","storeexpress1.0");
        paramMap.put("nonce","dect9z4g05giqudgl7uq79faayxe6eet");
        paramMap.put("time", String.valueOf(System.currentTimeMillis()));
        System.out.println(paramMap.get("time"));
        paramMap.put("uid", "1030001006");
        paramMap.put("batchId", "1");
        String sign2 = getSignStrValueByKeyOrder(paramMap, "ffe232&t%4df!67sx55eas");
        System.out.println("sigen:==  "+sign2);
        System.out.println("4939CEF70CBF7B7F825C49398098C88D");


    }
}
