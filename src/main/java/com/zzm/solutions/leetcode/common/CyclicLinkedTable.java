package com.zzm.solutions.leetcode.common;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Optional;

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
    public final Integer identity;
    /**
     * 指针域
     */
    public CyclicLinkedTable predecessor;
    /**
     * 指针域
     */
    public CyclicLinkedTable successor;

    public CyclicLinkedTable(Integer identity) {
        this.identity = identity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CyclicLinkedTable table = (CyclicLinkedTable) o;

        return new EqualsBuilder()
                .append(identity, table.identity)
                .append(predecessor, table.predecessor)
                .append(successor, table.successor)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(identity)
                .append(predecessor)
                .append(successor)
                .toHashCode();
    }

    @Override
    public CyclicLinkedTable clone() {
        CyclicLinkedTable table = new CyclicLinkedTable(this.identity);
        table.successor = Optional.ofNullable(this.successor).map(CyclicLinkedTable::clone).orElse(null);
        table.predecessor = Optional.ofNullable(this.predecessor).map(CyclicLinkedTable::clone).orElse(null);
        return table;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
