package com.zzm.solutions.leetcode.easy;

import com.zzm.solutions.leetcode.common.LinkedTable;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * <b> 环形链表 </b>
 * <p>题目：</p>
 * <blockquote>
 * 给定一个链表，判断链表中是否有环。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 * 进阶：
 * 你能用 O(1)（即，常量）内存解决此问题吗？
 * </blockquote><p>
 * 示例 1：<p>
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。<p>
 * 示例 2：<p>
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。<p>
 * 示例 3：<p>
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。<p>
 * 提示：<p>
 * 链表中节点的数目范围是 [0, 10^4]<p>
 * -10^5 <= Node.val <= 10^5<p>
 * pos 为 -1 或者链表中的一个 有效索引 。<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/27 星期二
 */
public class CheckCycleLinkedTable {


    /**
     * 思路：
     * 使用 queue，遍历每个节点，当遍历到的节点的下一个节点已经存在queue中时，说明存在环。
     *
     * @param root
     * @return
     */
    public static boolean hasCycle(LinkedTable root) {
        if (Objects.isNull(root)) {
            return false;
        }
        Set<LinkedTable> set = new HashSet<>();
        while (Objects.nonNull(root)) {
            if (set.contains(root)) {
                return true;
            }
            set.add(root);
            root = root.next;
        }

        return false;
    }

    /**
     * 思路：O(1):快慢指针
     * 本方法需要读者对「Floyd 判圈算法」（又称龟兔赛跑算法）有所了解。
     * 假想「乌龟」和「兔子」在链表上移动，「兔子」跑得快，「乌龟」跑得慢。当「乌龟」和「兔子」从链表上的同一个节点开始移动时，
     * 如果该链表中没有环，那么「兔子」将一直处于「乌龟」的前方；如果该链表中有环，那么「兔子」会先于「乌龟」进入环，并且一直在环内移动。
     * 等到「乌龟」进入环时，由于「兔子」的速度快，它一定会在某个时刻与乌龟相遇，即套了「乌龟」若干圈。
     * 具体地，我们定义两个指针，一快一满。慢指针每次只移动一步，而快指针每次移动两步。
     * 初始时，慢指针在位置 head，而快指针在位置 head.next。
     * 这样一来，如果在移动的过程中，快指针反过来追上慢指针，就说明该链表为环形链表。否则快指针将到达链表尾部，该链表不为环形链表。
     *
     * @param head
     * @return
     */
    public static boolean hasCycles(LinkedTable head) {
        if (Objects.isNull(head)) {
            return false;
        }
        LinkedTable slower = head;
        LinkedTable faster = head.next;
        while (!slower.equals(faster)) {
            if (Objects.isNull(faster) || Objects.isNull(faster.next)) {
                return false;
            }
            slower = slower.next;
            faster = faster.next.next;
        }
        return true;
    }

    public static void main(String[] args) {
        LinkedTable table0 = new LinkedTable(0);
        LinkedTable table2 = new LinkedTable(2, table0);
        table0.next = new LinkedTable(-4, table2);
        LinkedTable root = new LinkedTable(3, table2);
        boolean hasCycle = hasCycle(root);
        System.out.println("hasCycle = " + hasCycle);
        hasCycle = hasCycles(root);
        System.out.println("hasCycles = " + hasCycle);

        LinkedTable single = new LinkedTable(1);
        LinkedTable next = new LinkedTable(2, single);
        boolean cycle = hasCycle(next);
        System.out.println("cycle = " + cycle);
        cycle = hasCycles(next);
        System.out.println("cycles = " + cycle);

    }
}
