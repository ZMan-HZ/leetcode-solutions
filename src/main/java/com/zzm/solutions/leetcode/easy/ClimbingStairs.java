package com.zzm.solutions.leetcode.easy;

/**
 * <b>  爬楼梯 </b><p>题目：</p>
 * <blockquote>
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 * </blockquote><p>
 * 示例 1：<p>
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶<p>
 * 示例 2：<p>
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶<p>
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
public class ClimbingStairs {


    /**
     * 动态规划，递归 超时
     *
     * @param n
     * @return
     */
    public static int climb(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        return climb(n - 1) + climb(n - 2);
    }

    /**
     * 迭代
     *
     * @param n
     * @return
     */
    public static int climbStair(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int first = 1;
        int second = 2;
        int third = 0;

        for (int index = 3; index <= n; index++) {
            third = first + second;
            first = second;
            second = third;
        }
        return third;
    }

    public static void main(String[] args) {
        int n = 8;
        int climb = climb(n);
        System.out.println(climb);

        climb = climbStair(n);
        System.out.println(climb);
    }
}
