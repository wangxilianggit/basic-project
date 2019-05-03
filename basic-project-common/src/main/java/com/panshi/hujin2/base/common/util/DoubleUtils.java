package com.panshi.hujin2.base.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author ZhangZhiHao 2018/8/2 17:34
 */
public abstract class DoubleUtils {

    public static Double sum(Double d1, Double d2) {
        if (d1 == null) {
            d1 = 0D;
        }
        if (d2 == null) {
            d2 = 0D;
        }
        return BigDecimal.valueOf(d1).add(BigDecimal.valueOf(d2)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static Double subtract(Double d1, Double d2) {
        if (d1 == null) {
            d1 = 0D;
        }
        if (d2 == null) {
            d2 = 0D;
        }
        return BigDecimal.valueOf(d1).subtract(BigDecimal.valueOf(d2)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static Double divide(Double d1, Double d2) {
        if (d1 == null || d2 == null || d1 == 0.0 || d2 == 0.0) {
            return 0.0;
        }
        return BigDecimal.valueOf(d1).divide(BigDecimal.valueOf(d2), 2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static Double multiply(Double d1, Double d2) {
        if (d1 == null) {
            d1 = 0D;
        }
        if (d2 == null) {
            d2 = 0D;
        }
        return BigDecimal.valueOf(d1).multiply(BigDecimal.valueOf(d2)).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public static String currencyFormat(Double source) {
        return currencyFormat(source, null);
    }

    public static String currencyFormat(Double source, Locale locale) {
        NumberFormat numberInstance;
        if (locale == null) {
            numberInstance = NumberFormat.getNumberInstance();
        } else {
            numberInstance = NumberFormat.getNumberInstance(locale);
        }
        numberInstance.setMinimumFractionDigits(2);
        numberInstance.setMaximumFractionDigits(2);
        return numberInstance.format(source);
    }

}
