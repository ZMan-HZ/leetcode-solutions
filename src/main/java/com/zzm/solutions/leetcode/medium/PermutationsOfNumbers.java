package com.zzm.solutions.leetcode.medium;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
     * 组合： 此方法是为了更好理解全排列
     * 对于从 N 个元素的数组 nums 中取出 M 个数（不考虑顺序且不重复）的情况，常见的思路是使用递归的思想：
     * 1。从数组 nums 中取出 N 个数，那么可以先取出 nums 的第一个数作为第一个元素
     * 2。取出 nums 的第一个元素之后，从后面的 N-1 个元素中取出 M-1 个元素，（这是第一步的子问题）采用递归实现。
     * 3。当需要取出0个元素时，一个组合的任务完成;
     * 4。回到第一步，利用for循环接着取出第二个元素（开始下一个组合），一共循环 N-M 次即可
     *
     * @param nums   数组
     * @param length 组合所要取元素的个数
     * @return 所有组合
     */
    public static List<List<Integer>> combinations(int[] nums, int length) {

        if (Objects.isNull(nums)
                || nums.length == 0
                || length == 0
                || nums.length < length) {

            return new ArrayList<>(0);
        }
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> numbers = Arrays.stream(nums).boxed().collect(Collectors.toList());
        int[] combined = new int[length];
//        List<Integer> comb = Collections.nCopies(length, 0);
        combine(numbers, combined, 0, length, results);
//        combination(nums, combined, 0, length, results);

        return results;
    }

    /**
     * @param list    供选数组
     * @param start   开始坐标
     * @param counter 个数
     * @param results 结果
     */
    private static void combine(List<Integer> list, int[] combined, int start, int counter, List<List<Integer>> results) {

        if (counter == 0) {
            results.add(Arrays.stream(combined).boxed().collect(Collectors.toList()));
            return;
        }
        for (int index = start; index <= list.size() - counter; index++) {
            combined[combined.length - counter] = list.get(index);
            combine(list, combined, index + 1, counter - 1, results);
        }
    }

    private static void combination(int[] arr, int[] combined, int k, int n, List<List<Integer>> results) {
        //当需要取出的元素个数是0时，说明组合完成
        if (n == 0) {
            results.add(Arrays.stream(combined).boxed().collect(Collectors.toList()));
            return;
        }
        for (int i = k; i <= arr.length - n; i++) {
            //将提取出来的数依次放到新数组中
            combined[combined.length - n] = arr[i];
            //按照同样的方法从剩下的元素中选出n-1个元素
            combination(arr, combined, i + 1, n - 1, results);
        }
    }

    /**
     * 递归+回溯法：
     * 1。对n个元素进行全排列，将第一个元素依次和之后的元素互换，将第一个元素确定下来
     * 2。对之后的n-1个元素进行全排列，（可以看做是第一步的子问题）采用递归实现
     * 3。将互换后的元素重新换回来，以防止数组元素的顺序被打乱（回溯思想）
     *
     * @param nums 待排列数组
     * @return 全排列
     */
    private static List<List<Integer>> permutations(int[] nums) {
        if (Objects.isNull(nums) || nums.length == 0) {
            return new ArrayList<>(0);
        }
        List<List<Integer>> results = new ArrayList<>();
//        doPermutation(nums, 0, results);
//        List<Integer> numbers = Arrays.asList(ArrayUtils.toObject(nums));
        List<Integer> numbers = Arrays.stream(nums).boxed().collect(Collectors.toList());
        doPermutation(numbers, 0, results);
        return results;
    }

    private static void doPermutation(int[] nums, int start, List<List<Integer>> results) {
        //递归终止条件，起始位置等于最后一个了 ，说明这是一个排列了
        if (start == nums.length) {
            results.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            return;
        }
        for (int index = start; index < nums.length; index++) {
            //交换元素
            int num = nums[start];
            nums[start] = nums[index];
            nums[index] = num;
            doPermutation(nums, start + 1, results);
            //再交换回去
            nums[index] = nums[start];
            nums[start] = num;
        }
    }

    private static void doPermutation(List<Integer> numbers, int start, List<List<Integer>> results) {
        if (start == numbers.size()) {
            results.add(new ArrayList<>(numbers));
            return;
        }
        for (int index = start; index < numbers.size(); index++) {
            Collections.swap(numbers, start, index);
            doPermutation(numbers, start + 1, results);
            Collections.swap(numbers, index, start);
        }
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

        int[] nums = {1, 2, 3, 4};
        List<List<Integer>> permutations = permutations(nums);
        System.out.println(String.format("%s whole permutation are %n %s",
                Arrays.toString(nums), permutations.toString().replace("],", "],\n")));

        int length = 3;
        List<List<Integer>> combinations = combinations(nums, length);
        System.out.println(String.format("%s combinations of %s select %d", combinations, Arrays.toString(nums), length));

        List<List<Integer>> results = new ArrayList<>();
        int[] combined = new int[length];
        combination(nums, combined, 0, length, results);
        System.out.println(String.format("%s combinations of %s select %d", results, Arrays.toString(nums), length));


    }
}
