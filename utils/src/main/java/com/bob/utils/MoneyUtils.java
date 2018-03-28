package com.bob.utils;

import java.math.BigDecimal;

/**
 * Created by wangxiang on 18/3/28.
 */
public final class MoneyUtils {

    public static void main(String[] args) {

        BigDecimal b1 = new BigDecimal("12.34");
        BigDecimal b2 = new BigDecimal("13.46");
        BigDecimal b3 = new BigDecimal("12.4");

        BigDecimal result = safeAdd(b1, b2, b3);
        int i = result.compareTo(new BigDecimal("38.20"));
        if (i == 0) {
            System.out.println("ok");
        } else {
            System.out.println("wrong");
        }
    }

    /**
     * 加法
     *
     * @param b1
     * @param bn
     * @return
     */
    public static BigDecimal safeAdd(BigDecimal b1, BigDecimal... bn) {

        if (null == b1) {
            b1 = BigDecimal.ZERO;
        }

        BigDecimal result = b1;
        if (null != bn) {
            for (BigDecimal b : bn) {
                result = result.add(null == b ? BigDecimal.ZERO : b);
            }
        }
        return result;
    }

    /**
     * 减法
     *
     * @param b1
     * @param bn
     * @return
     */
    public static BigDecimal safeSubtract(BigDecimal b1, BigDecimal... bn) {

        if (null == b1) {
            b1 = BigDecimal.ZERO;
        }

        BigDecimal result = b1;
        if (null != bn) {
            for (BigDecimal b : bn) {
                result = result.subtract((null == b ? BigDecimal.ZERO : b));
            }
        }
        return result;
    }

    /**
     * 除法
     *
     * @param b1
     * @param b2
     * @param scale        保留小数点后多少位
     * @param defaultValue 默认值
     * @param <T>
     * @return
     */
    public static <T extends Number> BigDecimal safeDivide(T b1, T b2, int scale, BigDecimal defaultValue) {
        if (null == b1 || null == b2) {
            return defaultValue;
        }

        if (scale < 0) {
            throw new IllegalArgumentException("scale 参数必须大于零");
        }

        try {
            return BigDecimal.valueOf(b1.doubleValue()).divide(BigDecimal.valueOf(b2.doubleValue()), scale, BigDecimal.ROUND_HALF_UP);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 乘法
     *
     * @param b1
     * @param b2
     * @param scale 保留小数点位数
     * @param <T>
     * @return
     */
    public static <T extends Number> BigDecimal safeMultiply(T b1, T b2, int scale) {
        if (null == b1 || null == b2) {
            return BigDecimal.ZERO;
        }

        if (scale < 0) {
            throw new IllegalArgumentException("scale 参数必须大于零");
        }

        return BigDecimal.valueOf(b1.doubleValue()).multiply(BigDecimal.valueOf(b2.doubleValue())).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 格式化
     *
     * @param t
     * @param scale 保留小数点位数
     * @param <T>
     * @return
     */
    public static <T extends Number> BigDecimal round(T t, int scale) {

        if (scale < 0) {
            throw new IllegalArgumentException("scale 参数必须大于零");
        }

        if (null == t) {
            return BigDecimal.ZERO;
        }

        BigDecimal b = BigDecimal.valueOf(t.doubleValue());
        BigDecimal one = BigDecimal.ONE;
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP);
    }
}