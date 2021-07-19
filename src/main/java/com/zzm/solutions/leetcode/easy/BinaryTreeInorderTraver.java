package com.zzm.solutions.leetcode.easy;

import com.zzm.solutions.leetcode.common.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * <b>二叉树的中序遍历</b>
 * <p>题目：</p>
 * <blockquote>
 * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 * </blockquote>
 * <p>
 * 示例 1：
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2] <p>
 * 示例 2：
 * 输入：root = []
 * 输出：[]<p>
 * 示例 3：
 * 输入：root = [1]
 * 输出：[1]<p>
 * 示例 4：
 * 输入：root = [1,2]
 * 输出：[2,1]<p>
 * 示例 5：
 * 输入：root = [1,null,2]
 * 输出：[1,2]<p>
 * <p>
 * 提示：<p>
 * 树中节点数目在范围 [0, 100] 内<p>
 * -100 <= Node.val <= 100<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/19 星期一
 */
public class BinaryTreeInorderTraver {

    /**
     * 思路1：递归
     * 首先：中序遍历的结构是： 左根右
     * 遍历左/右子节点的时候，也是按照左根右的顺序进行，因此满足递归的条件
     *
     * @param root
     * @return
     */
    public static List<Integer> inorder(BinaryTreeNode root) {
        List<Integer> results = new ArrayList<>(0);
        if (Objects.isNull(root)) {
            return results;
        }
        recursive(root, results);
        return results;
    }

    //递归
    private static void recursive(BinaryTreeNode node, List<Integer> results) {
        //到叶子结点
        if (Objects.isNull(node)) {
            return;
        }
        //左根右
        recursive(node.left, results);
        results.add(node.data);
        recursive(node.right, results);
    }

    /**
     * 思路2：迭代
     * 递归到等价方式
     *
     * @param root
     * @return
     */
    public static List<Integer> inOrder(BinaryTreeNode root) {
        List<Integer> results = new ArrayList<>(0);
        if (Objects.isNull(root)) {
            return results;
        }
        Stack<BinaryTreeNode> stack = new Stack<>();
        while (Objects.nonNull(root) || !stack.isEmpty()) {
            //先遍历左子树
            while (Objects.nonNull(root)) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            results.add(root.data);
            root = root.right;
        }

        return results;
    }

    public static void main(String[] args) {
        BinaryTreeNode node3 = new BinaryTreeNode(3);
        BinaryTreeNode node2 = new BinaryTreeNode(2, node3, null);
        BinaryTreeNode root = new BinaryTreeNode(1, null, node2);
        List<Integer> inorder = inorder(root);
        System.out.println("inorder = " + inorder);

        inorder = inOrder(root);
        System.out.println("inorder = " + inorder);

    }
}
