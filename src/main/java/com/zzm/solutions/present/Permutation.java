package com.zzm.solutions.present;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 某易。
 * <b> 全排列 </b><p>题目：</p>
 * <blockquote>
 * 有 map:{1:["a","b"]
 * 2:["c","d"]}<p>
 * 实现一个功能：<p>
 * printList("121")=["aca","acb","ada","adb","bcb","bdb","bca","bda"]<p>
 * </p>
 * </blockquote>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @see com.zzm.solutions.leetcode.medium.NumberAndLetterCombination
 * @since 2021/6/30 星期三
 */
public class Permutation {


    /**
     * 没有啥算法
     *
     * @param map
     * @param keys
     * @return
     */
    public static List<String> printList(Map<Integer, List<String>> map, String keys) {
        List<List<String>> values = new ArrayList<>(keys.length());
        for (char chr : keys.toCharArray()) {
            values.add(map.get(chr - '0'));
        }
        //拉平[a,b,c,d,e,a,b]
        List<String> list = values.stream().flatMap(Collection::stream).collect(Collectors.toList());
        //排列组合
        List<String> strings = permutation(list, keys.length());
        //去重
        List<String> collect = strings.stream()
                .collect(Collectors.collectingAndThen(Collectors
                                .toCollection(() -> new TreeSet<>(Comparator.comparing(String::trim))),
                        ArrayList::new));
        int index = 0;
        //校验每位来自对应位置的list
        for (List<String> value : values) {
            int finalIndex = index;
            collect.removeIf(s -> !value.contains(String.valueOf(s.charAt(finalIndex))));
            index++;
        }

        return collect;
    }

    /**
     * 排列组合
     *
     * @param list   需要排列的list
     * @param length 需要的长度
     * @return
     */
    public static List<String> permutation(List<String> list, int length) {
        Stream<String> stream = list.stream();
        for (int n = 1; n < length; n++) {
            stream = stream.flatMap(str -> list.stream().map(str::concat));
        }
        return stream.collect(Collectors.toList());
    }


    public static void main(String[] args) {

    }
}
