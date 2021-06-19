package com.zzm.solutions.leetcode.easy;

/**
 * <b>二进制数字字符串相加</b>
 * <p>题目：</p><blockquote>
 * <p>给你两个二进制字符串，返回它们的和（用二进制表示）。</p>
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * </blockquote>
 * <p>
 * 提示：</p>
 * <p>每个字符串仅由字符 '0' 或 '1' 组成。</p>
 * <p>1 <= a.length, b.length <= 10^4</p>
 * <p>字符串如果不是 "0" ，就都不含前导零。</p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/19 星期六
 */
public class SumBinaryStringNumbers {

    private static final String FORMAT = "\t %s \n+\t %s \n= \t%s";


    public static String binaryStringSum(String number1, String number2) {
        return SumStringNumbers.binaryStringSum(number1, number2);
    }

    public static void main(String[] args) {
        String a = "10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101";// 超过Long的范围
        String b = "10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101";
        // "10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101"
        // "10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101"
//        a = "11";
//        b = "1";
        String val = binaryStringSum(a, b);
        String sys = String.format(FORMAT, a, b, val);
        System.out.println(sys);

    }

}
