package com.zzm.solutions.leetcode.medium;

import com.zzm.solutions.leetcode.common.CyclicLinkedTable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <b> LRU 缓存机制 </b>
 * <p>题目：</p>
 * <blockquote>
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 * [1].LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * [2].int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * [3].void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * <p>
 * 进阶：
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * </blockquote><p>
 * 示例 1：<p>
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * <p>
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 * 提示：<p>
 * 1 <= capacity <= 3000<p>
 * 0 <= key <= 10000<p>
 * 0 <= value <= 10^5<p>
 * 最多调用 2 * 10^5 次 get 和 put<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/8/1 星期日
 */
public class LRUCacheStrategy2 {

    /**
     * 哈希表 + 双向链表
     * 算法
     * LRU 缓存机制可以通过哈希表辅以双向链表实现，我们用一个哈希表和一个双向链表维护所有在缓存中的键值对。
     * 双向链表按照被使用的顺序存储了这些键值对，靠近头部的键值对是最近使用的，而靠近尾部的键值对是最久未使用的。
     * 哈希表即为普通的哈希映射（HashMap），通过缓存数据的键映射到其在双向链表中的位置。
     * 这样以来，我们首先使用哈希表进行定位，找出缓存项在双向链表中的位置，随后将其移动到双向链表的头部，即可在 O(1) 的时间内完成 get 或者 put 操作。
     * 具体的方法如下：
     * <pre>
     * 对于 get 操作，首先判断 key 是否存在：
     *    [1]  如果 key 不存在，则返回 −1；
     *    [2]  如果 key 存在，则 key 对应的节点是最近被使用的节点。通过哈希表定位到该节点在双向链表中的位置，并将其移动到双向链表的头部，最后返回该节点的值。
     * 对于 put 操作，首先判断 key 是否存在：
     *    [1]  如果 key 不存在，使用 key 和 value 创建一个新的节点，在双向链表的头部添加该节点，并将 key 和该节点添加进哈希表中。
     *         然后判断双向链表的节点数是否超出容量，如果超出容量，则删除双向链表的尾部节点，并删除哈希表中对应的项；
     *    [2]  如果 key 存在，则与 get 操作类似，先通过哈希表定位，再将对应的节点的值更新为 value，并将该节点移到双向链表的头部。
     * </pre>
     */

    private Integer capacity;
    private Integer size;
    private CyclicLinkedTable header;
    private CyclicLinkedTable tailer;
    private Map<Integer, CyclicLinkedTable> cache = new HashMap<>();

    public LRUCacheStrategy2(Integer capacity) {
        this.capacity = capacity;
        this.size = 0;
        header = new CyclicLinkedTable(Integer.MIN_VALUE);
        tailer = new CyclicLinkedTable(Integer.MAX_VALUE);
        header.successor = tailer;
        tailer.predecessor = header;
    }


    public Integer get(Integer key) {
        CyclicLinkedTable node = cache.get(key);
        if (Objects.isNull(node)) {
            return -1;
        }
        //如果存在key。map定位，再移动到head
        swapHeader(node);
        return node.data;
    }

    public void put(Integer key, Integer value) {
        CyclicLinkedTable node = cache.get(key);
        if (Objects.isNull(node)) {
            // 如果 key 不存在，创建一个新的节点
            CyclicLinkedTable newNode = new CyclicLinkedTable(key, value);
            // 添加进哈希表
            cache.put(key, newNode);
            // 添加至双向链表的头部
            addHeader(newNode);
            ++size;
            if (size > capacity) {
                // 如果超出容量，删除双向链表的尾部节点
                CyclicLinkedTable tail = removeTail();
                // 删除哈希表中对应的项
                cache.remove(tail.key);
                --size;
            }
        } else {
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            CyclicLinkedTable exist = new CyclicLinkedTable(value);
            exist.successor = node.successor;
            exist.predecessor = node.predecessor;
            cache.put(key, exist);
            swapHeader(exist);
        }

    }

    private void swapHeader(CyclicLinkedTable node) {
        removeNode(node);
        addHeader(node);
    }

    private void removeNode(CyclicLinkedTable node) {
        node.predecessor.successor = node.successor;
        node.successor.predecessor = node.predecessor;
    }

    private CyclicLinkedTable removeTail() {
        CyclicLinkedTable temp = tailer.predecessor;
        removeNode(temp);
        return temp;
    }

    private void addHeader(CyclicLinkedTable node) {
        node.predecessor = header;
        node.successor = header.successor;
        header.successor.predecessor = node;
        header.successor = node;
    }

    public static void main(String[] args) {
        LRUCacheStrategy2 cache = new LRUCacheStrategy2(2);
        cache.put(1, 1);
        cache.put(2, 2);
        Integer integer = cache.get(1);
        System.out.println("cache.get(1)=" + integer);
        cache.put(3, 3);
        integer = cache.get(2);
        System.out.println("cache.get(2)=" + integer);
        cache.put(4, 4);
        integer = cache.get(1);
        System.out.println("cache.get(1)=" + integer);
        integer = cache.get(3);
        System.out.println("cache.get(3)=" + integer);
        integer = cache.get(4);
        System.out.println("cache.get(4)=" + integer);
    }

}
