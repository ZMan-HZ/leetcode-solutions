package com.zzm.solutions.leetcode.easy;

import java.util.Arrays;
import java.util.Objects;

/**
 * <b>最大子序和</b>
 * <p>题目：</p>
 * <blockquote>
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * </blockquote>
 * <p>
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * <p>
 * 示例 2：
 * 输入：nums = [0]
 * 输出：0
 * </p>
 * <p>
 * 示例 3：
 * 输入：nums = [-1]
 * 输出：-1
 * </p>
 * <p>
 * 示例 4：
 * 输入：nums = [-100000]
 * 输出：-100000
 * </p>
 * <p>
 * 提示：</p>
 * <p>
 * 1 <= nums.length <= 3 * 10^4
 * <p>
 * -10^5 <= nums[i] <= 10^5
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/23 星期三
 */
public class MaximumSubarray {


    /**
     * 动态规划
     * 思路和算法
     * <p>
     * 假设 nums 数组的长度是 nn，下标从 0 到 n−1。
     * <p>
     * 我们用 f(i) 代表以第 i 个数结尾的「连续子数组的最大和」，那么很显然我们要求的答案就是：
     * <p>
     * max{f(i)}， 其中 0≤i≤n−1
     * <p>
     * 因此只需要求出每个位置的 f(i)，然后返回 f 数组中的最大值即可。那么如何求 f(i) 呢？nums[i]单独成为一段还是加入 f(i−1) 对应的那一段，
     * 这取决于nums[i] 和 f(i-1) + f(i−1)+nums[i] 的大小，希望获得一个比较大的，于是可以写出这样的动态规划转移方程：
     * <p>
     * f(i)=max{f(i−1)+nums[i],nums[i]}
     * <p>
     * 不难给出一个时间复杂度 O(n)、空间复杂度 O(n) 的实现，
     * <p>
     * 即用一个 f 数组来保存f(i) 的值，用一个循环求出所有 f(i)。考虑到f(i) 只和 f(i−1) 相关，
     * <p>
     * 于是我们可以只用一个变量 pre 来维护对于当前 f(i) 的 f(i−1) 的值是多少，从而让空间复杂度降低到 O(1)，这有点类似「滚动数组」的思想
     *
     * @param nums 数组
     * @return 最大子序的和
     */
    public static int maximumSum(int[] nums) {

        if (Objects.isNull(nums) || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        int[] result = new int[length];
        int max = nums[0];
        result[0] = nums[0];

        for (int index = 1; index < length; index++) {
            result[index] = Math.max(nums[index] + result[index - 1], nums[index]);
            max = Math.max(result[index], max);
        }

        return max;
    }

    /**
     * 贪婪算法，暴力求解
     *
     * @param nums 数组
     * @return 最大子序的和
     */
    public static int greedyMaximumSum(int[] nums) {

        if (Objects.isNull(nums) || nums.length == 0) {
            return 0;
        }
        int result = Integer.MIN_VALUE;

        int length = nums.length;
        for (int index = 0; index < length; index++) {
            int sum = nums[index];
            result = Math.max(sum, result);
            for (int jndex = index + 1; jndex < length; jndex++) {
                result = Math.max(result, nums[jndex]);
                sum += nums[jndex];
                result = Math.max(sum, result);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int maximumSum = maximumSum(nums);
        System.out.println(String.format("%d is maximum subarray sum value of array %s", maximumSum, Arrays.toString(nums)));

         maximumSum = greedyMaximumSum(nums);
        System.out.println(String.format("%d is maximum subarray sum value of array %s", maximumSum, Arrays.toString(nums)));
    }
}
