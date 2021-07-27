package com.zzm.solutions.leetcode.common;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Optional;

/**
 * <b>单链表</b>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/20 星期日
 */
public class LinkedTable {

    /**
     * 数据域
     */
    public final Integer data;
    /**
     * 指针域
     */
    public LinkedTable next;

    public LinkedTable(Integer data) {
        this.data = data;
    }

    public LinkedTable(Integer data, LinkedTable next) {
        this.data = data;
        this.next = next;
    }


    public LinkedTable copy() {
        LinkedTable table = new LinkedTable(this.data);
        table.next = Optional.ofNullable(this.next).map(LinkedTable::copy).orElse(null);
        return table;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
