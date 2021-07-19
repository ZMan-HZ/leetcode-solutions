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
public class BinaryTreeNode {
    /**
     * 数据域
     */
    public final Integer data;
    /**
     * 指针域
     */
    public BinaryTreeNode left;
    /**
     * 指针域
     */
    public BinaryTreeNode right;
    /**
     * 指针域
     */
    public BinaryTreeNode next;

    public BinaryTreeNode(Integer data) {
        this.data = data;
    }

    public BinaryTreeNode(Integer data, BinaryTreeNode left, BinaryTreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }

        if (o == null || getClass() != o.getClass()){
            return false;
        }

        BinaryTreeNode treeNode = (BinaryTreeNode) o;

        return new EqualsBuilder()
                .append(data, treeNode.data)
                .append(left, treeNode.left)
                .append(right, treeNode.right)
                .append(next, treeNode.next)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(data)
                .append(left)
                .append(right)
                .append(next)
                .toHashCode();
    }

    public BinaryTreeNode copy() {
        BinaryTreeNode node = new BinaryTreeNode(this.data);
        node.left = Optional.ofNullable(this.left).map(BinaryTreeNode::copy).orElse(null);
        node.right = Optional.ofNullable(this.right).map(BinaryTreeNode::copy).orElse(null);
        node.next = Optional.ofNullable(this.next).map(BinaryTreeNode::copy).orElse(null);
        return node;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
