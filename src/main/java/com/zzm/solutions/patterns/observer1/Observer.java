package com.zzm.solutions.patterns.observer1;

/**
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2023/4/16 星期日
 */
public interface Observer {

  /**
   * 更新
   *
   * @param data
   */
  void update(String data);
}
