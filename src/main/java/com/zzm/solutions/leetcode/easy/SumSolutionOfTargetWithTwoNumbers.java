package com.zzm.solutions.leetcode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <b>两数之和,寻找数组中两个数的和，等于target</b>
 * <p>题目：</p>
 * <blockquote>
 * 给定一个整数数组 nums 和一个整数目标值 target，<p>
 * 请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。<p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。<p>
 * 你可以按任意顺序返回答案。<p>
 * </blockquote>
 * 示例 1：<p>
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。<p>
 * 示例 2：<p>
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]<p>
 * 示例 3：<p>
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]<p>
 * 提示：<p>
 * 2 <= nums.length <= 10^4<p>
 * -10^9 <= nums[i] <= 10^9<p>
 * -10^9 <= target <= 10^9<p>
 * 只会存在一个有效答案<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/26 星期六
 */
public class SumSolutionOfTargetWithTwoNumbers {


    private SumSolutionOfTargetWithTwoNumbers() {
    }

    /**
     * 方式1： 贪婪式暴力枚举破解
     *
     * @param nums   数组
     * @param target 和
     * @return 坐标
     */
    public static int[] indexOfTwoNumbers(int[] nums, int target) {

        if (Objects.isNull(nums) || nums.length == 0) {
            return new int[0];
        }
        int length = nums.length;
        for (int index = 0; index < length; index++) {
            int num = target - nums[index];
            for (int jndex = index + 1; jndex < length; jndex++) {
                if (num == nums[jndex]) {
                    return new int[]{index, jndex};
                }
            }
        }
        return new int[0];
    }

    /**
     * 哈希表法
     *
     * @param target 和
     * @param nums   数组
     * @return 坐标
     */
    public static int[] indexOfTwoNumbers(int target, int[] nums) {
        if (Objects.isNull(nums) || nums.length == 0) {
            return new int[0];
        }
        int length = nums.length;
        Map<Integer, Integer> keyValue = new HashMap<>(length);
        for (int index = 0; index < length; index++) {
            if (keyValue.containsKey(target - nums[index])) {
                return new int[]{keyValue.get(target - nums[index]), index};
            }
            keyValue.put(nums[index], index);
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 13;
        int[] index = indexOfTwoNumbers(nums, target);
        System.out.println(String.format("%d is sum of two element indexed by %s in array", target, Arrays.toString(index)));
        int[] index3 = indexOfTwoNumbers(target, nums);
        System.out.println(String.format("%d is sum of two element indexed by %s in array", target, Arrays.toString(index3)));

        int[] nums2 = {3, 3};
        target = 6;
        int[] index2 = indexOfTwoNumbers(nums2, target);
        System.out.println(String.format("%d is sum of two element indexed by %s in array", target, Arrays.toString(index2)));
        int[] index4 = indexOfTwoNumbers(target, nums2);
        System.out.println(String.format("%d is sum of two element indexed by %s in array", target, Arrays.toString(index4)));
    }
}
