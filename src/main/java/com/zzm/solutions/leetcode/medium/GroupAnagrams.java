package com.zzm.solutions.leetcode.medium;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * <b> 字母异位词分组(将含有相同字母的单词分组) </b><p>题目：</p>
 * <blockquote>
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 * </blockquote><p>
 * 示例 1：<p>
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]<p>
 * 提示：<p>
 * 所有输入均为小写字母。<p>
 * 不考虑答案输出的顺序。<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/4 星期日
 */
public class GroupAnagrams {


    /**
     * 思路：
     * 对字符串的char进行排序，放入map中
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> results = new ArrayList<>();
        if (ArrayUtils.isEmpty(strs)) {
            return results;
        }
        Map<String, List<String>> map = new TreeMap<>();
        for (String args : strs) {
            char[] chars = args.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars).toLowerCase();
            if (map.containsKey(key)) {
                map.get(key).add(args);
            } else {
                map.put(key, Lists.newArrayList(args));
            }
        }
        results.addAll(map.values());
        return results;
    }


    public static void main(String[] args) {
        String[] strings = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> anagrams = groupAnagrams(strings);
        System.out.println(anagrams);
    }
}
