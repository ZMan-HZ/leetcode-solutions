package com.zzm.solutions.leetcode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <b> Z 字形变换</b>
 * <p>题目：</p>
 * <blockquote>
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * </blockquote>
 * <p>
 * 示例1：
 * <p>
 * 输入：s =  "PAYPALISHIRING", numRows = 3 时，排列如下：
 * <blockquote>
 * <pre>P   A   H   N</pre>
 * <pre>A P L S I I G</pre>
 * <pre>Y   I   R</pre>
 * </blockquote>
 * <p>
 * 之字形：左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"
 * </p>
 * 示例 2：
 * <p>
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * <blockquote>
 * <pre>P     I     N</pre>
 * <pre>A   L S   I G</pre>
 * <pre>Y A   H R</pre>
 * <pre>P     I</pre>
 * </blockquote>
 * 输出："PINALSIGYAHRPI"
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/22 星期二
 */
public class ZigzagStringConverter {

    private ZigzagStringConverter() {
    }

    /**
     * 方法一：按行排序
     * <p>
     * 思路
     * <p>
     * 通过从左向右迭代字符串，我们可以轻松地确定字符位于 Z 字形图案中的哪一行。
     * <p>
     * 算法
     * <p>
     * 我们可以使用min(rows,len(s)) 个列表来表示 Z 字形图案中的非空行。
     * 从左到右迭代 s，将每个字符添加到合适的行。可以使用当前行和当前方向这两个变量对合适的行进行跟踪。
     * 只有当向上移动到最上面的行或向下移动到最下面的行时，当前方向才会发生改变。
     *
     * @param string 字符串
     * @param rows   行数
     * @return 新串
     */
    public static String zigZag(String string, int rows) {
        if (Objects.isNull(string) || string.isEmpty()) {
            return "";
        }
        rows = Math.max(1, rows);
        if (string.length() <= rows || rows == 1) {
            return string;
        }
        List<StringBuilder> rowBuilder = new ArrayList<>(rows);
        for (int index = 0; index < Math.min(rows, string.length()); index++) {
            rowBuilder.add(new StringBuilder());
        }
        //变换方向
        boolean shift = false;
        //当前行
        int currentRow = 0;
        for (Character chr : string.toCharArray()) {
            rowBuilder.get(currentRow).append(chr);
            if (currentRow == 0 || currentRow == rows - 1) {
                shift = !shift;
            }
            currentRow += shift ? 1 : -1;
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder sb : rowBuilder) {
            result.append(sb);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        String zigzag = zigZag(s, 3);
        String msg = String.format("%s -> %s : %s", s, zigzag, "PAHNAPLSIIGYIR".equals(zigzag));
        System.out.println(msg);

        zigzag = zigZag(s, 15);
        msg = String.format("%s -> %s : %s", s, zigzag, "PINALSIGYAHRPI".equals(zigzag));
        System.out.println(msg);

        zigzag = zigZag("AB", 1);
        msg = String.format("%s -> %s : %s", s, zigzag, "AB".equals(zigzag));
        System.out.println(msg);
    }
}
