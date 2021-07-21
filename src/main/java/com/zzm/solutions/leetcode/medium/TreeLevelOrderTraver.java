package com.zzm.solutions.leetcode.medium;

import com.zzm.solutions.leetcode.common.BinaryTreeNode;
import com.zzm.solutions.leetcode.hard.SerializeTreeNode;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

/**
 * <b>二叉树的层序遍历</b>
 * <p>题目：</p>
 * <blockquote>
 * 给一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * </blockquote>
 * <p>
 * 示例 1：
 * 输入: 二叉树 [1,2,2,3,4,4,3]
 * <pre>     3     </pre>
 * <pre>    / \    </pre>
 * <pre>   9   20   </pre>
 * <pre>      / \ </pre>
 * <pre>     15  7</pre>
 * 输出: [
 * [3],
 * [9,20],
 * [15,7]]<p>
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
public class TreeLevelOrderTraver {


    /**
     * 思路：
     * 极其类似 {@link SerializeTreeNode} 中的序列化
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelTraver(BinaryTreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (Objects.isNull(root)) {
            return results;
        }
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> ans = new ArrayList<>();
            int size = queue.size();
            for (int index = 0; index < size; index++) {
                BinaryTreeNode node = queue.poll();
                if (Objects.nonNull(node)) {
                    ans.add(node.data);
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
            if (CollectionUtils.isNotEmpty(ans)) {
                results.add(ans);
            }
        }

        return results;
    }


    public static void main(String[] args) {
        BinaryTreeNode left3 = new BinaryTreeNode(9);

        BinaryTreeNode right3 = new BinaryTreeNode(15);
        BinaryTreeNode right4 = new BinaryTreeNode(7);
        BinaryTreeNode right2 = new BinaryTreeNode(20, right3, right4);

        BinaryTreeNode root = new BinaryTreeNode(3, left3, right2);

        List<List<Integer>> lists = levelTraver(root);
        System.out.println("lists = " + lists);
    }
}
