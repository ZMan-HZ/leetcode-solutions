package com.zzm.solutions.leetcode.easy;

import java.util.*;

/**
 * <b>有效的括号(成对的括号)</b>
 * <p>题目：</p>
 * <blockquote>
 * <p>
 * 给定一个只包括'('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * </p>
 * </blockquote>
 * <p>
 * 有效字符串需满足：
 * </p>
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * </p>
 * <p>
 * 左括号必须以正确的顺序闭合。
 * </p>
 * 示例 1：
 * 输入：s = "()"
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：s = "()[]{}"
 * 输出：true
 * <p>
 * 示例 3：
 * 输入：s = "(]"
 * 输出：false
 * <p>
 * 示例 4：
 * 输入：s = "([)]"
 * 输出：false
 * <p>
 * 示例 5：
 * 输入：s = "{[]}"
 * 输出：true
 * <p>
 * 提示：
 * </p>
 * <p>
 * s 仅由括号 '()[]{}' 组成
 * </p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/19 星期六
 */
public class ParenthesesValidator {

    private ParenthesesValidator() {
    }

    private static final Map<Character, Character> FINAL_MAP =
            Collections.unmodifiableMap(new HashMap<Character, Character>() {{
                put('(', ')');
                put('[', ']');
                put('{', '}');
            }});

    /**
     * 判断括号的有效性可以使用「栈」这一数据结构来解决。
     * 如果是左边括号，则压栈； 如果是右括号，则出栈，且必须与此有括号成对。
     * 数据结构，还可以尝试选Deque/Queue
     *
     * @param parentheses 括号字符
     * @return boolean
     */
    public static boolean isValidParentheses(String parentheses) {

        if (Objects.isNull(parentheses)
                || parentheses.isEmpty()
                || parentheses.length() % 2 != 0) {
            return false;
        }
        Stack<Character> cache = new Stack<>();
        for (char parenthesis : parentheses.toCharArray()) {
            if (FINAL_MAP.containsKey(parenthesis)) {
                cache.push(FINAL_MAP.get(parenthesis));
            } else if (FINAL_MAP.containsValue(parenthesis)) {
                if (cache.isEmpty() || !cache.pop().equals(parenthesis)) {
                    return false;
                }
            }

        }
        return cache.isEmpty();
    }

    public static void main(String[] args) {
        String str = "(([]){})";
        boolean flag = isValidParentheses(str);
        String msg = String.format("%s is%s valid parenthesis", str, flag ? "" : " NOT");
        System.out.println(msg);
    }
}
