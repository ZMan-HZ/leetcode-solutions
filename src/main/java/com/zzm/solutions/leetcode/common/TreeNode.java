package com.zzm.solutions.leetcode.common;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Optional;

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
    public final Integer identity;
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

    public TreeNode(Integer identity) {
        this.identity = identity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }

        if (o == null || getClass() != o.getClass()){
            return false;
        }

        TreeNode treeNode = (TreeNode) o;

        return new EqualsBuilder()
                .append(identity, treeNode.identity)
                .append(left, treeNode.left)
                .append(right, treeNode.right)
                .append(next, treeNode.next)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(identity)
                .append(left)
                .append(right)
                .append(next)
                .toHashCode();
    }

    @Override
    public TreeNode clone() {
        TreeNode node = new TreeNode(this.identity);
        node.left = Optional.ofNullable(this.left).map(TreeNode::clone).orElse(null);
        node.right = Optional.ofNullable(this.right).map(TreeNode::clone).orElse(null);
        node.next = Optional.ofNullable(this.next).map(TreeNode::clone).orElse(null);
        return node;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
