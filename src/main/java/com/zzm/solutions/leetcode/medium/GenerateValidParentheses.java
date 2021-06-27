package com.zzm.solutions.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>括号生成</b><p>题目：</p>
 * <blockquote>
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * </blockquote><p>
 * 示例 1：
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]<p>
 * 示例 2：
 * 输入：n = 1
 * 输出：["()"]
 * <p>
 * <p>
 * 提示：<p>
 * 1 <= n <= 8<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/27 星期日
 */
public class GenerateValidParentheses {

    private GenerateValidParentheses() {
    }


    /**
     * 方法1：DFS 深度优先遍历,树形结构 回溯，剪枝
     * 剩余括号数
     * 可以分析出：
     * 当前左右括号都有大于 0 个可以使用的时候，才产生分支；
     * 产生左分支的时候，只看当前是否还有左括号可以使用；
     * 产生右分支的时候，还受到左分支的限制，右边剩余可以使用的括号数量一定得在严格大于左边剩余的数量的时候，才可以产生分支；
     * 在左边和右边剩余的括号数都等于 0 的时候结算。
     *
     * @param n 对数
     * @return 生成的括号
     */
    public static List<String> generate(int n) {

        List<String> result = new ArrayList<>(0);
        if (n == 0) {
            return result;
        }
        generate(result, n, n, "");

        return result;
    }

    /**
     * 可以生出左树叶对条件：左括号剩余数量严格大于0
     * 可以生出右树叶的条件：左括号剩余数量严格小于右括号剩余数量
     * <p>
     * 剪掉枝：左括号剩余数量严格大于右括号剩余数量
     * <p>
     * 另一个思路，就是用了几个括号了。跟这个方法相反
     *
     * @param result  结果
     * @param left    剩余左括号数
     * @param right   剩余右括号数
     * @param current 当前括号字符串
     */
    private static void generate(List<String> result, int left, int right, String current) {

        if (left == 0 && right == 0) {
            result.add(current);
        }

        // 剪枝（左括号可以使用的个数严格大于右括号可以使用的个数，才剪枝，注意这个细节）
        if (left > right) {
            return;
        }

        if (left > 0) {
            generate(result, left - 1, right, current + "(");
        }
        if (right > 0) {
            generate(result, left, right - 1, current + ")");
        }

    }


    public static void main(String[] args) {
        int n = 3;
        List<String> parenthese = generate(n);

        System.out.println(String.format("Generate %d parentheses are %s", n, parenthese));
    }
}
