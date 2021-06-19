package com.zzm.solutions.leetcode.easy;

import com.zzm.solutions.common.StringConstants;
import com.zzm.solutions.common.StringUtils;

/**
 * <b>数字字符串相加</b>
 * <p>题目：</p><blockquote>
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。</blockquote>
 * <p>
 * 提示：</p>
 * <p>
 * num1 和num2 的长度都小于 5100</p>
 * <p>num1 和num2 都只包含数字 0-9</p>
 * <p>你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
 * </p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/19 周六
 */
public class SumStringNumbers {

    private static final Character CHAR_ZERO = '0';
    private static final String FORMAT = "%s + %s = %s";

    /**
     * 两个大整数模拟「竖式加法」的过程。
     * 竖式加法就是在纸上对两个整数相加的操作，从低到高逐位相加，如果当前位和超过10，则向高位进一位.
     * 定义两个指针 i 和 j 分别指向num1和 num2的末尾，即最低位，
     * 同时定义一个变量 carry 维护当前是否有进位，然后从末尾到开头逐位相加即可。
     * 统一在指针当前下标处于负数的时候返回 0，等价于对位数较短的数字进行了补零操作
     *
     * @param number1 加数
     * @param number2 被加数
     * @return 和
     */
    public static String decimalStringSum(String number1, String number2) {
        return stringSum(number1, number2, 10);
    }

    public static String binaryStringSum(String number1, String number2) {
        return stringSum(number1, number2, 2);
    }


    private static String stringSum(String number1, String number2, int weight) {
        number1 = StringUtils.trimStartZero(number1);
        number2 = StringUtils.trimStartZero(number2);
        if (StringConstants.ZERO.equals(number1)) {
            return number2;
        }
        if (StringConstants.ZERO.equals(number2)) {
            return number1;
        }
        int index = number1.length() - 1;
        int jndex = number2.length() - 1;
        int carry = 0;
        StringBuilder result = new StringBuilder(Math.max(index, jndex) + 1);
        while (index >= 0 || jndex >= 0) {
            if (index >= 0) {
                carry += number1.charAt(index--) - CHAR_ZERO;
            }
            if (jndex >= 0) {
                carry += number2.charAt(jndex--) - CHAR_ZERO;
            }
            result.append(carry % weight);
            carry = carry / weight;
        }
        if (carry != 0) {
            result.append(carry);
        }
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        String number1 = "123";
        String number2 = "456";

        String result = decimalStringSum(number1, number2);
        String sys = String.format(FORMAT, number1, number2, result);
        System.out.println(sys);

        number1 = "000";
        number2 = "123";
        result = decimalStringSum(number1, number2);
        sys = String.format(FORMAT, number1, number2, result);
        System.out.println(sys);

        number1 = "010";
        number2 = "123";
        result = decimalStringSum(number1, number2);
        sys = String.format(FORMAT, number1, number2, result);
        System.out.println(sys);
    }

}
