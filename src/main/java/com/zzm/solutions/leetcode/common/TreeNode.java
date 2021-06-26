package com.zzm.solutions.leetcode.common;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * <b>二叉树</b>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/20 星期日
 */
public class TreeNode {
    /**
     * 数据域
     */
    public final Integer value;
    /**
     * 指针域
     */
    public TreeNode left;
    /**
     * 指针域
     */
    public TreeNode right;
    /**
     * 指针域
     */
    public TreeNode next;

    public TreeNode(Integer value) {
        this.value = value;
    }


    @Override
    public TreeNode clone() {
        TreeNode node = new TreeNode(this.value);
        node.left = this.left.clone();
        node.right = this.right.clone();
        node.next = this.next.clone();
        return node;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
