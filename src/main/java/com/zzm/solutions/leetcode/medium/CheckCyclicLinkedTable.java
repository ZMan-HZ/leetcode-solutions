package com.zzm.solutions.leetcode.medium;

import com.zzm.solutions.leetcode.common.LinkedTable;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * <b> 环形链表II </b>
 * <p>题目：</p>
 * <blockquote>
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * 注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
 * 说明：不允许修改给定的链表。
 * 进阶：
 * 你是否可以使用 O(1) 空间解决此题？
 * </blockquote><p>
 * 示例 1：<p>
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * 解释：链表中有一个环，其尾部连接到第二个节点。<p>
 * 示例 2：<p>
 * 输入：head = [1,2], pos = 0
 * 输出：返回索引为 0 的链表节点
 * 解释：链表中有一个环，其尾部连接到第一个节点。<p>
 * 示例 3：<p>
 * 输入：head = [1], pos = -1
 * 输出：返回 null
 * 解释：链表中没有环。<p>
 * 提示：<p>
 * 链表中节点的数目范围是 [0, 10^4]<p>
 * -10^5 <= Node.val <= 10^5<p>
 * pos 为 -1 或者链表中的一个 有效索引 。<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/31 星期六
 */
public class CheckCyclicLinkedTable {

    /**
     * 思路：
     * 使用 queue，遍历每个节点，当遍历到的节点的下一个节点已经存在queue中时，说明存在环,直接返回节点即为循环开始的节点。
     *
     * @param root
     * @return
     */
    public static LinkedTable hasCycle(LinkedTable root) {
        if (Objects.isNull(root)) {
            return root;
        }
        Set<LinkedTable> set = new HashSet<>();
        while (Objects.nonNull(root)) {
            if (set.contains(root)) {
                return root;
            }
            set.add(root);
            root = root.next;
        }

        return null;
    }


    /**
     * 我们使用两个指针，fast 与 slow。它们起始都位于链表的头部。
     * 随后，slow 指针每次向后移动一个位置，而 fast 指针向后移动两个位置。
     * 如果链表中存在环，则 fast 指针最终将再次与 slow 指针在环中相遇。
     * 设链表中环外部分的长度为 a。
     * slow 指针进入环后，又走了 b 的距离与 fast 相遇。此时，fast 指针已经走完了环的 n 圈，
     * 因此它走过的总距离为 a+n(b+c)+b=a+(n+1)b+nc。
     * 根据题意，任意时刻 fast 指针走过的距离都为\slow 指针的 2 倍。因此，我们有
     * a+(n+1)b+nc=2(a+b)⟹a=c+(n−1)(b+c)
     * 有了 a=c+(n-1)(b+c)a=c+(n−1)(b+c) 的等量关系，我们会发现：从相遇点到入环点的距离加上 n−1 圈的环长，恰好等于从链表头部到入环点的距离。
     * 因此，当发现slow 与 fast 相遇时，我们再额外使用一个指针 ptr。
     * 起始，它指向链表头部；随后，它和 \slow 每次向后移动一个位置。最终，它们会在入环点相遇。
     *
     * @param head
     * @return
     */
    public static LinkedTable hasCycles(LinkedTable head) {
        if (Objects.isNull(head)) {
            return head;
        }
        LinkedTable slower = head;
        LinkedTable faster = head;
        while (Objects.nonNull(faster)) {
            slower = slower.next;
            if (Objects.nonNull(faster.next)) {
                faster = faster.next.next;
            } else {
                return null;
            }
            if (Objects.equals(faster, slower)) {
                LinkedTable cursor = head;
                while (!Objects.equals(cursor, slower)) {
                    cursor = cursor.next;
                    slower = slower.next;
                }
                return cursor;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        LinkedTable table0 = new LinkedTable(0);
        LinkedTable table2 = new LinkedTable(2, table0);
        table0.next = new LinkedTable(-4, table2);
        LinkedTable root = new LinkedTable(3, table2);
        LinkedTable hasCycle = hasCycle(root);
        System.out.println("hasCycle = " + hasCycle.data);
        hasCycle = hasCycles(root);
        System.out.println("hasCycles = " + hasCycle.data);

        LinkedTable single = new LinkedTable(1);
        LinkedTable next = new LinkedTable(2, single);
        LinkedTable cycle = hasCycle(next);
        System.out.println("cycle = " + cycle);
        cycle = hasCycles(next);
        System.out.println("cycles = " + cycle);
    }
}
