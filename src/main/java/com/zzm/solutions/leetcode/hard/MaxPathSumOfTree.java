package com.zzm.solutions.leetcode.hard;

import com.zzm.solutions.leetcode.common.BinaryTreeNode;

import java.util.Objects;

/**
 * <b>从前序与中序遍历序列构造二叉树</b>
 * <p>题目：</p>
 * <blockquote>
 * 给定一棵树的前序遍历 preOrder 与中序遍历  inOrder。请构造二叉树并返回其根节点。
 * </blockquote>
 * <p>
 * 示例 1：
 * <pre>    -10     </pre>
 * <pre>    / \    </pre>
 * <pre>   9   20   </pre>
 * <pre>      / \ </pre>
 * <pre>     15  7</pre>
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42<p>
 * 示例 2：
 * <pre>  1  </pre>
 * <pre> / \ </pre>
 * <pre>2   3</pre>
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6<p>
 * 提示：
 * 中节点数目范围是 [1, 3 * 10^4]<p>
 * -1000 <= Node.val <= 1000<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/24 星期六
 */
public class MaxPathSumOfTree {

    private MaxPathSumOfTree() {
    }

    /**
     * 思路：
     * 二叉树 abc，a 是根结点（递归中的 root），bc 是左右子结点（代表其递归后的最优解）。
     * 最大的路径，可能的路径情况：
     * <pre>  a </pre>
     * <pre> / \ </pre>
     * <pre>b   c </pre>
     * [1]. b + a + c。
     * [2]. b + a + a 的父结点。
     * [3]. a + c + a 的父结点。
     * 其中情况 1，表示如果不联络父结点的情况，或本身是根结点的情况。
     * 这种情况是没法递归的，但是结果有可能是全局最大路径和。
     * 情况 2 和 3，递归时计算 a+b 和 a+c，选择一个更优的方案返回，也就是上面说的递归后的最优解啦。
     * <p>
     * 另外结点有可能是负值，最大和肯定就要想办法舍弃负值（max(0, x)）（max(0,x)）。
     * 但是上面 3 种情况，无论哪种，a 作为联络点，都不能够舍弃
     */
    private int max = Integer.MIN_VALUE;

    public int max(BinaryTreeNode root) {
        if (Objects.isNull(root)) {
            return 0;
        }
        path(root);
        return max;
    }

    /**
     * 递归
     */
    private int path(BinaryTreeNode node) {
        if (Objects.isNull(node)) {
            return 0;
        }
        //深度优先，获取左右分支的最大路径和，本节点作为连接点
        //若左右分支返回的值为负数，则舍弃
        int left = Math.max(0, path(node.left));
        int right = Math.max(0, path(node.right));
        //由于路径最大的一种可能为left->node->right，而不向root的父结点延伸
        int self = left + node.data + right;
        //需要连接父节点，则需要连接当前节点的其中一个较大的子节点
        int answer = node.data + Math.max(0, Math.max(left, right));
        //更新最大值，全是负数结点的情况，也会在这里更新最大的负数
        max = Math.max(max, Math.max(self, answer));
        return answer;
    }


    public static void main(String[] args) {
        BinaryTreeNode left3 = new BinaryTreeNode(9);

        BinaryTreeNode right3 = new BinaryTreeNode(15);
        BinaryTreeNode right4 = new BinaryTreeNode(7);
        BinaryTreeNode right2 = new BinaryTreeNode(20, right3, right4);

        BinaryTreeNode root = new BinaryTreeNode(-10, left3, right2);

        int max = new MaxPathSumOfTree().max(root);
        System.out.println("max = " + max);


        BinaryTreeNode node2 = new BinaryTreeNode(2);
        BinaryTreeNode node3 = new BinaryTreeNode(3);
        BinaryTreeNode node = new BinaryTreeNode(1, node2, node3);
        int path = new MaxPathSumOfTree().max(node);
        System.out.println("path = " + path);
    }
}
