package com.zzm.solutions.leetcode.medium;

import org.apache.commons.lang3.ArrayUtils;

/**
 * <b> 最小路径和 (VRP 问题) </b><p>题目：</p>
 * <blockquote>
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * </blockquote><p>
 * 示例 1：<p>
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。<p>
 * 示例 2：<p>
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12<p>
 * 提示：<p>
 * m == grid.length<p>
 * n == grid[i].length<p>
 * 1 <= m, n <= 200<p>
 * 0 <= grid[i][j] <= 100<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/5 星期一
 */
public class VRPShortestPath {


    /**
     * VRP问题，动态规划
     * 创建二维数组 dp，与原始网格的大小相同，dp[i][j] 表示从左上角出发到(i,j) 位置的最小路径和。显然，dp[0][0]=grid[0][0]。对于 dp 中的其余元素，通过以下状态转移方程计算元素值。
     * 当 i>0 且 j=0 时，dp[i][0]=dp[i−1][0]+grid[i][0]。
     * 当 i=0 且 j>0 时，dp[0][j]=dp[0][j−1]+grid[0][j]。
     * 当 i>0 且 j>0 时，dp[i][j]=min(dp[i−1][j],dp[i][j−1])+grid[i][j]。
     * 最后得到dp[m−1][n−1] 的值即为从网格左上角到网格右下角的最小路径和。
     *
     * @param grid
     * @return
     */
    public static int vrp(int[][] grid) {

        if (ArrayUtils.isEmpty(grid) || ArrayUtils.isEmpty(grid[0])) {
            return 0;
        }
        int rows = grid.length;
        int columns = grid[0].length;
        int[][] dp = new int[rows][columns];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < columns; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[rows - 1][columns - 1];
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int vrp = vrp(grid);
        System.out.println(vrp);
    }

}
