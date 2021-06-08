package com.xiaojiangtun.util;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @Author: zhangyong
 * @Date: 2020/7/1 17:47
 */
public class DataUtil {

    public static char[] str = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static String genRandomNum(Integer num){
        int maxNum = 36;
        int i;
        int count = 0;
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while(count < num){
            i = Math.abs(r.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count ++;
            }
        }
        return pwd.toString();
    }

    public static String getWalletMemberToken(String memberId) {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("mmss")) +
                genRandomNum(4) +
                memberId.substring(0,4) +
                genRandomNum(4);
    }

    public static String getMemberToken(String memberId) {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("mmss")) +
                genRandomNum(3) +
                memberId.substring(memberId.length()-4) +
                genRandomNum(5);
    }

}
