package com.zzm.solutions.leetcode.medium;

import com.zzm.solutions.common.StringConstants;
import com.zzm.solutions.common.ApacheStringUtils;
import com.zzm.solutions.leetcode.easy.SumStringNumbers;


/**
 * <b>十进制数字字符串乘法，大数乘法</b>
 * <p>题目：</p><blockquote>
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * </blockquote>
 * <p>
 * 示例 1:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * <p>
 * 示例 2:
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * <p>
 * 说明：</p>
 * <p>num1 和 num2 的长度小于110</p>
 * <p>num1 和 num2 只包含数字 0-9</p>
 * <p>不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * </p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/19 周六
 */
public class MultiplyStringNumbers {

    private static final Character CHAR_ZERO = '0';
    private static final String FORMAT = "%s * %s = %s";

    /**
     * <p>竖式优化版</p>根据乘法竖式展开式进行计算
     * 该算法是通过两数相乘时，乘数某位与被乘数某位相乘，与产生结果的位置的规律来完成。
     * <blockquote>具体规律如下：
     * 乘数 num1 位数为 M，被乘数 num2 位数为 N， num1 x num2 结果 res 最大总位数为 M+N。
     * num1[i] x num2[j] 的结果为 tmp(位数为两位)，其第一位位于 res[i+j]，第二位位于 res[i+j+1]。
     * </blockquote>
     * <blockquote>
     * <pre>        1 2 3</pre>
     * <pre>          4 5</pre>
     * <pre>    -----------</pre>
     * <pre>          1 5</pre>
     * <pre>        1 0</pre>
     * <pre>      0 5</pre>
     * <pre>        1 2</pre>
     * <pre>      0 8</pre>
     * <pre>    0 4</pre>
     * <pre>   -----------</pre>
     * <pre>res[0 1 2 3 4]</pre>
     * </p></blockquote>
     *
     * @param number1 乘数
     * @param number2 被乘数
     * @return 积
     */
    public static String enhancedMultiply(String number1, String number2) {
        number1 = ApacheStringUtils.trimStartZero(number1);
        number2 = ApacheStringUtils.trimStartZero(number2);
        if (StringConstants.ZERO.equals(number1) || StringConstants.ZERO.equals(number2)) {
            return StringConstants.ZERO;
        }
        int length1 = number1.length();
        int length2 = number2.length();
        int[] tmp = new int[length1 + length2];

        for (int index = length1 - 1; index >= 0; index--) {
            int n1 = number1.charAt(index) - CHAR_ZERO;
            for (int jndex = length2 - 1; jndex >= 0; jndex--) {
                int n2 = number2.charAt(jndex) - CHAR_ZERO;
                int sum = tmp[index + jndex + 1] + n1 * n2;
                tmp[index + jndex + 1] = sum % 10;
                tmp[index + jndex] += sum / 10;
            }
        }
        StringBuilder sb = new StringBuilder(tmp.length);
        for (int each : tmp) {
            sb.append(each);
        }
        return ApacheStringUtils.trimStartZero(sb.toString());
    }

    /**
     * <p>普通竖式</p>
     * 竖式运算思想，以 num1 为 123，num2 为 456 为例分析：
     * <blockquote><pre>    1 2 3</pre>
     * <pre>    4 5 6</pre>
     * <pre>-----------</pre>
     * <pre>    7 3 8</pre>
     * <pre>  6 1 5 0</pre>
     * <pre>4 9 2 0 0</pre>
     * <pre>-----------</pre>
     * <pre>5 6 0 8 8</p></blockquote>
     * <p>
     * 然后进行累加
     * </p>
     *
     * @param number1 乘数
     * @param number2 被乘数
     * @return 积
     */
    public static String normalMultiply(String number1, String number2) {
        number1 = ApacheStringUtils.trimStartZero(number1);
        number2 = ApacheStringUtils.trimStartZero(number2);
        if (StringConstants.ZERO.equals(number1) || StringConstants.ZERO.equals(number2)) {
            return StringConstants.ZERO;
        }
        int length1 = number1.length();
        int length2 = number2.length();
        String result = "0";

        //逐位相乘
        for (int index = length2 - 1; index >= 0; index--) {
            int carry = 0;
            StringBuilder sb = new StringBuilder();
            //补位 0
            for (int tmp = 0; tmp < length2 - 1 - index; tmp++) {
                sb.append("0");
            }
            int n1 = number2.charAt(index) - CHAR_ZERO;
            for (int jndex = length1 - 1; jndex >= 0; jndex--) {
                int n2 = number1.charAt(jndex) - CHAR_ZERO;
                int res = n1 * n2 + carry;
                sb.append(res % 10);
                carry = res / 10;
            }
            if (carry != 0) {
                sb.append(carry);
            }
            result = SumStringNumbers.decimalStringSum(result, sb.reverse().toString());
        }

        return result;
    }


    public static void main(String[] args) {
        String number1 = "123";
        String number2 = "456";

        String result = enhancedMultiply(number1, number2);
        String sys = String.format(FORMAT, number1, number2, result);
        System.out.println(sys);
        System.out.println(result.equals(normalMultiply(number1, number2)));

        number1 = "000";
        number2 = "123";
        result = enhancedMultiply(number1, number2);
        sys = String.format(FORMAT, number1, number2, result);
        System.out.println(sys);
        System.out.println(result.equals(normalMultiply(number1, number2)));

        number1 = "010";
        number2 = "123";
        result = enhancedMultiply(number1, number2);
        sys = String.format(FORMAT, number1, number2, result);
        System.out.println(sys);
        System.out.println(result.equals(normalMultiply(number1, number2)));

    }

}
