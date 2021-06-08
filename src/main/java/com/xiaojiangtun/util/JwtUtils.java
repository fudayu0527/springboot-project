package com.xiaojiangtun.util;

import com.auth0.jwt.Algorithm;
import com.auth0.jwt.JWTSigner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    private static final Logger LOG = LoggerFactory.getLogger(JwtUtils.class);


    public static String generateJwt(String shopGid, String appKey, String tenant, String appSecret) {
        Map<String, Object> tokenValues = new HashMap<>();
        tokenValues.put("shop", shopGid);
        tokenValues.put("tenant", tenant);
        tokenValues.put("iss", appKey);

        // JWT签名设置
        JWTSigner.Options options = new JWTSigner.Options().setAlgorithm(Algorithm.HS256);
        options.setIssuedAt(true);

        JWTSigner jwtSigner = new JWTSigner(appSecret);
        String jwt = "Bearer " + jwtSigner.sign(tokenValues, options);
        LOG.info("generateJwt:{} ", jwt);
        return jwt;
    }
}
