package com.zzm.solutions.leetcode.medium.others;

/**
 * <b>两数相除</b>
 * <p>题目：</p>
 * <blockquote>
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * 整数除法的结果应当截去（truncate）其小数部分，
 * 例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 * </blockquote>
 * <p>
 * 示例 1：
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 * <p>
 * 示例 2：
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 解释: 7/-3 = truncate(-2.33333..) = -2
 * </p>
 * <p>
 * <p>
 * 提示：</p>
 * <p> -2^31 <= s <= 2^31 - 1</p>
 * 被除数和除数均为 32 位有符号整数。
 * <p>
 * 除数不为 0。
 * <p>
 * 假设我们的环境只能存储 32 位有符号整数，
 * 本题中，如果除法结果溢出，则返回 2^31 − 1。
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/22 星期二
 */
public class DivideTwoNumbers {


    /**
     * 举个例子：11 除以 3 。
     * <p>
     * 首先11比3大，结果至少是1， 然后我让3翻倍，就是6，发现11比3翻倍后还要大，那么结果就至少是2了，
     * <p>
     * 那我让这个6再翻倍，得12，11不比12大，吓死我了，差点让就让刚才的最小解2也翻倍得到4了。
     * <p>
     * 但是我知道最终结果肯定在2和4之间。也就是说2再加上某个数，这个数是多少呢？
     * <p>
     * 我让11减去刚才最后一次的结果6，剩下5，我们计算5是3的几倍，也就是除法，看，递归出现了
     * <p>
     * 边界情况就是除数是1和-1
     * // 100/3
     * // 100>3 100>6 100>12 100>24 100>48 100>96 100<192 ---- 使用了 2^5 = 32 个3，还剩 100 - 96 = 4 需要被除
     * // 4>3 4<6                                         ---- 使用了 2^0 = 1  个3，还剩 4   - 3  = 1 需要被除
     * // 1<3                                             ---- 被除数小于除数，递归结束，总计使用了 33 个 3
     *
     * @param dividend 除数
     * @param divisor  被除数
     * @return 商
     */
    public static int divide(int dividend, int divisor) {

        if (divisor == 1) {
            return dividend;
        }
        if (divisor == -1) {
            if (dividend > Integer.MIN_VALUE) {
                return 0 - dividend;
            }
            return Integer.MAX_VALUE;
        }
        boolean negative = (dividend ^ divisor) < 0;

        return negative ? 0 - doDivide(dividend, divisor) : doDivide(dividend, divisor);
    }

    private static int doDivide(long dividend, long divisor) {
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        if (dividend < divisor || dividend == 0) {
            return 0;
        }
        long sum = divisor;
        int counter = 1;
        while (dividend >= sum) {
            sum <<= 1;
            counter <<= 1;
        }
        counter >>>= 1;
        sum >>>= 1;

        return counter + doDivide(dividend - sum, divisor);
    }


    public static void main(String[] args) {

        int dividend = 10;
        int divisor = 3;
        int divide = divide(dividend, divisor);
        String msg = String.format("%d / %d = %d", dividend, divisor, divide);
        System.out.println(msg);

        dividend = 7;
        divisor = -3;
        divide = divide(dividend, divisor);
        msg = String.format("%d / %d = %d", dividend, divisor, divide);
        System.out.println(msg);

        dividend = 6;
        divisor = -3;
        divide = divide(dividend, divisor);
        msg = String.format("%d / %d = %d", dividend, divisor, divide);
        System.out.println(msg);

    }
}
