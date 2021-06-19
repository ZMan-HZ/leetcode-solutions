package com.zzm.solutions.common;

import java.util.Objects;

/**
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/19 周六
 */
public class StringUtils {

    private StringUtils() {
    }

    /**
     * 移除数字字符串开头的0
     *
     * @param number 数字字符串
     * @return
     */
    public static String trimStartZero(String number) {
        if (Objects.isNull(number)
                || number.isEmpty()
                || StringConstants.ZERO.equals(number)) {
            return StringConstants.ZERO;
        }

        if (!number.startsWith(StringConstants.ZERO)) {
            return number;
        }
        //递归可以使用while迭代
        return trimStartZero(number.substring(1));
    }
}
