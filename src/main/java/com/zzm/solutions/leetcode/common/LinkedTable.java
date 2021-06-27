package com.zzm.solutions.leetcode.common;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
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
    public final Integer identity;
    /**
     * 指针域
     */
    public LinkedTable next;

    public LinkedTable(Integer identity) {
        this.identity = identity;
    }

    public LinkedTable(Integer identity, LinkedTable next) {
        this.identity = identity;
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LinkedTable table = (LinkedTable) o;

        return new EqualsBuilder()
                .append(identity, table.identity)
                .append(next, table.next)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(identity)
                .append(next)
                .toHashCode();
    }

    @Override
    public LinkedTable clone() {
        LinkedTable table = new LinkedTable(this.identity);
        table.next = Optional.ofNullable(this.next).map(LinkedTable::clone).orElse(null);
        return table;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
