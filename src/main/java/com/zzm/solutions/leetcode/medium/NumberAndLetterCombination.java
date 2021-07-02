package com.zzm.solutions.leetcode.medium;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.zzm.solutions.common.ApacheStringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;

/**
 * <b> 电话号码的字母组合 </b><p>题目：</p>
 * <blockquote>
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。<p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * </blockquote><p>
 * 示例 1：<p>
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]<p>
 * 示例 2：<p>
 * 输入：digits = ""
 * 输出：[]<p>
 * 示例 3：<p>
 * 输入：digits = "2"
 * 输出：["a","b","c"]<p>
 * 提示：<p>
 * 0 <= digits.length <= 4<p>
 * digits[i] 是范围 ['2', '9'] 的一个数字。<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/1 星期四
 */
public class NumberAndLetterCombination {

    private static final Map<Character, List<String>> LETTERS_MAP = ImmutableMap.<Character, List<String>>builder()
            .put('2', Lists.newArrayList("a", "b", "c"))
            .put('3', Lists.newArrayList("d", "e", "f"))
            .put('4', Lists.newArrayList("g", "h", "i"))
            .put('5', Lists.newArrayList("j", "k", "l"))
            .put('6', Lists.newArrayList("m", "n", "o"))
            .put('7', Lists.newArrayList("p", "g", "r", "s"))
            .put('8', Lists.newArrayList("t", "u", "v"))
            .put('9', Lists.newArrayList("w", "x", "y", "z"))
            .build();

    private NumberAndLetterCombination() {
    }

    /**
     * 方法1：
     * 思路：
     * 1.先获取总的长度length
     * 2. 取模求余数获取每一种情况
     *
     * @param digits
     * @return
     */
    public static List<String> letterPermutation(String digits) {

        List<String> result = new ArrayList<>();
        if (ApacheStringUtils.isEmpty(digits) || ApacheStringUtils.isBlank(digits)) {
            return result;
        }
        int length = 1;
        for (char key : digits.toCharArray()) {
            int size = Optional.ofNullable(LETTERS_MAP.get(key)).map(List::size).orElse(1);
            length *= size;
        }

        for (int index = 0; index < length; index++) {
            int lastIndex = index;
            StringBuilder ans = new StringBuilder();
            for (int at = 0; at < digits.length(); at++) {
                char key = digits.charAt(at);
                List<String> letters = LETTERS_MAP.get(key);
                if (Objects.isNull(letters)) {
                    continue;
                }
                int size = letters.size();
                int position = lastIndex % size;
                String aw = LETTERS_MAP.get(key).get(position);
                ans.append(aw);
                lastIndex = lastIndex / size;
            }
            result.add(ans.toString());
        }

        return result;
    }

    /**
     * 方法2：
     * 回溯法：
     * 用到了组合中类似回溯递归思想
     *
     * @param keys
     * @return
     */
    public static List<String> combinator(String keys) {
        List<String> result = new ArrayList<>();
        if (ApacheStringUtils.isEmpty(keys) || ApacheStringUtils.isBlank(keys)) {
            return result;
        }
        for (char key : keys.toCharArray()) {
            if (!LETTERS_MAP.containsKey(key)) {
                keys = keys.replaceAll(String.valueOf(key), "");
            }
        }
        combine(new StringBuilder(), keys, 0, result);

        return result;
    }
    /**
     * 这种写法，代码的鲁棒性不好把握
     * 比如：map中没有对应key的字符串。
     * 只能在外层进入递归前限制
     *
     * @param combined
     * @param keys
     * @param index
     * @param results
     */
    private static void combine(StringBuilder combined, String keys, int index, List<String> results) {
        if (index == keys.length()) {
            results.add(combined.toString());
            return;
        }
        List<String> letters = LETTERS_MAP.get(keys.charAt(index));
        for (String ans : letters) {
            combined.append(ans);
            combine(combined, keys, index + 1, results);
            combined.deleteCharAt(index);
        }
    }

    /**
     * 方法3：
     * 思路：
     * 使用Queue这种数据结构
     * 1。将对应key的letter按顺序进入queue
     * 2。然后出队后，再和后一个key的每个字符组合再次进入队列
     * 3。直到keys结尾取完
     *
     * @param keys
     * @return
     */
    public static List<String> combinations(String keys) {
        List<String> result = new ArrayList<>();
        if (ApacheStringUtils.isEmpty(keys) || ApacheStringUtils.isBlank(keys)) {
            return result;
        }
        Queue<String> queue = new LinkedList<>();
        for (char key : keys.toCharArray()) {
            List<String> letters = Optional.ofNullable(LETTERS_MAP.get(key)).orElse(Lists.newArrayList());
            if (queue.isEmpty()) {
                //将第一个key对应的字符全部入队
                queue.addAll(letters);
            } else {
                //记录前面入队queue的长度值，即第一个入队的数量
                int size = queue.size();
                for (int index = 0; index < size; index++) {
                    String s = queue.poll();
                    for (String letter : letters) {
                        queue.add(s + letter);
                    }
                }
            }
        }
        //queue中全部元素即为组合好的
        result.addAll(queue);
        return result;
    }


    public static void main(String[] args) {
        String digits = "1237";
        List<String> permutation = letterPermutation(digits);
        System.out.println(permutation);

        List<String> combinator = combinator(digits);
        System.out.println(combinator);

        List<String> combinations = combinations(digits);
        System.out.println(combinations);

    }
}
