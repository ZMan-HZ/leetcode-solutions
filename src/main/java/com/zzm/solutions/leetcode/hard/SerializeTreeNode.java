package com.zzm.solutions.leetcode.hard;

import com.zzm.solutions.leetcode.common.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;
import java.util.StringJoiner;


/**
 * <b>序列化二叉树 & 之字形序列化二叉树</b>
 * <p>题目：</p>
 * <blockquote>
 * 给定一颗二叉树，进行从根结点开始的左至右的序列化，以及根结点开始的之字形序列化 。
 * </blockquote>
 * <p>
 * 示例 1：
 * <blockquote>
 * <pre>       1</pre>
 * <pre>      /  \</pre>
 * <pre>    2     3</pre>
 * <pre>   /     / \</pre>
 * <pre>  4     5   6</pre>
 * <pre> /    /  \</pre>
 * <pre>7    8    9</pre>
 * </blockquote>
 * 序列化：1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9
 * <p>
 * 之字形：1 -> 2 -> 3 ->6 -> 5 -> 4 -> 7 -> 8 -> 9
 * </p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/20 星期日
 */
public class SerializeTreeNode {


    /**
     * 思路：
     * <p>
     * 使用两个栈S1，S2来分别维护奇数层和偶数层的节点
     * <p>
     * 1. S1不为空时弹出，并将其子节点按 左 右 的顺序 压入S2
     * <p>
     * 2. S2不为空时弹出，并将其子节点按 右 左 的顺序压入S1；
     *
     * @param root 数根节点
     * @return 序列化的value字符串
     */
    public static String zigZagSerializeTreeNode(TreeNode root) {
        if (Objects.isNull(root)) {
            return "";
        }
        StringJoiner join = new StringJoiner(" -> ");

        Stack<TreeNode> oddLayer = new Stack<>();
        Stack<TreeNode> evenLayer = new Stack<>();

        oddLayer.push(root);

        TreeNode cursor = root;

        while (!oddLayer.isEmpty() || !evenLayer.isEmpty()) {
            while (!oddLayer.isEmpty()) {
                TreeNode tree = oddLayer.pop();
                join.add(tree.value.toString());
                //以“左右”的顺序入栈
                if (Objects.nonNull(tree.left)) {
                    evenLayer.push(tree.left);
                }
                if (Objects.nonNull(tree.right)) {
                    evenLayer.push(tree.right);
                }
                cursor.next = tree;
                cursor = cursor.next;
            }
            while (!evenLayer.isEmpty()) {
                TreeNode tree = evenLayer.pop();
                join.add(tree.value.toString());
                //以“右左”的顺序入栈
                if (Objects.nonNull(tree.right)) {
                    oddLayer.push(tree.right);
                }
                if (Objects.nonNull(tree.left)) {
                    oddLayer.push(tree.left);
                }
                cursor.next = tree;
                cursor = cursor.next;
            }
        }

        return join.toString();
    }

    /**
     * 思路：用Deque代替栈
     * <p>
     * 使用两个栈S1，S2来分别维护奇数层和偶数层的节点
     * <p>
     * 1. S1不为空时弹出，并将其子节点按 左 右 的顺序 压入S2
     * <p>
     * 2. S2不为空时弹出，并将其子节点按 右 左 的顺序压入S1；
     *
     * @param root 数根节点
     * @return 序列化的value字符串
     */
    private static String zigZagSerializedTreeNode(TreeNode root) {
        if (Objects.isNull(root)) {
            return "";
        }
        StringJoiner join = new StringJoiner(" -> ");

        Deque<TreeNode> oddFloor = new LinkedList<>();
        Deque<TreeNode> evenFloor = new LinkedList<>();

        oddFloor.addFirst(root);

        TreeNode cursor = root;

        while (!oddFloor.isEmpty() || !evenFloor.isEmpty()) {

            while (!oddFloor.isEmpty()) {
                TreeNode tree = oddFloor.pop();
                join.add(tree.value.toString());
                //以“左右”的顺序入栈
                if (Objects.nonNull(tree.left)) {
                    evenFloor.push(tree.left);
                }
                if (Objects.nonNull(tree.right)) {
                    evenFloor.push(tree.right);
                }
                cursor.next = tree;
                cursor = cursor.next;
            }
            while (!evenFloor.isEmpty()) {
                TreeNode tree = evenFloor.pop();
                join.add(tree.value.toString());
                //以“右左”的顺序入栈
                if (Objects.nonNull(tree.right)) {
                    oddFloor.push(tree.right);
                }
                if (Objects.nonNull(tree.left)) {
                    oddFloor.push(tree.left);
                }
                cursor.next = tree;
                cursor = cursor.next;
            }
        }

        return join.toString();
    }


    /**
     * 根结点开始，从左至右重构二叉树为链表形式。
     * <p>
     * 即：为TreeNode的next添加指向，并返回序列化后每个节点的value字符串
     *
     * @param root 数根节点
     * @return 序列化的value字符串
     */
    public static String serializeTreeNode(TreeNode root) {
        if (Objects.isNull(root)) {
            return "";
        }
        Queue<TreeNode> queue = new LinkedList<>();
        StringJoiner join = new StringJoiner(" -> ");
        queue.add(root);
        TreeNode cursor = root;
        while (!queue.isEmpty()) {
            TreeNode next = queue.poll();
            join.add(next.value.toString());
            if (Objects.nonNull(next.left)) {
                queue.add(next.left);
            }
            if (Objects.nonNull(next.right)) {
                queue.add(next.right);
            }
            cursor.next = next;
            cursor = cursor.next;
        }

        return join.toString();
    }


    public static void main(String[] args) {

        testSerializeTreeNode();
        testZigzagSerializeTreeNode();
        testZigzagSerializedTreeNode();

    }

    private static void testSerializeTreeNode() {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);

        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node3.left = node5;
        node3.right = node6;
        node4.left = node7;
        node5.left = node8;
        node5.right = node9;

        String treeNode = serializeTreeNode(root);
        String msg = String.format("Tree be serialized to \n%s", treeNode);
        System.out.println(msg);
    }

    private static void testZigzagSerializeTreeNode() {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);

        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node3.left = node5;
        node3.right = node6;
        node4.left = node7;
        node5.left = node8;
        node5.right = node9;

        String treeNode = zigZagSerializeTreeNode(root);
        String msg = String.format("Tree be serialized to \n%s", treeNode);
        System.out.println(msg);
    }

    private static void testZigzagSerializedTreeNode() {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);

        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node3.left = node5;
        node3.right = node6;
        node4.left = node7;
        node5.left = node8;
        node5.right = node9;

        String treeNode = zigZagSerializedTreeNode(root);
        String msg = String.format("Tree be serialized to \n%s", treeNode);
        System.out.println(msg);
    }


}