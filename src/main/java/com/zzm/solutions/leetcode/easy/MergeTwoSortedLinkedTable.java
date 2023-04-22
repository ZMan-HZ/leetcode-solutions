package com.zzm.solutions.leetcode.easy;

import com.zzm.solutions.leetcode.common.LinkedTable;

import java.util.Objects;

/**
 * <b>合并两个有序链表</b>
 * <p>题目：</p>
 * <blockquote>
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * </blockquote>
 * <p>
 * 示例 1：
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * <p>
 * 示例 2：
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * </p>
 * <p>
 * 示例 3：
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 * </p>
 * <p>
 * 提示：</p>
 * <p>
 * 两个链表的节点数目范围是 [0, 50]
 * <p>
 * -100 <= Node.val <= 100
 * <p>
 * l1 和 l2 均按 非递减顺序 排列
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/26 星期六
 */
public class MergeTwoSortedLinkedTable {

    private MergeTwoSortedLinkedTable() {
    }

    /**
     * 迭代
     *
     * @param left  第一个有序链表
     * @param right 第二个有序链表
     * @return 合并后的有序链表
     */
    public static LinkedTable mergeLinkedTable(LinkedTable left, LinkedTable right) {
        if (Objects.isNull(left)) {
            return right;
        }
        if (Objects.isNull(right)) {
            return left;
        }
        LinkedTable header = new LinkedTable(0);
        LinkedTable current = header;

        while (true) {
            if (Objects.isNull(left)) {
                current.next = right;
                return header.next;
            }
            if (Objects.isNull(right)) {
                current.next = left;
                return header.next;
            }
            if (left.data < right.data) {
                current.next = left;
                left = left.next;
            } else {
                current.next = right;
                right = right.next;
            }
            current = current.next;
        }

    }

    /**
     * 迭代
     *
     * @param left  第一个有序链表
     * @param right 第二个有序链表
     * @return 合并后的有序链表
     */
    public static LinkedTable mergeTwoLinkedTable(LinkedTable left, LinkedTable right) {
        if (Objects.isNull(left)) {
            return right;
        }
        if (Objects.isNull(right)) {
            return left;
        }
        LinkedTable header = new LinkedTable(0);
        LinkedTable current = header;

        while (Objects.nonNull(left) && Objects.nonNull(right)) {
            if (left.data < right.data) {
                current.next = left;
                left = left.next;
            } else {
                current.next = right;
                right = right.next;
            }
            current = current.next;
        }

        if (Objects.nonNull(left)) {
            current.next = left;
        }
        if (Objects.nonNull(right)) {
            current.next = right;
        }


        return header.next;
    }

    public static void main(String[] args) {

        LinkedTable lRoot = new LinkedTable(1);
        LinkedTable left1 = new LinkedTable(2);
        LinkedTable left2 = new LinkedTable(4);
        LinkedTable left3 = new LinkedTable(5);

        lRoot.next = left1;
        left1.next = left2;
        left2.next = left3;

        LinkedTable rRoot = new LinkedTable(1);
        LinkedTable right1 = new LinkedTable(3);
        LinkedTable right2 = new LinkedTable(4);

        rRoot.next = right1;
        right1.next = right2;

        LinkedTable table = mergeLinkedTable(lRoot.copy(), rRoot.copy());
        LinkedTable table1 = mergeTwoLinkedTable(lRoot.copy(), rRoot.copy());
        System.out.println(String.format("%s is merged", table.toString()));
        System.out.println(String.format("%s is merged", table1.toString()));


    }
}
