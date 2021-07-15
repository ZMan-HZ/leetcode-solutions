package com.zzm.solutions.leetcode.hard;

import java.util.Objects;

/**
 * <b>柱状图中最大的矩形</b>
 * <p>题目：</p>
 * <blockquote>
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * </blockquote>
 * <p>
 * 示例 1：
 * 输入：其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 * 输出：10
 * 解释：所能构成的最大面积为 10 个单位<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/15 星期四
 */
public class LargestRectangleInHistogram {

    /**
     * 思路：
     * 暴力解法.
     * 枚举高度。
     * 遍历柱形高度，向两边扩散，求出以当前高度为矩形的最大宽度
     *
     * @param heights
     * @return
     */
    public static int max(int[] heights) {
        int max = 0;
        if (Objects.isNull(heights) || heights.length == 0) {
            return max;
        }
        int length = heights.length;
        for (int index = 0; index < length; index++) {
            int height = heights[index];
            int left = index;
            int right = index;
            //找左边最后1个 >= 当前高度的下标
            while (left > 0 && heights[left - 1] >= height) {
                left--;
            }
            //找右边最后1个 >= 当前高度的下标
            while (right < length - 1 && heights[right + 1] >= height) {
                right++;
            }
            int width = right - left + 1;
            max = Math.max(max, width * height);
        }

        return max;
    }


    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        int max = max(heights);
        System.out.println(max);
    }
}
