package com.zzm.solutions.leetcode.medium;

import com.zzm.solutions.leetcode.common.LinkedTable;

import java.util.Objects;

/**
 * <b>删除链表的倒数第 N 个结点</b><p>题目：</p>
 * <blockquote>
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 进阶：你能尝试使用一趟扫描实现吗？
 * </blockquote><p>
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]<p>
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 * </p><p>
 * 示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 * </p><p>
 * 提示：</p><p>
 * 链表中结点的数目为 sz<p>
 * 1 <= sz <= 30<p>
 * 0 <= Node.val <= 100<p>
 * 1 <= n <= sz
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/26 星期六
 */
public class DeleteNodeFromEndOfList {

    private DeleteNodeFromEndOfList() {
    }

    /**
     * 方法1：一次遍历 双指针法<p>
     * 一句话概括： 两个指针一个指针p1先开始跑，指针p1跑到k-1个节点后，另一个节点p2开始跑，当p1跑到最后时，p2所指的指针就是倒数第k个节点。<p>
     * 思想的简单理解： <p>
     * 前提假设：链表的结点个数(长度)为n。<p>
     * 规律一：要找到倒数第k个结点，需要向前走多少步呢？<p>
     * 比如倒数第一个结点，需要走n步，那倒数第二个结点呢？<p>
     * 很明显是向前走了n-1步，所以可以找到规律是找到倒数第k个结点，需要向前走n-k+1步。 <p>
     * 算法开始：<p>
     * 1。设两个都指向head的指针p1和p2，当p1走了k-1步的时候，停下来。p2之前一直不动。<p>
     * 2。p1的下一步是走第k步，这个时候，p2开始一起动了。至于为什么p2这个时候动呢？看下面的分析。<p>
     * 3。当p1走到链表的尾部时，即p1走了n步。由于我们知道p2是在p1走了k-1步才开始动的，也就是说p1和p2永远差k-1步。<p>
     * 所以当p1走了n步时，p2走的应该是在n-(k-1)步。即p2走了n-k+1步，此时巧妙的是p2正好指向的是规律一的倒数第k个结点处。
     *
     * @param root 根节点
     * @param nth  倒数第N个
     * @return 新链表
     */
    public static LinkedTable removeNthFromEnd(LinkedTable root, int nth) {
        if (Objects.isNull(root)) {
            return null;
        }
        LinkedTable node = new LinkedTable(0, root);
        // 声明两个指向头结点的节点
        LinkedTable current = node;
        //游标节点
        LinkedTable cursor = node;

        // cursor 节点先跑，cursor 节点 跑到 第 nth 个节点的时候, current 节点开始跑
        // 当 cursor 节点跑到最后一个节点时
        // current 节点所在的位置就是第 （L-nth +1 ） 个节点，也就是 倒数 第 nth（L代表总链表长度）
        while (Objects.nonNull(cursor)) {
            cursor = cursor.next;
            if (nth < 1 && Objects.nonNull(cursor)) {
                current = current.next;
            }
            nth--;
        }
        current.next = current.next.next;

        return node.next;
    }

    /**
     * 两次遍历
     *
     * @param root 根节点
     * @param nth  倒数第N个
     * @return 新链表
     */
    public static LinkedTable removeNthFromEnd(int nth, LinkedTable root) {
        if (Objects.isNull(root)) {
            return null;
        }
        int length = 0;
        LinkedTable node = new LinkedTable(0, root);
        while (Objects.nonNull(root)) {
            length++;
            root = root.next;
        }
        LinkedTable current = node;
        for (int index = 1; index < length - nth + 1; index++) {
            current = current.next;
        }
        current.next = current.next.next;

        return node.next;
    }

    public static void main(String[] args) {
        LinkedTable root = new LinkedTable(1);
        LinkedTable left1 = new LinkedTable(2);
        LinkedTable left2 = new LinkedTable(4);
        LinkedTable left3 = new LinkedTable(5);

        root.next = left1;
        left1.next = left2;
        left2.next = left3;

        LinkedTable clone = root.copy();

        int nth = 1;
        LinkedTable table = removeNthFromEnd(root, nth);

        System.out.println(String.format("remove the %d from end of linked table got %s", nth, table));

        table = removeNthFromEnd(nth, clone);

        System.out.println(String.format("remove the %d from end of linked table got %s", nth, table));

    }
}
