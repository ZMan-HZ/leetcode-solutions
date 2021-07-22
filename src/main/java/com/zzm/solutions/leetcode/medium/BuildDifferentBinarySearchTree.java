package com.zzm.solutions.leetcode.medium;

/**
 * <b>不同的二叉搜索树</b><p>题目：</p>
 * <blockquote>
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 * </blockquote><p>
 * 示例 1：
 * 输入：n = 3
 * 输出：5
 * 解释： <blockquote>
 * <pre>   2</pre>
 * <pre>  /  \</pre>
 * <pre>1     3</pre>
 * <p> OR </p>
 * <pre>1</pre>
 * <pre>  \</pre>
 * <pre>   3</pre>
 * <pre>  /</pre>
 * <pre>2 </pre>
 * <p> OR </p>
 * <pre>1</pre>
 * <pre>  \</pre>
 * <pre>   2</pre>
 * <pre>    \</pre>
 * <pre>     3</pre>
 * <p> OR </p>
 * <pre>       3</pre>
 * <pre>     /</pre>
 * <pre>   2</pre>
 * <pre> /</pre>
 * <pre>1</pre>
 * <p> OR </p>
 * <pre>    3</pre>
 * <pre>  /</pre>
 * <pre>1</pre>
 * <pre> \</pre>
 * <pre>  2</pre>
 * </blockquote>
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：1<p>
 * 输出：[1]<p>
 * 提示：<p>
 * 1 <= n <= 19<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/20 星期二
 */
public class BuildDifferentBinarySearchTree {


    /**
     * 思路：动态规划
     * 假设 n 个节点存在二叉排序树的个数是 G (n)，令 f(i) 为以 i 为根的二叉搜索树的个数，则
     * G(n) = f(1) + f(2) + f(3) + f(4) + ... + f(n)
     * 当 i 为根节点时，其左子树节点个数为 i-1 个，右子树节点为 n-i，则
     * f(i) = G(i-1)*G(n-i)
     * 综合两个公式可以得到 <b>卡特兰数</b> 公式
     * G(n) = G(0)*G(n-1)+G(1)*G(n-2)+...+G(n-1)*G(0)
     *
     * @param num
     * @return
     */
    public static int treeSize(int num) {
        int[] ans = new int[num + 1];
        //初始化
        ans[0] = 1;
        ans[1] = 1;
        for (int i = 2; i < num + 1; i++) {
            for (int j = 1; j < i + 1; j++) {
                ans[i] += ans[j - 1] * ans[i - j];
            }
        }
        return ans[num];
    }


    public static void main(String[] args) {
        int num = 3;
        int size = treeSize(num);
        System.out.println("size = " + size);
    }
}
