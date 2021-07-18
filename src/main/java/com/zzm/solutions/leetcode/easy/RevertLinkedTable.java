package com.zzm.solutions.leetcode.easy;

import com.zzm.solutions.leetcode.common.LinkedTable;

import java.util.Objects;

/**
 * <b>反转链表</b><p>题目：</p>
 * <blockquote>
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * </blockquote><p>
 * 示例 1：
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]<p>
 * 示例 2：
 * 输入：head = [1,2]
 * 输出：[2,1]<p>
 * 示例 3：
 * 输入：head = []
 * 输出：[]<p>
 * 提示：<p>
 * 链表中节点的数目范围是 [0, 5000]<p>
 * -5000 <= Node.val <= 5000<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/18 星期日
 */
public class RevertLinkedTable {

    /**
     * 双指针
     * 直接反转
     *
     * @param head
     * @return
     */
    public static LinkedTable revert(LinkedTable head) {
        if (Objects.isNull(head)) {
            return head;
        }
        LinkedTable cursor = head;
        LinkedTable pre = null;
        while (Objects.nonNull(cursor)) {
            LinkedTable tmp = cursor.next;
            cursor.next = pre;
            pre = cursor;
            cursor= tmp;
        }
        return pre;
    }

    public static void main(String[] args) {

        LinkedTable node4 = new LinkedTable(5);
        LinkedTable node3 = new LinkedTable(4, node4);
        LinkedTable node2 = new LinkedTable(3, node3);
        LinkedTable node1 = new LinkedTable(2, node2);
        LinkedTable head = new LinkedTable(1, node1);
        LinkedTable revert = revert(head);
        System.out.println("revert = " + revert);
    }
}
