package com.zzm.solutions.leetcode.medium;

import org.apache.commons.lang3.ArrayUtils;

/**
 * <b> 在排序数组中查找元素的第一个和最后一个位置 </b><p>题目：</p>
 * <blockquote>
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。<p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。<p>
 * </blockquote><p>
 * 示例 1：<p>
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]<p>
 * 示例 2：<p>
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]<p>
 * 示例 3：<p>
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]<p>
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
public class FindFirstAndLastIndexOf {

    /**
     * 双指针法
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] firstAndLastIndexOf(int[] nums, int target) {
        int[] answer = {-1, -1};
        if (ArrayUtils.isEmpty(nums)) {
            return answer;
        }
        int length = nums.length;
        if (length == 1 && target == nums[0]) {
            answer[0] = 0;
            answer[1] = 0;
            return answer;
        }
        int left = 0;
        int right = length - 1;

        while (left <= right) {
            int num1 = nums[left];
            int num2 = nums[right];
            if (num1 == num2 && num1 == target) {
                answer[0] = left;
                answer[1] = right;
                return answer;
            }
            if (num1 == target) {
                answer[0] = left;
                right--;
            } else if (num2 == target) {
                answer[1] = right;
                left++;
            } else {
                left++;
                right--;
            }
        }

        return answer;
    }

    /**
     * 由于数组已经排序，因此整个数组是单调递增的，我们可以利用二分法来加速查找的过程。
     * 考虑target 开始和结束位置，其实我们要找的就是数组中「第一个等于target 的位置」（记为leftIdx）和「第一个大于target 的位置减一」（记为rightIdx）。
     *
     * @param target
     * @param nums
     * @return
     */
    public static int[] firstAndLastIndexOf(int target, int[] nums) {
        int[] answer = {-1, -1};
        if (ArrayUtils.isEmpty(nums)) {
            return answer;
        }
        int length = nums.length;
        int left = indexOf(nums, target, false);
        int right = indexOf(nums, target, true) - 1;
        if (left <= right && right < length && target == nums[left] && target == nums[right]) {
            answer[0] = left;
            answer[1] = right;
        }
        return answer;
    }

    private static int indexOf(int[] nums, int target, boolean findLast) {
        int length = nums.length;
        int left = 0;
        int right = length - 1;
        int ans = length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (!findLast && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        int[] index = firstAndLastIndexOf(nums, target);
        System.out.println(ArrayUtils.toString(index));
        index = firstAndLastIndexOf(target, nums);
        System.out.println(ArrayUtils.toString(index));

        int[] nums2 = {1};
        target = 1;
        int[] ints = firstAndLastIndexOf(nums2, target);
        System.out.println(ArrayUtils.toString(ints));
        index = firstAndLastIndexOf(target, nums2);
        System.out.println(ArrayUtils.toString(index));

        int[] nums3 = {2, 2};
        target = 2;
        ints = firstAndLastIndexOf(nums3, target);
        System.out.println(ArrayUtils.toString(ints));
        index = firstAndLastIndexOf(target, nums3);
        System.out.println(ArrayUtils.toString(index));
    }
}
