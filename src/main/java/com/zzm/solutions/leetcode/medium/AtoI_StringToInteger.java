package com.zzm.solutions.leetcode.medium;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <b> 字符串转换整数 (atoi) </b><p>题目：</p>
 * <blockquote>
 * 一个机器人位于一个 m x n 网格的左上角, 机器人每次只能向下或者向右移动一步。
 * 机器人试图达到网格的右下角,问总共有多少条不同的路径？
 * </blockquote><p>
 * 示例 1：<p>
 * 输入：m = 3, n = 7
 * 输出：28<p>
 * 示例 2：<p>
 * 输入：m = 3, n = 2
 * 输出：3
 * 解释：
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向下<p>
 * 示例 3：<p>
 * 输入：m = 3, n = 3
 * 输出：6
 * 提示：<p>
 * 1 <= m, n <= 100<p>
 * 题目数据保证答案小于等于 2 * 10^9<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/5 星期一
 */
public class AtoI_StringToInteger {


    /**
     * 没完全理解这个题目
     * 比如：输入"00000-42a1234"，
     * 输出为什么是 0
     * @param s
     * @return
     */
    public static int atoi(String s) {
        String regex = "^([+-]?\\d+)";
        Pattern pattern = Pattern.compile(regex);
        String tmp = s.trim();
        while (tmp.startsWith("0")) {
            tmp = tmp.substring(1);
        }
        Matcher matcher = pattern.matcher(tmp);
        StringBuilder group = new StringBuilder();
        while (matcher.find()) {
            group.append(matcher.group(1));
        }
        int result = 0;
        String number = group.toString();
        if (StringUtils.isEmpty(number)) {
            return 0;
        }
        boolean negative = number.startsWith("-");
        String max = String.valueOf(Integer.MAX_VALUE);
        String min = String.valueOf(Integer.MIN_VALUE);
        if (!negative) {
            if (number.length() > max.length()
                    || (number.length() == max.length() && number.compareTo(max) > 0)) {
                result = Integer.MAX_VALUE;
            } else {
                result = Integer.parseInt(number);
            }
        } else {
            if (number.length() > min.length()
                    || (number.length() == min.length() && number.compareTo(min) > 0)) {
                result = Integer.MIN_VALUE;
            } else {
                result = Integer.parseInt(number);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "42";
        int atoi = atoi(s);
        System.out.println(atoi);

        s = "       -42";
        atoi = atoi(s);
        System.out.println(atoi);

        s = "4193 with words";
        atoi = atoi(s);
        System.out.println(atoi);

        s = "2147483649 with words";
        atoi = atoi(s);
        System.out.println(atoi);

        s = " with words 987";
        atoi = atoi(s);
        System.out.println(atoi);

        s = " with words -2147483649";
        atoi = atoi(s);
        System.out.println(atoi);

        s = "21474836460";
        atoi = atoi(s);
        System.out.println(atoi);
    }
}
