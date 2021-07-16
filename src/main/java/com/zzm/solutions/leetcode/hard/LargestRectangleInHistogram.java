package com.zzm.solutions.leetcode.hard;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Objects;
import java.util.Stack;

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

    /**
     * 思路：
     * 单调栈
     * 操作规则（下面都以单调递增栈为例）
     * 1. 如果新的元素比栈顶元素大，就入栈
     * 2. 如果新的元素较小，那就一直把栈内元素弹出来，直到栈顶比新元素小
     * 加入这样一个规则之后，会有什么效果
     * 1. 栈内的元素是递增的
     * 2. 当元素出栈时，说明这个新元素是出栈元素向后找第一个比其小的元素
     * 举个例子，现在索引在 6 ，栈里是 1 5 6 。
     * 接下来新元素是 2 ，那么 6 需要出栈。
     * 当 6 出栈时，右边 2 代表是 6 右边第一个比 6 小的元素。
     * 3. 当元素出栈后，说明新栈顶元素是出栈元素向前找第一个比其小的元素
     * 当 6 出栈时，5 成为新的栈顶，那么 5 就是 6 左边第一个比 6 小的元素。
     *
     * @param heights
     * @return
     */
    public static int maxRectangle(int[] heights) {
        int max = 0;
        if (ArrayUtils.isEmpty(heights)) {
            return max;
        }
        Stack<Integer> stack = new Stack<>();
        int length = heights.length;
        int[] temp = new int[length + 2];
        //为了避免是单调递增的数组入栈，并且让入栈的height能全部出栈，在数组首位各补一个 0
        System.arraycopy(heights, 0, temp, 1, length);
        for (int index = 0; index < temp.length; index++) {
            int current = temp[index];
            while (!stack.isEmpty() && temp[stack.peek()] > current) {
                int peek = stack.pop();
                int left = stack.peek();
                max = Math.max(max, (index - left - 1) * temp[peek]);
            }
            stack.push(index);
        }

        return max;
    }


    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        int max = max(heights);
        System.out.println(max);

        max = maxRectangle(heights);
        System.out.println(max);
    }
}
