package com.zzm.solutions.leetcode.medium;

import com.google.common.collect.Maps;
import com.zzm.solutions.leetcode.common.BinaryTreeNode;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Map;

/**
 * <b>从前序与中序遍历序列构造二叉树</b>
 * <p>题目：</p>
 * <blockquote>
 * 给定一棵树的前序遍历 preOrder 与中序遍历  inOrder。请构造二叉树并返回其根节点。
 * </blockquote>
 * <p>
 * 示例 1：
 * <pre>     3     </pre>
 * <pre>    / \    </pre>
 * <pre>   9   20   </pre>
 * <pre>      / \ </pre>
 * <pre>     15  7</pre>
 * 输入: preOrder = [3,9,20,15,7], <p>
 * inOrder = [9,3,15,20,7]
 * 输出: [3,9,20,null,null,15,7]<p>
 * 提示：
 * 1 <= preOrder.length <= 3000<p>
 * inOrder.length == preOrder.length<p>
 * -3000 <= preOrder[i], inOrder[i] <= 3000<p>
 * preOrder 和 inOrder 均无重复元素<p>
 * inOrder 均出现在 preOrder<p>
 * preOrder 保证为二叉树的前序遍历序列<p>
 * inOrder 保证为二叉树的中序遍历序列<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/22 星期四
 */
public class BuildTreeFromOrderTraversal {


    /**
     * 思路：递归<p>
     * 前序遍历：[[根],[左子树的前序遍历],[右子树的前序遍历]]<p>
     * 中序遍历：[[左子树的中序遍历],[根],[右子树的前序遍历]]<p>
     * 首先，可以确定根结点，然后从中序遍历定位根结点，然后前面的元素即为二叉树的左子树，<p>
     * 虽然前序和中序的顺序不一样，但是长度一定一样。<p>
     * <pre>[ [根],   [左子树的前序遍历],   [右子树的前序遍历]]</pre>
     * <pre>   |       |            |     |             |</pre>
     * <pre>preLeft  preLeft+1      |     |           preRight</pre>
     * <pre>                        |   pIndex-inLeft+preLeft+1</pre>
     * <pre>                pIndex-inLeft+preLeft</pre>
     *<p>
     * <pre>[[左子树的中序遍历],   [根],   [右子树的前序遍历]]</pre>
     * <pre> |             |      |      |             |</pre>
     * <pre>InLeft     pIndex-1 pIndex  pIndex+1     InRight</pre>
     *
     * @param preOrder 前序遍历序列
     * @param inOrder  中序遍历序列
     * @return
     */
    public static BinaryTreeNode build(int[] preOrder, int[] inOrder) {
        if (ArrayUtils.isEmpty(preOrder) || ArrayUtils.isEmpty(inOrder)) {
            return null;
        }
        //先存放中序遍历中每个节点的位置，便于查找使用Map结构
        Map<Integer, Integer> indexKV = Maps.newHashMap();
        int preLength = preOrder.length;
        int inLength = inOrder.length;
        for (int index = 0; index < inLength; index++) {
            indexKV.put(inOrder[index], index);
        }
        //开始递归调用，注意右区间的越界
        return build(preOrder, 0, preLength - 1, indexKV, 0, inLength - 1);
    }

    /**
     * @param preOrder 前序遍历序列
     * @param preLeft  前序遍历序列子区间的左边界，可以取到（闭区间）
     * @param preRight 前序遍历序列子区间的右边界，可以取到（闭区间）
     * @param map      中序遍历序列里，数值与下标的对应关系
     * @param inLeft   中序遍历序列子区间的左边界，可以取到（闭区间）
     * @param inRight  中序遍历序列子区间的右边界，可以取到（闭区间）
     * @return
     */
    private static BinaryTreeNode build(int[] preOrder, int preLeft, int preRight,
                                        Map<Integer, Integer> map, int inLeft, int inRight) {

        if (preLeft > preRight || inLeft > inRight) {
            return null;
        }
        int data = preOrder[preLeft];
        BinaryTreeNode root = new BinaryTreeNode(data);
        int pIndex = map.get(data);
        //左子树
        root.left = build(preOrder, preLeft + 1, pIndex - inLeft + preLeft, map, inLeft, pIndex - 1);
        //右子树
        root.right = build(preOrder, pIndex - inLeft + preLeft + 1, preRight, map, pIndex + 1, inRight);

        return root;
    }

    public static void main(String[] args) {
        int[] pre = {3, 9, 20, 15, 7};
        int[] in = {9, 3, 15, 20, 7};
        BinaryTreeNode node = build(pre, in);
        System.out.println("node = " + node);
    }
}
