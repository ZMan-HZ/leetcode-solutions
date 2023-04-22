package com.zzm.solutions.leetcode.easy;

import java.util.Scanner;

/**
 * <b>汽水瓶，喝最多汽水</b>
 *
 * <p>题目：
 *
 * <blockquote>
 *
 * 某商店规定：三个空汽水瓶可以换一瓶汽水，允许向老板借空汽水瓶（但是必须要归还）。 小张手上有n个空汽水瓶，她想知道自己最多可以喝到多少瓶汽水。 输入文件最多包含 10
 * 组测试数据，每个数据占一行，仅包含一个正整数 n（ 1<=n<=100 ），表示小张手上的空汽水瓶数。n=0 表示输入结束，你的程序不应当处理这一行。
 *
 * </blockquote>
 *
 * <p>示例 1：输入例子： 3 10 81 0 输出：1 5 40 样例 1 解释：用三个空瓶换一瓶汽水，剩一个空瓶无法继续交换
 *
 * <p>提示：
 *
 * <p>两个链表的节点数目范围是 [0, 50]
 *
 * <p>-100 <= Node.val <= 100
 *
 * <p>l1 和 l2 均按 非递减顺序 排列
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2023/4/22 星期六
 */
public class EmptyBottleForDrinkMost {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    // 注意 hasNext 和 hasNextLine 的区别
    while (in.hasNextInt()) { // 注意 while 处理多个 case
      int a = in.nextInt();
      if (a == 0) {
        return;
      }
      int n = calc(a);
      System.out.println(n);
    }
  }

  private static int calc(int n) {
    if (n < 2) {
      return 0;
    }
    if (n == 2) {
      return 1;
    }

    return (n / 3) + calc(n % 3 + n / 3);
  }
}
