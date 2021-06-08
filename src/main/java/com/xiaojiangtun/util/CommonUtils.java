package com.xiaojiangtun.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * @author zhudiyuan
 * @version 1.0
 * @date 9:30 2019/11/25
 **/
public class CommonUtils {
    private static final Logger log = LoggerFactory.getLogger(CommonUtils.class);

    /**
     * 金额为分的格式
     */
    public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";
    final static DecimalFormat NF_YUAN = new DecimalFormat("#####################0.00");
    final static DecimalFormat decimalFormat = new DecimalFormat("###################.###########");

    public static String getHostIp() {
        String ipStr = null;
        try {
            InetAddress address = InetAddress.getLocalHost();
            ipStr = address.getHostAddress();
        } catch (UnknownHostException e) {
            log.error("UnknownHostException: ipStr: {},error: {}", ipStr, e);
        }
        return ipStr;
    }

    /*public static String connectStr(String ,){

    }*/

    public static String changeY2F(Long amount) {
        return BigDecimal.valueOf(amount).multiply(new BigDecimal(100)).toString();
    }

    public static BigDecimal changeF2Y(Long amount) {
        return BigDecimal.valueOf(amount).divide(new BigDecimal(100));
    }

    public static BigDecimal moneyFen2Yuan(String fen) {
        if (fen == null || !fen.matches(CURRENCY_FEN_REGEX)) {
            return null;
        }
        return formatYuan(new BigDecimal(fen).divide(new BigDecimal(100)));
    }

    public static BigDecimal changeY2FForMat(Long amount) {
        return formatYuan(changeF2Y(amount));
    }

    public static String changeF2YForMatStr(Long amount) {
        return formatYuan(changeF2Y(amount)).toString();
    }

    public static BigDecimal formatYuan(BigDecimal yuan) {
        return yuan == null ? null : new BigDecimal(NF_YUAN.format(yuan));
    }

    public static String changeF2YForMatStr(String amountStr) {
        return moneyFen2Yuan(amountStr).toString();
    }

    public static String changeF2YFormat(Long amount) {
        return decimalFormat.format(CommonUtils.changeF2Y(amount));
    }

    public static String changeF2YFormat(BigDecimal amount) {
        return decimalFormat.format(amount.divide(new BigDecimal(100)));
    }

    public static int getRandom() {
        Random rand = new Random();
        return rand.nextInt(60) + 20;
    }

    public static Integer getCacheTime(Integer fixTime, Integer randomTime) {
        int random = (int) (Math.random() * randomTime);//随机时间
        return fixTime + random;
    }

    /**
     * 获取模糊查询的城市名
     *
     * @return
     */
    public static String getFuzzyCityName(String city) {
        city = city.endsWith("市") || city.endsWith("州") || city.endsWith("区") ? city.substring(0, city.length() - 1) : city;
        if (city.length() > 2) {
            city = city.substring(0, 2);
        }
        return city;
    }


    public static void main(String[] args) {
        System.out.println("==" + CommonUtils.changeF2Y(100000L));
        System.out.println("==" + getRandom());
    }
}
