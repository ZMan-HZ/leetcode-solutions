package com.zzm.solutions.patterns.observer2;

/**
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2023/4/16 星期日
 */
public interface Observer {

  /**
   * 更新
   *
   * @param temperature
   */
  void update(Float temperature);
}
