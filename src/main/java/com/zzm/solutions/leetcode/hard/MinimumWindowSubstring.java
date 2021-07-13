package com.zzm.solutions.leetcode.hard;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * <b>最小覆盖子串</b>
 * <p>题目：</p>
 * <blockquote>
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * </blockquote>
 * <p>
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * <p>
 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"
 * <p>
 * 提示：<p>
 * 1 <= nums.length <= 10 <p>
 * -10 <= nums[i] <= 10 <p>
 * nums 中的所有元素 互不相同
 * <p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/12 星期一
 */
public class MinimumWindowSubstring {

    /**
     * 思路：滑动窗口
     * 1。 两个指针，一前一后，两者之间形成一个window
     * 2。 后边的指针先移动，往前移动一格就判断是否包含目标串，不包含就一直移动
     * 3。如果包含目标串，此时移动前面的指针，缩短子串，然后一次判断是否包含目标子串
     * 4。如果移动前面指针发现，缩短后都不包含了，此时再向前移动后面的指针
     * 5。直到结尾
     * <p>
     * //注意这个 t 可以包含重复字符
     * //所以子串必须包含的个数也一样
     * //因此这个算法目前来看是有问题的
     *
     * @param s 源串
     * @param t 目标子串
     * @return 最小覆盖子串
     */
    public static String min(String s, String t) {
        String result = "";
        if (StringUtils.isEmpty(s) || StringUtils.isBlank(s)
                || StringUtils.isBlank(t) || StringUtils.isEmpty(t)) {

            return result;
        }
        int length = s.length();
        int left = 0;
        int right = 0;
        int board = length - t.length();
        int minLength = Integer.MAX_VALUE;

        //注意这个 t 可以包含重复字符
        //所以子串必须包含的个数也一样
        //因此这个算法目前来看是有问题的
        List<String> target = Arrays.asList(t.split(""));

        while (left <= board && right <= length) {
            String subString = s.substring(left, right);
            List<String> sub = Arrays.asList(subString.split(""));
            if (sub.size() >= target.size() && sub.containsAll(target)) {
                left++;
                minLength = Math.min(subString.length(), minLength);
                result = subString.length() <= minLength ? subString : result;
            } else {
                right++;
            }
        }

        return result;
    }

    /**
     * 思路：滑动窗口
     * 1。 两个指针，一前一后，两者之间形成一个window
     * 2。 后边的指针先移动，往前移动一格就判断是否包含目标串，不包含就一直移动
     * 3。如果包含目标串，此时移动前面的指针，缩短子串，然后一次判断是否包含目标子串
     * 4。如果移动前面指针发现，缩短后都不包含了，此时再向前移动后面的指针
     * 5。直到结尾
     * <p>
     * //注意这个 t 可以包含重复字符
     * //所以子串必须包含的个数也一样
     *
     * @param s 源串
     * @param t 目标子串
     * @return 最小覆盖子串
     */
    public static String minWindow(String s, String t) {
        if (StringUtils.isEmpty(s) || StringUtils.isBlank(s)
                || StringUtils.isBlank(t) || StringUtils.isEmpty(t)) {

            return "";
        }
        int length = s.length();
        int left = 0;
        int right = 0;
        int minLength = length + 1;
        //滑动窗口内有多少个T中的字符
        int count = 0;
        int start = 0;

        //用于存放要覆盖的每个字符出现的次数
        Map<Character, Integer> table = new HashMap<>(t.length());
        for (char chr : t.toCharArray()) {
            table.put(chr, table.getOrDefault(chr, 0) + 1);
        }

        Map<Character, Integer> window = new HashMap<>();
        while (right < length) {
            char r = s.charAt(right);
            if (!table.containsKey(r)) {
                right++;
                continue;
            }
            //子串中的r字符出现次数小于目标字符串中的个数
            if (window.getOrDefault(r, 0) < table.getOrDefault(r, 0)) {
                count++;
            }
            window.put(r, window.getOrDefault(r, 0) + 1);
            right++;

            //窗口内已经包含所有的目标字符中的字符
            while (count == t.length()) {
                if (right - left < minLength) {
                    start = left;
                    minLength = right - left;
                }
                char l = s.charAt(left);
                if (!table.containsKey(l)) {
                    left++;
                    continue;
                }
                //更新记录的覆盖的目标字符数，缩短子串，left右移
                if (window.getOrDefault(l, 0).equals(table.getOrDefault(l, 0))) {
                    count--;
                }
                window.put(l, window.getOrDefault(l, 0) - 1);
                left++;
            }
        }

        return minLength == length + 1 ? "" : s.substring(start, start + minLength);
    }


    public static void main(String[] args) {

        String s = "ADOBECODEBANC";
        String t = "ABC";
        String window = minWindow(s, t);
        System.out.println(window);

        s = "aa";
        t = "aa";
        window = minWindow(s, t);
        System.out.println(window);

        s = "acbbaca";
        t = "aba";
        window = minWindow(s, t);
        System.out.println(window);
    }
}
