package com.zzm.solutions.leetcode.medium;

/**
 * <b> 不同路径(走格子/台阶问题) </b><p>题目：</p>
 * <blockquote>
 * 一个机器人位于一个 m x n 网格的左上角, 机器人每次只能向下或者向右移动一步。
 * 机器人试图达到网格的右下角,问总共有多少条不同的路径？
 * </blockquote><p>
 * 示例 1：<p>
 * 输入：m = 3, n = 7
 * 输出：28<p>
 * 示例 2：<p>
 * 输入：m = 3, n = 2
 * 输出：3
 * 解释：
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向下<p>
 * 示例 3：<p>
 * 输入：m = 3, n = 3
 * 输出：6
 * 提示：<p>
 * 1 <= m, n <= 100<p>
 * 题目数据保证答案小于等于 2 * 10^9<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/4 星期日
 */
public class UniquePath {

    /**
     * 动态规划，（杨辉三角）
     * 思路与算法：
     * 我们用f(i,j) 表示从左上角走到 (i,j) 的路径数量，其中 i 和 j 的范围分别是 [0,m) 和 [0,n)。
     * 由于我们每一步只能从向下或者向右移动一步，因此要想走到(i,j)，如果向下走一步，那么会从(i−1,j) 走过来；
     * 如果向右走一步，那么会从(i,j−1) 走过来。因此我们可以写出动态规划转移方程：
     * f(i,j)=f(i−1,j)+f(i,j−1)
     * 需要注意的是，如果i=0，那么f(i−1,j) 并不是一个满足要求的状态，我们需要忽略这一项；
     * 同理，如果 j=0，那么f(i,j−1) 并不是一个满足要求的状态，我们需要忽略这一项。
     * 初始条件为 f(0,0)=1，即从左上角走到左上角有一种方法。
     * 最终的答案即为f(m−1,n−1)。
     *
     * @param m
     * @param n
     * @return
     */
    public static int unique(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        //到(i,j)的步数
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        //边界全部置为1，因为只能向下或者向右，因此边界只能是都向右，或者都向下才可以走到，只有1种情况
        for (int index = 0; index < m; index++) {
            dp[index][0] = 1;
        }
        for (int index = 0; index < n; index++) {
            dp[0][index] = 1;
        }

        for (int index = 1; index < m; index++) {
            for (int jndex = 1; jndex < n; jndex++) {
                dp[index][jndex] = dp[index][jndex - 1] + dp[index - 1][jndex];
            }
        }

        return dp[m - 1][n - 1];
    }

    /**
     * 组合：
     * 从左上角到右下角的过程中，我们需要移动 m+n−2 次，其中有m−1 次向下移动，n−1 次向右移动。
     * 组合方程： C： m-1， m+n-2，从m+n-2个数中取出m-1个数的组合
     *
     * @param m
     * @param n
     * @return
     */
    public static int uniquePath(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        long ans = 1;
        for (int x = n, y = 1; y < m; ++x, ++y) {
            ans = ans * x / y;
        }
        return (int) ans;
    }

    public static void main(String[] args) {
        int m = 3;
        int n = 2;
        int unique = unique(m, n);
        System.out.println(unique);
    }
}
