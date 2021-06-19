package com.zzm.solutions.leetcode.hard;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * <b>寻找两个正序数组的中位数</b>
 * <p>题目：</p>
 * <blockquote>
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * </blockquote>
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * <p>
 * 示例 2：
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * </p>
 * <p>
 * 示例 3：
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 * </p>
 * <p>
 * 示例 4：
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 * </p>
 * <p>
 * 示例 5：
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 * </p>
 * <p>
 * 提示：</p>
 * <p>
 * nums1.length == m
 * <p>
 * nums2.length == n
 * <p>
 * 0 <= m <= 1000
 * <p>
 * 0 <= n <= 1000
 * <p>
 * 1 <= m + n <= 2000
 * <p>
 * -106 <= nums1[i], nums2[i] <= 106
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/19 星期六
 */
public class MedianOfTwoSortedArrays {


    /**
     * 思路：
     * 合并两个有序数组，使其仍然有序，然后求中位数
     * 复杂度：O(m+n)
     *
     * @param array1 有序数组1
     * @param array2 有序数组2
     * @return 中位数
     */
    public static double getMedianOfMergedArrays(int[] array1, int[] array2) {
        if ((Objects.isNull(array1) || array1.length == 0)
                && (Objects.isNull(array2) || array2.length == 0)) {
            return 0.0;
        }
        int length1 = Optional.ofNullable(array1).map(array -> array.length).orElse(0);
        int length2 = Optional.ofNullable(array2).map(array -> array.length).orElse(0);

        int left = (length1 + length2 + 1) / 2 - 1;
        int right = (length1 + length2 + 2) / 2 - 1;

        int[] merged = new int[length1 + length2];

        int cursor = 0;
        int index = 0;
        int jndex = 0;

        while (index <= length1 || jndex <= length2) {
            if (index == length1) {
                while (jndex < length2) {
                    merged[cursor++] = array2[jndex++];
                }
                break;
            }
            if (jndex == length2) {
                while (index < length1) {
                    merged[cursor++] = array1[index++];
                }
                break;
            }
            if (array1[index] < array2[jndex]) {
                merged[cursor++] = array1[index++];
            } else {
                merged[cursor++] = array2[jndex++];
            }
        }
        return (merged[left] + merged[right]) / 2.0;
    }

    /**
     * 求两个有序数组的中位数，而且限制了时间复杂度为O(log (m+n))，看到这个时间复杂度，应该使用二分查找法来求解。
     * 中位数的定义，如果某个有序数组长度是奇数，那么其中位数就是最中间那个，如果是偶数，那么就是最中间两个数字的平均值。
     * 这里对于两个有序数组也是一样的，假设两个有序数组的长度分别为m和n，由于两个数组长度之和 m+n 的奇偶不确定，因此需要分情况来讨论
     * 对于奇数的情况，直接找到最中间的数即可，偶数的话需要求最中间两个数的平均值。
     * 为了简化代码，不分情况讨论，我们使用一个小trick，我们分别找第 (m+n+1) / 2 个，和 (m+n+2) / 2 个，然后求其平均值即可，这对奇偶数均适用。
     * 加入 m+n 为奇数的话，那么其实 (m+n+1) / 2 和 (m+n+2) / 2 的值相等，相当于两个相同的数字相加再除以2，还是其本身。
     * <p>
     * 定义一个函数来在两个有序数组中找到第K个元素。
     * 首先，为了避免产生新的数组从而增加时间复杂度，我们使用两个变量i和j分别来标记数组nums1和nums2的起始位置。
     * 然后来处理一些边界问题，比如当某一个数组的起始位置大于等于其数组长度时，说明其所有数字均已经被淘汰了，相当于一个空数组了，那么实际上就变成了在另一个数组中找数字，直接就可以找出来了。
     * 还有就是如果K=1的话，那么我们只要比较nums1和nums2的起始位置i和j上的数字就可以了。
     * 难点就在于一般的情况怎么处理？
     * 因为我们需要在两个有序数组中找到第K个元素，为了加快搜索的速度，我们要使用二分法，对K二分，意思是我们需要分别在nums1和nums2中查找第K/2个元素，
     * 注意这里由于两个数组的长度不定，所以有可能某个数组没有第K/2个数字，
     * 所以我们需要先检查一下，数组中到底存不存在第K/2个数字，
     * 如果存在就取出来，否则就赋值上一个整型最大值。
     * 如果某个数组没有第K/2个数字，那么我们就淘汰另一个的前K/2个数字即可。
     * 有没有可能两个数组都不存在第K/2个数字呢，
     * 这道题里是不可能的，因为我们的K不是任意给的，而是给的m+n的中间值，所以必定至少会有一个数组是存在第K/2个数字的。
     * 最后就是二分法的核心啦，比较这两个数组的第K/2小的数字midVal1和midVal2的大小，
     * 如果第一个数组的第K/2个数字小的话，那么说明我们要找的数字肯定不在nums1中的前K/2个数字，
     * 所以我们可以将其淘汰，将nums1的起始位置向后移动K/2个，并且此时的K也自减去K/2，调用递归。
     * 反之，我们淘汰nums2中的前K/2个数字，并将nums2的起始位置向后移动K/2个，并且此时的K也自减去K/2，调用递归即可。
     */
    public static double getMedianOfArrays(int[] array1, int[] array2) {

        if ((Objects.isNull(array1) || array1.length == 0)
                && (Objects.isNull(array2) || array2.length == 0)) {
            return 0.0;
        }
        int length1 = Optional.ofNullable(array1).map(array -> array.length).orElse(0);
        int length2 = Optional.ofNullable(array2).map(array -> array.length).orElse(0);

        int left = (length1 + length2 + 1) / 2;
        int right = (length1 + length2 + 2) / 2;

        if (Objects.isNull(array1)) {
            return (array2[right - 1] + array2[left - 1]) / 2.0;
        }
        if (Objects.isNull(array2)) {
            return (array1[right - 1] + array1[left - 1]) / 2.0;
        }

        return (findKthOfArrays(array1, 0, array2, 0, left)
                + findKthOfArrays(array1, 0, array2, 0, right)) / 2.0;
    }

    private static double findKthOfArrays(int[] array1, int start1, int[] array2, int start2, int kth) {

        int length1 = array1.length;
        int length2 = array2.length;

        if (start1 > length1) {
            return array2[start2 + kth - 1];
        }
        if (start2 > length2) {
            return array1[start1 + kth - 1];
        }

        if (kth == 1) {
            return Math.min(array1[start1], array2[start2]);
        }

        int medianLeft = start1 + kth / 2 - 1 < length1 ? array1[start1 + kth / 2 - 1] : Integer.MAX_VALUE;
        int medianRight = start2 + kth / 2 - 1 < length2 ? array2[start2 + kth / 2 - 1] : Integer.MAX_VALUE;

        if (medianLeft < medianRight) {
            return findKthOfArrays(array1, start1 + kth / 2, array2, start2, kth - kth / 2);
        } else {
            return findKthOfArrays(array1, start1, array2, start2 + kth / 2, kth - kth / 2);
        }
    }


    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        double median = getMedianOfMergedArrays(nums1, nums2);
        String msg = String.format("%s is median of %s and %s ", median, Arrays.toString(nums1), Arrays.toString(nums2));
        System.out.println(msg);
        median = getMedianOfArrays(nums1, nums2);
        msg = String.format("%s is median of %s and %s ", median, Arrays.toString(nums1), Arrays.toString(nums2));
        System.out.println(msg);

        int[] nums3 = {1, 3};
        int[] nums4 = {2, 4};
        median = getMedianOfMergedArrays(nums3, nums4);
        msg = String.format("%s is median of %s and %s ", median, Arrays.toString(nums3), Arrays.toString(nums4));
        System.out.println(msg);
        median = getMedianOfArrays(nums3, nums4);
        msg = String.format("%s is median of %s and %s ", median, Arrays.toString(nums3), Arrays.toString(nums4));
        System.out.println(msg);

        int[] nums5 = null;
        int[] nums6 = {2, 4};
        median = getMedianOfMergedArrays(nums5, nums6);
        msg = String.format("%s is median of %s and %s ", median, Arrays.toString(nums5), Arrays.toString(nums6));
        System.out.println(msg);
        median = getMedianOfArrays(nums5, nums6);
        msg = String.format("%s is median of %s and %s ", median, Arrays.toString(nums5), Arrays.toString(nums6));
        System.out.println(msg);

        int[] nums7 = {1, 3};
        int[] nums8 = null;
        median = getMedianOfMergedArrays(nums7, nums8);
        msg = String.format("%s is median of %s and %s ", median, Arrays.toString(nums7), Arrays.toString(nums8));
        System.out.println(msg);
        median = getMedianOfArrays(nums7, nums8);
        msg = String.format("%s is median of %s and %s ", median, Arrays.toString(nums7), Arrays.toString(nums8));
        System.out.println(msg);
    }


}
