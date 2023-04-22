package com.zzm.solutions.leetcode.easy;

import java.util.Scanner;

/**
 * <b>明明的随机数</b> * *
 *
 * <p>题目： * *
 *
 * <blockquote>
 *
 * 明明生成了 N个1到500之间的随机整数。请你删去其中重复的数字，即相同的数字只保留一个，把其余相同的数去掉，然后再把这些数从小到大排序，按照排好的顺序输出。
 *
 * </blockquote>
 *
 * <p>输入描述：第一行先输入随机整数的个数 N 。 接下来的 N 行每行输入一个整数，代表明明生成的随机数。 具体格式可以参考下面的"示例"。
 *
 * <p>输出描述： 输出多行，表示输入数据处理后的结果
 *
 * <p>示例 1：输入例子： 3 2 2 1 输出例子： 1 2 例子说明： 输入解释：
 * 第一个数字是3，也即这个小样例的N=3，说明用计算机生成了3个1到500之间的随机整数，接下来每行一个随机数字，共3行，也即这3个随机数字为： 2 2 1 所以样例的输出为： 1 2
 *
 * <p>1≤n≤1000 ，输入的数字大小满足
 *
 * <p>1≤val≤500
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2023/4/22 星期六
 */
public class RemoveDupNumbers {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    // 注意 hasNext 和 hasNextLine 的区别
    int n = in.nextInt();
    int[] numbers = new int[n];
    int[] temp = new int[1001];
    int lines = 0;
    while (in.hasNextInt()) {
      if (lines > n) {
        break;
      }
      int input = in.nextInt();
      if (temp[input] == 1) {
        continue;
      }
      temp[input] = 1;
      numbers[lines++] = input;
    }
    // sort
    for (int index = 0; index < numbers.length; index++) {
      for (int jndex = index; jndex < numbers.length; jndex++) {
        int tmp;
        if (numbers[index] > numbers[jndex]) {
          tmp = numbers[index];
          numbers[index] = numbers[jndex];
          numbers[jndex] = tmp;
        }
      }
    }
    for (int e = 0; e < numbers.length; e++) {
      if (numbers[e] > 0) {
        System.out.println(numbers[e]);
      }
    }
  }
}
