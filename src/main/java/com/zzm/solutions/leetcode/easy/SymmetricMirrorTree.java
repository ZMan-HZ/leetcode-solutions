package com.zzm.solutions.leetcode.easy;

import com.zzm.solutions.leetcode.common.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * <b>对称二叉树</b><p>题目：</p>
 * <blockquote>
 * 给定一个二叉树，检查它是否是镜像对称的。<p>
 * 进阶：<p>
 * 你可以运用递归和迭代两种方法解决这个问题吗？<p>
 * </blockquote><p>
 * 示例 1：
 * 输入: 二叉树 [1,2,2,3,4,4,3] 是对称的。
 * <pre>     1     </pre>
 * <pre>    / \    </pre>
 * <pre>   2   2   </pre>
 * <pre>  / \ / \ </pre>
 * <pre> 3  4 4  3</pre>
 * 输出: true<p>
 * 示例 2：
 * 输入:[1,2,2,null,3,null,3]
 * <pre>     1    </pre>
 * <pre>    / \   </pre>
 * <pre>   2   2  </pre>
 * <pre>   \   \  </pre>
 * <pre>    3   3</pre>
 * 输出: false<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/21 星期三
 */
public class SymmetricMirrorTree {

    /**
     * 方法1： 递归
     * 两个树互为镜像：
     * 1。它们的两个根结点具有相同的值
     * 2。每个树的右子树都与另一个树的左子树镜像对称
     *
     * @param root
     * @return
     */
    public static boolean isMirror(BinaryTreeNode root) {
        return mirror(root, root);
    }

    private static boolean mirror(BinaryTreeNode left, BinaryTreeNode right) {
        if (Objects.isNull(left) && Objects.isNull(right)) {
            return true;
        }
        if (Objects.isNull(left) || Objects.isNull(right)) {
            return false;
        }
        //根结点必须相等
        boolean equals = left.data.equals(right.data);
        //左子树和右子树必须对称
        boolean lm = mirror(left.left, right.right);
        //右子树和左子树对称
        boolean rm = mirror(left.right, right.left);

        return equals && lm && rm;
    }

    /**
     * 方法2：迭代
     * 用一个队列维护节点
     * 首先根结点入队两次，每次提取两个节点，如果相等，然后将这两个节点的左右子树按相反的顺序插入队列
     * 当队列为空，或者不对称时，算法结束
     *
     * @param root
     * @return
     */
    public static boolean isSymmetric(BinaryTreeNode root) {

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        //根结点两次入队
        queue.add(root);
        queue.add(root);

        while (!queue.isEmpty()) {
            BinaryTreeNode left = queue.poll();
            BinaryTreeNode right = queue.poll();

            if (Objects.isNull(left) && Objects.isNull(right)) {
               continue;
            }

            if (Objects.isNull(left)
                    || Objects.isNull(right)
                    || !left.data.equals(right.data)) {
                return false;
            }

            queue.offer(left.left);
            queue.add(right.right);

            queue.add(left.right);
            queue.add(right.left);
        }

        return true;
    }


    public static void main(String[] args) {

        BinaryTreeNode left3 = new BinaryTreeNode(3);
        BinaryTreeNode left4 = new BinaryTreeNode(4);
        BinaryTreeNode left2 = new BinaryTreeNode(2, left3, left4);

        BinaryTreeNode right3 = new BinaryTreeNode(3);
        BinaryTreeNode right4 = new BinaryTreeNode(4);
        BinaryTreeNode right2 = new BinaryTreeNode(2, right4, right3);

        BinaryTreeNode root = new BinaryTreeNode(1, left2, right2);

        boolean mirror = isMirror(root);
        System.out.println("mirror = " + mirror);

        boolean symmetric = isSymmetric(root);
        System.out.println("symmetric = " + symmetric);

    }
}
