package com.zzm.solutions.leetcode.hard;

import com.zzm.solutions.leetcode.common.LinkedTable;
import com.zzm.solutions.leetcode.easy.MergeTwoSortedLinkedTable;

import java.util.Objects;

/**
 * <b> 合并K个升序链表 </b><p>题目：</p>
 * <blockquote>
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * </blockquote><p>
 * 示例 1：<p>
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6<p>
 * 示例 2：<p>
 * 输入：lists = []
 * 输出：[]
 * <p>
 * 示例 3：<p>
 * 输入：lists = [[]]
 * 输出：[]
 * <p>
 * 提示：<p>
 * k == lists.length<p>
 * 0 <= k <= 10^4<p>
 * 0 <= lists[i].length <= 500<p>
 * -10^4 <= lists[i][j] <= 10^4<p>
 * lists[i] 按 升序 排列<p>
 * lists[i].length 的总和不超过 10^4<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/27 星期日
 */
public class MergeMultiSortedLinkedTables {

    private MergeMultiSortedLinkedTables() {
    }


    /**
     * 利用前面的合并两个有序链表
     *
     * @param lists 链表数组
     * @return 合并后的链表
     */
    public static LinkedTable buildLinkedTableFrom(LinkedTable[] lists) {

        if (Objects.isNull(lists) || lists.length == 0) {
            return null;
        }
        //这里不初始化的原因是，初始化的值可能不是新的链表的root。
        LinkedTable header = null;

        for (int index = 0; index < lists.length; index++) {
            LinkedTable right = lists[index];
            header = MergeTwoSortedLinkedTable.mergeTwoLinkedTable(header, right);
        }

        return header;
    }

    /**
     * 分治合并
     * <p>
     * <pre>
     *     1. 将 k 个链表配对并将同一对中的链表合并；
     *     2. 第一轮合并以后， k 个链表被合并成了 k/2 个链表，平均长度为2n/k，然后是 k/4 个链表，k/8个链表等等；
     *     3.重复这一过程，直到我们得到了最终的有序链表。
     * </pre>
     *
     * @param lists 链表数组
     * @return 合并后的链表
     */
    public static LinkedTable mergeLinkedTable(LinkedTable[] lists) {
        if (Objects.isNull(lists) || lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    private static LinkedTable merge(LinkedTable[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        if (left > right) {
            return null;
        }
        int middle = (left + right) >>> 1;

        LinkedTable leftHalf = merge(lists, left, middle);
        LinkedTable rightHalf = merge(lists, middle + 1, right);

        return MergeTwoSortedLinkedTable.mergeLinkedTable(leftHalf, rightHalf);
    }


    public static void main(String[] args) {
        LinkedTable three = new LinkedTable(5);
        LinkedTable two = new LinkedTable(4, three);
        LinkedTable one = new LinkedTable(1, two);

        LinkedTable six = new LinkedTable(4);
        LinkedTable five = new LinkedTable(3, six);
        LinkedTable four = new LinkedTable(1, five);

        LinkedTable eight = new LinkedTable(6);
        LinkedTable seven = new LinkedTable(2, eight);

        LinkedTable[] lists = {one, four, seven};
        LinkedTable[] tables = {one.clone(), four.clone(), seven.clone()};
        LinkedTable table = buildLinkedTableFrom(lists);
        System.out.println(String.format("%s merged", table));

        table = mergeLinkedTable(tables);
        System.out.println(String.format("%s merged", table));

    }
}
