package com.zzm.solutions.leetcode.medium;

import org.apache.commons.lang3.ArrayUtils;

/**
 * <b> 盛最多水的容器 </b><p>题目：</p>
 * <blockquote>
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。<p>
 * 说明：你不能倾斜容器
 * </blockquote><p>
 * 示例 1：<p>
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：坐标轴y轴代表输入数组 [1,8,6,2,5,4,8,3,7]，x轴为元素的index。在此情况下，容器能够容纳水的最大值为 49。<p>
 * 示例 2：<p>
 * 输入：height = [1,1]
 * 输出：1<p>
 * 示例 3：<p>
 * 输入：height = [4,3,2,1,4]
 * 输出：16<p>
 * 示例 4：<p>
 * 输入：height = [1,2,1]
 * 输出：2<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/2 星期五
 */
public class MostWaterOfContainer {

    private MostWaterOfContainer() {
    }

    /**
     * 思路：
     * 双指针：left 和 right
     * 两个指针所隔着的为长，最短的一个指针的height为高，求面积的最值
     *
     * @param heights 高，坐标轴 y
     * @return 所围成的最大面积
     */
    public static int maxSurroundedArea(int[] heights) {
        int max = 0;
        if (ArrayUtils.isEmpty(heights)) {
            return max;
        }

        int left = 0;
        int right = heights.length - 1;
        while (left < right) {
            int leftHeight = heights[left];
            int rightHeight = heights[right];
            int area = (right - left) * Math.min(leftHeight, rightHeight);
            max = Math.max(max, area);
            //移动较小一侧的指针
            if (leftHeight < rightHeight) {
                left++;
            } else if (leftHeight > rightHeight) {
                right--;
            } else {
                left++;
                right--;
            }
        }

        return max;
    }


    public static void main(String[] args) {
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int area = maxSurroundedArea(heights);
        System.out.println(area);
    }
}
