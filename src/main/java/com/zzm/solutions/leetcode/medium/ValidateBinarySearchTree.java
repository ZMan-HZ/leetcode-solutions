package com.zzm.solutions.leetcode.medium;

import com.zzm.solutions.leetcode.common.BinaryTreeNode;

import java.util.Objects;
import java.util.Stack;

/**
 * <b>验证二叉搜索树</b><p>题目：</p>
 * <blockquote>
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。<p>
 * 假设一个二叉搜索树具有如下特征：<p>
 * 1.节点的左子树只包含小于当前节点的数。<p>
 * 2.节点的右子树只包含大于当前节点的数。<p>
 * 3.所有左子树和右子树自身必须也是二叉搜索树。<p>
 * </blockquote><p>
 * 示例 1：
 * 输入:
 * <pre>   2</pre>
 * <pre> /  \</pre>
 * <pre>1    3</pre>
 * 输出: true]<p>
 * 示例 2：
 * 输入:
 * <pre>     5</pre>
 * <pre>    / \</pre>
 * <pre>   1   4</pre>
 * <pre>      / \</pre>
 * <pre>     3   6</pre>
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。<p>
 *      根节点的值为 5 ，但是其右子节点值为 4 。<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/20 星期二
 */
public class ValidateBinarySearchTree {


    /**
     * 思路：递归
     *
     * @param root
     * @return
     */
    public static boolean isValid(BinaryTreeNode root) {
        if (Objects.isNull(root)) {
            return true;
        }
        int peek = Integer.MAX_VALUE;
        int feet = Integer.MIN_VALUE;

        return recursive(root, peek, feet);
    }

    private static boolean recursive(BinaryTreeNode node, int peek, int feet) {
        if (Objects.isNull(node)) {
            return true;
        }
        //当前节点比左子树小 或者 当前节点比右子树大，均为不合法二叉搜索树
        if (node.data <= feet || node.data >= peek) {
            return false;
        }
        //下一层的上下界，左子树：当前节点的值作为上界（左子树都要比当前节点小），原来的下界不变
        //右子树：当前节点的值作为下界（右子树都要比当前节点大），但不能大于上一层的上界，因此原来的上界不变
        return recursive(node.left, node.data, feet) && recursive(node.right, peek, node.data);
    }

    /**
     * 思路：递归版中序遍历
     * 二叉搜索树「中序遍历」得到的值构成的序列一定是升序的，这启示我们在中序遍历的时候实时检查当前节点的值是否大于前一个中序遍历到的节点的值即可。
     * 如果均大于说明这个序列是升序的，整棵树是二叉搜索树，否则不是
     */
    static long data = Long.MIN_VALUE;

    public static boolean isValidBST(BinaryTreeNode root) {
        return inorder(root);
    }

    private static boolean inorder(BinaryTreeNode node) {
        if (Objects.isNull(node)) {
            return true;
        }
        //遍历左子树
        boolean left = inorder(node.left);
        //如果当前节点不大于中序遍历的前一个节点，则不是有效二叉树，返回true，否则，继续遍历
        if (node.data <= data) {
            return false;
        }
        data = node.data;
        boolean inorder = inorder(node.right);
        return left && inorder;
    }

    /**
     * 思路：迭代版中序遍历
     *
     * @param root
     * @return
     */
    public static boolean isValidBT(BinaryTreeNode root) {
        if (Objects.isNull(root)) {
            return true;
        }
        int data = -Integer.MAX_VALUE;
        Stack<BinaryTreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || Objects.nonNull(root)) {
            while (Objects.nonNull(root)) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.data <= data) {
                return false;
            }
            data = root.data;
            root = root.right;
        }
        return true;
    }


    public static void main(String[] args) {

        BinaryTreeNode node3 = new BinaryTreeNode(3);
        BinaryTreeNode node6 = new BinaryTreeNode(6);
        BinaryTreeNode node4 = new BinaryTreeNode(4, node3, node6);
        BinaryTreeNode node1 = new BinaryTreeNode(1);
        BinaryTreeNode root = new BinaryTreeNode(5, node1, node4);
        boolean valid = isValid(root);
        System.out.println("valid = " + valid);

        boolean validBST = isValidBST(root);
        System.out.println("validBST = " + validBST);

        boolean validBT = isValidBT(root);
        System.out.println("validBT = " + validBT);

    }
}
