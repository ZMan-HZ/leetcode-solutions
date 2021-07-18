package com.zzm.solutions.leetcode.hard;

import java.util.Objects;
import java.util.Stack;

/**
 * <b>最大矩形</b>
 * <p>题目：</p>
 * <blockquote>
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * </blockquote>
 * <p>
 * 示例 1：
 * 输入：matrix = [
 * ["1","0","1","0","0"],
 * ["1","0","1","1","1"],
 * ["1","1","1","1","1"],
 * ["1","0","0","1","0"]]
 * 输出：6
 * 解释：最大矩形如上所示。 <p>
 * 示例 2：
 * 输入：matrix = []
 * 输出：0
 * <p>
 * 示例 3：
 * 输入：matrix = [["0"]]
 * 输出：0 <p>
 * 示例 4：
 * 输入：matrix = [["1"]]
 * 输出：1<p>
 * 示例 5：
 * 输入：matrix = [["0","0"]]
 * 输出：0 <p>
 * <p>
 * 提示：<p>
 * rows == matrix.length<p>
 * cols == matrix[0].length<p>
 * 0 <= row, cols <= 200<p>
 * matrix[i][j] 为 '0' 或 '1'
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/18 星期日
 */
public class MaximalRectangle {


    /**
     * 思路：
     * 利用上一道题：柱形图中最大的矩形{@link com.zzm.solutions.leetcode.hard.MaximumRectangleInHistogram}
     * 1。以每一行为底，以列上为1的元素的和为高
     * 2。若底上的某列的元素为0，不管此列上方是否有1，均不计入
     * 3。此时就是求柱状图中的最大矩形
     *
     * @param matrix
     * @return
     */
    public static int max(char[][] matrix) {
        if (Objects.isNull(matrix) || matrix.length == 0
                || matrix[0].length == 0) {
            return 0;
        }

        for (int index = 0; index < matrix.length; index++) {


        }


        return 0;
    }

    /**
     * 单调递增栈
     *
     * @param heights
     * @return
     */
    private static int maximum(int[] heights) {
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        int length = heights.length;
        int[] temp = new int[length + 2];
        System.arraycopy(heights, 0, temp, 1, length);
        for (int index = 0; index < temp.length; index++) {
            int current = temp[index];
            while (!stack.isEmpty() && heights[stack.peek()] > current) {
                int peek = stack.pop();
                int left = stack.peek();
                max = Math.max(max, (index - left - 1) * temp[peek]);

            }
            stack.push(index);
        }

        return max;
    }


    public static void main(String[] args) {

    }
}
