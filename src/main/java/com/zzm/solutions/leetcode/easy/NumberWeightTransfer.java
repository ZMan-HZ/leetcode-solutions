package com.zzm.solutions.leetcode.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * <b> 进制转换</b> * * * *
 *
 * <p>题目：写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。
 *
 * <blockquote>
 *
 * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。
 *
 * </blockquote>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2023/4/22 星期六
 */
public class NumberWeightTransfer {


  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    // 注意 hasNext 和 hasNextLine 的区别
    String input = in.next();
    int number = trans(input);
    System.out.println(number);
  }

  private static int trans(String input) {
    Map<Character, Integer> map = new HashMap<>();
    map.put('A', 10);
    map.put('B', 11);
    map.put('C', 12);
    map.put('D', 13);
    map.put('E', 14);
    map.put('F', 15);
    input = input.substring(2);
    int index = input.length() - 1;
    int number = 0;
    for (Character str : input.toCharArray()) {
      if (map.containsKey(str)) {
        number += Math.pow(16, index--) * map.get(str);
      } else {
        number += Math.pow(16, index--) * Integer.parseInt(str.toString());
      }
    }
    return number;
  }
}
