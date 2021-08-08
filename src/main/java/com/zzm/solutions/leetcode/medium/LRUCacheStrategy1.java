package com.zzm.solutions.leetcode.medium;

import java.util.LinkedHashMap;
import java.util.Map;

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
public class LRUCacheStrategy1 extends LinkedHashMap<Integer, Integer> {


    /**
     * 在 Java 语言中，同样有类似的数据结构 LinkedHashMap。
     * 这些做法都不会符合面试官的要求，因此下面只给出使用封装好的数据结构实现的代码，而不多做任何阐述。
     */
    private Integer capacity;

    public LRUCacheStrategy1(Integer capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }


    public Integer get(Integer key) {
        return super.getOrDefault(key, -1);
    }

    public void puts(Integer key, Integer value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        LRUCacheStrategy1 cache = new LRUCacheStrategy1(2);
        cache.puts(1, 1);

    }

}
