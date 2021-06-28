package com.zzm.solutions.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <b> 全排列 </b><p>题目：</p>
 * <blockquote>
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * </blockquote><p>
 * 示例 1：<p>
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]<p>
 * 示例 2：<p>
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]<p>
 * 示例 3：<p>
 * 输入：nums = [1]
 * 输出：[[1]]<p>
 * 提示：<p>
 * 1 <= nums.length <= 6<p>
 * -10 <= nums[i] <= 10<p>
 * nums 中的所有整数 互不相同<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/28 星期一
 */
public class PermutationsOfNumbers {


    private PermutationsOfNumbers() {
    }


    /**
     * 回溯法：
     * 一种通过探索所有可能的候选解来找出所有的解的算法。
     * 如果候选解被确认不是一个解（或者至少不是最后一个解），
     * 回溯算法会通过在上一步进行一些变化抛弃该解，即回溯并且再次尝试。
     *
     *
     *
     * @param nums
     * @return
     */
    private static List<List<Integer>> permutations(int[] nums) {

        return null;
    }

    /**
     * 没有算法，直接采用Stream进行操作.
     * 此方法不适用负数
     *
     * @param nums
     * @return
     */
    private static List<List<Integer>> permute(int[] nums) {
        if (Objects.isNull(nums) || nums.length == 0) {
            return new ArrayList<>();
        }
        List<String> numbers = Arrays.stream(nums).boxed().map(String::valueOf).collect(Collectors.toList());
        Stream<String> stream = numbers.stream().distinct();
        for (int index = 1; index < numbers.size(); index++) {
            stream = stream.flatMap(string -> numbers.stream().filter(n -> !string.contains(n)).map(string::concat));
        }
        List<String> collect = stream.collect(Collectors.toList());

        return collect.stream().map(s -> {
            String[] strings = s.split("");
            return Arrays.stream(strings).map(Integer::parseInt).collect(Collectors.toList());
        }).collect(Collectors.toList());
    }


    public static void main(String[] args) {

        int[] nums = {1, 2, 3};
        List<List<Integer>> permutations = permutations(nums);
        System.out.println(String.format("%s whole permutation are %s", Arrays.toString(nums), permutations));

    }
}
