package com.zzm.solutions.leetcode.easy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2023/4/22 星期六
 */
public class EatPeaches {

  /**
   * 3 11 6 7 8
   *
   * <p>第1小时 吃3个：第1棵树吃完 第2小时 吃4个：第2棵树剩下7个 第3小时 吃4个：第2棵树剩下3个 第4小时 吃3个：第2棵树吃完 第5小时 吃4个：第3棵树剩下2个 第6小时
   * 吃2个：第3棵树吃完 第7小时 吃4个：第4棵树剩下3个 第8小时 吃3个：第4棵树吃完
   *
   * @param args
   */
  public static void main(String[] args) {
    //
    Scanner scanner = new Scanner(System.in);
    String next = scanner.nextLine();
    String[] strings = next.split(" ");

    int length = strings.length;
    int[] number = new int[length - 1];
    for (int index = 0; index < length - 1; index++) {
      String string = strings[index];
      for (int jndex = 0; jndex < string.length(); jndex++) {
        char chr = string.charAt(jndex);
        if (chr >= '0' && chr < '9') {
          continue;
        } else {
          System.out.println(-1);
          return;
        }
      }
      number[index] = Integer.parseInt(string);
    }

    int hour = Integer.parseInt(strings[length - 1]);

    Arrays.sort(number);

    int head = 1;
    int tail = number[number.length - 1];
    int max = tail;

    while (head < max) {
      int mid = (tail - head) / 2 + head;
      int time = getTime(number, mid);
      if (time <= hour) {
        tail = mid;
        max = mid;
      } else {
        head = mid + 1;
      }
    }
    System.out.println(head);
  }

  private static int getTime(int[] number, int mid) {
    int time = 0;
    for (int n : number) {
      int i = (n + mid - 1) / mid;
      time += i;
    }
    return time;
  }
}
