package com.zzm.solutions.patterns.singleton;

/**
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2023/4/16 星期日
 */
public class Singleton4 {

  private Singleton4() {}

  public static Singleton4 getInstance() {
    return InnerSingleton.instance;
  }

  private static class InnerSingleton {
    private static final Singleton4 instance = new Singleton4();
  }
}
