package com.zzm.solutions.leetcode.medium;

import org.apache.commons.lang3.ArrayUtils;

/**
 * <b> 旋转图像矩阵 </b><p>题目：</p>
 * <blockquote>
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 * </blockquote><p>
 * 示例 1：<p>
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。<p>
 * 示例 2：<p>
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9<p>
 * 提示：<p>
 * n == height.length<p>
 * 0 <= n <= 3 * 10^4<p>
 * 0 <= height[i] <= 10^5<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/4 星期日
 */
public class RotateImageMatrix {


    /**
     * 思路：
     * 两次遍历翻转
     * 第一次：垂直翻转，第一行成最后一行
     * 第二次：对角线翻转，最后一行成最后一列
     * 完成旋转
     *
     * @param matrix 矩阵
     */
    public static void rotate(int[][] matrix) {

        if (ArrayUtils.isEmpty(matrix)) {
            return;
        }
        int length = matrix.length;
        //垂直翻转
        for (int index = 0; index < length / 2; index++) {
            int width = matrix[index].length;
            if (length != width) {
                return;
            }
            for (int jndex = 0; jndex < width; jndex++) {
                int tmp = matrix[index][jndex];
                matrix[index][jndex] = matrix[length - index - 1][jndex];
                matrix[length - index - 1][jndex] = tmp;
            }
        }
        //对角线翻转
        for (int index = 0; index < length; index++) {
            int width = matrix[index].length;
            for (int jndex = index; jndex < width; jndex++) {
                int tmp = matrix[index][jndex];
                matrix[index][jndex] = matrix[jndex][index];
                matrix[jndex][index] = tmp;
            }
        }
    }

    /**
     * 遍历一次
     * 不好想。矩阵的操作，尽量先想翻转：水平，垂直，对角线
     *
     * @param matrix
     */
    public static void rotation(int[][] matrix) {

        if (ArrayUtils.isEmpty(matrix)) {
            return;
        }
        int length = matrix.length;
        for (int index = 0; index < length / 2; index++) {
            int width = matrix[index].length;
            if (length != width) {
                return;
            }
            for (int jndex = 0; jndex < (width + 1) / 2; jndex++) {
                int tmp = matrix[index][jndex];
                matrix[index][jndex] = matrix[length - jndex - 1][index];
                matrix[length - jndex - 1][index] = matrix[length - index - 1][length - jndex - 1];
                matrix[length - index - 1][length - jndex - 1] = matrix[jndex][length - index - 1];
                matrix[jndex][length - index - 1] = tmp;
            }
        }
    }


    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(String.format("matrix: %s", ArrayUtils.toString(matrix)));
        rotate(matrix);
        System.out.println("after rotated:");
        System.out.println(String.format("matrix: %s ", ArrayUtils.toString(matrix)));
        int[][] matrix3 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotation(matrix3);
        System.out.println("after rotated:");
        System.out.println(String.format("matrix: %s ", ArrayUtils.toString(matrix3)));


        int[][] matrix2 = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        System.out.println(String.format("matrix: %s", ArrayUtils.toString(matrix2)));
        rotate(matrix2);
        System.out.println("after rotated:");
        System.out.println(String.format("matrix: %s ", ArrayUtils.toString(matrix2)));
        int[][] matrix4 = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        rotation(matrix4);
        System.out.println("after rotated:");
        System.out.println(String.format("matrix: %s ", ArrayUtils.toString(matrix4)));


    }
}
