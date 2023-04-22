package com.zzm.solutions.patterns.singleton;

import java.util.Objects;

/**
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2023/4/16 星期日
 */
public class Singleton3 {

  private Singleton3() {}

  private static volatile Singleton3 instance;

  public static Singleton3 getInstance() {
    if (Objects.isNull(instance)) {
      synchronized (Singleton3.class) {
        if (Objects.isNull(instance)) {
          instance = new Singleton3();
        }
      }
    }
    return instance;
  }
}
