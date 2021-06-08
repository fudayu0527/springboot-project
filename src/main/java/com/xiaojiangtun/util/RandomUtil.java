package com.xiaojiangtun.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;
import java.util.Random;

/**
 * @Author: zhangyong
 * @Date: 2020/7/6 11:33
 */
public class RandomUtil {

    public static char[] str = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private static final Random RAND = new Random();

    public static Random random() {
        return RAND;
    }

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

    /**
     * @Description: 获取随机名字
     * @Date: 2020/7/30
     * @Author: xuanyanxu
     * @return: java.lang.String
     */
    public static String randomName() {
        // 随机人名长度
        int length = RandomUtils.nextInt(2, 6);
        return RandomStringUtils.random(length, "name");
    }

    /**
     * @param
     * @Description: 获取随机头像
     * @Date: 2020/7/30
     * @Author: xuanyanxu
     * @return: java.lang.String
     */
    public static String randomUrl() {
        // 随机获取头像位置
        List<String> urls = Lists.newArrayList("url");
        return urls.get(RAND.nextInt(urls.size() - 1));
    }

}
