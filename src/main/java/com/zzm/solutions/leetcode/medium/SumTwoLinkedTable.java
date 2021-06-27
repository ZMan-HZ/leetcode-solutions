package com.zzm.solutions.leetcode.medium;

import com.zzm.solutions.leetcode.common.LinkedTable;

import java.util.Objects;
import java.util.Optional;

/**
 * <b>两链表的数值相加，构造新的链表</b>
 * <p>题目：</p>
 * <blockquote>
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。<p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。<p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头<p>
 * </blockquote>
 * 示例 1：<p>
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807<p>
 * 示例 2：<p>
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]<p>
 * 示例 3：<p>
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]<p>
 * 提示：<p>
 * 每个链表中的节点数在范围 [1, 100] 内<p>
 * 0 <= Node.val <= 9<p>
 * 题目数据保证列表表示的数字不含前导零<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/26 星期六
 */
public class SumTwoLinkedTable {

    private SumTwoLinkedTable() {
    }

    /**
     * 直接模拟计算，类似字符串加法
     *
     * @param left  左链表，即加数链表
     * @param right 右链表，即被加数链表
     * @return 和 链表
     */
    public static LinkedTable sumTwoLinkedTable(LinkedTable left, LinkedTable right) {

        if (Objects.isNull(left)) {
            return right;
        }
        if (Objects.isNull(right)) {
            return left;
        }

        LinkedTable header = new LinkedTable(0);
        LinkedTable current = header;
        int carry = 0;
        while (Objects.nonNull(left) || Objects.nonNull(right)) {
            int v1 = Optional.ofNullable(left).map(node -> node.identity).orElse(0);
            int v2 = Optional.ofNullable(right).map(node -> node.identity).orElse(0);
            int sum = v1 + v2 + carry;
            carry = sum / 10;
            current.next = new LinkedTable(sum % 10);
            current = current.next;
            if (Objects.nonNull(left)) {
                left = left.next;
            }
            if (Objects.nonNull(right)) {
                right = right.next;
            }
        }
        if (carry > 0) {
            current.next = new LinkedTable(carry);
        }

        return header.next;
    }

    public static void main(String[] args) {
        LinkedTable lRoot = new LinkedTable(2);
        LinkedTable left1 = new LinkedTable(4);
        LinkedTable left2 = new LinkedTable(3);

        lRoot.next = left1;
        left1.next = left2;

        LinkedTable rRoot = new LinkedTable(5);
        LinkedTable right1 = new LinkedTable(6);
        LinkedTable right2 = new LinkedTable(4);

        rRoot.next = right1;
        right1.next = right2;

        LinkedTable table = sumTwoLinkedTable(lRoot, rRoot);
        System.out.println(String.format("%s is sum of two linked table", table));
    }
}
