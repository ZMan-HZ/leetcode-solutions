package com.zzm.solutions.leetcode.easy;

import com.zzm.solutions.leetcode.common.BinaryTreeNode;
import com.zzm.solutions.leetcode.medium.TreeLevelOrderTraver;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * <b>二叉树的最大深度</b>
 * <p>题目：</p>
 * <blockquote>
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 * </blockquote>
 * <p>
 * 示例 1：
 * 输入: 二叉树
 * <pre>     3     </pre>
 * <pre>    / \    </pre>
 * <pre>   9   20   </pre>
 * <pre>      / \ </pre>
 * <pre>     15  7</pre>
 * 输出: 3 <p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/22 星期四
 */
public class MaxDepthOfTree {


    /**
     * 广度优先搜索
     * 极其类似 二叉树的层级遍历
     * {@link TreeLevelOrderTraver#levelTraver(com.zzm.solutions.leetcode.common.BinaryTreeNode)}
     * @param root
     * @return
     */
    public static int maxDepth(BinaryTreeNode root) {
        int max = 0;
        if (Objects.isNull(root)) {
            return max;
        }
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int index = 0; index < size; index++) {
                BinaryTreeNode node = queue.poll();
                if (Objects.nonNull(node)) {
                    if (Objects.nonNull(node.left)) {
                        queue.add(node.left);
                    }
                    if (Objects.nonNull(node.right)) {
                        queue.add(node.right);
                    }
                }
            }
            max++;
        }

        return max;
    }

    public static void main(String[] args) {
        BinaryTreeNode left3 = new BinaryTreeNode(9);

        BinaryTreeNode right3 = new BinaryTreeNode(15);
        BinaryTreeNode right4 = new BinaryTreeNode(7);
        BinaryTreeNode right2 = new BinaryTreeNode(20, right3, right4);

        BinaryTreeNode root = new BinaryTreeNode(3, left3, right2);

        int depth = maxDepth(root);
        System.out.println("depth = " + depth);
    }
}
