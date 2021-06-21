package com.zzm.solutions.leetcode.medium.dynamicprogram;

import java.util.Objects;

/**
 * <b>最长回文子串</b>
 * <p>题目：</p>
 * <blockquote>
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * </blockquote>
 * <p>
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * <p>
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 * </p>
 * <p>
 * 示例 3：
 * 输入：s = "a"
 * 输出："a"
 * </p>
 * <p>
 * 示例 4：
 * 输入：s = "ac"
 * 输出："a"
 * </p>
 * <p>
 * 提示：</p>
 * <p>
 * <p>s 仅由数字和英文字母（大写和/或小写）组成</p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/21 星期一
 */
public class LongestPalindromicSubstring {

    private LongestPalindromicSubstring() {
    }

    /**
     * 方法一：动态规划<p>
     * 对于一个子串而言，如果它是回文串，并且长度大于2，那么将它首尾的两个字母去除之后，它仍然是个回文串。<p>
     * 例如对于字符串"ababa"，如果我们已经知道"bab" 是回文串，那么"ababa"一定是回文串，这是因为它的首尾两个字母都是"a"。<p>
     * 根据这样的思路，可以用动态规划的方法解决本题
     * <blockquote>
     * 用 P(i, j)表示字符串s的第 i 到 j 个字母组成的串（下文表示成s[i, j]）是否为回文串：<p>
     * 1.​如果子串 S(i)....S(j)是回文串,则P(i, j)也是 <p>
     * 2. 其他情况, 则P(i, j)不是回文 <p>
     * 「其它情况」包含两种可能性：<p>
     * <pre>
     *    1. s[i, j]本身不是一个回文串;
     *    2. i > j，s[i, j]本身不合法。
     * </pre>
     * </blockquote>
     * 动态规划的状态转移方程：
     * <pre>
     *    P(i, j) = P(i+1, j-1) && ( S(i) == S(j) )
     *    即：只有 s[i+1, j-1]是回文串，并且 s 的第 i 和 j 个字母相同时，
     *       s[i, j]才会是回文串。
     * </pre>
     * 考虑动态规划中的边界条件, 即子串的长度为1或2。<p>
     * 对于长度为 1 的子串，显然是个回文串；
     * <p>
     * 对于长度为 2 的子串，只要它的两个字母相同，它就是一个回文串。
     * <p>
     * 因此动态规划的边界条件：
     * <pre>
     *      P(i, i) = true
     *      P(i, i+1) = ( S(i) == S(i+1) )
     * </pre>
     * 答案:
     * 即 所有P(i, j)=true 时 j−i+1（即子串长度）的最大值。
     * <p>
     * 注意：在状态转移方程中，是从长度较短的字符串向长度较长的字符串进行转移的，因此一定要注意动态规划的循环顺序。
     *
     * @param string 字符串
     * @return 最长回文子串
     */
    public static String longestPalindrome(String string) {
        if (Objects.isNull(string) || string.isEmpty()) {
            return "";
        }
        int length = string.length();
        if ((length < 2)) {
            return string;
        }
        int max = 1;
        int init = 0;
        //标记S[i, j]是否回文，即从S(i)-> S(j)是否回文
        boolean[][] flags = new boolean[length][length];
        int tmp = 0;
        //初始化，长度1的都是回文。
        while (tmp < length) {
            flags[tmp][tmp] = true;
            tmp++;
        }
        char[] chars = string.toCharArray();
        //动态规划开始循环
        // 先枚举子串长度
        for (int start = 2; start <= length; start++) {
            for (int index = 0; index < length; index++) {
                // 由 start 和 index 可以确定右边界，即 j - index + 1 = start 得
                int jndex = start + index - 1;

                if (jndex >= length) {
                    break;
                }
                // 头尾字符不相等，不是回文串
                if (chars[index] != chars[jndex]) {
                    flags[index][jndex] = false;
                } else {
                    // 相等的情况下
                    // 考虑头尾去掉以后没有字符剩余，或者剩下一个字符的时候，肯定是回文串
                    flags[index][jndex] = jndex - index < 3 || flags[index + 1][jndex - 1];
                }
                // 只要dp[i][j] == true 成立，表示s[i...j] 是否是回文串
                // 此时更新记录回文长度和起始位置
                if (flags[index][jndex] && jndex - index + 1 > max) {
                    max = jndex - index + 1;
                    init = index;
                }
            }
        }

        return string.substring(init, init + max);
    }

    public static void main(String[] args) {
        String s = "babad";
        String palindrome = longestPalindrome(s);
        System.out.println(String.format("%s has longest palindrome sub string: %s", s, palindrome));


    }
}
