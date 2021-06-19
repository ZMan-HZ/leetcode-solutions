package com.zzm.solutions.leetcode.easy;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * <b>最长公共前缀</b>
 * <p>题目：</p><blockquote>
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。</blockquote>
 * <p>
 * 示例 1：
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * <p>
 * 示例 2：
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""</p><p>
 * 解释：输入不存在公共前缀。</p>
 * <p>
 * 提示：</p>
 * <p>
 * 0 <= strs.length <= 200</p>
 * <p>0 <= strs[i].length <= 200</p>
 * <p>strs[i] 仅由小写英文字母组成</p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/19 星期六
 */
public class LongestCommonPrefix {


    /**
     * 先寻找数组中最短的字符。
     * 则公共前缀的最大长度，即得到最短字符的长度length
     * 然后取每个字符前 0 ->（length--）的子字符串放入set中，如果set的size等于1，则找到了最长公共前缀
     *
     * @param strings 字符数组
     * @return 最长公共前缀字符
     */
    public static String longestCommonPrefixString(String[] strings) {

        if (strings.length == 0) {
            return "";
        }
        if (strings.length == 1) {
            return strings[0];
        }
        int prefixLength = Integer.MAX_VALUE;
        for (String string : strings) {
            prefixLength = Math.min(string.length(), prefixLength);
        }
        for (int length = prefixLength; length > 0; length--) {
            String prefix = "";
            Set<String> prefixes = new LinkedHashSet<>();
            for (String string : strings) {
                prefix = string.substring(0, length);
                prefixes.add(prefix);
            }
            if (prefixes.size() == 1) {
                return prefix;
            }
        }
        return "";
    }

    /**
     * 先从前面但字符串找出最长前缀，然后再以此为字符串往后找最长公共前缀
     *
     * @param strings 字符数组
     * @return 最长公共前缀字符
     */
    public static String longestCommonPrefix(String[] strings) {

        if (strings.length == 0) {
            return "";
        }
        if (strings.length == 1) {
            return strings[0];
        }
        String prefix = strings[0];
        for (int index = 1; index < strings.length; index++) {
            //只要indexOf不是0，就说明不是prefix。-1： 说明不包含，>0：说明包含但不是前缀
//            while (strings[index].indexOf(prefix) != 0) {
            while (!strings[index].startsWith(prefix)) {
                if (prefix.isEmpty()) {
                    return "";
                }
                //缩短prefix的长度
                prefix = prefix.substring(0, prefix.length() - 1);
            }
        }
        return prefix;
    }


    public static void main(String[] args) {
        String[] strings = {"flower", "flight", "flow"};

        String string = longestCommonPrefixString(strings);
        String msg = String.format("%s have common prefix: %s", Arrays.toString(strings), string);
        System.out.println(msg);

        string = longestCommonPrefix(strings);
        msg = String.format("%s have common prefix: %s", Arrays.toString(strings), string);
        System.out.println(msg);


    }

}
