package com.zzm.solutions.leetcode.medium;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

/**
 * <b>颜色分类</b>
 * <p>题目：</p>
 * <blockquote>
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * </blockquote>
 * <p>
 * 示例 1：
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * <p>
 * 示例 2：
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 * <p>
 * 示例 3：
 * 输入：nums = [0]
 * 输出：[0]
 * <p>
 * 示例 4：
 * 输入：nums = [1]
 * 输出：[1]
 * <p>
 * 提示：<p>
 * n == nums.length<p>
 * 1 <= n <= 300<p>
 * nums[i] 为 0、1 或 2
 * <p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/6 星期二
 */
public class SortColors {

    /**
     * 原地排序：
     * 快速排序的思想，分段分区间
     * 区间 [0, p1）只存 0; 即 p1前的元素都是0
     * 区间 [p1, i) 只存 1;
     * 区间 (p2, length-1] 只存 2; 即 p2之后的元素都是2
     * 并且初始化区间时，要保证每个区间都是空区间。遵循原则： 遵守循环不变量
     *
     * @param colors
     */
    public static void sort(int[] colors) {
        if (ArrayUtils.isEmpty(colors)) {
            return;
        }
        int length = colors.length;
        if (length < 2) {
            return;
        }
        int p1 = 0;
        int p2 = length - 1;
        int index = 0;
        //循环继续的条件，注意两个开区间的条件
        while (index <= p2) {
            if (colors[index] == 0) {
                swap(colors, index, p1);
                p1++;
                index++;
            } else if (colors[index] == 1) {
                index++;
            } else if (colors[index] == 2) {
                swap(colors, index, p2);
                p2--;
            }
        }
    }

    private static void swap(int[] colors, int from, int to) {
        int tmp = colors[from];
        colors[from] = colors[to];
        colors[to] = tmp;
    }


    public static void main(String[] args) {
        int[] colors = {2, 0, 2, 1, 1, 0};
        sort(colors);
        System.out.println(Arrays.toString(colors));
    }
}
