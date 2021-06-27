package com.zzm.solutions.leetcode.hard;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

/**
 * <b> 最长有效括号</b><p>题目：</p>
 * <blockquote>
 * 给你一个只包含 '(' 和 ')' 的字符串 s，找出最长有效（格式正确且连续）括号子串的长度。
 * </blockquote><p>
 * 示例 1：<p>
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"<p>
 * 示例 2：<p>
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * <p>
 * 示例 3：<p>
 * 输入：s = ""
 * 输出：0
 * <p>
 * 提示：<p>
 * 0 <= s.length <= 3 * 10^4<p>
 * s[i] 为 '(' 或 ')'<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/27 星期日
 */
public class LongestValidParetheses {

    private LongestValidParetheses() {
    }

    /**
     * 正向查找与逆向查找结合<p>
     * 思路和算法<p>
     * 在此方法中，利用两个计数器left 和right 。 <p>
     * 首先，从左到右遍历字符串，对于遇到的每个'('，增加left 计数器， <p>
     * 对于遇到的每个')' ，增加 right 计数器。每当left 计数器与right 计数器相等时， <p>
     * 计算当前有效字符串的长度，并且记录目前为止找到的最长子字符串。 <p>
     * 当right 计数器比left 计数器大时，我们将 left 和 right 计数器同时变回 0。 <p>
     * 这样的做法贪心地考虑了以当前字符下标结尾的有效括号长度， <p>
     * 每次当 右括号数量 多于 左括号数量 的时候之前的字符都扔掉不再考虑， <p>
     * 重新从下一个字符开始计算，但这样会漏掉一种情况，就是遍历的时候左括号的数量始终大于右括号的数量，即 (() ，这种时候最长有效括号是求不出来的。 <p>
     * 解决的方法也很简单，我们只需要从右往左遍历用类似的方法计算即可，只是这个时候判断条件反了过来： <p>
     * 1. 当 left 计数器比 right 计数器大时，我们将left 和right 计数器同时变回 0; <p>
     * 2. 当left 计数器与right 计数器相等时，我们计算当前有效字符串的长度，并且记录目前为止找到的最长子字符串 <p>
     *
     * @param s 字符串
     * @return 长度
     */
    public static int lengthOflongestValidParetheses(String s) {
        int max = 0;
        int left = 0;
        int right = 0;
        if (Objects.isNull(s) || s.isEmpty()) {
            return max;
        }
        for (char chr : s.toCharArray()) {
            if (chr == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                max = Math.max(max, 2 * right);
            } else if (left < right) {
                left = right = 0;
            }
        }
        left = right = 0;
        int index = s.length() - 1;
        while (index >= 0) {
            char chr = s.charAt(index--);
            if (chr == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                max = Math.max(max, 2 * right);
            } else if (left > right) {
                left = right = 0;
            }
        }

        return max;
    }

    /**
     * 方法二：栈
     * 通过栈，可以在遍历给定字符串的过程中去判断到目前为止扫描的子串的有效性，同时能得到最长有效括号的长度。<p>
     * 具体做法是:<p>
     * <pre>
     * 始终保持栈底元素为当前已经遍历过的元素中「最后一个没有被匹配的右括号的下标」，这样的做法主要是考虑了边界条件的处理，栈里其他元素维护左括号的下标：<p>
     *      1.对于遇到的每个'(' ，将它的下标放入栈中<p>
     *      2.对于遇到的每个')' ，先弹出栈顶元素表示匹配了当前右括号：<p>
     *          · 如果栈为空，说明当前的右括号为没有被匹配的右括号，我们将其下标放入栈中来更新我们之前提到的「最后一个没有被匹配的右括号的下标」<p>
     *          · 如果栈不为空，当前右括号的下标减去栈顶元素即为「以该右括号为结尾的最长有效括号的长度」<p>
     * </pre>
     * 从前往后遍历字符串并更新答案即可。
     * 需要注意的是，如果一开始栈为空，第一个字符为左括号的时候我们会将其放入栈中，这样就不满足提及的「最后一个没有被匹配的右括号的下标」，<p>
     * 为了保持统一，我们在一开始的时候往栈中放入一个值为 -1的元素。<p>
     *
     * @param s 字符串
     * @return 长度
     */
    public static int longestValidParenthesesLength(String s) {
        int max = 0;
        if (Objects.isNull(s) || s.isEmpty()) {
            return max;
        }
        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1);
        int index = 0;
        for (char chr : s.toCharArray()) {
            //如果是'(',栈中压入坐标index；
            if (chr == '(') {
                stack.push(index);
            } else {
                //如果是 '）'，栈顶出栈，说明匹配
                stack.pop();
                if (stack.isEmpty()) {
                    //栈为空，说明 当前 ')'没有匹配, 坐标入栈。没有匹配到的右括号的坐标
                    stack.push(index);
                } else {
                    //栈不空，当前右括号的坐标 减去 栈顶元素，即前面已经pop出了匹配的坐标，此时栈顶就是与当前')'匹配的'('
                    max = Math.max(max, index - stack.peek());
                }
            }
            index++;
        }

        return max;
    }

    public static void main(String[] args) {

        String s = "(()";
        int length = lengthOflongestValidParetheses(s);
        System.out.println(String.format("%d is longest valid parentheses of %s", length, s));
        length = longestValidParenthesesLength(s);
        System.out.println(String.format("%d is longest valid parentheses of %s", length, s));

        s = ")()())";
        length = lengthOflongestValidParetheses(s);
        System.out.println(String.format("%d is longest valid parentheses of %s", length, s));
        length = longestValidParenthesesLength(s);
        System.out.println(String.format("%d is longest valid parentheses of %s", length, s));


    }
}
