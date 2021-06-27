package com.zzm.solutions.leetcode.medium;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * <b>无重复字符的最长子串</b>
 * <p>题目：</p>
 * <blockquote>
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * </blockquote>
 * <p>
 * 示例 1：
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 示例 2：
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * </p>
 * <p>
 * 示例 3：
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * </p>
 * <p>
 * 示例 4：
 * 输入: s = ""
 * 输出: 0
 * </p>
 * <p>
 * 提示：</p>
 * <p>
 * <p>s 由英文字母、数字、符号和空格组成</p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/19 星期六
 */
public class LongestNonDupSubstring {


    /**
     * 思路：
     * 两个指针A，B，指针A从0开始滑动指向字符串的每个字符，记录经过的所有字符C Set，
     * 指针B仅当A滑动得到的字符已经在C Set中录入即重复时则从0向前移动并移除B指向的字符。
     * 最大值则为两个指针间的长度
     *
     * @param string 字符串
     * @return int长度
     */
    public static int lengthOfLongestNonDupSubstring(String string) {

        if (Objects.isNull(string) || string.isEmpty()) {
            return 0;
        }
        int result = -1;
        int length = string.length();
        Set<Character> charSet = new HashSet<>();
        int index1 = 0;
        int index2 = 0;
        while (index1 < length && index2 < length) {
            char chr = string.charAt(index2);
            if (!charSet.contains(chr)) {
                char chr1 = string.charAt(index2++);
                charSet.add(chr1);
                result = Math.max(result, index2 - index1);
            } else {
                char chr2 = string.charAt(index1++);
                charSet.remove(chr2);
            }
        }
        return result;
    }

    public static void main(String[] args) {

        System.out.println("Length of Longest Non duplicated sub string...");

        String s = "aaaaa";
        int length = lengthOfLongestNonDupSubstring(s);
        String msg = String.format("[%s] -> %s", s, length);
        System.out.println(msg);

        s = "abcabcabc";
        length = lengthOfLongestNonDupSubstring(s);
        msg = String.format("[%s]  -> %s", s, length);
        System.out.println(msg);

        s = "pwwkew";
        length = lengthOfLongestNonDupSubstring(s);
        msg = String.format("[%s] -> %s", s, length);
        System.out.println(msg);

        s = "";
        length = lengthOfLongestNonDupSubstring(s);
        msg = String.format("[%s] -> %s", s, length);
        System.out.println(msg);

    }

}
