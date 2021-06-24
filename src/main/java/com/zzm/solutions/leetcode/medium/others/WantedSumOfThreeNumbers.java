package com.zzm.solutions.leetcode.medium.others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * <b>三数之和，符合要求值对三个不重复数</b>
 * <p>题目：</p>
 * <blockquote>
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组
 * </blockquote>
 * <p>
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * <p>
 * 示例 2：
 * 输入：nums = []
 * 输出：[]
 * </p>
 * <p>
 * 示例 3：
 * 输入：nums = [0]
 * 输出：[]
 * </p>
 * <p>
 * 提示：</p>
 * <p>
 * 0 <= nums.length <= 3000
 * <p>
 * -10^5 <= nums[i] <= 10^5
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/24 星期四
 */
public class WantedSumOfThreeNumbers {


    /**
     * <b>
     * 排序 + 双指针
     * </b>
     * <p>
     * 算法流程：
     * <p>
     * <pre>
     *  1. 特判，对于数组长度 n，如果数组为 null 或者数组长度小于 3，返回 []。
     *  2. 对数组进行排序。
     *  3. 遍历排序后数组：
     *    (1):若nums[i]>0：因为已经排序好，所以后面不可能有三个数加和等于 0，直接返回结果。
     *    (2):对于重复元素：跳过，避免出现重复解
     *    (3):左指针left = i+1, 右指针right=n-1,当left小于right时,执行循环:
     *      [1]:当nums[Left]+nums[i]+nums[Right]==0,执行循环,
     *         判断左界和右界是否和下一位置重复,去除重复解.
     *         并同时将Left,Right移到下一位置,寻找新的解
     *      [2]:若和大于 0，说明 nums[Right] 太大，Right左移
     *      [3]:若和小于 0，说明 nums[Left] 太小，Left右移
     * </pre>
     *
     * @param nums 数组
     * @return 符合要求的所有数组
     */
    public static List<List<Integer>> foundTargetSumFromArrays(int[] nums, int target) {

        List<List<Integer>> result = new ArrayList<>();
        if (Objects.isNull(nums) || nums.length < 3) {
            return result;
        }
        int length = nums.length;
        Arrays.sort(nums);
        for (int index = 0; index < length; index++) {
            int current = nums[index];
            if (current > target) {
                return result;
            }
            //去重
            if (index > 0 && current == nums[index - 1]) {
                continue;
            }
            //左右指针
            int left = index + 1;
            int right = length - 1;

            while (left < right) {
                int sum = current + nums[left] + nums[right];
                if (sum == target) {
                    List<Integer> answer = new ArrayList<>();
                    answer.add(current);
                    answer.add(nums[left]);
                    answer.add(nums[right]);
                    result.add(answer);
                    //去重,避免选择重复,即答案的组合结果，current不变情况，出现left和right相同的组合
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    //去重,避免选择重复,即答案的组合结果，current不变情况，出现left和right相同的组合
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;

                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, -1, 0, 2, -1, -4};
        int target = 0;
        List<List<Integer>> arrays = foundTargetSumFromArrays(nums, target);
        System.out.println(String.format("%d is sum value of each %s from array %s", target, arrays.toString(), Arrays.toString(nums)));

    }
}
