package com.zzm.solutions.leetcode.common;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * <b>链表</b>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/20 星期日
 */
public class LinkedTable {

    /**
     * 数据域
     */
    public final Integer value;
    /**
     * 指针域
     */
    public LinkedTable next;

    public LinkedTable(Integer value) {
        this.value = value;
    }

    @Override
    protected LinkedTable clone() {
        LinkedTable table = new LinkedTable(this.value);
        table.next = this.next;
        return table;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
