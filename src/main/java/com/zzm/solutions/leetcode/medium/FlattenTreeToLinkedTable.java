package com.zzm.solutions.leetcode.medium;

import com.zzm.solutions.leetcode.common.BinaryTreeNode;
import com.zzm.solutions.leetcode.hard.SerializeTreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

/**
 * <b> 二叉树展开为链表</b>
 * <p>题目：</p>
 * <blockquote>
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * <p>
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * </blockquote>
 * <p>
 * 示例 1：
 * <blockquote>
 * <pre>     1</pre>
 * <pre>    / \</pre>
 * <pre>  2    5</pre>
 * <pre> / \    \</pre>
 * <pre>3  4    6</pre>
 * </blockquote>
 * 输入：root = [1,2,5,3,4,null,6]<p>
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/23 星期五
 */
public class FlattenTreeToLinkedTable {

    /**
     * {@link SerializeTreeNode#serializeTreeNode(com.zzm.solutions.leetcode.common.BinaryTreeNode)}
     * 思路： 先序遍历： 根 左 右
     * 使用：栈
     * 首先，根结点入栈
     * 然后，当根结点出栈时，让其 右子树先入栈，后 左子树。
     * 最后，出栈的顺序就是前序遍历的顺序
     * <p>
     * 本地思路还有其他解法：如前序遍历，递归等等
     * 本思路是
     *
     * @param root
     * @return
     */
    public static void flatten(BinaryTreeNode root) {
        //极限情况，只有一个节点时，避免出现树的循环引用，需要判断
        //或者在cursor赋值时，比如初始化为null
        if (Objects.isNull(root)
                || (Objects.isNull(root.left)
                && Objects.isNull(root.right))) {
            return;
        }
        Deque<BinaryTreeNode> queue = new LinkedList<>();
        BinaryTreeNode cursor = root;
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode node = queue.pop();
            if (Objects.nonNull(node)) {
                if (Objects.nonNull(node.right)) {
                    queue.push(node.right);
                }
                if (Objects.nonNull(node.left)) {
                    queue.push(node.left);
                }
                cursor.left = null;
                cursor.right = node;
                cursor = cursor.right;
            }
        }
    }


    public static void main(String[] args) {

        BinaryTreeNode node3 = new BinaryTreeNode(3);
        BinaryTreeNode node4 = new BinaryTreeNode(4);
        BinaryTreeNode node2 = new BinaryTreeNode(2, node3, node4);

        BinaryTreeNode node6 = new BinaryTreeNode(6);
        BinaryTreeNode node5 = new BinaryTreeNode(5, null, node6);

        BinaryTreeNode root = new BinaryTreeNode(1, node2, node5);

        flatten(root);

        System.out.println("flatten = " + root);

        BinaryTreeNode node = new BinaryTreeNode(0);

        flatten(node);

        System.out.println("node = " + node);

    }
}
