package com.zzm.solutions.leetcode.medium;

import org.apache.commons.lang3.ArrayUtils;

/**
 * <b> 搜索旋转排序数组 </b><p>题目：</p>
 * <blockquote>
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 * 设计一个时间复杂度为 O(log n) 的解决方案吗？
 * </blockquote><p>
 * 示例 1：<p>
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4<p>
 * 示例 2：<p>
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1<p>
 * 示例 3：<p>
 * 输入：nums = [1], target = 0
 * 输出：-1<p>
 * 提示：<p>
 * 1 <= nums.length <= 5000<p>
 * -10^4 <= nums[i] <= 10^4<p>
 * nums 中的每个值都 独一无二<p>
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转<p>
 * -10^4 <= target <= 10^4<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/3 星期六
 */
public class SearchRotatedSortedArray {

    /**
     * 复杂度为O(log n)的话，就要考虑二分法
     * 没有复杂度要求的话，直接for就够了
     * <p>
     * 思路：
     * 二分法。
     * 从中间分，一定有一部分是有序的，领外一部分是部分有序的，再继续二分
     * 根据有序的部分，确定修改查询的上下界
     *
     * @param nums   数组
     * @param target 目标值
     * @return index
     */
    public static int search(int[] nums, int target) {

        if (ArrayUtils.isEmpty(nums)) {
            return -1;
        }
        int length = nums.length;
        int left = 0;
        int right = length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[length - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }


    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        int search = search(nums, target);
        System.out.println(search);
    }
}
