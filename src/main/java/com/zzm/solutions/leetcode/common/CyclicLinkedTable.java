package com.zzm.solutions.leetcode.common;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Optional;

/**
 * <b>双向链表</b>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/20 星期日
 */
public class CyclicLinkedTable<K> {
    /**
     * 数据域
     */
    public final Integer data;

    public K key;
    /**
     * 指针域
     */
    public CyclicLinkedTable predecessor;
    /**
     * 指针域
     */
    public CyclicLinkedTable successor;


    public CyclicLinkedTable(Integer data) {
        this.data = data;
    }

    public CyclicLinkedTable(K key, Integer data) {
        this.data = data;
        this.key = key;
    }

    public CyclicLinkedTable(Integer data, CyclicLinkedTable predecessor, CyclicLinkedTable successor) {
        this.data = data;
        this.predecessor = predecessor;
        this.successor = successor;
    }

    public CyclicLinkedTable copy() {
        CyclicLinkedTable table = new CyclicLinkedTable(this.data);
        table.successor = Optional.ofNullable(this.successor).map(CyclicLinkedTable::copy).orElse(null);
        table.predecessor = Optional.ofNullable(this.predecessor).map(CyclicLinkedTable::copy).orElse(null);
        return table;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("data", data)
                .append("predecessor", predecessor)
                .append("successor", successor)
                .toString();
    }
}
