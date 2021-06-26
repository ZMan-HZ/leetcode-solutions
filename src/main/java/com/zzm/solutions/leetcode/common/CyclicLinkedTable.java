package com.zzm.solutions.leetcode.common;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * <b>双向链表</b>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/20 星期日
 */
public class CyclicLinkedTable {
    /**
     * 数据域
     */
    public final Integer value;
    /**
     * 指针域
     */
    public CyclicLinkedTable pre;
    /**
     * 指针域
     */
    public CyclicLinkedTable next;

    public CyclicLinkedTable(Integer value) {
        this.value = value;
    }


    @Override
    protected CyclicLinkedTable clone() {
        CyclicLinkedTable table = new CyclicLinkedTable(this.value);
        table.next = this.next.clone();
        table.pre = this.pre.clone();
        return table;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
