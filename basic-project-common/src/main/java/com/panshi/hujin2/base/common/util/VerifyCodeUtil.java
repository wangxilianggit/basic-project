package com.panshi.hujin2.base.common.util;

/**
 * @author ZhangZH 2017/12/12 19:24
 */
public class VerifyCodeUtil {

    /**
     * 生成4位的随机数
     */
    public static String generateRandomNumber() {
        int randomInt = (int) ((Math.random() * 9 + 1) * 1000);
        return String.valueOf(randomInt);
    }
}
