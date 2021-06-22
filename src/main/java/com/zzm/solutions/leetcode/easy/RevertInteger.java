package com.zzm.solutions.leetcode.easy;

/**
 * <b>整数反转</b>
 * <p>题目：</p>
 * <blockquote>
 * 给你一个 32 位的有符号整数 s ，返回将 s 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围 [−2^31,  2^31 − 1] ，就返回 0。
 * </blockquote>
 * <p>
 * 示例 1：
 * 输入: s = 123
 * 输出: 321
 * <p>
 * 示例 2：
 * 输入: s = -123
 * 输出: -321
 * </p>
 * <p>
 * 示例 3：
 * 输入: s = 120
 * 输出: 21
 * </p>
 * <p>
 * 示例 4：
 * 输入: s = 0
 * 输出: 0
 * </p>
 * <p>
 * 提示：</p>
 * <p> -2^31 <= s <= 2^31 - 1</p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/22 星期二
 */
public class RevertInteger {

    private RevertInteger() {
    }

    /**
     * 数学计算反转
     *
     * @param x 数字
     * @return 反转
     */
    public static int revertInteger(int x) {
        int result = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            //校验是否越界： 2147483648 - 1
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            //校验是否越界：-2147483648
            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            result = result * 10 + pop;
        }
        return result;
    }

    public static void main(String[] args) {
        int x = 123;
        int integer = revertInteger(x);
        String msg = String.format("%s reverted to %s", x, integer);
        System.out.println(msg);

        x = 123456789;
        integer = revertInteger(x);
        msg = String.format("%s reverted to %s", x, integer);
        System.out.println(msg);

        x = Integer.MAX_VALUE / 3;
        integer = revertInteger(x);
        msg = String.format("%s reverted to %s", x, integer);
        System.out.println(msg);

    }
}
