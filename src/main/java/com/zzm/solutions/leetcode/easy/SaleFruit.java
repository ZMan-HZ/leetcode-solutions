package com.zzm.solutions.leetcode.easy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2023/4/22 星期六
 */
public class SaleFruit {

  static class Inner implements Comparable<Inner> {

    private int m;
    private int n;

    public Inner(int m, int n) {
      this.m = m;
      this.n = n;
    }

    @Override
    public int compareTo(Inner o) {
      if (m > o.m) {
        return 1;
      }
      if (m < o.m) {
        return -1;
      }
      if (n > o.n) {
        return -1;
      }
      if (n < o.n) {
        return 1;
      }
      return 0;
    }
  }

  public static void main(String[] args) {
    //

    Scanner scanner = new Scanner(System.in);
    String next = scanner.nextLine();
    String[] mm = next.split(",");
    String nextline = scanner.nextLine();
    String[] nn = nextline.split(",");

    int k = scanner.nextInt();
    Inner[] inner = new Inner[mm.length];

    for (int index = 0; index < mm.length; index++) {
      inner[index] = new Inner(Integer.parseInt(mm[index]), Integer.parseInt(nn[index]));
    }
    Arrays.sort(inner);

    for (int index = 0; index < inner.length; index++) {
      if (k >= inner[index].m && inner[index].n > inner[index].m) {
        k += (inner[index].n - inner[index].m);
      }
    }
    System.out.println(k);
  }
}
