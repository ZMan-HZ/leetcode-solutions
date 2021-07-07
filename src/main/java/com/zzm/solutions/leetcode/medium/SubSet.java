package com.zzm.solutions.leetcode.medium;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <b>子集</b>
 * <p>题目：</p>
 * <blockquote>
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * </blockquote>
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * <p>
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * <p>
 * 提示：<p>
 * 1 <= nums.length <= 10 <p>
 * -10 <= nums[i] <= 10 <p>
 * nums 中的所有元素 互不相同
 * <p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/7 星期三
 */
public class SubSet {


    /**
     * 思路：
     * 第一感觉就是循环，取 0 - length个元素的组合
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subSet(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (ArrayUtils.isEmpty(nums)) {
            return results;
        }
        //从取 0 个元素的子集开始, 到取所有元素作为子集结束
        for (int count = 0; count <= nums.length; count++) {
            List<List<Integer>> subsets = new ArrayList<>();
            int[] subset = new int[count];
            combine(nums, subset, 0, count, subsets);
            results.addAll(subsets);
        }

        return results;
    }

    private static void combine(int[] nums, int[] subset, int start, int counter, List<List<Integer>> results) {
        if (counter == 0) {
            results.add(Arrays.stream(subset).boxed().collect(Collectors.toList()));
            return;
        }
        for (int index = start; index < nums.length; index++) {
            subset[subset.length - counter] = nums[index];
            combine(nums, subset, index + 1, counter - 1, results);
        }
    }

    /**
     * 字典排序法
     * 思路：只有对应位置为1时的坐标取对应位置的元素
     * 比如nums的长度为3的【1，2，3】
     * 则有字典组合如：
     * 000  ---> 没有1，则时[]空集
     * 001 ----> 取值的元素[3]
     * 010 ----> 取值的元素[2]
     * 100 ----> 取值的元素[1]
     * 011  ---> 取值的元素[2，3]
     * 101 ----> 取值的元素[1，3]
     * 110 ----> 取值的元素[1，2]
     * 111 ----> 取值的元素[1，2，3]
     * 此时所有子集取完
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subs(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (ArrayUtils.isEmpty(nums)) {
            return results;
        }
        int length = nums.length;
        //构造字典,总共个数应该是2^n个
        //这里需要一个技巧，从 0 - length的二进制字符不满足始终是 length 位字符。
        //比如： 0 -> 0； 1 -> 1， 2 -> 10
        //需要扩充1位，成length +1 位的二进制后，去掉头部的一个字符，则始终都是length位二进制
        int start = 1 << length;
        int end = 1 << (length + 1);
        for (int count = start; count < end; count++) {
            //转换成二进制，然后去除头部的一位
            String bit = Integer.toBinaryString(count).substring(1);
            List<Integer> current = new ArrayList<>();
            for (int index = 0; index < length; index++) {
                if (bit.charAt(index) == '1') {
                    current.add(nums[index]);
                }
            }
            results.add(current);
        }
       /* results.sort((o1, o2) -> {
            int i = o1.size() - o2.size();
            return i == 0 ? o1.get(0) - o2.get(0) : i;
        });*/
        results.sort(Comparator.comparingInt(List::size));
        return results;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> subSet = subSet(nums);
        System.out.println(subSet);
        List<List<Integer>> subs = subs(nums);
        System.out.println(subs);
    }
}
